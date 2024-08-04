/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package vnpay;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import vnpay.Config;
import java.util.Iterator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.HashMap;
import DAO.AccountDao;
import DAO.VnpPaymentDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import model.Account;

/**
 *
 * @author Admin
 */
@WebServlet(name="vnpayReturn", urlPatterns={"/vnpay_return"})
public class vnpayReturn extends HttpServlet {

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
            out.println("<title>Servlet vnpayReturn</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet vnpayReturn at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountDao aDao = new AccountDao();
        VnpPaymentDao vDao = new VnpPaymentDao();
        Account a = aDao.getAccountById(account.getId());
        String amount_raw = request.getParameter("vnp_Amount");
        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
        String vnp_PayDate = request.getParameter("vnp_PayDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        
        try {
            Date parsedDate = dateFormat.parse(vnp_PayDate);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
        if(vDao.getPaymentBytxn(vnp_TxnRef) != null){
            request.setAttribute("mess", "Thanh toán đã được xử lý trước đó");
            request.getRequestDispatcher("home").forward(request, response);
            return;
        }
        //Begin process return from VNPAY
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }
        

        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = Config.hashAllFields(fields);
        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (signValue.equals(vnp_SecureHash)) {
            if (request.getParameter("vnp_ResponseCode").equals("00")) {
                int amount = Integer.parseInt(amount_raw) / 100;
                vDao.addVnpPayment(vnp_TxnRef,a.getId() , amount,vnp_OrderInfo , timestamp);
                aDao.updateMoney(a, amount + a.getMoney());
                NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
                String formatPrice  = formatter.format(amount);
                request.setAttribute("mess", "Thanh Toan Thanh Cong");
                request.setAttribute("ma", vnp_TxnRef);
                request.setAttribute("tong", formatPrice);
                request.setAttribute("infor", vnp_OrderInfo);
                request.setAttribute("ngay", timestamp);
                request.getRequestDispatcher("home").forward(request, response);
            } else {
                request.setAttribute("mess", "Thanh Toan khong Thanh Cong");
                request.getRequestDispatcher("home").forward(request, response);
            }
        } else {
            request.setAttribute("mess", "Thanh Toan khong Thanh Cong");
            request.getRequestDispatcher("home").forward(request, response);
        }
        
        } catch (ParseException e) {
            e.printStackTrace();
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
