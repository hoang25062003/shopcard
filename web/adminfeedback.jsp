<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách báo cáo sự cố</title>
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-cyborg-gaming.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">

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

            .heading-section {
                text-align: center;
                margin-top: 20px;
                color: black;
            }

            body {
                font-family: 'Poppins', sans-serif;
                background-color: white;
                color: black;
            }

            .container {
                margin-top: 50px;
            }

            .form-group label {
                color: black;
                font-weight: bold;
            }

            .form-control, .form-control-file {
                background-color: white;
                color: black;
                border: 1px solid #ccc;
            }

            .form-control::placeholder {
                color: #666;
            }

            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
                color: white;
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

            .details th, .details td {
                padding: 8px;
                text-align: left;
                border: 1px solid #ddd;
                color: black;
            }
            .details th {
                background-color: #f2f2f2;
            }
            .details tr:hover {
                background-color: #e6e6e6;
            }

            .details tr.selected {
                background-color: #cccccc !important;
            }

            .btn-back {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 1.2em;
                border-radius: 5px;
                cursor: pointer;
                margin-bottom: 20px;
                text-decoration: none;
                display: inline-block;
            }

            .btn-back:hover {
                background-color: #0056b3;
            }

            .img-thumbnail {
                max-width: 100px;
                height: auto;
            }

            .status-checkbox {
                cursor: pointer;
            }

            /* Custom DataTables styles */
            .dataTables_length label {
                color: black;
            }

            .dataTables_length select {
                background-color: white;
                color: black;
                border: 1px solid #ccc;
            }

            .dataTables_filter label {
                color: black;
            }

            .dataTables_filter input[type="search"] {
                background-color: white;
                color: black;
                border: 1px solid #ccc;
            }

            .dataTables_info {
                color: black;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="heading-section">Danh sách báo cáo sự cố</h2>

            <!-- Search form -->
            <form method="GET" action="FeedBackAdmin">
                <div class="form-group">
                    <label for="username">Tìm kiếm theo tên người dùng:</label>
                    <input type="text" class="form-control" id="username" name="username" value="${username}">
                </div>
                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            </form>

            <div id="incident-report-list" style="margin-top: 20px;">
                <table id="feedbackTable" class="details display table table-light">
                    <thead>
                        <tr>
                            <th><a href="FeedBackAdmin?sort=CreatedAt&order=${sort == 'CreatedAt' && order == 'asc' ? 'desc' : 'asc'}">Ngày tạo</a></th>
                            <th><a href="FeedBackAdmin?sort=username&order=${sort == 'username' && order == 'asc' ? 'desc' : 'asc'}">Tên người dùng</a></th>
                            <th>Mô tả</th>
                            <th><a href="FeedBackAdmin?sort=Status&order=${sort == 'Status' && order == 'asc' ? 'desc' : 'asc'}">Trạng thái</a></th>
                            <th>Hình ảnh</th>
                        </tr>
                    </thead>
                    <tbody id="incident-report-tbody">
                        <c:forEach items="${incidentReports}" var="report">
                            <tr>
                                <td>${report.feedback.createdAt}</td>
                                <td>${report.username}</td>
                                <td>${report.feedback.content}</td>
                                <td>
                                    <form action="UpdateFeedbackStatus" method="POST">
                                        <input type="hidden" name="id" value="${report.feedback.ID}">
                                        <input type="checkbox" class="status-checkbox" name="status" value="1" ${report.feedback.status ? 'checked' : ''} onchange="this.form.submit()">
                                    </form>
                                </td>
                                <td>
                                    <c:if test="${not empty report.feedback.image}">
                                        <img src="${report.feedback.image}" alt="Hình ảnh" class="img-thumbnail">
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="Chung" class="btn-back">Trở lại</a>

            <div class="pagination">
                <c:forEach var="i" begin="1" end="${noOfPages}">
                    <a class="btn-back ${i == currentPage ? 'active' : ''}" href="FeedBackAdmin?page=${i}&sort=${sort}&order=${order}&username=${username}">${i}</a>
                </c:forEach>
            </div>
        </div>

    </body>
</html>
