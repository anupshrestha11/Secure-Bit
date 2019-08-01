package Controller;

import Algorithm.CBC.CBC;
import Data.FileData;
import Service.MaintainSize;
import Service.RequestFileService;
import Service.SendingEmail;
import SessionCheckerServlet.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.sql.SQLException;

public class RequestAcceptServlet extends HttpServlet {
    private FileData fileData=new FileData();
    private CBC cbc=new CBC();
    private SendingEmail sendingEmail = new SendingEmail();
    private MaintainSize maintainSize=new MaintainSize();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new SessionChecker(req,resp);
        int requestId=Integer.parseInt(req.getParameter("id"));
        RequestFileService requestFileService = new RequestFileService();
        System.out.println(requestId);
        try {
           fileData= requestFileService.RequestAccepted(requestId);


String ownerUserName=maintainSize.getMaintainedSize(fileData.getKeyForEncryption(),((String)(req.getSession().getAttribute("userName"))));
           String requestingUserName=maintainSize.getMaintainedSize(fileData.getKeyForEncryption(),fileData.getRequestingUserName());
ownerUserName= DatatypeConverter.printHexBinary(ownerUserName.getBytes());
requestingUserName=DatatypeConverter.printHexBinary(requestingUserName.getBytes());
           String[] Random=cbc.XorOperation((ownerUserName.split("")),requestingUserName.split(""));
           String key=DatatypeConverter.printHexBinary(fileData.getKeyForEncryption().getBytes());
        String uniqueKey = cbc.ArrayToString(cbc.XorOperation(Random, key.split("")));

        sendingEmail.mail(uniqueKey,fileData.getEmail());












        }catch (SQLException e){
            e.printStackTrace();
        }

        ShowFileRequestServlet showFileRequestServlet=new ShowFileRequestServlet();
        showFileRequestServlet.doGet(req,resp);
        req.getRequestDispatcher("/ViewRequest.jsp").forward(req,resp);
    }
}
