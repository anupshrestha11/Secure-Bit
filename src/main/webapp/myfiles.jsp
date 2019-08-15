<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="Encoder.PasswordEncoder" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="loader.jsp" %>

<%@include file="js.jsp" %>
<%@include file="navbars.jsp" %>
<%
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
    String currentDate = dateFormat.format(date);

%>
<%

    String keyMessage = (String) request.getSession().getAttribute("requestedKey");
    String keyMessage1 = (String) request.getSession().getAttribute("invalidRequestedKey");
%>

<script>


    function onLoad() {
        var invalidKey = '<%=keyMessage1%>';
        var sucessKeyVariable = '<%=keyMessage%>';
        if (invalidKey == 2) {
            alert("Enter Your Valid Password!!!!!!!");
        }
        if (invalidKey == 1) {
            alert("File Key Is:::\n" + sucessKeyVariable);
        }

    }


</script>
<body onload="onLoad()"></body>
<div id="content-wrapper">

    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/dashboard">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">My Files</li>
        </ol>
        <c:if test="${sessionScope.deleteFileMessage != null}">
            <ol class="breadcrumb">
                <li class="breadcrumb-item text-success">${sessionScope.deleteFileMessage}</li>
            </ol>
        </c:if>

        <!-- DataTables Example -->
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-table"></i>
                My Files
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered " id="dataTable" width="100%" cellspacing="0" style="table-layout: fixed;">
                        <style>
                            table td{
                                overflow: hidden;
                            }
                        </style>
                        <thead>
                        <tr>
                            <th width="20px">S.N</th>
                            <th width="50px">File Name</th>
                            <th width="200px">Description</th>
                            <th width="50px">Upload Date</th>
                            <th width="100px">Action</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${fileDatas}" var="file" varStatus="i">

                            <tr>

                                <td><c:out value="${i.index+1}"></c:out></td>
                                <td><c:out value="${file.orginalName}"></c:out></td>
                                <td><c:out value="${file.fileDescription}"></c:out></td>
                                <td><c:out value="${file.date}"></c:out></td>
                                    <%--                                <td><c:out value="${file.keyForEncryption}"></c:out></td>--%>

                                <td class="text-center"><a onclick="return checkDelete()"
                                       href="/deletefile?id=<c:out value="${file.fileId}"></c:out>">
                                    <button class="btn btn-warning">Delete</button>
                                </a>
                                    <button name="fileEncryptionKey" type="button" class="btn btn-primary"
                                            data-toggle="modal"
                                            data-target="#showKey<c:out value="${i.index}"></c:out> ">Show Key
                                    </button>
                                    <button class="btn btn-success" name="downloadFile" data-toggle="modal"
                                            data-target="#enterKey<c:out value="${i.index}"></c:out>">Download
                                    </button>
                                </td>

                            </tr>
                            <div class="modal fade" id="showKey<c:out value="${i.index}"></c:out>" tabindex="-1"
                                 role="dialog"
                                 aria-labelledby="exampleModalCenterTitle"
                                 aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle"><c:out
                                                    value="${file.orginalName}"></c:out>
                                                Encryption Key</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form action="/passwordchecker.jsp" name="getKey" method="post">
                                            <div class="modal-body">

                                                <input type="hidden" name="fileEncryptionKey"
                                                       value="<c:out value="${file.keyForEncryption}"></c:out>">
                                                <b><input type="password" id="pwd" name="password"/></b>
                                            </div>
                                            <div class="modal-footer">
                                                <input type="submit" value="Get Key">
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>
                            <div class="modal fade" id="enterKey<c:out value="${i.index}"></c:out>" tabindex="-1"
                                 role="dialog"
                                 aria-labelledby="Enter Key To Download"
                                 aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="EnterKey"><c:out
                                                    value="${file.orginalName}"></c:out>-Enter Encryption Key</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form action="/downloadMyFile" name="encryptionKey" method="post">
                                            <div class="modal-body">
                                                <input type="hidden" name="fileId" value="<c:out value="${file.fileId}"></c:out>">
                                                <input type="text" id="encryptionkey" name="encryptionkey"/></b>
                                            </div>
                                            <div class="modal-footer"><b>
                                                <input type="submit" class="btn btn-outline-success" value="Download">
                                            </b>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>

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

<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>


<%@include file="footer.jsp" %>


<%
    request.getSession().setAttribute("requestedKey", null);
    request.getSession().setAttribute("invalidRequestedKey", null);
    request.getSession().setAttribute("deleteFileMessage", null);
%>