<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="loader.jsp" %>

<%@include file="navbars.jsp" %>
<%@include file="js.jsp" %>
<%
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
    String currentDate = dateFormat.format(date);

%>
<div id="content-wrapper">

    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/dashboard">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">Requests</li>
        </ol>
        <c:if test="${sessionScope.acceptedRequestMessage != null}">
            <ol class="breadcrumb">
                <li class="breadcrumb-item text-success">${sessionScope.acceptedRequestMessage}</li>
            </ol>
        </c:if>


        <!-- DataTables Example -->
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-table"></i>
                Requests
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

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
                                    <a onclick="return checkDelete()" href="deleterequest?id=${request.requestId}"
                                       class="btn btn-danger">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>
            <div class="card-footer small text-muted">Last Refresh : <%=currentDate%>
            </div>
        </div>
    </div>
</div>


<%@include file="footer.jsp" %>
<%
    request.getSession().setAttribute("acceptedRequestMessage", null);
%>

