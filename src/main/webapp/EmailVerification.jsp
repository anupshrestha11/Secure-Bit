<%@ page contentType="text/html;charset=UTF-8"  session="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anup
  Date: 8/6/2019
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    HttpSession httpSession = request.getSession(false);
    if (httpSession.getAttribute("randomEmailVerify") == null) {

        response.sendRedirect("/index.jsp");
    }
    httpSession.getAttribute("errorSignupCode");

%>
<html>
<head>
    <title>Verify Email</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body class="bg-primary">
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <form name="emailVerify" action="/verifyEmail" method="post">

                <div class="form-group">
                    <label class="display-3">Enter Code</label>
                    <% if (httpSession.getAttribute("errorSignupCode") != null) { %>
                    <label class="text-danger">${errorSignupCode}</label>
                    <%
                        }
                    %>
                    <input type="text" name="verifyEmail" minlength="6" maxlength="6" class="form-control" placeholder="Enter the Code that has been sent to your email" required/>
                </div>
                <br>

                <input type="submit" value="Submit Code" class="form-control btn btn-success">
            </form>
        </div>
    </div>
</div>

</body>
</html>
<%

    request.getSession().setAttribute("errorSignupCode", null);
%>
