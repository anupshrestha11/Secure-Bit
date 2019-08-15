package Controller;

import Algorithm.AES.AESMain;
import Algorithm.CBC.CBC;
import Data.FileData;
import Service.DashboardDataRetriveService;
import Service.FileUploadService;
import SessionCheckerServlet.SessionChecker;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UploadServlet extends HttpServlet {
    private int maxFileSize = 1024 * 1024 * 5; //5mb

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionChecker sessionChecker = new SessionChecker(request, response);

        if (sessionChecker.isSessionActive(request)) {
            response.sendRedirect("/uplaodfile.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        new SessionChecker(request, response);
        String encryptedFileName;
        String fileName;

        FileUploadService fileUploadService = new FileUploadService();
        CBC cbc = new CBC();
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("userName") == null) {
            response.sendRedirect("/login.jsp");
        }
        AESMain aesMain = new AESMain();
        String path = request.getServletContext().getRealPath("/");
        int ownerId = ((Integer) request.getSession().getAttribute("userId")).intValue();
        String uploadPath = path + ownerId + "/";
        File storePath = new File(uploadPath);


        if (!storePath.exists()) {

            storePath.mkdir();
        }

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setFileSizeMax(maxFileSize);

        FileData fileData = new FileData();

        try {

            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            for (FileItem fileItem : fileItems) {

                String fieldName = fileItem.getFieldName();
                String value = fileItem.getString();

                if (fieldName.equals("fileKey")) {
//                    aesMain.setKey(value);
                    fileData.setKeyForEncryption(value);
                }

                if (fieldName.equals("description")) {
                    fileData.setFileDescription(value);
                }


                if (!fileItem.isFormField()) {
                    fileName = (fileItem.getName());
                    File store = new File(storePath + "/" + fileName);
                    fileItem.write(store);
                    Path fileToEncrypt = Paths.get(storePath + "/" + fileName);


                    double randFileNumber = Math.random();
                    encryptedFileName = "out" + randFileNumber;
                    File encryptedFile = new File(storePath + "/" + encryptedFileName + ".txt");
                    encryptedFile.createNewFile();
                    System.out.println(fileData.getKeyForEncryption());
                    cbc.CbcEncrypt(fileToEncrypt, encryptedFileName + ".txt", storePath,fileData.getKeyForEncryption());
                    if (store.exists()) {
                        store.delete();
                    }


                    fileData.setEncryptedName(encryptedFileName);
                    fileData.setOrginalName(fileName);
                    fileData.setOwnerId(ownerId);

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        fileData.setDate(strDate);
        try {
            fileUploadService.saveFile(fileData);
            request.getSession().setAttribute("uploadFileMessage", "Upload Sucessful");
            List<FileData> fileDataList = new ArrayList<>();
            DashboardDataRetriveService dashboardDataRetriveService = new DashboardDataRetriveService();
            fileDataList = dashboardDataRetriveService.getFileDatas(ownerId);
            request.getSession().setAttribute("fileDatas", fileDataList);
            response.sendRedirect("/uplaodfile.jsp");
        } catch (SQLException s) {
            s.printStackTrace();
        }


    }

}
