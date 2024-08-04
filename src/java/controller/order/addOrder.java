/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.order;

import DAO.AccountDao;
import DAO.OrderDao;
import DAO.OrderDetailDao;
import DAO.ProductDao;
import DAO.ProductDetailDao;
import background.OrderQueue;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import model.Account;
import model.Order;
import model.Product;
import model.ProductDetail;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOut", urlPatterns = {"/add-order"})
public class addOrder extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOut at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            request.getRequestDispatcher("login").forward(request, response);
        } else {
            AccountDao aDao = new AccountDao();
            Account a = aDao.getAccountById(account.getId());
            String id_raw = request.getParameter("pId");
            String quanlity_raw = request.getParameter("pQuanlity");
            ProductDao pDao = new ProductDao();
            ProductDetailDao dDao = new ProductDetailDao();
            OrderDao oDao = new OrderDao();
            OrderDetailDao oDDao = new OrderDetailDao();

            try {
                int id = Integer.parseInt(id_raw);
                int quanlity = Integer.parseInt(quanlity_raw);
                Product p = pDao.getProductById(id);
                int total = p.getPrice() * quanlity;
                if (a.getMoney() < total) {
                    request.setAttribute("mess", "notmoney");
                    request.getRequestDispatcher("home").forward(request, response);
                } else if (p.getQuanlity() < quanlity) {
                    request.setAttribute("mess", "QuantityError");
                    request.getRequestDispatcher("home").forward(request, response);
                } else {
                    //addOrder vào database với status = 0
                    oDao.addOrder(a.getId(), id, quanlity, total);
                    int idQueue = oDao.getIdLastOrder();
                    
                    OrderQueue.queue.add(idQueue);

//                    Map<Integer, List<ProductDetail>> orderMap = new HashMap<>();//lưu list các orderdetail 
//                    // xử lý từng Order
//                    while (!listOrder.isEmpty()) {
//                        Order order = listOrder.poll();
//                        
//                        aDao.updateMoney(a, a.getMoney() - order.getTotal()); 
//                        List<ProductDetail> list = dDao.getProductDetail(order.getProductId(), quanlity);
//                        for (ProductDetail productDetail : list) {
//                            dDao.delProductDetailById(productDetail.getId());
//                            oDDao.addOrderDetail(order.getId(), productDetail.getSeri(), productDetail.getPin());
//                        }
//                        
//                        pDao.updateQuantity(order.getProductId(), pDao.getProductById(order.getProductId()).getQuanlity()-quanlity);
//                        oDao.UpdateStatus(order.getId(), 1);
//
//                        orderMap.put(order.getId(), list);
//                    }
//                    //cái này test
//                    List<ProductDetail> listT = dDao.getProductDetail(31, 1);
//                    for (ProductDetail productDetail : listT) {
//                                dDao.delProductDetailById(productDetail.getId());
//                        }
//                    request.setAttribute("test", listT);
//                    //------
//                    request.setAttribute("orderMap", orderMap);
//
//                    request.getRequestDispatcher("abc.jsp").forward(request, response);
                    session.setAttribute("idQueue", idQueue);
                    session.setAttribute("checkout", "success");
                    session.setAttribute("mess", "xac nhan");
                    
                    response.sendRedirect("home");

                }

            } catch (NumberFormatException e) {
                request.getRequestDispatcher("error").forward(request, response);
            }

        }

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
