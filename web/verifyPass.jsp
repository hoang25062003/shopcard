<%-- 
    Document   : verifyPass
    Created on : Jul 7, 2024, 9:18:53 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Forgot Password?</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">        <style>
            body{
                background-repeat: no-repeat;
                background-size: cover;
                background-position: center;
            }
        </style>
    </head>
    <body>
        <div class="container" style="padding: 100px;">
            <h2  style="text-align: center;" class="mt-5"> Enter OTP to reset password</h2>
            <form  style="padding: 30px; background-color: lightcyan; border-radius: 15px"  action="verifyPass" method="POST">

                <input type="hidden" value="${param.email}" id="email" name="email" required><br>
                <div class="form-group">
                    <label for="phone">Enter OTP:</label><br>
                    <input type="text" id="phone"  class="form-control" name="otp" required><br>
                </div>
                <p>${mess}</p>
                <input type="submit" class="btn btn-primary" value="Reset Password">
            </form>
            <p class="text-center"><a href="login.jsp">Back To Login</a></p>
        </div>
    </body>
</html>
