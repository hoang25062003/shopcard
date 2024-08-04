<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .login-form {
                width: 340px;
                margin: 50px auto;
                font-size: 15px;
            }
            .login-form form {
                margin-bottom: 15px;
                background: #f5f5f5;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }
            .login-form h2 {
                margin: 0 0 15px;
            }
            .form-control, .btn {
                min-height: 38px;
                border-radius: 2px;
            }
            .btn {
                margin: 0 1px;
                font-size: 1.1rem;
                padding: 8px 15px;
            }
            .error {
                color: red;
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <div class="login-form">
            <form id="loginForm" action="login" method="post">
                <h2 class="text-center">Log in</h2>
                <c:if test="${not empty errorMessage}">
                    <div class="error">${errorMessage}</div>
                </c:if>
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="Username" required="required">
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="Password" required="required">
                </div>
                <div class="form-group">
                    <input type="text" name="captcha" class="form-control" placeholder="Enter captcha" required="required">
                    <img id="captchaImg" src="captcha" alt="Captcha">
                    <button type="button" id="reloadCaptcha" class="btn btn-secondary"><i class="fa fa-refresh"></i></button>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Log in</button>
                </div>
                <div class="clearfix">
                    <a href="forgetPassword.jsp" class="float-right">Forgot Password?</a>
                </div>
            </form>
            <p class="text-center"><a href="register.jsp">Create an Account</a></p>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script>
            $(document).ready(function () {
                // Reload captcha image
                $('#reloadCaptcha').click(function () {
                    $('#captchaImg').attr('src', 'captcha?' + new Date().getTime());
                });
            });
        </script>
    </body>
</html>
