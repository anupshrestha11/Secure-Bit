package Controller;

import Algorithm.AES.AESMain;
import Algorithm.CBC.CBC;
import Data.FileData;
import Service.DownloadFileService;
import Service.MaintainSize;
import SessionCheckerServlet.SessionChecker;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DownloadFileServlet extends HttpServlet {
    private CBC cbc = new CBC();
    private AESMain aesMain = new AESMain();
    private MaintainSize maintainSize = new MaintainSize();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new SessionChecker(req, resp);
        String enteredKey = req.getParameter("key");
        int fileId = Integer.parseInt(req.getParameter("fileId"));
        DownloadFileService downloadFileService = new DownloadFileService();
        FileData fileData = new FileData();
        try {
            fileData = downloadFileService.downloadFile(fileId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (fileData == null) {
            req.getRequestDispatcher("/download.jsp").forward(req, resp);
        }


        String uniqueKey = null;
        String ownerUserName=maintainSize.getMaintainedSize(fileData.getKeyForEncryption(),fileData.getOwnerUserName());
        String requestingUserName=maintainSize.getMaintainedSize(fileData.getKeyForEncryption(),(String)(req.getSession().getAttribute("userName")));

        ownerUserName= DatatypeConverter.printHexBinary(ownerUserName.getBytes());
        requestingUserName=DatatypeConverter.printHexBinary(requestingUserName.getBytes());
        String[] Random=cbc.XorOperation((ownerUserName.split("")),requestingUserName.split(""));
        String key=DatatypeConverter.printHexBinary(fileData.getKeyForEncryption().getBytes());
        uniqueKey = cbc.ArrayToString(cbc.XorOperation(Random, key.split("")));
        System.out.println(uniqueKey);



        if (uniqueKey.equalsIgnoreCase(enteredKey)) {

            aesMain.setKey(fileData.getKeyForEncryption());

            String applicationPath = getServletContext().getRealPath("");
            String downloadPath = applicationPath + File.separator + fileData.getOwnerId();
            String filePath = downloadPath + File.separator + fileData.getEncryptedName() + ".txt";
            Path path = Paths.get(filePath);

            String newFilePath = downloadPath + File.separator + fileData.getOrginalName();

            File file = new File(newFilePath);
            file.createNewFile();

            cbc.CbcDecrypt(path, fileData.getOrginalName(), new File(downloadPath + File.separator));

            File downloadFile = new File(newFilePath);
            FileInputStream inStream = new FileInputStream(downloadFile);

            String relativePath = getServletContext().getRealPath("");

            ServletContext context = getServletContext();

            String mimeType = context.getMimeType(newFilePath);
            if (mimeType == null) {
                mimeType = "application/octel-stream";
            }
            resp.setContentType(mimeType);
            resp.setContentLength((int) downloadFile.length());

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            resp.setHeader(headerKey, headerValue);

            OutputStream outStream = resp.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();

            file.delete();

        } else {
            req.getSession().setAttribute("downloadError", "Please enter valid key");
        }
        req.getRequestDispatcher("/download.jsp").forward(req, resp);

    }


}
