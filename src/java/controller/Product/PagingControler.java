/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Product;

import DAO.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "PagingControler", urlPatterns = {"/paging"})
public class PagingControler extends HttpServlet {

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
        PrintWriter out = response.getWriter();       
        int page = Integer.parseInt(request.getParameter("page"));
        String key = request.getParameter("key");             
         String cateid_raw = request.getParameter("cateid");
        int cateid = Integer.parseInt(cateid_raw);
        String sort = request.getParameter("sort"); 
        ProductDao PDao = new ProductDao();
        List<Product> products = PDao.searchArrangeIn(key, cateid, sort, page);
         NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        
        for (Product product : products) {   
        String formatPrice  = formatter.format(product.getPrice());
            out.println("<div class=\"col-lg-3 col-sm-6\">\n" +
"                                                    <form action=\"add-to-cart\" method=\"post\" onsubmit=\"return showPopup("+product.getId()+")\">\n" +
"                                                        <div class=\"item\">\n" +
"                                                            <img src=\"img/"+product.getImg()+"\" width=\"150\" height=\"150\" alt=\"\">\n" +
"                                                            <h4>"+product.getName()+"<br><span>Số Lượng</span></h4>\n" +
"                                                            <ul>\n" +
"                                                                <li><i class=\"fa fa-money-bill-1\"></i> "+formatPrice+"VNĐ</li>\n" +
"                                                                <li><i class=\"fa fa-\"></i>"+product.getQuanlity()+"</li> \n" +
"                                                            </ul>\n" +
"                                                            <br/>\n" +
"                                                            <input class=\"btn btn-success\" style=\"border: cornsilk; margin-top: 60px; margin-left: 20px\" type=\"submit\" value=\"Buy\">\n" +
"                                                        </div>\n" +
"                                                    </form>\n" +
"                                                    <div id=\"namePopup"+product.getId()+"\" class=\"popup-overlay\">\n" +
"                                                        <div class=\"popup-content\">\n" +
"                                                            <form action=\"add-order\" method=\"get\">\n" +
"                                                                <input type=\"hidden\" name=\"pId\" value=\""+product.getId()+"\">\n" +
"                                                                <div class=\"popup-header\">\n" +
"                                                                    <h4>Thông tin sản phẩm</h4>\n" +
"                                                                    <button type=\"button\" class=\"close-button\" onclick=\"hidePopup("+product.getId()+")\">&times;</button>\n" +
"                                                                </div>\n" +
"                                                                <div class=\"popup-body\">\n" +
"                                                                    <label for=\"pname\">Tên:</label>\n" +
"                                                                    <input type=\"text\" id=\"pname\" name=\"pName\" value=\""+product.getName()+"\" readonly=\"\">\n" +
"                                                                    <br>\n" +
"                                                                    <label for=\"pprice\">Giá:</label>\n" +
"                                                                    <input type=\"text\" id=\"pprice\" name=\"pPrice\" value=\""+formatPrice+"VNĐ\" readonly=\"\">\n" +
"                                                                    <br>\n" +
"                                                                    <label for=\"pquanlity\">Số lượng:</label>\n" +
"                                                                    <input type=\"number\" id=\"pquanlity\" name=\"pQuanlity\" min=\"1\" max=\""+product.getQuanlity()+"\" required>\n" +
"                                                                </div>\n" +
"                                                                <div class=\"popup-footer\">\n" +
"                                                                    <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\n" +
"                                                                    <button type=\"button\" class=\"btn btn-secondary\" onclick=\"hidePopup("+product.getId()+")\">Close</button>\n" +
"                                                                </div>\n" +
"                                                            </form>\n" +
"                                                        </div>\n" +
"                                                    </div>\n" +
"                                                </div>");
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
