/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.Order;

/**
 *
 * @author Admin
 */
public class OrderDao extends DBContext {
     public int getIdLastOrder() {
        String sql = "SELECT id FROM shopcard.order \n"
                + "WHERE CreatedAt = (SELECT MAX(CreatedAt) FROM shopcard.order);";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void UpdateStatus(int id, int status) {

        String sql = "UPDATE `shopcard`.`order`\n"
                + "SET\n"
                + "`status` = ?\n"
                + "WHERE `ID` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(2, id);
            stm.setInt(1, status);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("ngu");
        }
    }

    public void addOrder(int aId, int pId, int q, int total) {

        String sql = "INSERT INTO `shopcard`.`order`\n"
                + "(`AccountID`,`ProductID`,`Quality`,`Total`,`status`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, aId);
            stm.setInt(2, pId);
            stm.setInt(3, q);
            stm.setInt(4, total);
            stm.setInt(5, 0);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("ngu");
        }
    }
    
    

    public Queue<Order> getOrderUnpaidById(int id) {
        Queue<Order> queue = new LinkedList<>();
        String sql = "SELECT * FROM shopcard.order where status = 0 and AccountID = ?;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                queue.add(new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getBoolean(6)));

            }

        } catch (Exception e) {
            System.out.println("ngu");
        }
        return queue;
    }
    
    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM shopcard.order WHERE ID = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, orderId);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getDate(7)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getOrderHistory(int accountId) {
        List<Order> orderHistory = new ArrayList<>();
        String sql = "SELECT * FROM shopcard.order where AccountID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int productId = rs.getInt(3);
                int quantity = rs.getInt(4);
                int total = rs.getInt(5);
                Date createdAt = rs.getDate(7);
                Order order = new Order(id, accountId, productId, quantity, total, createdAt);
                orderHistory.add(order);
            }
            

        } catch (Exception e) {

        }
        return orderHistory;
    }

    public static void main(String[] args) {
        OrderDao d = new OrderDao();
        List<Order> l = d.getOrderHistory(2);
        for (Order o : l) {
            System.out.println(o);
        }

    }

}
