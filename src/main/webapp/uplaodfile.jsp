<%@include file="session.jsp" %>
<%@include file="header.jsp" %>
<%@include file="navbar.jsp" %>


<style>
    .box {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
</style>
<div class="box">
    <form action="/uploadfile" method="post" class="text-center" enctype="multipart/form-data">
      <div class="form-group">
          <br>
          <br>
          <label>${uploadMessage}</label>

      </div>
        <div class="form-group">
            <label for="filekey">File Key:</label>
            <input type="text" id="filekey" name="fileKey" required maxlength="10"/>
        </div>
            <div class="form-group">
            <input type="file" class="uploadbtn" name="file" required>
        </div>
        <div class="form-group ">
            <label for="Description">Description: </label>
            <textarea name="description" id="Description" rows="10" class="form-control" width="100px" required minlength="20"></textarea>
        </div>






        <div class="form-group ">
            <input type="submit" value="Upload" class="btn btn-success form-group">
        </div>
    </form>
</div>
</body>
