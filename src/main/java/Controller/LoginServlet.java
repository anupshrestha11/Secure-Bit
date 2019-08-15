package Controller;

import Data.SignupData;
import Service.LoginService;
import SessionCheckerServlet.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private SignupData signupData ;
    private LoginService loginService = new LoginService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionChecker sessionChecker = new SessionChecker(req, resp);
        if (sessionChecker.isSessionActive(req)) {
            resp.sendRedirect("/dashboard");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        signupData = new SignupData();
        signupData.setUserName(request.getParameter("username"));
        signupData.setPassword(request.getParameter("password"));
        try {
            if (loginService.checkUserForLogin(signupData)) {

                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("userName", signupData.getUserName());
                httpSession.setAttribute("userId", signupData.getUserId());
                httpSession.setAttribute("userFullName", signupData.getFullName());
                httpSession.setAttribute("userEmail", signupData.getEmail());
                System.out.println(httpSession.getId());
                httpSession.setAttribute("encodedPassword", signupData.getEncodedPassword());
                response.sendRedirect("/dashboard");

            } else {
                request.getSession().setAttribute("loginPageMessage", "2");
               response.sendRedirect("/index.jsp");

            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
