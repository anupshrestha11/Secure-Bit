package Controller;

import Data.FileData;
import Data.FileRequestData;
import Service.ShowDownloadFilesService;
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

public class ShowDownloadFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        new SessionChecker(req,resp);
ShowDownloadFilesService showDownloadFilesService = new ShowDownloadFilesService();
        try {
            List<FileData> fileDataList = showDownloadFilesService.getAllDownloadFiles(req.getSession().getAttribute("userEmail").toString());
            req.getSession().setAttribute("acceptedFiles", fileDataList);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new SessionChecker(req,resp);
    }
}
