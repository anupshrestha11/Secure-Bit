package Controller;

import Data.SignupData;
import Service.EmailVerification;
import Service.SignupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    private SignupService signupService = new SignupService();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession httpSession = request.getSession();

        SignupData signupData = new SignupData();

        signupData.setFullName(request.getParameter("name"));
        signupData.setEmail(request.getParameter("email").toLowerCase());
        signupData.setUserName(request.getParameter("username"));
        signupData.setPassword(request.getParameter("password"));

        String name = signupData.getFullName();
        String a = "";
        String temp = "";
        for (int i = 0; i < name.length(); i++) {
            if (i == 0) {
                temp += Character.toString(name.charAt(i)).toUpperCase();
            } else if (a.equals(" ")) {
                temp += Character.toString(name.charAt(i)).toUpperCase();
            } else {

                temp += Character.toString(name.charAt(i));

            }
            a = Character.toString(name.charAt(i));

        }

        signupData.setFullName(temp);
        httpSession.setAttribute("signUpData", signupData);

        System.out.println(httpSession.getId());

        try {
            if (!signupService.checkIfUserIsUnique(signupData)) {

                httpSession.setAttribute("signupPageMessage", "2");
                request.getRequestDispatcher("/index.jsp").forward(request,response);

            } else {

                int random = ((int) (Math.random() * 900000) + 100000);
                EmailVerification emailVerification = new EmailVerification();
                emailVerification.mail(random, signupData.getEmail());
                httpSession.setAttribute("randomEmailVerify", random);
                request.getRequestDispatcher("/EmailVerification.jsp").forward(request,response);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


    }
}
