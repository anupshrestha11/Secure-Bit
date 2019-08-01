package Controller;

import Data.FileRequestData;
import Service.RequestFileService;
import SessionCheckerServlet.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class RequestFileServlet extends HttpServlet {
private RequestFileService requestFileService=new RequestFileService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new SessionChecker(request,response);
        try {
            requestFileService.getFromFileId((Integer.parseInt(request.getParameter("id"))), request);
            response.sendRedirect("/dashboard");
        }
        catch (SQLException e)
        {

            e.printStackTrace();
        }

    }
}
