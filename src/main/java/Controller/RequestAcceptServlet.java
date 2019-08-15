package Controller;

import Algorithm.CBC.CBC;
import Data.FileData;
import Service.RequestFileService;
import Service.SendingEmail;
import Service.UniqueKeyGenerator;
import SessionCheckerServlet.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RequestAcceptServlet extends HttpServlet {
    private FileData fileData;
    private CBC cbc;
    private SendingEmail sendingEmail = new SendingEmail();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new SessionChecker(req, resp);
        fileData = new FileData();
        cbc = new CBC();
        int requestId = Integer.parseInt(req.getParameter("id"));
        RequestFileService requestFileService = new RequestFileService();
        try {
            fileData = requestFileService.RequestAccepted(requestId);

            UniqueKeyGenerator uniqueKeyGenerator = new UniqueKeyGenerator(fileData.getKeyForEncryption(), ((String) (req.getSession().getAttribute("userName"))), fileData.getRequestingUserName());
            String uniqueKey = uniqueKeyGenerator.getUniqueKey();

            sendingEmail.mail(uniqueKey, fileData.getEmail(), fileData.getOwnerName(), fileData.getOrginalName(), true);
            req.getSession().setAttribute("acceptedRequestMessage", "File Key Is Sent To :" + fileData.getEmail());
        } catch (SQLException e) {


            req.getSession().setAttribute("acceptedRequestMessage", "Unable To Send File Key To " + fileData.getEmail());
            e.printStackTrace();
        }

        ShowFileRequestServlet showFileRequestServlet = new ShowFileRequestServlet();
        showFileRequestServlet.doGet(req, resp);
        req.getRequestDispatcher("/ViewRequest.jsp").forward(req, resp);
    }
}
