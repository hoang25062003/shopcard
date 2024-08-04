<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration Form</title>
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

        <div class="container" style="padding: 100px; width: 50%">
            <h2 style="text-align: center;" class="mt-5">User Profile</h2>
            <form style="padding: 30px; background-color: lightcyan; border-radius: 15px" action="register" method="POST" class="mt-4" onsubmit="return validatePassword()">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name" value="${sessionScope.account.name}" name="name" readonly>
                </div>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" value="${sessionScope.account.userName}" name="username" readonly>
                </div>
                <!--                <div class="form-group">
                                    <label for="password">Password:</label>
                                    <input type="password" class="form-control" id="password" name="password" readonly>
                                </div>
                -->
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" value="${sessionScope.account.email}" name="email" readonly>
                </div>
                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="text" class="form-control" id="phone" value="${sessionScope.account.phone}" name="phone" readonly>
                </div>
                <p>${mess}</p>
                
                <div class="d-flex justify-content-between align-content-center">
                    <a href="updateProfile.jsp" class="btn btn-primary">Update profile</a>
                    <a href="change-password.jsp" class="btn btn-primary">Thay đổi mật khẩu</a>
                    <a href="home" class="btn btn-secondary">Back to Home</a>
                </div>
            </form>
        </div>

        <!-- Bootstrap JS (optional, only if needed) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <!-- Custom JS for password validation -->
        <script>
                function validatePassword() {
                    var password = document.getElementById("password").value;
                    var repassword = document.getElementById("repassword").value;
                    if (password !== repassword) {
                        alert("Passwords do not match. Please try again.");
                        return false;
                    }
                    return true;
                }
        </script>
    </body>
</html>
