/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package DAO;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;
/**
 *
 * @author -MSI-
 */
public class SetAccountAdmin extends HttpServlet {
   
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
            out.println("<title>Servlet SetAccountAdmin</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SetAccountAdmin at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    AdminDao dao = new AdminDao();
    List<Account> listC = dao.getAllAdmin();

    for (Account admin : listC) {
        String nameParam = request.getParameter("nameadmin_" + admin.getId());
        String moneyParam = request.getParameter("money_" + admin.getId());

        int money = 0;
        if (moneyParam != null && !moneyParam.trim().isEmpty()) {
            try {
                // Loại bỏ ký tự không phải số như " VNĐ" trước khi chuyển đổi
                money = Integer.parseInt(moneyParam.replaceAll("[^0-9]", ""));
            } catch (NumberFormatException e) {
                // Xử lý ngoại lệ nếu giá trị không thể chuyển đổi thành số nguyên
                e.printStackTrace();
            }
        }

        dao.updateAdmin(admin.getId(), money, nameParam);
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
