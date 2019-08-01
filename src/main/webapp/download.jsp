<%@include file="session.jsp"%>
<%@include file="header.jsp"%>
<%@include file="navbar.jsp"%>
<table class="table table-striped text-center">
    <thead>
    <th>s.n</th>
    <th>Owner Name</th>
    <th>Contact Email</th>
    <th>File Name</th>
    <th>File Description</th>
    <th>Enter Key</th>
    <th>Action</th>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.acceptedFiles}" var="accepted" varStatus="i">
        <tr>
            <td><c:out value="${i.index+1}"></c:out></td>
            <td><c:out value="${accepted.ownerName}"></c:out></td>
            <td><c:out value="${accepted.email}"></c:out></td>
            <td><c:out value="${accepted.orginalName}"></c:out></td>
            <td><c:out value="${accepted.fileDescription}"></c:out></td>
            <form action="/download" method="post" class="form">
            <div class="form-group">
                <td><input type="text" name="key"/></td>
            </div>
            <div>
                <input type="hidden" name="fileId" value="<c:out value="${accepted.fileId}"></c:out>">
            </div>
            <div class="form-group">
                <td><input type="submit" value="Download" class="btn-success"></td>
            </div>
            </form>
        </tr>
    </c:forEach>
</tbody>
</table>