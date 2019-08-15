<%@include file="loader.jsp" %>

<%@include file="navbars.jsp" %>


<style>
    .box {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
</style>
<div id="content-wrapper">

    <div class="container-fluid">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/dashboard">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">Upload</li>
        </ol>
        <div class="box">
            <form id="uploadForm" action="/uploadfile" method="post" class="text-center" enctype="multipart/form-data">
                <div class="form-group">
                    <br>
                    <br>
                    <c:if test="${sessionScope.uploadFileMessage != null}">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item text-success">${sessionScope.uploadFileMessage}</li>
                        </ol>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="filekey">File Key:</label>
                    <input type="text" id="filekey" name="fileKey" required/>
                </div>
                <div class="form-group">
                    <input type="file" class="uploadbtn" name="file" required>
                </div>
                <div class="form-group ">
                    <label for="Description">Description: </label>
                    <textarea name="description" id="Description" rows="10" class="form-control" width="100px" required
                              minlength="20"></textarea>
                </div>


                <div class="form-group ">
                    <input type="submit" value="Upload" class="btn btn-success form-group">
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
<%
    request.getSession().setAttribute("uploadFileMessage", null);
%>