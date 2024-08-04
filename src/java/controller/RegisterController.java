package controller;

import DAO.RegisterDAO;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import utill.EmailUtil;
import utill.RandomNumberGenerator;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    // Email validation method
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RegisterDAO dao = new RegisterDAO();
            String userName = request.getParameter("username");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            
            if (!isValidEmail(email)) {
                request.setAttribute("mess", "Invalid email format!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            if (dao.isEmailExists(email)) {
                request.setAttribute("mess", "This email already exists!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } 
            if (dao.isUserNameExists(userName)) {
                request.setAttribute("mess", "This username already exists!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } 
            else {
                String otp = RandomNumberGenerator.generateRandom6DigitNumber();
                Account newAccount = new Account(
                        userName,
                        password,
                        name,
                        email,
                        phone,
                        2, // Default role ID
                        "default.png",
                        0, // Initial money
                        3, // Initial status set to 3 (notActive)
                        otp,
                        new Date(), // OTP generation time
                        new Date(), // Account creation time
                        null, // Created by
                        null, // Updated at
                        null, // Updated by
                        null, // Deleted at
                        null, // Deleted by
                        false // isDelete flag
                );

                boolean registrationSuccess = dao.registerUser(newAccount);

                if (registrationSuccess) {
                    sendVerificationEmail(email, otp);
                    request.setAttribute("mess", "Register Successfully. Please check your email!");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } else {
                    request.setAttribute("mess", "Registration failed, please try again!");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mess", "Registration failed, please try again!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void sendVerificationEmail(String email, String otp) {
        try {
            String subject = "Verify Your Account";
            String verificationLink = "http://localhost:9999/SWP/verify?email=" + email;
            String body = "Thank you for registering! Please click the following link to verify your account:\n"
                    + "<a href=\"" + verificationLink + "\">Click here</a>\n\n"
                    + "Alternatively, you can use the OTP code below:\n"
                    + otp + "\n\n"
                    + "This OTP is valid for 5 minutes.";
            EmailUtil.sendEmail(email, subject, body);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle email sending failure
        }
    }
}
