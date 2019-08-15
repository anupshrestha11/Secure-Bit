<%@ page import="Service.ShowDownloadFilesService" %>
<%@ page import="Data.FileData" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Data.FileRequestData" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Service.ShowRequestFileService" %>
<%@ page import="Service.BrowseDataService" %>
<%@ page import="SessionCheckerServlet.SessionChecker" %>
<!DOCTYPE html>
<html lang="en">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="session.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    ShowDownloadFilesService showDownloadFilesService = new ShowDownloadFilesService();
    ShowRequestFileService showRequestFileService = new ShowRequestFileService();
    BrowseDataService browseDataService = new BrowseDataService();
            int sessionId = ((Integer) request.getSession().getAttribute("userId")).intValue();

        try {
            List<FileData> fileDataList = showDownloadFilesService.getAllDownloadFiles(request.getSession().getAttribute("userEmail").toString());
            request.getSession().setAttribute("acceptedFiles", fileDataList);
            request.getSession().setAttribute("downloadCount", showDownloadFilesService.getCount());
            List<FileRequestData> fileRequestData = new ArrayList<>();

            fileRequestData = showRequestFileService.getAllRequestData(sessionId);
            request.getSession().setAttribute("requestCount", showRequestFileService.getCount());
            request.getSession().setAttribute("requestFiles", fileRequestData);


            List<FileData> fileDataList1 = browseDataService.getData(((Integer) request.getSession().getAttribute("userId")).intValue());
            request.getSession().setAttribute("browseFiles", fileDataList1);
            request.getSession().setAttribute("totalBrowseFiles", browseDataService.getCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
%>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
          type="text/css">

    <!-- Page level plugin CSS-->
    <%--    <!-- <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet"> -->--%>

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">

</head>

<body id="page-top">

<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="/dashboard"> <img width="40px" src="logo1.png"> &nbsp; Secure-Bit</a>

    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
    </button>

    <!-- Navbar Search -->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0" action="/search" method="get">
        <div class="input-group">
            <input name="search" type="text" class="form-control" placeholder="Search for..." aria-label="Search"
                   aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search"></i>
                </button>
            </div>
        </div>
    </form>

    <h3 style="color: white;">${userFullName}</h3>

    <ul class="navbar-nav ml-auto ml-md-0">
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-user-circle fa-fw" style="font-size: 30px"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">

                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
            </div>
        </li>
    </ul>
</nav>

<div id="wrapper">

    <!-- Sidebar -->
    <ul class="sidebar navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="/dashboard">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Dashboard</span>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="myfiles.jsp">
                <i class="fas fa-fw fa-file-image"></i>
                <span>My Files</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="uplaodfile.jsp">
                <i class="fas fa-fw fa-file-upload"></i>
                <span>Upload File</span></a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="ViewRequest.jsp">
                <i class="fas fa-fw fa-file-archive"></i>
                <span>View File Requests</span></a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="browsefile.jsp">
                <i class="fas fa-fw fa-shopping-cart"></i>
                <span>Browse Files</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="download.jsp">
                <i class="fas fa-fw fa-file-download"></i>
                <span>Download</span></a>
        </li>
    </ul>

