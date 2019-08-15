package Controller;

import Data.FileData;
import Service.SearchFileservice;
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

public class SearchFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        new SessionChecker(req, resp);
        SearchFileservice searchFileservice = new SearchFileservice();

        List<FileData> fileDataList = new ArrayList<>();

        try {
            fileDataList = searchFileservice.SearchFile(req.getParameter("search"), ((Integer) req.getSession().getAttribute("userId")).intValue());
            req.getSession().setAttribute("searchedFiles", fileDataList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        req.getRequestDispatcher("/browsefile.jsp").forward(req,resp);
        resp.sendRedirect("/browsefile.jsp");
    }

}
