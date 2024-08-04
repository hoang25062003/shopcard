<!DOCTYPE html>
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
            <h2 style="text-align: center;" class="mt-5">Change Password</h2>
            <form style="padding: 30px; background-color: lightcyan; border-radius: 15px" action="ChangePassword" method="POST" class="mt-4" >
                <div class="form-group">
                    <label for="name">Old Password:</label>
                    <input type="password" class="form-control" id="name"  name="oldPassword" >
                </div>
                <div class="form-group">
                    <label for="username">New Password:</label>
                    <input type="password" class="form-control" id="username" name="newPassword" >
                </div>
                <div class="form-group">
                    <label for="username">Confirm Password:</label>
                    <input type="password" class="form-control" id="password"  name="confirmNewPassword" >
                </div>
                <p>${mess}</p>
                <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Change password</button>
                <a href="home" class="btn btn-secondary">Back to Home</a>
                </div>
            </form>
        </div>

        <!-- Bootstrap JS (optional, only if needed) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>
