<%-- 
    Document   : home_t
    Created on : Jun 14, 2024, 11:56:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="de_DE"/>
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
    .popup-overlay {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.6);
        z-index: 1000;
        justify-content: center;
        align-items: center;
    }

    .popup-content {
        background: white;
        padding: 20px;
        border-radius: 8px;
        width: 300px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

    .popup-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;
    }

    .popup-header h4 {
        margin: 0;
        color: black;
    }

    .close-button {
        background: none;
        border: none;
        font-size: 1.5em;
        cursor: pointer;
    }

    .popup-body label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    .popup-body input {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    .popup-footer {
        text-align: right;
    }

    .popup-footer .btn {
        padding: 8px 12px;
        margin-left: 5px;
    }

    .dropdown-menu {
        display: none;
        list-style-type: none;
        margin: 0;
        padding: 0;
        background-color: #f0f0f0; /* Màu xám */
        padding: 10px; /* Tùy chỉnh khoảng cách bên trong nếu cần */
        border: 1px solid #ccc; /* Đường viền nếu muốn */
    }

    #profile-menu:hover .dropdown-menu {
        display: block;
        background-color: gray; /* Màu xám */
        width: 200px;
    }

    .dropdown-menu li {
        margin: 5px 0;
    }

    .profile-picture {
        width: 100px;  /* Thay đổi giá trị này để điều chỉnh kích thước */
        height: 50px; /* Thay đổi giá trị này để điều chỉnh kích thước */
        border-radius: 60%; /* Giữ nguyên để ảnh có hình tròn */
        object-fit: cover; /* Đảm bảo ảnh được cắt gọn nếu cần */
    }
</style>

<style>
    /* Toast message style */
    .toast-container {
        position: fixed;
        left: 20px; /* Vị trí bên trái */
        bottom: 20px; /* Vị trí bên dưới */
        z-index: 10000; /* Đảm bảo toast luôn hiển thị trên các phần tử khác */
        background-color: #333; /* Màu nền */
        color: #fff; /* Màu chữ */
        padding: 15px; /* Khoảng cách nội dung */
        border-radius: 5px; /* Góc bo tròn */
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* Đổ bóng để tạo hiệu ứng nổi nhẹ */
        opacity: 0; /* Bắt đầu với độ mờ là 0 */
        transition: opacity 0.3s ease-in-out; /* Chuyển đổi độ mờ mượt mà */
    }

    /* Hiệu ứng hiển thị */
    .toast-container.show {
        opacity: 1;
    }
</style>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
<script>
    function showAlert(message, ma, tong, infor, ngay, id) {
        if (message === "notmoney") {
            Swal.fire({
                title: 'Tài khoản của bạn không đủ tiền',
                html: `Vui lòng nạp tiền <a href="home" style="color: blue;">Tại Đây</a>`,

                confirmButtonText: 'OK'
            });
        }

        if (message === "QuantityError") {
            Swal.fire({
                title: 'lỗi số lượng vui lòng order lại',

                confirmButtonText: 'OK'
            });
        }
        if (message === "Thanh Toan Thanh Cong") {
            Swal.fire({
                title: 'Thanh Toan Thanh Cong',
                html: 'Mã giao dịch: ' + ma +
                        '<br> Số tiền: ' + tong + 'VNĐ' +
                        '<br> Mô tả giao dịch: ' + infor +
                        '<br> Thời gian thanh toán:: ' + ngay +
                        '<br> Xem lịch sử nạp tiền <a href="payment" style="color: blue;">Tại Đây</a>',

                confirmButtonText: 'OK'
            });
        }

        if (message === "Thanh Toan khong Thanh Cong") {
            Swal.fire({
                title: 'Thanh Toan khong Thanh Cong',

                confirmButtonText: 'OK'
            });
        }
        if (message === "Thanh toán đã được xử lý trước đó") {
            Swal.fire({
                title: 'Thanh toán đã được xử lý trước đó',

                confirmButtonText: 'OK'
            });
        }
        if (message === "xac nhan") {
            Swal.fire({
                title: 'Đơn hàng đã được ghi nhận',
                html: 'Theo dõi thông tin <a href="order_return?id=' + id + '"style="color: blue;">Tại Đây</a>',
                confirmButtonText: 'OK'
            });

        }
    }


</script>
<script>
    document.getElementById('profile-menu').addEventListener('click', function (event) {
        event.preventDefault();
        const dropdownMenu = this.querySelector('.dropdown-menu');
        dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
    });
</script>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <title>Cyborg - Awesome HTML5 Template</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-cyborg-gaming.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">

        <!--
        
        TemplateMo 579 Cyborg Gaming
        
        https://templatemo.com/tm-579-cyborg-gaming
        
        -->
    </head>

    <body>
        <c:if test="${requestScope.mess != null}">
            <script>
                showAlert("${requestScope.mess}", "${requestScope.ma}", "${requestScope.tong}", "${requestScope.infor}", "${requestScope.ngay}", "${requestScope.idQueue}");
            </script>
        </c:if>
        <c:if test="${sessionScope.mess != null}">
           
            <script>
                showAlert("${sessionScope.mess}", "${sessionScope.ma}", "${sessionScope.tong}", "${sessionScope.infor}", "${sessionScope.ngay}", "${sessionScope.idQueue}");
            </script>
        </c:if>
        <c:if test="${sessionScope.checkout != null}">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            <script>
                        $(document).ready(function () {
                            // Function to show toast message
                            function showToast(message) {
                                // Tạo phần tử toast
                                var toast = $('<div class="toast-container">' + message + '</div>');

                                // Thêm toast vào body
                                $('body').append(toast);

                                // Hiển thị toast với hiệu ứng
                                setTimeout(function () {
                                    toast.addClass('show');
                                }, 100); // Đợi 100ms để đảm bảo phần tử được render trước khi thêm lớp 'show'

                                // Ẩn toast sau 5 giây
                                setTimeout(function () {
                                    toast.removeClass('show');
                                    setTimeout(function () {
                                        toast.remove(); // Loại bỏ toast khỏi DOM sau khi hoàn thành hiệu ứng
                                    }, 300); // Thời gian của hiệu ứng
                                }, 3000); // 5 giây
                            }

                            // Kiểm tra nếu message là 'error' và hiển thị toast
                            if ("${sessionScope.checkout}" === "success") {
                                showToast("Đã xác nhận đơn hàng mã số: " + ${sessionScope.idQueue});
                            }

                        });
            </script>
        </c:if>
                <% 
                  session.removeAttribute("mess");
                  session.removeAttribute("checkout");
                  session.removeAttribute("idQueue");
                  %>

        <!-- ***** Preloader Start ***** -->
        <div id="js-preloader" class="js-preloader">
            <div class="preloader-inner">
                <span class="dot"></span>
                <div class="dots">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
        </div>
        <!-- ***** Preloader End ***** -->

        <!-- ***** Header Area Start ***** -->
        <header class="header-area header-sticky">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <nav class="main-nav">
                            <!-- ***** Logo Start ***** -->
                            <a href="home" class="logo">
                                <img src="assets/images/logo.png" alt="">
                            </a>
                            <!-- ***** Logo End ***** -->
                            <!-- ***** Search End ***** -->
                            <div class="search-input">
                                <form id="search" action="#">
                                    <input oninput="searchName(this)" type="text" id='searchText' name="searchKeyword" />
                                    <i class="fa fa-search"></i>
                                </form>
                            </div>
                            <!-- ***** Search End ***** -->
                            <!-- ***** Menu Start ***** -->
                            <ul class="nav">

                                <c:if test="${sessionScope.account != null}">

                                    <c:if test="${sessionScope.account.roleId == 1   }">
                                        <li><a href="${pageContext.request.contextPath}/Chung">Admin</a></li>
                                        </c:if>

                                    <c:set var="a" value="${requestScope.a}"  />
                                    <li><a >Ví : <fmt:formatNumber value="${a.money}" type="number" groupingUsed="true"/>VNĐ</a></li> 

                                    <li id="profile-menu" class="list-group-item">
                                        <a href="profile.jsp">
                                            ${sessionScope.account.name}
                                            <img src="${sessionScope.account.img}" alt="Profile Picture" class="profile-picture">
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li><a href="profile.jsp">Thay đổi thông tin</a></li> 
                                            <li><a href="vnpay_pay.jsp">Nạp Tiền</a></li>
                                            <li><a href="order" >Xem lịch sử giao dịch</a></li>
                                            <li><a href="logout">Đăng xuất</a></li>
                                        </ul>
                                    </li>

                                </c:if>
                                <c:if test="${sessionScope.account == null   }">
                                    <li><a href="register.jsp">Sign up</a></li>
                                    <li><a href="login">Login</a></li>
                                    <li><a></a></li>
                                </c:if>
                            </ul>   
                            <a class='menu-trigger'>
                                <span>Menu</span>
                            </a>

                            <!-- ***** Menu End ***** -->
                        </nav>
                    </div>
                </div>
            </div>
        </header>
        <!-- ***** Header Area End ***** -->

        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="page-content">

                        <!-- ***** Banner Start ***** -->
                        <div class="main-banner">
                            <div class="row">
                                <div class="col-lg-7">
                                    <div class="header-text">
                                        <h6>Welcome To Cyborg</h6>
                                        <h4><em>Buy</em> Our Popular     Card Here</h4>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- ***** Banner End ***** -->


                        <!-- ***** Most Popular Start ***** -->
                        <div class="most-popular">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="heading-section">
                                        <h4>Thẻ điện thoại</h4>
                                        <div class="d-flex">
                                            <div class="ms-auto">
                                                <select id="providerFilter" class="form-select" style="width: auto; margin-left: 20px;" onchange="filterProducts()">
                                                    <option value="0">Nhà mạng</option>
                                                    <c:forEach items="${requestScope.cates}" var="c">  
                                                        <option value="${c.id}">${c.name}</option>
                                                    </c:forEach>
                                                </select>

                                            </div>
                                            <select id="priceFilter" class="form-select" style="width: auto;" onchange="filterProducts()">
                                                <option value="">Sắp xếp</option>
                                                <option value="cheapest">Rẻ nhất</option>
                                                <option value="expensive">Đắt nhất</option>
                                            </select>

                                        </div>
                                    </div>
                                    <div  class="row">
                                        <!-- Các mục phổ biến -->
                                        <div id="content" class="row">
                                            <c:forEach items="${requestScope.products}" var="p">    
                                                <div class="col-lg-3 col-sm-6">
                                                    <form action="add-to-cart" method="get" onsubmit="return showPopup(${p.id})">
                                                        <div class="item">
                                                            <img src="img/${p.img}" alt="" width="150" height="150">
                                                            <h4>${p.name}<br><span>Số Lượng</span></h4>
                                                            <ul>
                                                                <li><i class="fa fa-money-bill-1"></i> <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true" /> VNĐ</li>
                                                                <li><i class="fa fa-"></i>${p.quanlity}</li> 
                                                            </ul>
                                                            <br/>
                                                            <input class="btn btn-success" style="border: cornsilk; margin-top: 60px; margin-left: 20px" type="submit" value="Buy">
                                                        </div>
                                                    </form>
                                                    <div id="namePopup${p.id}" class="popup-overlay">
                                                        <div class="popup-content">
                                                            <form action="add-order" method="get">
                                                                <input type="hidden" name="pId" value="${p.id}">
                                                                <div class="popup-header">
                                                                    <h4>Thông tin sản phẩm</h4>
                                                                    <button type="button" class="close-button" onclick="hidePopup(${p.id})">&times;</button>
                                                                </div>
                                                                <div class="popup-body">
                                                                    <label for="pname">Tên:</label>
                                                                    <input type="text" id="pname" name="pName" value="${p.name}" readonly="">
                                                                    <br>
                                                                    <label for="pprice">Giá:</label>
                                                                    <input type="text" id="pprice" name="pPrice" value="<fmt:formatNumber value="${p.price}" type="number" groupingUsed="true" /> VNĐ" readonly="">
                                                                    <br>
                                                                    <label for="pquanlity">Số lượng:</label>
                                                                    <input type="number" id="pquanlity" name="pQuanlity" min="1" max="${p.quanlity}" required>
                                                                </div>
                                                                <div class="popup-footer">
                                                                    <button type="submit" class="btn btn-primary">Submit</button>
                                                                    <button type="button" class="btn btn-secondary" onclick="hidePopup(${p.id})">Close</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <!-- Nút phân trang -->
                                        <div class="col-lg-12">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination justify-content-end">
                                                    <c:forEach  begin="1" end="${endP}" var="i">   
                                                        <li class="page-item">
                                                            <a class="page-link" onclick="paging${i}()">${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </nav>
                                        </div>

                                        <div class="col-lg-12">
                                            <div class="main-button">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- ***** Most Popular End ***** -->
                        <!-- *****pup op buy ***** -->



                        <!-- ***** Gaming Library Start ***** -->
                        <div class="gaming-library">
                            <div class="col-lg-12">
                                <div class="heading-section">
                                    <a href="<c:url value='/FeedBackController'/>" class="btn btn-danger btn-lg" onclick="checkLogin(event)">
                                        Báo cáo sự cố
                                    </a>
                                </div>
                            </div>
                        </div>

                        <script>
                            function checkLogin(event) {
                                event.preventDefault(); // Ngăn chặn hành động mặc định của liên kết

                                // Kiểm tra trạng thái đăng nhập của người dùng
                                var isLoggedIn = '<%= session.getAttribute("account") != null %>';

                                if (isLoggedIn) {
                                    window.location.href = '<c:url value="/FeedBackController"/>';
                                } else {
                                    window.location.href = '<c:url value="/login.jsp"/>';
                                }
                            }
                        </script>


                        <!-- ***** Gaming Library End ***** -->
                    </div>
                </div>
            </div>
        </div>

        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <p>Copyright © 2036 <a href="#">Cyborg Gaming</a> Company. All rights reserved. 

                            <br>Design: <a href="https://templatemo.com" target="_blank" title="free CSS templates">TemplateMo</a></p>
                    </div>
                </div>
            </div>
        </footer>


        <!-- Scripts -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script>
                            var endP = ${endP};
                            for (let i = 1; i <= endP; i++) {
                                window['paging' + i] = function () {
                                    var searchValue = document.getElementById('searchText').value;
                                    var cateF = document.getElementById('providerFilter').value;
                                    var priceF = document.getElementById('priceFilter').value;
                                    $.ajax({
                                        url: "/SWP/paging?page=" + i + "&key=" + encodeURIComponent(searchValue) + "&cateid=" + cateF + "&sort=" + priceF,
                                        type: "get",
                                        success: function (data) {
                                            var row = document.getElementById("content");
                                            row.innerHTML = data;
                                        }
                                    });
                                };
                            }

        </script>
        <script>
            function searchName(param) {
                var txtS = param.value;
                $.ajax({
                    url: "/SWP/search?key=" + txtS,
                    type: "get",

                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;
                    }
                });

            }
        </script>
        <script>
            function filterProducts() {
                var cateF = document.getElementById('providerFilter').value;
                var priceF = document.getElementById('priceFilter').value;
                var searchValue = document.getElementById('searchText').value;
                $.ajax({
                    url: "/SWP/sort?cateid=" + cateF + "&sort=" + priceF + "&key=" + searchValue,
                    type: "get",

                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;
                    }
                });

            }
            function goHome() {
                window.location.href = '/SWP/home';
            }
        </script>
        <script>
            function showPopup(id) {
                document.getElementById("namePopup" + id).style.display = 'flex';
                return false;
            }

            function hidePopup(id) {
                document.getElementById("namePopup" + id).style.display = 'none';
            }
        </script>



        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

        <script src="assets/js/isotope.min.js"></script>
        <script src="assets/js/owl-carousel.js"></script>
        <script src="assets/js/tabs.js"></script>
        <script src="assets/js/popup.js"></script>
        <script src="assets/js/custom.js"></script>


    </body>

</html>
