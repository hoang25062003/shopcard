package controller.FeedBackController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import DAO.FeedBackDAO;
import java.nio.file.Files;
import java.nio.file.Paths;
import model.Account;
import model.FeedBack;

@WebServlet(name = "FeedBackController", urlPatterns = {"/FeedBackController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class FeedBackController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        request.getRequestDispatcher("/feedback.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String content = request.getParameter("content");
//        Part imagePart = request.getPart("image");
//        String image = null;
//        if (imagePart != null && imagePart.getSize() > 0) {
//            image = imagePart.getSubmittedFileName();
//        }

        int accountID = ((Account) session.getAttribute("account")).getId(); // Assuming you have an Account object with getId() method

        FeedBack feedback = new FeedBack();
        feedback.setAccountID(accountID);
        feedback.setContent(content);
//        feedback.setImage(image);
        feedback.setStatus(true); // Assuming the default status is true
        // Xử lý tải lên ảnh
        String realPath = request.getServletContext().getRealPath("/image");
        Files.createDirectories(Paths.get(realPath)); // Tạo thư mục nếu chưa tồn tại

        for (Part part : request.getParts()) {
            if (part != null && part.getSubmittedFileName() != null) {
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                part.write(Paths.get(realPath, filename).toString()); // Lưu file

                //d1.addImage(productID, "image/" + filename); // Thêm hình ảnh vào cơ sở dữ liệu
                feedback.setImage("image/" + filename);
            }
        }

        FeedBackDAO feedbackDAO = new FeedBackDAO();
        try {
            feedbackDAO.insertFeedBack(feedback);
            request.setAttribute("message", "Báo cáo của bạn đã được gửi thành công.");
        } catch (Exception e) {
            Logger.getLogger(FeedBack.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("message", "Đã xảy ra lỗi khi gửi báo cáo. Vui lòng thử lại.");
        }

        request.getRequestDispatcher("/feedback.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet xử lý báo cáo sự cố";
    }
}
