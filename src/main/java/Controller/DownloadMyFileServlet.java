package Controller;

import Algorithm.CBC.CBC;
import Data.FileData;
import Service.DownloadFileService;
import SessionCheckerServlet.SessionChecker;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadMyFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new SessionChecker(req,resp);
        String enteredEncryptionKey = req.getParameter("encryptionkey");
        int fileId = Integer.parseInt(req.getParameter("fileId"));
        DownloadFileService downloadFileService = new DownloadFileService();
        CBC cbc = new CBC();
        FileData fileData = new FileData();
        try {
            if (downloadFileService.CheckKey(enteredEncryptionKey, fileId)) {

                fileData = downloadFileService.downloadFile(fileId);

                String applicationPath = getServletContext().getRealPath("");
                String downloadPath = applicationPath + File.separator + fileData.getOwnerId();
                String filePath = downloadPath + File.separator + fileData.getEncryptedName() + ".txt";
                Path path = Paths.get(filePath);

                String newFilePath = downloadPath + File.separator + fileData.getOrginalName();

                File file = new File(newFilePath);
                file.createNewFile();

                cbc.CbcDecrypt(path, fileData.getOrginalName(), new File(downloadPath + File.separator), fileData.getKeyForEncryption());
                File downloadFile = new File(newFilePath);
                FileInputStream inStream = new FileInputStream(downloadFile);


                ServletContext context = getServletContext();

                String mimeType = context.getMimeType(newFilePath);
                if (mimeType == null) {
                    mimeType = "application/octel-stream";
                }
                resp.setContentType(mimeType);
                resp.setContentLength((int) downloadFile.length());

                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", fileData.getOrginalName());
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
                req.getSession().setAttribute("downloadError", "Please Enter Valid key");
            }
            req.getRequestDispatcher("/myfiles.jsp").forward(req, resp);
        } catch (Exception e) {
        }
    }
}
