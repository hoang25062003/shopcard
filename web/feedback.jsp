<!DOCTYPE html>
<html lang="en">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Báo cáo sự cố</title>
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-cyborg-gaming.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <style>
            .btn-danger {
                background-color: #dc3545;
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 1.2em;
                border-radius: 5px;
                cursor: pointer;
            }

            .btn-danger:hover {
                background-color: #c82333;
            }

            .heading-section {
                text-align: center; /* Canh giữa nút bấm */
                margin-top: 20px;
            }

            body {
                font-family: 'Poppins', sans-serif;
                background-color: #141414;
                color: white;
            }

            .container {
                margin-top: 50px;
            }

            .form-group label {
                color: white;
                font-weight: bold;
            }

            .form-control, .form-control-file {
                background-color: #333;
                color: white;
                border: 1px solid #555;
            }

            .form-control::placeholder {
                color: #bbb;
            }

            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
            }

            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #004085;
            }

            .alert-success {
                color: #155724;
                background-color: #d4edda;
                border-color: #c3e6cb;
            }
        </style>
        <script>
            function redirectToHome() {
                setTimeout(function () {
                    window.location.href = '/SWP/home';
                }, 1000); // Chuyển hướng sau 1 giây
            }
        </script>
    </head>
    <body>
        
        <div class="container mt-5">
            <h2>Báo cáo sự cố</h2>
            <c:if test="${not empty message}">
                <div class="alert alert-success" role="alert">
                    ${message}
                </div>
                <script>redirectToHome();</script>
            </c:if>
            <form action="<c:url value='/FeedBackController'/>" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="content">Nội dung:</label>
                    <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
                </div>
                <div class="form-group">
                    
                    <label for="image">Ảnh (tuỳ chọn):</label>
                    <input type="file" class="form-control-file" id="image" name="image">
                </div>
                <button type="submit" class="btn btn-primary">Gửi báo cáo</button>
            </form>
        </div>

        <!-- Scripts -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/isotope.min.js"></script>
        <script src="assets/js/owl-carousel.js"></script>
        <script src="assets/js/tabs.js"></script>
        <script src="assets/js/popup.js"></script>
        <script src="assets/js/custom.js"></script>
    </body>
</html>
