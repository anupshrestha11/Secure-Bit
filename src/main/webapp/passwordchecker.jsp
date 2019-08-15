<%@ page import="Encoder.PasswordEncoder" %><%--
  Created by IntelliJ IDEA.
  User: Anup
  Date: 8/5/2019
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%

    String enteredPassword=request.getParameter("password");
    String userPassword=(String)request.getSession().getAttribute("encodedPassword");
    PasswordEncoder passwordEncoder=new PasswordEncoder();
    String encodedEnteredPassword="";
    String fileKey = request.getParameter("fileEncryptionKey");
    try{

        encodedEnteredPassword=passwordEncoder.sha512PasswordEncoder(enteredPassword);
    }
    catch (Exception e)
    {


    }


    if(encodedEnteredPassword.equals(userPassword))
    {
        request.getSession().setAttribute("invalidRequestedKey","1");
        request.getSession().setAttribute("requestedKey",fileKey);
response.sendRedirect("/myfiles.jsp");
    }
    else{


        request.getSession().setAttribute("invalidRequestedKey","2");
        response.sendRedirect("/myfiles.jsp");


    }

%>
