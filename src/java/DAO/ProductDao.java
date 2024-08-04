/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.DBContext;
import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

import model.Product;

/**
 *
 * @author Admin
 */
public class ProductDao extends DBContext {

    public void updateQuantity(int id, int quantity) {
        try {
            String sql = "UPDATE `shopcard`.`product`\n"
                    + "SET\n"
                    + "`Quality` = ?\n"
                    + "WHERE `ID` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Product> getAllProducts() {
        List<Product> pList = new ArrayList<>();
        String sql = "SELECT * FROM shopcard.product";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
                pList.add(p);
            }

        } catch (Exception e) {

        }

        return pList;
    }

    public Product getProductById(int id) {

        String sql = "SELECT * FROM shopcard.product where ID = ? and Quality != 0";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
                return p;
            }
        } catch (Exception e) {

        }

        return null;
    }

    public Product getProductByIdAll(int id) {

        String sql = "SELECT * FROM shopcard.product where ID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
                return p;
            }
        } catch (Exception e) {

        }

        return null;
    }

    public int getTotalProductIn() {
        String sql = "Select count(*) from shopcard.product where Quality != 0 and isDelete = 0;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Product> search(String key, int index) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from shopcard.product where 1=1 and Quality != 0 and isDelete = 0";
        if (key != null && !key.equals("")) {
            sql += " and name like '%" + key + "%' ";
        }

        sql += " LIMIT 8 OFFSET ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 8);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6)));

            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<Product> searchArrangeIn(String key, int cid, String arange, int index) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from shopcard.product where 1=1 and Quality != 0 and isDelete = 0";
        if (key != null && !key.equals("")) {
            sql += " and name like '%" + key + "%' ";
        }

        if (cid != 0) {
            sql += " and CateId=" + cid;
        }

        if (arange.equals("cheapest")) {
            sql += " ORDER BY price ";
        }
        if (arange.equals("expensive")) {
            sql += " ORDER BY price DESC";
        }
        sql += " LIMIT 8 OFFSET ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 8);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6)));

            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<Product> paging(int page) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * \n"
                + "FROM shopcard.product where Quality != 0 and isDelete = 0 \n"
                + "ORDER BY id ASC \n"
                + "LIMIT 8 OFFSET ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 8);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6)));

            }
        } catch (Exception e) {
        }
        return list;
    }

    // Danh sách mua 
    public List<Object[]> getAllPurchaseOrders(int page) {
        List<Object[]> orderList = new ArrayList<>();
        String sql = "SELECT o.ID AS OrderID, a.Name AS BuyerName, p.Name AS ProductName, p.Price, o.Quality, "
                + "o.Total, p.Img, o.CreatedAt "
                + "FROM `order` o "
                + "JOIN account a ON o.AccountID = a.ID "
                + "JOIN product p ON o.ProductID = p.ID "
                + "ORDER BY o.CreatedAt DESC "
                + "LIMIT 10 OFFSET ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (page - 1) * 10); // Calculate the offset
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                String buyerName = rs.getString("BuyerName");
                String productName = rs.getString("ProductName");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quality");
                int total = rs.getInt("Total");
                String img = rs.getString("Img");
                java.sql.Timestamp createdAt = rs.getTimestamp("CreatedAt");

                Object[] orderInfo = new Object[8]; // Create an array to hold the data
                orderInfo[0] = orderID;
                orderInfo[1] = buyerName;
                orderInfo[2] = productName;
                orderInfo[3] = price;
                orderInfo[4] = quantity;
                orderInfo[5] = total;
                orderInfo[6] = img;
                orderInfo[7] = createdAt;

                orderList.add(orderInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

    // Lấy sản phẩm bán chạy nhất
    public List<Product> getBestSellingProducts(int limit) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.ID, p.Name, p.Price, p.Quality, p.Img, p.CateId, SUM(o.Quality) AS total_sold "
                + "FROM product p "
                + "JOIN `order` o ON p.ID = o.ProductID "
                + "GROUP BY p.ID, p.Name, p.Price, p.Quality, p.Img, p.CateId "
                + "ORDER BY total_sold DESC "
                + "LIMIT ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, limit);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("Name");
                    int price = rs.getInt("Price");
                    int quality = rs.getInt("Quality"); // Lấy quality từ bảng product
                    String img = rs.getString("Img");
                    int cateId = rs.getInt("CateId");

                    Product product = new Product(id, name, price, quality, img, cateId);
                    list.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        ProductDao d = new ProductDao();
        for (Product p : d.searchArrangeIn("viettel", 0, "cheapest", 1)) {
            System.out.println(p);
        }

    }
}
