<%@include file="header.jsp" %>
<div class="container">
    <form action="EmailCode" method="get">
        <h3>Enter Code</h3>
        <p>Code is sent to your registed Email Addres <br> Check your mail</p>
        <div class="form-group">
            <input type="text" name="pincode" minlength="6"/>
        </div>
        <input type="submit" value="Submit" class="btn btn-success">
    </form>
</div>
</body>
</html>
