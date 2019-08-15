<%
    HttpSession httpSession = request.getSession();
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate,no-reload");

    if (httpSession.getAttribute("userName") == null) {
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
%>