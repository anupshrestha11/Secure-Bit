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
            <li class="breadcrumb-item active">Download</li>
        </ol>

        <c:if test="${sessionScope.downloadError != null}">
            <ol class="breadcrumb">
                <li class="breadcrumb-item text-success">${sessionScope.downloadError}</li>
            </ol>
        </c:if>

        <!-- DataTables Example -->
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-table"></i>
                Requests</div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
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

                </div>
            </div>
            <div class="card-footer small text-muted">Last Refresh : <%=currentDate%> </div>
        </div>
    </div></div>

<%@include file="footer.jsp"%>
<%
    request.getSession().setAttribute("downloadError", null);
%>