<%-- 
    Document   : paymentHistory
    Created on : Jul 17, 2024, 9:05:29 PM
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
        <title>JSP Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
            }

            .card {
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .container {
                max-width: 900px;
                margin: 20px auto;
            }

            .card-header {
                padding: 20px;
                border-bottom: 1px solid #e9ecef;
            }

            .card-header h5 {
                margin: 0;
                font-size: 18px;
                font-weight: bold;
            }

            .card-header small {
                color: #6c757d;
                font-size: 14px;
            }

            .avatar.sm {
                width: 2.25rem;
                height: 2.25rem;
                font-size: .818125rem;
            }


            .table {
                width: 100%;
                margin-bottom: 0;
            }

            .table th {
                background-color: #f1f3f9;
                color: #6c757d;
                font-weight: 600;
                text-transform: uppercase;
                font-size: 12px;
                padding: 12px 20px;
            }

            .table td {
                padding: 15px 20px;
                vertical-align: middle;
                border-bottom: 1px solid #e9ecef;
            }

            .table tr:last-child td {
                border-bottom: none;
            }

            .card-footer {
                padding: 15px 20px;
                background-color: #ffffff;
                border-top: 1px solid #e9ecef;
            }

            .btn {
                color: #007bff;
                text-decoration: none;
                font-weight: 600;
            }

            .btn:hover {
                text-decoration: underline;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-12 mb-3 mb-lg-5">
                    <div class="position-relative card table-nowrap table-card">
                        <div class="card-header align-items-center">
                            <h5 class="mb-0">Lịch sử giao dịch</h5>
                        </div>
                        <div class="table-responsive">
                            <table class="table mb-0">
                                <thead class="small text-uppercase bg-body text-muted">
                                    <tr>
                                        <th>Mã đơn hàng</th>
                                        <th>Tổng tiền thanh toán</th>
                                        <th>Thông tin giao dịch</th>
                                        <th>Ngày mua</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="payment" items="${pay}">
                                        <tr class="align-middle">
                                            <td>
                                                ${payment.txnref}
                                            </td>
                                            <td><fmt:formatNumber value="${payment.amount}" type="number" groupingUsed="true"/> VNĐ</td>
                                            <td>${payment.orderInfor}</td>
                                            <td>
                                                <div class="d-flex align-items-center">
                                                    <span>${payment.payDate}</span>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer">
                            <div style="display: flex; justify-content: space-between; align-items: center;">
                                <a href="order" class="btn btn-gray">Xem lịch sử đơn hàng</a>
                                <a href="home" class="btn btn-gray">Back to Home</a>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
