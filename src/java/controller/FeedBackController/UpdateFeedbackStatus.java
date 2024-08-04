package controller.FeedBackController;

import DAO.FeedBackDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateFeedbackStatus extends HttpServlet {

    private FeedBackDAO feedBackDAO;

    public void init() {
        feedBackDAO = new FeedBackDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int feedbackId = Integer.parseInt(request.getParameter("id"));
        boolean status = request.getParameter("status") != null;

        feedBackDAO.updateFeedbackStatus(feedbackId, status);

        response.sendRedirect("FeedBackAdmin");
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
        return "Servlet to update feedback status";
    }
}
