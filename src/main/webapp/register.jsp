

<%@include file="header.jsp" %>

<script>

    function validate()
    {

        if(document.signUpForm.password.value!=document.signUpForm.repassword.value)
        {
            alert("Password And Repassword Must Match");
            document.signUpForm.repassword.focus();
            return false;


        }


    }


</script>

<div class="container">

    <br>
    <h1 font-color="red">Registration Page</h1>
    <form action="/signup" method="post" name="signUpForm" onsubmit="return validate();">

        <b class="text-warning"> ${signupPageMessage}</b> <br/><br/>
        <div class="form-group">
            <label>Name</label>
            <input type="text" name="name" placeholder="Enter Full Name" class="form-control" required/>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" placeholder="Enter Email Address" class="form-control" required/>
        </div>
        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username" placeholder="Enter Username" class="form-control" required minlength="5" maxlength="10"/>
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" placeholder="Enter Password" class="form-control" required
                   minlength="6"/>
        </div>
        <div class="form-group">
            <label>Confirm Password</label>
            <input type="password" name="repassword" placeholder="Retype-Password" class="form-control" required
                   minlength="6"/>
        </div>
        <input type="submit" value="Register" class="btn btn-success"/> &nbsp &nbsp
        <input type="reset" value="Reset Form" class="btn btn-warning"/>
    </form>
    <br><br>
    Already have account? &nbsp;&nbsp; <a href="/login.jsp" class="btn btn-outline-primary">Login</a>
</div>
</body>
</html>
