/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.ProductDetail;

/**
 *
 * @author Admin
 */
public class ProductDetailDao extends DBContext {

    public List<ProductDetail> getProductDetail(int id, int quantity) {
         List<ProductDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM shopcard.productdetail where Name_ID = ? ORDER BY id DESC LIMIT ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, quantity);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProductDetail p = new ProductDetail(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("ngu");
        }

        return list;
    }

    public void delProductDetailById(int id) {

        String sql = "DELETE FROM shopcard.productdetail WHERE id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("ngu");
        }

    }

    public void istProductDetailById(int id, int seri, int pin) {

        String sql = "INSERT INTO `shopcard`.`productdetail`\n"
                + "(`Name_ID`,`Seri`,`Pin`)\n"
                + "VALUES\n"
                + "(?,?,?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, seri);
            stm.setInt(3, pin);

            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("ngu");
        }

    }
    public static void main(String[] args) {
        ProductDetailDao dao = new ProductDetailDao();
        for (int i = 100; i <= 150; i++) {
            dao.istProductDetailById(31, i, i);
        }
        
        
    }

}
