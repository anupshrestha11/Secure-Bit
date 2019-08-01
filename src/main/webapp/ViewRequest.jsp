<%@include file="session.jsp" %>
<%@include file="header.jsp" %>
<%@include file="navbar.jsp" %>

<table class="table table-striped text-center">
    <thead>
    <th>s.n</th>
    <th>Requesting User</th>
    <th>Requesting User Email</th>
    <th>File Name</th>
    <th>File Description</th>
    <th>Action</th>
    </thead>
    <tbody>
    <c:forEach items="${requestFiles}" var="request" varStatus="i">
        <tr>
            <td><c:out value="${i.index+1}"></c:out></td>
            <td><c:out value="${request.requestingUserName}"></c:out></td>
            <td><c:out value="${request.requestingUserEmail}"></c:out></td>
            <td><c:out value="${request.fileName}"></c:out></td>
            <td><c:out value="${request.fileDescription}"></c:out></td>
            <td><a href="/requestAccept?id=${request.requestId}">
                <button class="btn btn-warning">Accept</button>
            </a>
                <a href="">
                    <button class="btn btn-Danger">Delete</button>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
