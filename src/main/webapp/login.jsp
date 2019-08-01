
<%@include file="header.jsp" %>
<%

HttpSession httpSession=request.getSession();
if(httpSession.getAttribute("userName")!=null)
{
    response.sendRedirect("/login");
}
%>
<div class="container">
    <form action="/login" method="post" class="log">
        <br><h3>Login Page</h3>
        <b class="text-danger"> ${loginPageMessage}</b> <br/>
        <div class="form-group">
            <input type="text" name="username" placeholder="Username" required/>
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Password" minlength="6" required/>
        </div>
        <input type="submit" class="btn btn-success" value="Login">
        <a href="forgotPassword.jsp" class="btn btn-link">Forgot Password</a>
    </form>

    <br>
    <a href="/register.jsp" class="btn btn-outline-primary">Create Account</a>.
</div>

</body>
</html>
