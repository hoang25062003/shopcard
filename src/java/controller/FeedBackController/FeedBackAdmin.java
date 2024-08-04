package controller.FeedBackController;

import DAO.FeedBackDAO;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

public class FeedBackAdmin extends HttpServlet {

    private FeedBackDAO feedBackDAO;

    public void init() {
        feedBackDAO = new FeedBackDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // false để không tạo session mới nếu chưa có
        if (session != null && session.getAttribute("account") != null) {
            Account account = (Account) session.getAttribute("account");
            if (account.getRoleId() == 1) { // Kiểm tra roleId có phải là admin không (ví dụ roleId của admin là 1)
                String username = request.getParameter("username");
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");

        // Pagination parameters
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Calculate offset for SQL query
        int offset = (page - 1) * recordsPerPage;

        List<Map<String, Object>> incidentReports = feedBackDAO.selectAllFeedBacks(offset, recordsPerPage, sort, order, username);

        // Set attributes for pagination
        int noOfRecords = feedBackDAO.getNumberOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("incidentReports", incidentReports);
        request.setAttribute("username", username);
        request.setAttribute("sort", sort);
        request.setAttribute("order", order);
        request.getRequestDispatcher("adminfeedback.jsp").forward(request, response);
            } else {
                // Không phải admin, chuyển hướng về trang đăng nhập
                response.sendRedirect("login");
            }
        } else {
            // Chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("login");
        }
    }
        
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet FeedBackAdmin provides incident report data";
    }
}
