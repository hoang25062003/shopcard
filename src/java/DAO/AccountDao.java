/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Product;

/**
 *
 * @author Admin
 */
public class AccountDao extends DBContext {
    
    public void updateMoney(Account account, int money) {
        try {
            String sql = "UPDATE `shopcard`.`account`\n"
                    + "SET\n"
                    + "`Money` = ?\n"
                    + "WHERE `ID` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, money);
            stm.setInt(2, account.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateAccount(Account account) {
        try {
            String sql = "UPDATE `shopcard`.`account`\n"
                    + "SET\n"
                    + "`Name` = ?,\n"
                    + "`Email` = ?,\n"
                    + "`Phone` = ?\n"
                    + "WHERE `ID` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getName());
            stm.setString(2, account.getEmail());
            stm.setString(3, account.getPhone());
            stm.setInt(4, account.getId());
            

            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public Account getAccountById(int id) {
        String sql = "SELECT * FROM account WHERE 1=1 and id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            
            ResultSet rs = stm.executeQuery();
             if (rs.next()) {
                  Account account = new Account(rs.getInt(1), rs.getString(2),rs.getString(3),
                          rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7) ,
                          rs.getString(8), rs.getInt(9),rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getDate(13), rs.getInt(14), rs.getDate(15), 
                          rs.getInt(16), rs.getDate(17), rs.getInt(18), rs.getBoolean(19));
                  return account;
             }
        } catch (SQLException ex) {
            System.out.println("Login failed: " + ex.getMessage());
            
        }
        return null;
    }
    
    public Account login(String username, String password) {
        String sql = "SELECT * FROM account WHERE 1=1 and Username = ? AND Password = ? and isDelete = 0";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
             if (rs.next()) {
                  Account account = new Account(rs.getInt(1), rs.getString(2),rs.getString(3),
                          rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7) ,
                          rs.getString(8), rs.getInt(9),rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getDate(13), rs.getInt(14), rs.getDate(15), 
                          rs.getInt(16), rs.getDate(17), rs.getInt(18), rs.getBoolean(19));
                  return account;
             }
        } catch (SQLException ex) {
            System.out.println("Login failed: " + ex.getMessage());
            
        }
        return null;
    }
            public void changePassword(int id, String newPassword) {
        try {
            String SQL = "update account set Password = ?\n"
                    + " where ID = ? ";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, newPassword);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
      public static void main(String[] args) {
        AccountDao d = new AccountDao();
        Account a = new Account(2, "123", "123", "123");
          d.updateAccount(a);
    }
      
          public boolean updatePasswordbyEmail(String email, String newPassword) {
        String sql = "UPDATE account SET PassWord = ? WHERE Email = ?";
        try  {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, email);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateOTP(String email, String otp) {
        String sql = "UPDATE account SET Otp = ?, OtpGender = ? WHERE Email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, otp);
            ps.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            ps.setString(3, email);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
  
}

  