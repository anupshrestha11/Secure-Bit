<%@include file="header.jsp"%>
<%@include file="session.jsp"%>
<%@include file="navbar.jsp"%>

<div class="jumbotron text-center">
    <h1><img src="logo1.png" alt="logo"><br/>Secure-Bit</h1>
    <h3>Welcome ${userFullName}</h3>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores atque cumque distinctio dolorem magnam obcaecati sit soluta velit veniam. Ab autem exercitationem laborum minima odio reiciendis repellat reprehenderit voluptatibus? Dolorem!</p>
</div>
<%--<%@include file="uplaodfile.jsp"%>--%>
<div>
    <label>${sessionScope.uploadMessage}</label>
</div>
<%@include file="myfiles.jsp"%>

</body>
</html>
