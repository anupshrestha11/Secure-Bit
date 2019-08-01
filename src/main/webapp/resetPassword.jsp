<%@include file="header.jsp" %>
<div class="container">
    <form action="ResetPassword" method="post">
        <h3>Reset Password</h3>
        <b class="text-danger">${requestScope.rMessage}</b>
        <div class="form-group">
            <input type="password" name="newpwd" placeholder="New Password" minlength="6"/>
        </div>
        <div class="form-group">
            <input type="password" name="renewpwd" placeholder="Re-enter Password" minlength="6"/>
        </div>
        <input type="submit" value="Reset" class="btn btn-success">
    </form>
</div>

</body>
</html>
