/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import DAO.AdminDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.AccountWithTotal;
import model.Categories;
import model.Product;

/**
 *
 * @author -MSI-
 */
@WebServlet(urlPatterns={"/favoriteAccount"})
public class FavoriteAccountServlet extends HttpServlet {
   
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
            out.println("<title>Servlet FavoriteAccountServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FavoriteAccountServlet at " + request.getContextPath () + "</h1>");
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
            List<AccountWithTotal> listUsers = null;
            int page = 1;
            int maxPage = 1;

            // Xử lý cho phần tài khoản người dùng
            String email = request.getParameter("EmailSearch");
            String pageStr = request.getParameter("page");
            String viewAll = request.getParameter("viewAll");
            if (pageStr != null) {
                try {
                    page = Integer.parseInt(pageStr);
                } catch (NumberFormatException e) {
                    page = 1;
                }
            }

            if (email == null || email.trim().isEmpty()) {
                listUsers = dao.getAccountWithTotalsByPage(page);
                maxPage = dao.getmaxPageAccountWithTotals();
            } else {
                listUsers = dao.searchAccountWithTotalsByPage(email, page);
                maxPage = dao.getmaxPageAccountWithTotalsbySearch(email);
            }
            if (viewAll != null && viewAll.equals("true")) {
                    listUsers = dao.getAccountWithTotals();
                    maxPage = 1;
                }
            int totalorder = dao.getOrder();
            int totalprice = dao.getTotalOrderAmount();
            int totalquality = dao.getTotalQuality();
            // Đặt các thuộc tính cho request
            request.setAttribute("listUsers", listUsers);
            request.setAttribute("currentPage", page);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("EmailSearch", email);
             request.setAttribute("totalorder", totalorder);
             request.setAttribute("totalprice", totalprice);
             request.setAttribute("totalquality", totalquality);
 
            // Chuyển tiếp đến trang JSP
            request.getRequestDispatcher("FavoriteAccount.jsp").forward(request, response);
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
        processRequest(request, response);
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
