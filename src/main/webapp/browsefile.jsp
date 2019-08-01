<%@include file="session.jsp" %>
<%@include file="header.jsp" %>
<%@include file="navbar.jsp" %>
<%
    httpSession.getAttribute("securedFiles");
%>

<form action="/search" method="get" class="form-inline my-2 my-lg-0 " style="display: flex; justify-content: right; ">
    <div style="margin: 10px 50px;">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </div>
</form>
<table class="table table-striped text-center">
    <thead>
    <th>s.n</th>
    <th>Owner Name</th>
    <th>Contact Email</th>
    <th>File Name</th>
    <th>File Description</th>
    <th>Action</th>
    </thead>
    <tbody>
    <c:if test="${sessionScope.searchedFiles == null}">
        <c:forEach items="${sessionScope.browseFiles}" var="browse" varStatus="i">
            <tr>
                <td><c:out value="${i.index+1}"></c:out></td>
                <td><c:out value="${browse.ownerName}"></c:out></td>
                <td><c:out value="${browse.email}"></c:out></td>
                <td><c:out value="${browse.orginalName}"></c:out></td>
                <td><c:out value="${browse.fileDescription}"></c:out></td>
                <td><a href="/requestfile?id=<c:out value="${browse.fileId}"></c:out>">
                    <button class="btn btn-warning">RequestFile</button>
                </a></td>
                </td>
                <td></td>
            </tr>
        </c:forEach>
    </c:if>

    <c:forEach items="${sessionScope.searchedFiles}" var="search" varStatus="i">
        <tr>
            <td><c:out value="${i.index+1}"></c:out></td>
            <td><c:out value="${search.ownerName}"></c:out></td>
            <td><c:out value="${search.email}"></c:out></td>
            <td><c:out value="${search.orginalName}"></c:out></td>
            <td><c:out value="${search.fileDescription}"></c:out></td>
            <td><a href="/requestfile?id=<c:out value="${browse.fileId}"></c:out>">
                <button class="btn btn-warning">RequestFile</button>
            </a></td>
            </td>
            <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%
    request.getSession().setAttribute("searchedFiles",null);
%>
