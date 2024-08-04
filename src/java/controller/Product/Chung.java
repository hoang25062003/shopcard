/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Product;

import DAO.AdminDao;
import DAO.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Categories;
import model.Product;

/**
 *
 * @author admin
 */
@WebServlet(name = "Chung", urlPatterns = {"/Chung"})
public class Chung extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
                HttpSession session = request.getSession(false); // false để không tạo session mới nếu chưa có
        if (session != null && session.getAttribute("account") != null) {
            Account account = (Account) session.getAttribute("account");
            if (account.getRoleId() == 1) { // Kiểm tra roleId có phải là admin không (ví dụ roleId của admin là 1)
        ProductDao productDAO = new ProductDao();

        List<Product> bestSellingProducts = productDAO.getBestSellingProducts(10);
        request.setAttribute("bestSellingProducts", bestSellingProducts);

        // Retrieve the current page number from the request parameter
        String pageStr = request.getParameter("page");
        int currentPage = pageStr == null ? 1 : Integer.parseInt(pageStr);

        // Get the total number of purchase orders to calculate total pages
        int totalOrders = productDAO.getTotalProductIn(); // Assuming this returns the total number of products/orders
        int ordersPerPage = 10;
        int totalPages = (int) Math.ceil((double) totalOrders / ordersPerPage);

        // Retrieve the list of purchase orders for the current page
        List<Object[]> purchaseOrders = productDAO.getAllPurchaseOrders(currentPage);

        // Convert the list of purchase orders to a List<Map<String, Object>>
        List<Map<String, Object>> purchaseOrdersList = new ArrayList<>();
        for (Object[] order : purchaseOrders) {
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("OrderID", order[0]);
            orderMap.put("BuyerName", order[1]);
            orderMap.put("ProductName", order[2]);
            orderMap.put("Price", order[3]);
            orderMap.put("Quantity", order[4]);
            orderMap.put("Total", order[5]);
            orderMap.put("Img", order[6]);
            orderMap.put("CreatedAt", order[7]);
            purchaseOrdersList.add(orderMap);
        }
 AdminDao dao = new AdminDao();
        // Set attributes for the request to pass to the JSP
        int totalorder = dao.getOrder();
            int totalprice = dao.getTotalOrderAmount();
            int totalquality = dao.getTotalQuality();
        request.setAttribute("purchaseOrders", purchaseOrdersList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
                request.setAttribute("totalorder", totalorder);
             request.setAttribute("totalprice", totalprice);
             request.setAttribute("totalquality", totalquality);
        request.getRequestDispatcher("/DashBoard.jsp").forward(request, response);
            } else {
                // Không phải admin, chuyển hướng về trang đăng nhập
                response.sendRedirect("login");
            }
        } else {
            // Chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("login");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
