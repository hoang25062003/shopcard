package controller;

import DAO.RegisterDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Account;

@WebServlet(name = "UserProfile", urlPatterns = {"/UserProfile"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UserProfile extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "uploads";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProfile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // Xử lý upload ảnh đại diện
        Part part = request.getPart("avatar");
        String fileName = extractFileName(part);
        String savePath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        String filePath = savePath + File.separator + fileName;
        part.write(filePath);
        String imgPath = UPLOAD_DIRECTORY + "/" + fileName;

        RegisterDAO dao = new RegisterDAO();
        boolean isUpdated = dao.updateUserProfile(id, name, userName, phone, email, imgPath);

        if (isUpdated) {
            request.setAttribute("mess", "Update user profile successfully!");
            HttpSession session = request.getSession();
            Account u = (Account) session.getAttribute("account");
            u.setName(name);
            u.setUserName(userName);
            u.setEmail(email);
            u.setPhone(phone);
            u.setImg(imgPath); // Cập nhật đường dẫn ảnh đại diện
            session.setAttribute("account", u);
        } else {
            request.setAttribute("mess", "Failed to update user profile.");
        }

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
