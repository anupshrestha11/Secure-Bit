<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>

<%

    HttpSession httpSession = request.getSession();
    if (httpSession.getAttribute("userName") != null) {
        response.sendRedirect("/login");
    }
%>
<!DOCTYPE html>
<html>
<title>Welcome</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">


<%

    String signMessage = (String) request.getSession().getAttribute("signupPageMessage");
    String loginMessage = (String) request.getSession().getAttribute("loginPageMessage");
%>


<style>
    body, h1 {
        font-family: "Raleway", sans-serif
    }

    body, html {
        height: 100%
    }

    .bgimg {
        /*background-image: url('/front.jpg');*/
        min-height: 100%;
        background-position: center;
        background-size: cover;
        position: relative;

        background-image: linear-gradient(-90deg, red, black);

    }

    label {

        padding-right: 100px;
        color: red;
    }

    button {


        border-radius: 10px;
    }


    {
        box-sizing: border-box
    ;
    }


    /* The popup form - hidden by default */
    .login-popup {
        display: none;
        position: absolute;
        bottom: 0;
        right: 15px;
        border: 3px solid #f1f1f1;
        z-index: 9;
    }

    .signup-popup {
        display: none;
        position: fixed;
        bottom: 0;
        right: 15px;
        border: 3px solid #f1f1f1;
        z-index: 9;
    }

    /* Add styles to the form container */
    .form-container {
        max-width: 300px;
        padding: 10px;
        background-color: silver;
    }

    /* Full-width input fields */
    .form-container input[type=text], .form-container input[type=password], .form-container input[type=email] {
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        border: none;
        background: #f1f1f1;
    }

    /* When the inputs get focus, do something */
    .form-container input[type=text]:focus, .form-container input[type=password]:focus, .form-container input[type=email]:focus {
        background-color: #ddd;
        outline: none;
    }

    /* Set a style for the submit/login button */
    .form-container .btn {
        background-color: #4CAF50;
        color: white;
        padding: 16px 20px;
        border: none;
        cursor: pointer;
        width: 100%;
        margin-bottom: 10px;
        opacity: 0.8;

    }

    /* Add a red background color to the cancel button */
    .form-container .cancel {
        background-color: red;
    }

    /* Add some hover effects to buttons */
    .form-container .btn:hover {
        opacity: 1;
    }

    .w3-display-middle{
        width: 100%;
        text-align: center;
    }

</style>

<script>


    function onLoad() {
        var signVariable = '<%=signMessage%>';
        var logVariable = '<%=loginMessage%>';
        if (signVariable == 2) {
            openSignForm();
            alert("UserName Or Email Already Exists!!!");
            signVariable = "";
        }
        if (logVariable == 1) {
            alert("SignUp Sucessful Login To Continue!!!!");
            openLoginForm();
            logVariable = "";
        }

        if (logVariable == 2) {
            alert("Login Credentials Donot Match!!!");
            openLoginForm();
            logVariable = "";
        }

    }

    function validate() {

        if (document.signupform.userpassword.value != document.signupform.repassword.value) {
            alert("Password And Repassword Must Match");
            document.signupform.repassword.focus();
            return false;


        }


    }

    function openLoginForm() {
        document.getElementById("signup").style.display = "none";

        document.getElementById("login").style.display = "block";

    }

    function closeLoginForm() {
        document.getElementById("login").style.display = "none";
    }

    function openSignForm() {
        document.getElementById("login").style.display = "none";

        document.getElementById("signup").style.display = "block";
    }

    function closeSignForm() {
        document.getElementById("signup").style.display = "none";

    }
</script>

<body onload="onLoad()" class="">

<div class="bgimg w3-display-container w3-animate-opacity w3-text-white">
<%--    <div class="w3-display-topleft w3-padding-large w3-xlarge">--%>
<%--        <img src="logo.png" alt="logo"/>--%>

<%--    </div>--%>
    <%--    <div class="d-flex justify-content-center">--%>
    <%--        <h1 class="display-1 ">Secure-Bit</h1>--%>
    <%--    </div>--%>
    <div class="w3-display-middle">
        <img src="logo.png" alt="logo" width="10%"/>
        <h1 class="display-1" style="font-size: 5em; width: 100%; background: darkturquoise;margin: 0;">Secure-Bit</h1>
        <h3 class="" style="font-size: 2em; width: 100%; background: grey; margin: 0;">Secure Every Bit</h3>
        <button class=" w3-button w3-xxlarge w3-animate-top w3-hover-black" onclick="openSignForm()"><u
                style="color: snow"><b>Sign-Up</b> </u></button>&nbsp; &nbsp;&nbsp;&nbsp;
        <button class=" w3-button w3-xxlarge w3-animate-right w3-hover-black " onclick="openLoginForm()"><u
                style="color: snow"><b>Sign-In</b></u></button>&nbsp; &nbsp;&nbsp;&nbsp;

    </div>
<%--    <div class="w3-display-bottomright">--%>
<%--        <label class="w3-xxlarge w3-animate-top"><b><u>Secure Every Bit</u></b></label>--%>
<%--    </div>--%>

</div>

<div class="login-popup " id="login">
    <form action="/login" method="post" class="form-container">
        <h1>Login</h1>

        <input type="text" placeholder="User Name" id="username" name="username" required>

        <input type="password" placeholder="Enter Password" id="password" name="password" minlength="6" required>

        <button type="submit" class="btn">Login</button>
        <button class="btn cancel" onclick="closeLoginForm()">Close</button>
    </form>
</div>


<div class="signup-popup" id="signup">
    <form action="/signup" name="signupform" method="post" class="form-container" onsubmit="return validate()">
        <h1>Sign Up</h1>
        <input type="text" placeholder="Full Name" id="name" name="name" required>

        <input type="email" placeholder="Email Address" id="email" name="email" required>

        <input type="text" placeholder="UserName" id="signusername" name="username" minlength="5" required>

        <input type="password" placeholder="Enter Password" id="userpassword" name="password" minlength="6" required>

        <input type="password" id="repassword" name="repassword" placeholder="Retype-Password" class="form-control"
               required
               minlength="6"/>

        <button type="submit" class="btn">Signup</button>
        <button class="btn cancel" onclick="closeSignForm()">Close</button>
    </form>
</div>
<%
    request.getSession().setAttribute("signupPageMessage",null);
    request.getSession().setAttribute("loginPageMessage",null);

%>


</body>
</html>


</div>
</body>
</html>
