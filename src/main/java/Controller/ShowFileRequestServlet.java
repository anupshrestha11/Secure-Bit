package Controller;

import Data.FileRequestData;
import Service.ShowRequestFileService;
import SessionCheckerServlet.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowFileRequestServlet extends HttpServlet {
    private ShowRequestFileService showRequestFileService = new ShowRequestFileService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new SessionChecker(request, response);
        int sessionId = ((Integer) request.getSession().getAttribute("userId")).intValue();

        try {
            List<FileRequestData> fileRequestData = new ArrayList<>();

            fileRequestData = showRequestFileService.getAllRequestData(sessionId);
            request.getSession().setAttribute("requestCount", showRequestFileService.getCount());
            request.getSession().setAttribute("requestFiles", fileRequestData);
        } catch (SQLException s) {
            s.printStackTrace();
        }


    }
}
