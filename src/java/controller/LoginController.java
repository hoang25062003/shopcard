package controller;

import DAO.AccountDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin đăng nhập từ request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");

        // Kiểm tra captcha
        if (!validateCaptcha(captcha, request)) {
            request.setAttribute("errorMessage", "Invalid captcha.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Kiểm tra đăng nhập
        AccountDao accountDao = new AccountDao();
        Account account = accountDao.login(username, password);

        if (account == null) {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Check if account status is 1 (active)
            if (account.getStatus() == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("account", account);
                response.sendRedirect("home");
            } else if (account.getStatus() == 3) {
                request.setAttribute("errorMessage", "Cannot login cause email does not active.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Account status not recognized.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }

    // Method to validate captcha
    private boolean validateCaptcha(String userInput, HttpServletRequest request) {
        // Get the captcha string stored in session
        HttpSession session = request.getSession();
        String captcha = (String) session.getAttribute("captcha");

        // Validate the captcha string with the user input
        return userInput != null && captcha != null && userInput.equalsIgnoreCase(captcha);
    }
}
