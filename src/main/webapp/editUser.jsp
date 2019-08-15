<%@include file="header.jsp" %>
<%@include file="session.jsp" %>
<%
    httpSession.getAttribute("UserDatas");
%>
<div class="container">
    <%@include file="element.jsp" %>
    <form action="EditUser" method="post">
        <h3 class="caption">Edit Your Details</h3>
        <br>
        <div class="form-group">
            <label>Name</label>
            <input type="text" name="name" class="form-control" value="<c:out value="${UserDatas.name}"></c:out>"
                   placeholder="Enter Name"
                   required/>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" class="form-control" value="<c:out value="${UserDatas.email}"></c:out>"
                   placeholder="Enter Email" required/>
        </div>
        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username" class="form-control" disabled
                   value="<c:out value="${UserDatas.username}"></c:out>"
                   placeholder="Enter Username" required/>
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control" name="password" value="abcdefgh" disabled/>
        </div>
        <input type="submit" value="Update" class="btn btn-success">
    </form>
</div>

</body>
</html>
