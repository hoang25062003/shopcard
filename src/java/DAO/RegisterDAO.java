package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import utill.RandomNumberGenerator;

public class RegisterDAO extends DBContext {

    public boolean registerUser(Account account) {
        String sql = "INSERT INTO account (UserName, PassWord, Name, Email, Phone, RoleID, Img, Money, Status, Otp, OtpGender, CreatedAt, CreatedBy, UpdateAt, UpdateBy, DeleteAt, DeleteBy, isDelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getUserName());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getName());
            ps.setString(4, account.getEmail());
            ps.setString(5, account.getPhone());
            ps.setInt(6, account.getRoleId());
            ps.setString(7, account.getImg());
            ps.setInt(8, account.getMoney());
            ps.setInt(9, 3); // Status initially set to 3 (notActive)
            ps.setString(10, account.getOtp());
            ps.setTimestamp(11, new java.sql.Timestamp(account.getOtpGender().getTime()));
            ps.setTimestamp(12, new java.sql.Timestamp(account.getCreatedAt().getTime()));
            ps.setObject(13, account.getCreatedBy(), java.sql.Types.INTEGER);
            ps.setTimestamp(14, account.getUpdateAt() != null ? new java.sql.Timestamp(account.getUpdateAt().getTime()) : null);
            ps.setObject(15, account.getUpdateBy(), java.sql.Types.INTEGER);
            ps.setTimestamp(16, account.getDeleteAt() != null ? new java.sql.Timestamp(account.getDeleteAt().getTime()) : null);
            ps.setObject(17, account.getDeleteBy(), java.sql.Types.INTEGER);
            ps.setBoolean(18, account.isIsDelete());

            int result = ps.executeUpdate();

            // Retrieve the generated user ID
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateUserStatus(int userId, int newStatus) {
        String sql = "UPDATE account SET Status = ? WHERE ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, newStatus);
            ps.setInt(2, userId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM account WHERE Email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean checkOtp(String email, String otp) {
        String sql = "SELECT otp FROM account WHERE Email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String currentOtp = rs.getString("otp");
                return currentOtp != null && currentOtp.equals(otp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateUserStatusByEmail(String email, int newStatus) {
        String sql = "UPDATE account SET Status = ? WHERE Email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, newStatus);
            ps.setString(2, email);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Update failed
        }
    }

    public boolean isUserNameExists(String username) {
        String sql = "SELECT COUNT(*) FROM account WHERE UserName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateUserProfile(String id, String name, String userName, String phone, String email, String img) {
        String sql = "UPDATE account SET UserName = ?, Name = ?, Email = ?, Phone = ?, img = ? WHERE ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, img);
            ps.setString(6, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String resendOtp(String email) {
        String newOtp = RandomNumberGenerator.generateRandom6DigitNumber(); // Generate a new OTP
        String sql = "UPDATE account SET Otp = ?, OtpGender = CURRENT_TIMESTAMP WHERE Email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newOtp);
            ps.setString(2, email);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0 ? newOtp : null;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void invalidateOtp(String email) {
    String sql = "UPDATE account SET otp = NULL WHERE email = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, email);
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
