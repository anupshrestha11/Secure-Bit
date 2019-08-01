<%@ page import="java.util.List" %>
<%@ page import="Data.FileData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

<table class="table table-striped text-center">
    <thead>
    <th>S.N.</th>
    <th>File Name</th>
    <th>Description</th>
    <th>Upload Date</th>
    <th>Key</th>
    <th>Action</th>
    </thead>
    <tbody>
    <c:forEach items="${fileDatas}" var="file" varStatus="i">
        <tr>
            <td><c:out value="${i.index+1}"></c:out></td>
            <td><c:out value="${file.orginalName}"></c:out></td>
            <td><c:out value="${file.fileDescription}"></c:out></td>
            <td><c:out value="${file.date}"></c:out></td>
            <td><c:out value="${file.keyForEncryption}"></c:out></td>
            <td><a href="/deletefile?id=<c:out value="${file.fileId}"></c:out>">
                <button class="btn btn-warning">Delete</button>
            </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
