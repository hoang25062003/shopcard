package controller;

import DAO.RegisterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "VerifyRegister", urlPatterns = {"/verify"})
public class VerifyRegister extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Retrieve user input from the verification form
            String email = request.getParameter("email");
            String otp = request.getParameter("otp");

            // Check if the submitted OTP matches the OTP stored in the database
            RegisterDAO dao = new RegisterDAO();
            boolean otpMatched = dao.checkOtp(email, otp);

            if (otpMatched) {
                dao.updateUserStatusByEmail(email, 1); // Update status to 1 (isActive)
                
                // Redirect to a verification success page
                request.setAttribute("mess", "Verify successfully! Please back to login!");
                request.getRequestDispatcher("verify.jsp").forward(request, response);
            } else {
                // Redirect to a verification failure page
                request.setAttribute("mess", "This OTP does not match!");
                request.getRequestDispatcher("verify.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("verification_failure.jsp");
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
        request.getRequestDispatcher("verify.jsp").forward(request, response);

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
