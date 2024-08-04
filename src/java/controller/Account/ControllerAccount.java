/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Account;

import DAO.AdminDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Categories;
import model.Product;

/**
 *
 * @author -MSI-
 */
public class ControllerAccount extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerAccount</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerAccount at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Kiểm tra session để đảm bảo người dùng đã đăng nhập và có vai trò là admin
    HttpSession session = request.getSession(false); // false để không tạo session mới nếu chưa có
    if (session != null && session.getAttribute("account") != null) {
        Account account = (Account) session.getAttribute("account");
        if (account.getRoleId() == 1) { // Kiểm tra roleId có phải là admin không (ví dụ roleId của admin là 1)
            AdminDao dao = new AdminDao();
            List<Account> listUsers = null;
            int page = 1;
            int maxPage = 1;
            List<Account> listAdmin = dao.getAllAdmin();
            // Xử lý cho phần tài khoản người dùng
            String email = request.getParameter("EmailSearch");
            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                try {
                    page = Integer.parseInt(pageStr);
                } catch (NumberFormatException e) {
                    page = 1;
                }
            }

            if (email == null || email.trim().isEmpty()) {
                listUsers = dao.getUsersByPage(page);
                maxPage = dao.getmaxPageAccount();
            } else {
                listUsers = dao.searcAccountsByPage(email, page);
                maxPage = dao.getMaxPageForSearch(email);
            }
         
            int totalorder = dao.getOrder();
            int totalprice = dao.getTotalOrderAmount();
            int totalquality = dao.getTotalQuality();
            // Đặt các thuộc tính cho request
            request.setAttribute("listUsers", listUsers);
             request.setAttribute("listAdmin", listAdmin);
            request.setAttribute("currentPage", page);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("EmailSearch", email);
             request.setAttribute("totalorder", totalorder);
             request.setAttribute("totalprice", totalprice);
             request.setAttribute("totalquality", totalquality);
 
            // Chuyển tiếp đến trang JSP
            request.getRequestDispatcher("ManagerAccount.jsp").forward(request, response);
        } else {
            // Không phải admin, chuyển hướng về trang đăng nhập
            response.sendRedirect("login");
        }
    } else {
        // Chưa đăng nhập, chuyển hướng về trang đăng nhập
        response.sendRedirect("login");
    }
}



    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
           AdminDao dao = new AdminDao();
    List<Account> listUsers = dao.getUsersByPage(1);

    for (Account user : listUsers) {
        String statusParam = request.getParameter("status_" + user.getId());
         String priceParam = request.getParameter("name_" + user.getId());
         boolean newStatus = statusParam != null; 
        // Debugging prints
        System.out.println("User ID: " + user.getId());
        System.out.println("Status Param: " + statusParam);
        System.out.println("New Status: " + newStatus);
        int price = 0;
        if (priceParam != null && !priceParam.trim().isEmpty()) {
            try {
                // Loại bỏ ký tự không phải số như " VNĐ" trước khi chuyển đổi
                price = Integer.parseInt(priceParam.replaceAll("[^0-9]", ""));
            } catch (NumberFormatException e) {
                // Xử lý ngoại lệ nếu giá trị không thể chuyển đổi thành số nguyên
                e.printStackTrace();
            }
        }
        dao.updateStatus(user.getId(), newStatus, price);
    }

    response.sendRedirect("controllerAccount");
    
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
