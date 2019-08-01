package Controller;

import Data.SignupData;
import Service.SignupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignupServlet extends HttpServlet {
private SignupData signupData=new SignupData();
private SignupService signupService=new SignupService();
private String returnedMessage;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

signupData.setFullName(request.getParameter("name"));
signupData.setEmail(request.getParameter("email"));
signupData.setUserName(request.getParameter("username"));
signupData.setPassword(request.getParameter("password"));

try{

  if(signupService.saveUser(signupData))
  {


      request.setAttribute("loginPageMessage","Account Creation Sucessful!! Login To Continue");
      request.getRequestDispatcher("/login.jsp").forward(request,response);

  }
else {

      request.setAttribute("signupPageMessage","UserName Or Email Already Exists");
request.getRequestDispatcher("/register.jsp").forward(request,response);
  }

}catch (SQLException s)
{
    s.printStackTrace();
}





    }
}
