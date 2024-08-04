<%-- 
    Document   : forgetPassword
    Created on : Jul 1, 2024, 8:57:25 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        </style>
    </head>
    <body>
        <div class="login-form">
            <form id="loginForm" action="forgot" method="post">
                <h2 class="text-center">Forget Password?</h2>
                <div class="form-group">
                    <input type="text" name="email" class="form-control" placeholder="Enter email address" required="required">
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Reset Password</button>
                </div>
                <p>${mess}</p>
            </form>
            <p class="text-center"><a href="login.jsp">Back to Login</a></p>
        </div>
    </body>
</html>
