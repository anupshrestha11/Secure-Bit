package Controller;

import Data.FileData;
import Service.DashboardDataRetriveService;
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

public class DashboardServlet extends HttpServlet {

    private DashboardDataRetriveService dashboardDataRetriveService = new DashboardDataRetriveService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new SessionChecker(request, response);

        int ownerId = ((Integer) request.getSession().getAttribute("userId")).intValue();
        try {
            List<FileData> fileDataList = new ArrayList<>();
            fileDataList = dashboardDataRetriveService.getFileDatas(ownerId);
            request.getSession().setAttribute("fileDatas", fileDataList);
            BrowseFileServlet browseFileServlet = new BrowseFileServlet();
            browseFileServlet.doGet(request, response);
            ShowFileRequestServlet showFileRequestServlet = new ShowFileRequestServlet();
            showFileRequestServlet.doGet(request, response);
            ShowDownloadFileServlet showDownloadFileServlet = new ShowDownloadFileServlet();
            showDownloadFileServlet.doGet(request, response);
            request.getSession().setAttribute("totalValues", dashboardDataRetriveService.getCount());

            request.getRequestDispatcher("/dashboards.jsp").forward(request, response);
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
