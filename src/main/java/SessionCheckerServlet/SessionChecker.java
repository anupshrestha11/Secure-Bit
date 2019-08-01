package SessionCheckerServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionChecker {
    public SessionChecker (HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userName") == null||session==null) {
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }


    public Boolean isSessionActive(HttpServletRequest request)
    {
        HttpSession httpSession=request.getSession();
        if(httpSession.getAttribute("userName")==null)
        {
            return false;

        }
        else {


            return true;
        }

    }



}
