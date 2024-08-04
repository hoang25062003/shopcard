package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.FeedBack;

public class FeedBackDAO extends DBContext {

    public void insertFeedBack(FeedBack feedback) throws SQLException {
        String sql = "INSERT INTO FeedBack (AccountID, Content, Image, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, feedback.getAccountID());
            statement.setString(2, feedback.getContent());
            if (feedback.getImage() != null) {
                statement.setString(3, feedback.getImage());
            } else {
                statement.setNull(3, java.sql.Types.VARCHAR);
            }
            statement.setBoolean(4, feedback.isStatus());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("Error inserting feedback", e);
        }
    }

    public List<Map<String, Object>> selectAllFeedBacks(int offset, int limit, String sortBy, String order, String username) {
        List<Map<String, Object>> feedbacks = new ArrayList<>();
        String sql = "SELECT f.*, a.username FROM FeedBack f JOIN Account a ON f.AccountID = a.ID WHERE 1=1";

        if (username != null && !username.isEmpty()) {
            sql += " AND a.username LIKE ?";
        }

        // Adding sorting
        if (sortBy != null && !sortBy.isEmpty()) {
            sql += " ORDER BY " + sortBy;
            if (order != null && order.equalsIgnoreCase("desc")) {
                sql += " DESC";
            } else {
                sql += " ASC";
            }
        }

        // Adding pagination
        sql += " LIMIT ? OFFSET ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int paramIndex = 1;
            if (username != null && !username.isEmpty()) {
                statement.setString(paramIndex++, "%" + username + "%");
            }
            statement.setInt(paramIndex++, limit);
            statement.setInt(paramIndex++, offset);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int accountId = rs.getInt("AccountID");
                String content = rs.getString("Content");
                String image = rs.getString("Image");
                Timestamp createdAt = rs.getTimestamp("CreatedAt");
                boolean status = rs.getBoolean("Status");
                String user = rs.getString("username");

                FeedBack feedback = new FeedBack(id, accountId, content, image, createdAt, status);
                Map<String, Object> feedbackWithDetails = new HashMap<>();
                feedbackWithDetails.put("feedback", feedback);
                feedbackWithDetails.put("username", user);

                feedbacks.add(feedbackWithDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    public int getNumberOfRecords() {
        int noOfRecords = 0;
        String sql = "SELECT COUNT(*) FROM FeedBack";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noOfRecords;
    }
    public void updateFeedbackStatus(int feedbackId, boolean status) {
        String sql = "UPDATE FeedBack SET Status = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, status);
            statement.setInt(2, feedbackId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
