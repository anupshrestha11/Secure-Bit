package Controller;

import Data.FileData;
import Service.DashboardDataRetriveService;
import Service.DeleteFileService;
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

public class DeleteFileServlet extends HttpServlet {
    private DeleteFileService deleteFileService = new DeleteFileService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        new SessionChecker(request, response);

        int fileId = Integer.parseInt(request.getParameter("id"));
        int ownerId = ((Integer) request.getSession().getAttribute("userId"));

        try {


            String path = request.getServletContext().getRealPath("/");
            String totalPath = path + ownerId + "/";
            deleteFileService.deleteFile(ownerId, fileId, totalPath);
            request.getSession().setAttribute("deleteFileMessage", "File Deleted Sucessfully");
            List<FileData> fileDataList = new ArrayList<>();
            DashboardDataRetriveService dashboardDataRetriveService = new DashboardDataRetriveService();
            fileDataList = dashboardDataRetriveService.getFileDatas(ownerId);
            request.getSession().setAttribute("fileDatas", fileDataList);
            response.sendRedirect("/myfiles.jsp");
        } catch (SQLException s) {
            s.printStackTrace();
        }


    }
}
