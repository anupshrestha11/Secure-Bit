package Controller;

import Service.RequestDeleteService;
import SessionCheckerServlet.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RequestDeleteServlet extends HttpServlet {
    private RequestDeleteService requestDeleteService = new RequestDeleteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new SessionChecker(request, response);


        int requestId = Integer.parseInt(request.getParameter("id"));
        try {

            requestDeleteService.deleteRequest(requestId);

            request.getSession().setAttribute("acceptedRequestMessage", "Request Deleted");
            ShowFileRequestServlet showFileRequestServlet = new ShowFileRequestServlet();
            showFileRequestServlet.doGet(request, response);
        } catch (SQLException s) {
            request.getSession().setAttribute("acceptedRequestMessage", "Cannot Delete Request");
            s.printStackTrace();
        }


        response.sendRedirect("ViewRequest.jsp");


    }
}
