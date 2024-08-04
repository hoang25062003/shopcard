<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="UTF-8" />
    <title>Responsive Admin Dashboard | MU</title>
    <link rel="stylesheet" href="assets/style.css" />
    <!-- Boxicons CDN Link -->
    <link href="https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css" rel="stylesheet" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
</style>
<style>
        /* Save button styles */
        .save2-button {
            display: inline-block;
            padding: 5px 10px; /* Adjusted padding for the new size */
            font-size: 1rem;
            font-weight: 600;
            color: #fff;
            background-color: #001f5b; /* Dark blue color */
            border: none;
            border-radius: 8px; /* Rounded corners */
            text-align: center;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
            width: 60px; /* Fixed width */
            height: 31px; /* Fixed height */
        }

        .save2-button:hover {
            background-color: #00163e; /* Slightly darker shade on hover */
        }

        .save2-button:focus {
            outline: none;
            box-shadow: 0 0 0 3px rgba(0, 31, 91, 0.25); /* Blue shadow on focus */
        }

        .save2-button:active {
            background-color: #000b2d; /* Even darker shade on active */
            box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.2); /* Inset shadow on active */
        }

        /* Add margin to separate the table and button */
        .button {
            margin-top: 10px;
        }

    </style>
     <style>
        .pagination a {
            margin: 0 5px;
            text-decoration: none;
            color: black;
        }
        .pagination a.current {
            text-decoration: underline;
        }
    </style>
    
</head>
<body>
    <div class="sidebar">
<!--        <div class="logo-details">
            <i class="bx bxl-c-plus-plus"></i>
            <span class="logo_name">MU</span>
        </div>-->
        <ul class="nav-links">

            <li>
                <a href="Chung">
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
                <a href="controllerAccount" >
                    <i class="bx bx-user"></i>
                    <span class="links_name">Danh sách tài khoản</span>
                </a>
            </li>
            <li>
                <a href="FeedBackAdmin" >
                    <i class="bx bx-message"></i>
                    <span class="links_name">Báo Cáo Sự Cố</span>
                </a>
            </li>
            <li>
                <a href="favoriteAccount" class="active">
                    <i class="bx bx-heart"></i>
                    <span class="links_name">Tài khoản yêu thích</span>
                </a>
            </li>               
            <li>
                    <a href="home">
                        <i class="bx bx-list-ul"></i>
                        <span class="links_name">Trang chủ</span>
                    </a>
                </li>
<!--                <li class="log_out">
                    <a href="logout">
                        <i class="bx bx-log-out"></i>
                        <span class="links_name"  >Log out</span>
                    </a>
                </li>-->
            <!-- Các liên kết khác -->
        </ul>
    </div>
    <section class="home-section">
        <nav>
            <div class="sidebar-button">
                <i class="bx bx-menu sidebarBtn"></i>
                <span class="dashboard">Dashboard</span>
            </div>
            <div class="search-box">
                <form action="favoriteAccount" method="get">
                     <input type="text" name ="EmailSearch" placeholder="Search..." />
                    <button type="submit"><i class="bx bx-search"></i></button>
                </form>
            </div>
            
        </nav>

        <!-- Table to display the data -->
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
                            <div class="number">${totalprice}.000 Đ</div>
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
                    <div class="title">Danh sách tài khoản yêu thích</div>
                        <form id="searchForm" action="favoriteAccount" method="get">
    <!-- Your search input fields -->
    <button type="button" class="ViewALL-button" onclick="viewAll()">View All</button>
</form>
                    <script>
    function viewAll() {
        const form = document.getElementById('searchForm');
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'viewAll';
        input.value = 'true';
        form.appendChild(input);
        form.submit();
    }
</script>
                    <div class="sales-details">
                        <!-- Check if listUsers is not empty -->
                        <c:if test="${not empty listUsers}">
                                                        <form action="favoriteAccount" method="post">
                                <table  class="details">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>UserName</th>
                                            <th>Email</th>
                                            <th>Name</th>
                                            <th>Phone</th>
                                            <th>Money</th>
                                            <th>Total Order Amount</th>
                                            <th>Total Money Account</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${listUsers}">
                                            <tr>
                                                <td>${user.id}</a></td>
                                                <td>${user.userName}</td>
                                                <td>${user.email}</td>
                                                <td>${user.name}</td>
                                                <td>${user.phone}</td>
                                                <td>${user.money}</td>
                                                <td>${user.totalOrderAmount}</td>
                                                <td>${user.totalAccount}</td>

                                        </c:forEach>
                                    </tbody>
                                </table>
                                                                                                <div class="pagination">
    <c:if test="${maxPage > 1}">
        <c:if test="${currentPage > 1}">
            <a href="?page=${currentPage - 1}&NameSearch=${NameSearch}">&lt;</a>
        </c:if>
        
        <c:choose>
            <c:when test="${maxPage > 4}">
                <c:forEach var="i" begin="${currentPage - 1}" end="${currentPage + 1}">
                    <c:if test="${i > 0 && i <= maxPage}">
                        <a href="?page=${i}&NameSearch=${NameSearch}" class="${currentPage == i ? 'current' : ''}">${i}</a>
                    </c:if>
                </c:forEach>
                <c:if test="${currentPage + 2 <= maxPage}">
                    <span>...</span>
                </c:if>
                <a href="?page=${maxPage}&NameSearch=${NameSearch}" class="${currentPage == maxPage ? 'current' : ''}">${maxPage}</a>
            </c:when>
            <c:otherwise>
                <c:forEach var="i" begin="1" end="${maxPage}">
                    <a href="?page=${i}&NameSearch=${NameSearch}" class="${currentPage == i ? 'current' : ''}">${i}</a>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        
        <c:if test="${currentPage < maxPage}">
            <a href="?page=${currentPage + 1}&NameSearch=${NameSearch}">&gt;</a>
        </c:if>
    </c:if>
</div>
                                <div class="button">
                                    <button type="submit" class="save2-button">Save</button> 
                                </div>
                            </form>
                        </c:if>
                        <c:if test="${empty listUsers}">
                            <p>No users found.</p>
                        </c:if>
                    </div>

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
