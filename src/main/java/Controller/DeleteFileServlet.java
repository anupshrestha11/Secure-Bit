package Controller;

import Service.DeleteFileService;
import SessionCheckerServlet.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteFileServlet extends HttpServlet {
private DeleteFileService deleteFileService=new DeleteFileService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        new SessionChecker(request,response);

      int fileId=Integer.parseInt(request.getParameter("id"));
      int ownerId=((Integer)request.getSession().getAttribute("userId"));

try {


    String path=request.getServletContext().getRealPath("/");
    String totalPath=path+ownerId+"/";
    deleteFileService.deleteFile(ownerId, fileId,totalPath);
    request.getSession().setAttribute("uploadMessage","File Deleted Sucessfully");
    response.sendRedirect("/dashboard");
}catch (SQLException s)
{
    s.printStackTrace();
}



    }
}
