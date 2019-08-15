<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="loader.jsp"%>

<%@include file="navbars.jsp"%>
<%
    Date date= Calendar.getInstance().getTime();
    DateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
    String currentDate=dateFormat.format(date);

%>
<div id="content-wrapper">

    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/dashboard">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">Browse Files</li>
        </ol>
        <c:if test="${sessionScope.sendRequestMessage != null}">
            <ol class="breadcrumb">
                <li class="breadcrumb-item text-success">${sessionScope.sendRequestMessage}</li>
            </ol>
        </c:if>

        <!-- DataTables Example -->
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-table"></i>
                Browse Files</div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

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
            <td><a href="/requestfile?id=<c:out value="${search.fileId}"></c:out>">
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

                </div>
            </div>
            <div class="card-footer small text-muted">Last Refresh : <%=currentDate%>
            </div>
        </div>
    </div>
</div>




<%@include file="footer.jsp"%>
<%
    request.getSession().setAttribute("sendRequestMessage",null);
%>