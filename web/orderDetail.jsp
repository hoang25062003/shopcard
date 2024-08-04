<%-- 
    Document   : orderDetail
    Created on : Jul 6, 2024, 3:32:05 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="de_DE"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-12 mb-3 mb-lg-5">
                    <div class="position-relative card table-nowrap table-card">
                        <div class="card-header align-items-center">
                            <h5 class="mb-0">Chi tiết đơn hàng</h5>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table mb-0">
                                    <tbody>
                                        <tr>
                                            <th>Tên sản phẩm</th>
                                    <img src="img/${product.img}" alt="" style="width: 300px"/>
                                    <td>${product.name}</td>
                                    </tr>
                                    <tr>
                                        <th>Số lượng</th>
                                        <td>${order.quanlity}</td>
                                    </tr>
                                    <tr>
                                        <th>Tổng thanh toán</th>
                                        <td><fmt:formatNumber value="${order.total}" type="number" groupingUsed="true"/> VNĐ</td>
                                    </tr>
                                    <tr>
                                        <th>Ngày mua</th>
                                        <td>${order.createdAt}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12 mb-3 mb-lg-5">
                    <div class="position-relative card table-nowrap table-card">

                        <div class="table-responsive">
                            <table class="table mb-0">
                                <thead class="small text-uppercase bg-body text-muted">
                                    <tr>
                                        <th>ID</th>
                                        <th>Số Seri</th>
                                        <th>Pin</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="detail" items="${orderDetail}">
                                        <tr class="align-middle">
                                            <td>${detail.id}</td>
                                            <td>${detail.seri}</td>
                                            <td>${detail.pin}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="text-center mt-4 mb-4">
                        <a href="${pageContext.request.contextPath}/order" class="btn btn-primary">Quay về lịch sử đơn hàng</a>
                    </div>
                </div>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>