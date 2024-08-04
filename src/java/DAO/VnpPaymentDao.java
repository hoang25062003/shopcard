/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.VnpayPayment;

/**
 *
 * @author Admin
 */
public class VnpPaymentDao extends DBContext {

    public void addVnpPayment(String t, int aid, int a, String o, Timestamp d) {

        String sql = "INSERT INTO `shopcard`.`vnpay_payment`\n"
                + "(`vnp_TxnRef`,\n"
                + "`AccountID`,\n"
                + "`Amount`,\n"
                + "`OrderInfo`,\n"
                + "`PayDate`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, t);
            stm.setInt(2, aid);
            stm.setInt(3, a);
            stm.setString(4, o);
            stm.setTimestamp(5, d);

            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("ngu");
        }

    }

    public VnpayPayment getPaymentBytxn(String txnref) {

        String sql = "SELECT * FROM shopcard.vnpay_payment where  vnp_TxnRef= ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, txnref);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                VnpayPayment v = new VnpayPayment(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDate(5));
                return v;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public List<VnpayPayment> getPaymentsByAccountId(int accountId) {
        List<VnpayPayment> payments = new ArrayList<>();
        String sql = "SELECT * FROM shopcard.vnpay_payment WHERE AccountID = ? ORDER BY PayDate DESC";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, accountId);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    VnpayPayment v = new VnpayPayment(
                            rs.getString(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getTimestamp(5)
                    );
                    payments.add(v);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }

    public static void main(String[] args) {
        VnpPaymentDao vDao = new VnpPaymentDao();
        if (vDao.getPaymentBytxn("27193271") != null) {
            System.out.println("haha");
        }
        System.out.println("huhu");
    }
}
