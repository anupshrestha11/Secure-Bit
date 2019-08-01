<%@include file="header.jsp" %>
<%
    request.getSession().getAttribute("fMessage");
%>
<div class="container">

    <form action="ForgotPassword" method="post">
        <h2>Forgot Password</h2><br>
        <b class="text-danger">${fMessage}</b>
        <div class="form-group">
            <input type="text" name="user" value="" placeholder="Enter Username/Email" required>
        </div>
        <input type="submit" value="Submit" class="btn btn-success">
    </form>

</div>
</body>
</html>
