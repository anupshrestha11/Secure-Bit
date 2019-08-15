<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="loader.jsp" %>

<%@include file="navbars.jsp" %>


<div id="content-wrapper">

    <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="dashboards.jsp">Dashboard</a>
            </li>

        </ol>

        <!-- Icon Cards-->
        <div class="row">
            <div class="col-xl-3 col-sm-6 mb-3">
                <div class="card text-white bg-primary o-hidden h-100">
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fas fa-fw fa-file"></i>
                        </div>
                        <div class="mr-5">My Total Files :&nbsp;&nbsp;${totalValues} </div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="myfiles.jsp">
                        <span class="float-left">View Files</span>
                        <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                    </a>
                </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
                <div class="card text-white bg-warning o-hidden h-100">
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fas fa-fw fa-file-archive"></i>
                        </div>
                        <div class="mr-5">Pending File Request : ${requestCount} </div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="ViewRequest.jsp">
                        <span class="float-left">View File Request</span>
                        <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                    </a>
                </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
                <div class="card text-white bg-success o-hidden h-100">
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fas fa-fw fa-file-download"></i>
                        </div>
                        <div class="mr-5">Accepted File Request : ${downloadCount}</div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="download.jsp">
                        <span class="float-left">Download Files</span>
                        <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                    </a>
                </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
                <div class="card text-white bg-danger o-hidden h-100">
                    <div class="card-body">
                        <div class="card-body-icon">
                            <i class="fas fa-fw fa-shopping-cart"></i>
                        </div>
                        <div class="mr-5">Available Files : ${totalBrowseFiles}</div>
                    </div>
                    <a class="card-footer text-white clearfix small z-1" href="browsefile.jsp">
                        <span class="float-left">Browse Files</span>
                        <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
                    </a>
                </div>
            </div>
        </div>
        <%
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            String currentDate = dateFormat.format(date);

        %>
        <div class="card-footer small text-muted">Last Refresh : <%=currentDate%>
        </div>

<%--        <div class="container-fluid">--%>
        <%--            <h2 class="text-center" style="padding-top: 50px">Latest Files</h2>--%>
        <%--            <hr>--%>
        <%--            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">--%>
        <%--                <div class="carousel-inner">--%>
        <%--                    <% int j = 0;%>--%>
        <%--                    <% for (int i = 0; i < 3; i++) {%>--%>
        <%--                    <div class="carousel-item <% if (j==0){%>active<%}%> " align="center">--%>
        <%--                        <div class="row text-center" align="center">--%>
        <%--                            <c:forEach items="${sessionScope.browseFiles}" var="browse" varStatus="i" begin="<%=j%>"--%>
        <%--                                       end="<%=(j+3)%>">--%>
        <%--                                <div class="col-3" align="center">--%>
        <%--                                    <div class="card" style="width: 18rem;">--%>
        <%--                                            &lt;%&ndash;                                        <img class="card-img-top" src="..." alt="Card image cap">&ndash;%&gt;--%>
        <%--                                        <div class="card-body">--%>
        <%--                                            <h5 class="card-title"><c:out value="${browse.orginalName}"></c:out></h5>--%>
        <%--                                            <p class="card-text"><c:out value="${browse.fileDescription}"></c:out></p>--%>
        <%--                                            <a href="/requestfile?id=<c:out value="${browse.fileId}"></c:out>" class="btn btn-primary">Request</a>--%>
        <%--                                        </div>--%>
        <%--                                    </div>--%>
        <%--                                </div>--%>
        <%--                            </c:forEach>--%>
        <%--                            <%j = j + 4;%>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>

        <%--                    <% }%>--%>

        <%--                </div>--%>
        <%--                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">--%>
        <%--                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
        <%--                    <span class="sr-only">Previous</span>--%>
        <%--                </a>--%>
        <%--                <a class="carousel-control-next btn-outline-" href="#carouselExampleControls" role="button"--%>
        <%--                   data-slide="next">--%>
        <%--                    <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
        <%--                    <span class="sr-only">Next</span>--%>
        <%--                </a>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <%--        <!-- Sticky Footer -->--%>
        <%--        <footer class="sticky-footer">--%>
        <%--            <div class="container my-auto">--%>
        <%--                <div class="copyright text-center my-auto">--%>

        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </footer>--%>

    </div>
    <!-- /.content-wrapper -->

</div>
<!-- /#wrapper -->

<%@include file="footer.jsp" %>