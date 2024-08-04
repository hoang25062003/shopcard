/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DetailOrder;

/**
 *
 * @author Admin
 */
public class OrderDetailDao extends DBContext {

    public void addOrderDetail(int oId, String seri, String pin) {

        String sql = "INSERT INTO `shopcard`.`detailorder`\n"
                + "(`OrderID`,`Seri`,`Pin`)\n"
                + "VALUES\n"
                + "(?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, oId);
            stm.setString(2, seri);
            stm.setString(3, pin);

            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("ngu");
        }
    }

    public List<DetailOrder> getOrderDetailsById(int orderId) {
        List<DetailOrder> details = new ArrayList<>();
        String sql = "SELECT * FROM shopcard.detailorder WHERE OrderID = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, orderId);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    DetailOrder detail = new DetailOrder(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getDate(5)
                    );
                    details.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }
}
