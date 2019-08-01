package Controller;

import Data.FileData;
import Service.BrowseDataService;
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

public class BrowseFileServlet extends HttpServlet {
private BrowseDataService browseDataService=new BrowseDataService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        new SessionChecker(request,response);

        List<FileData> fileDataList=new ArrayList<>();
        try {
            fileDataList = browseDataService.getData(((Integer) request.getSession().getAttribute("userId")).intValue());
        request.getSession().setAttribute("browseFiles",fileDataList);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }










    }
}
