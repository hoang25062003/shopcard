<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="de_DE"/>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import=" java.util.Locale" %>
<%@ page import=" java.text.NumberFormat" %>

<style>
    .details {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    .details th, .details td {
        padding: 8px;
        text-align: left;
        border: 1px solid #ddd;
    }
    .details th {
        background-color: #f2f2f2;
    }
    .details tr:hover {
        background-color: #f5f5f5;
    }

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
        text-align: center;
        margin-top: 20px;
    }

    .top-sales .title {
        font-size: 1.5em;
        margin-bottom: 20px;
        color: #333;
    }

    .top-sales ul {
        list-style-type: none;
        padding: 0;
    }

    .top-sales ul li {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
    }

    .top-sales ul li img {
        width: 50px;
        height: auto;
        margin-right: 10px;
    }

    .top-sales ul li .product {
        flex: 1;
    }

    .top-sales ul li .price {
        font-weight: bold;
    }

    .box-topic {
        font-size: 1.2em;
        margin-bottom: 5px;
        color: #333;
    }

    .box .number {
        font-size: 2em;
        color: #333;
    }

    .box .indicator {
        display: flex;
        align-items: center;
    }

    .box .indicator i {
        font-size: 1.2em;
        color: #28a745;
        margin-right: 5px;
    }

    .box .indicator .text {
        font-size: 0.9em;
        color: #333;
    }

    .pagination {
        text-align: center;
        margin-top: 20px;
    }

    .pagination a {
        color: #007bff;
        padding: 10px 20px;
        text-decoration: none;
        margin: 0 5px;
        border: 1px solid #007bff;
        border-radius: 5px;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    .pagination a:hover {
        background-color: #007bff;
        color: #fff;
    }

    .pagination .current-page {
        padding: 10px 20px;
        margin: 0 5px;
        border: 1px solid #007bff;
        border-radius: 5px;
        background-color: #007bff;
        color: #fff;
        text-decoration: none;
    }
</style>

<html lang="en" dir="ltr">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta charset="UTF-8" />
        <title>Responsive Admin Dashboard | MU</title>
        <link rel="stylesheet" href="assets/style.css" />
        <!-- Boxicons CDN Link -->
        <link href="https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css" rel="stylesheet" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    </head>
    <body>
        <div class="sidebar">
            <div class="logo-details">
                <i class="bx bxl-c-plus-plus"></i>
                <span class="logo_name">Cyborg</span>
            </div>
            <ul class="nav-links">
            <li>
                <a href="Chung" class="active">
                    <i class="bx bx-grid-alt"></i>
                    <span class="links_name">Chung</span>
                </a>
            </li>
            <li>
                <a href="adminProduct" >
                    <i class="bx bx-box"></i>
                    <span class="links_name">Sản phẩm</span>
                </a>
            </li>

            <li>
                <a href="controllerAccount">
                    <i class="bx bx-user"></i>
                    <span class="links_name">View List Account</span>
                </a>
            </li>
            <li>
                <a href="FeedBackAdmin" >
                    <i class="bx bx-message"></i>
                    <span class="links_name">Báo Cáo Sự Cố</span>
                </a>
            </li>               
            <li>
                    <a href="home">
                        <i class="bx bx-list-ul"></i>
                        <span class="links_name">Home</span>
                    </a>
                </li>
            <li class="log_out">
                <a href="logout">
                    <i class="bx bx-log-out"></i>
                    <span class="logout" >Log out</span>
                </a>
            </li>
            </ul>
        </div>
        <section class="home-section">
            <nav>
                <div class="sidebar-button">
                    <i class="bx bx-menu sidebarBtn"></i>
                    <span class="dashboard">Dashboard</span>
                </div>
                <div class="search-box">
                    <input type="text" placeholder="Search..." />
                    <i class="bx bx-search"></i>
                </div>
            <div class="profile-details">
                <img src="${account.getImg()}" alt="" />
                <span class="admin_name">${account.getName()}</span>
                <i class="bx bx-chevron-down"></i>
            </div>
            </nav>

            <div class="home-content">
                <div class="overview-boxes">

                    <div class="box">

                        <div class="right-side">
                            <div class="box-topic">Tổng số đơn hàng</div>
                                <div class="number">
         <div class="number">${totalorder} Đơn</div>
    </div>
                            <div class="indicator">
                                <i class="bx bx-up-arrow-alt"></i>
                                <span class="text">Up from yesterday</span>
                            </div>
                        </div>
                        <i class="bx bx-cart-alt cart"></i>
                    </div>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Tổng số thẻ đã bán</div>
                            <div class="number">${totalquality} thẻ</div>
                            
                            <div class="indicator">
                                <i class="bx bx-up-arrow-alt"></i>
                                <span class="text">Up from yesterday</span>
                            </div>
                        </div>
                        <i class="bx bxs-cart-add cart two"></i>
                    </div>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Tổng doanh thu</div>
                            <div class="number">${totalprice} K</div>
                            <div class="indicator">
                                <i class="bx bx-up-arrow-alt"></i>
                                <span class="text">Up from yesterday</span>
                            </div>
                        </div>
                        <i class="bx bx-cart cart three"></i>
                    </div> 
                </div>
                <div class="sales-boxes">
                    <div class="recent-sales box">
                        <div class="title">Danh sách mua hàng</div>
                        <div class="sales-details">
                            <table class="details">
                                <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Customer</th>
                                        <th>Order ID</th>
                                        <th>Product</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Total</th>
                                        <th>Image</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% 
                                        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN")); 
                                        List<Map<String, Object>> purchaseOrders = (List<Map<String, Object>>) request.getAttribute("purchaseOrders");
                                        for (Map<String, Object> order : purchaseOrders) { 
                                            String formatPrice = formatter.format(order.get("Price"));
                                            String formatTotal = formatter.format(order.get("Total"));
                                    %>
                                    <tr>
                                        <td><a href="#"><%= order.get("CreatedAt") %></a></td>
                                        <td><a href="#"><%= order.get("BuyerName") %></a></td>
                                        <td><a href="#"><%= order.get("OrderID") %></a></td>
                                        <td><a href="#"><%= order.get("ProductName") %></a></td>
                                        <td><a href="#"><%= formatPrice %></a> VND</td>
                                        <td><a href="#"><%= order.get("Quantity") %></a></td>
                                        <td><a href="#"><%= formatTotal %></a> VND</td>
                                        <td><img src="img/<%= order.get("Img") %>" alt="Product Image" style="width: 100px; height: auto;"></td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>

                        <div class="pagination">
                            <table>
                                <tr>
                                    <td colspan="8" style="text-align: center;">
                                        <c:if test="${currentPage > 1}">
                                            <a href="?page=${currentPage - 1}">Previous</a>
                                        </c:if>
                                        <span class="current-page">Page ${currentPage} of ${totalPages}</span>
                                        <c:if test="${currentPage < totalPages}">
                                            <a href="?page=${currentPage + 1}">Next</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <div class="button">
                            <a href="excel">Tải xuống file excel</a>
                        </div>
                    </div>

                    <div class="top-sales box">
                        <div class="title">Sản phẩm bán chạy nhất</div>
                        <ul class="top-sales-details">
                            <c:forEach items="${bestSellingProducts}" var="product">
                                <li>
                                    <a href="#">
                                        <img src="img/${product.img}" alt="${product.name}" />
                                        <span class="product">${product.name}</span>
                                    </a>
                                    <span class="price">${product.price}</span> 
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </section>

        <script>
            let sidebar = document.querySelector(".sidebar");
            let sidebarBtn = document.querySelector(".sidebarBtn");
            sidebarBtn.onclick = function () {
                sidebar.classList.toggle("active");
                if (sidebar.classList.contains("active")) {
                    sidebarBtn.classList.replace("bx-menu", "bx-menu-alt-right");
                } else
                    sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
            };
        </script>
    </body>
</html>
