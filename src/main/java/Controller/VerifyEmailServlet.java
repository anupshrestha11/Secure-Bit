package Controller;

import Data.SignupData;
import Service.SignupService;
import SessionCheckerServlet.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerifyEmailServlet extends HttpServlet {
    private SignupServlet signupServlet = new SignupServlet();
    private SignupService signupService = new SignupService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        System.out.println("email");
        System.out.println("email "+httpSession.getId());
        if (httpSession.getId() == req.getParameter("id")){
            httpSession = req.getSession(false);
            System.out.println("id match");
        }
        int getEnteredCode = Integer.parseInt(req.getParameter("verifyEmail"));
        System.out.println(((Integer)httpSession.getAttribute("randomEmailVerify")).intValue());
        if (getEnteredCode == ((Integer) httpSession.getAttribute("randomEmailVerify")).intValue()) {
           SignupData signupData=(SignupData)httpSession.getAttribute("signUpData");
//           httpSession.removeAttribute("signUpData");
            try {
                signupService.saveUser(signupData);
//                httpSession.invalidate();
                httpSession.setAttribute("loginPageMessage", "1");
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            httpSession.setAttribute("errorSignupCode", "Invalid Code");
            req.getRequestDispatcher("/EmailVerification.jsp").forward(req,resp);
        }
    }
}
