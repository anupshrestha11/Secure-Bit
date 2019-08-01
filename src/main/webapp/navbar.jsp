<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <a class="navbar-brand" href="#">Secure-Bit</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="dashboard.jsp">Home </a>
            </li>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="myfiles.jsp">My Files</a>--%>
<%--            </li>--%>
            <li class="nav-item">
                <a class="nav-link" href="ViewRequest.jsp">View File Request</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="browsefile.jsp">Browse File</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="uplaodfile.jsp">Upload File</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="download.jsp">Download</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Setting</a>
            </li>
        </ul>
        <a href="/logout">
            <button class="btn btn-outline-warning" style="margin: 0px 5px;">Logout</button>
        </a>

    </div>
</nav>

<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script>
    $(function(){
        $('a').each(function(){
            if ($(this).prop('href') == window.location.href) {
                $(this).addClass('active'); $(this).parents('li').addClass('active');
            }
        });
    });
</script>