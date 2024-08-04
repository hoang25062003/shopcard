/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DAO.DBContext;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.AccountWithTotal;
import model.Categories;
import model.Product;
import org.apache.poi.ss.usermodel.CellType;
import java.util.Scanner;
public class AdminDao extends DBContext {
public List<Account> getAllAcoount() {
        List<Account> listUsers = new ArrayList<>();
        String sql = "SELECT ID, UserName, Email, Name, Money,Phone,CreatedAt, isDelete FROM account WHERE RoleId = 2";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            Account user = new Account();
            user.setId(rs.getInt("ID"));
            user.setUserName(rs.getString("UserName"));
            user.setEmail(rs.getString("Email"));
            user.setName(rs.getString("Name"));
            user.setMoney(rs.getInt("Money"));
            user.setPhone(rs.getString("Phone"));
            user.setCreatedAt(rs.getDate("CreatedAt"));
            user.setIsDelete(rs.getBoolean("isDelete"));
            listUsers.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUsers;
    }
public List<Account> getAllAdmin() {
        List<Account> listUsers = new ArrayList<>();
        String sql = "SELECT ID, UserName, Email, Name, Money,Phone,CreatedAt, isDelete FROM account WHERE RoleId = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            Account user = new Account();
            user.setId(rs.getInt("ID"));
            user.setUserName(rs.getString("UserName"));
            user.setEmail(rs.getString("Email"));
            user.setName(rs.getString("Name"));
            user.setMoney(rs.getInt("Money"));
            user.setPhone(rs.getString("Phone"));
            user.setCreatedAt(rs.getDate("CreatedAt"));
            listUsers.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUsers;
    }
public List<Account> getUsersByPage(int page) {
    List<Account> listUsers = new ArrayList<>();
    String sql = "SELECT ID, UserName, Email, Name, Money,Phone,CreatedAt, isDelete FROM account WHERE RoleId = 2 LIMIT ? OFFSET ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 8); // Limit the number of users per page to 8
        ps.setInt(2, (page - 1) * 8); // Calculate the offset for the current page
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Account user = new Account();
            user.setId(rs.getInt("ID"));
            user.setUserName(rs.getString("UserName"));
            user.setEmail(rs.getString("Email"));
            user.setName(rs.getString("Name"));
            user.setMoney(rs.getInt("Money"));
            user.setPhone(rs.getString("Phone"));
            user.setCreatedAt(rs.getDate("CreatedAt"));
            user.setIsDelete(rs.getBoolean("isDelete"));
            listUsers.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listUsers;
}
 public int getmaxPageAccount() {
    int totalAccount = 0;
    int maxPage = 0;
    String sql = "SELECT COUNT(*) AS TotalAccount FROM Account";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalAccount = rs.getInt("TotalAccount");
        }
        maxPage = totalAccount / 8;
        if (totalAccount % 8 != 0) {
            maxPage++;
        }
    } catch (Exception e) {
        e.printStackTrace(); // Print the error if there is one
    }
    return maxPage;
}
public List<Account> searcAccountsByPage(String nameSearch, int page) {
    List<Account> listAccount = new ArrayList<>();
    String sql = "SELECT ID, UserName, Email, Name, Money, Phone, CreatedAt, isDelete FROM Account WHERE Email LIKE ? AND RoleId = 2 LIMIT ? OFFSET ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + nameSearch + "%");
        ps.setInt(2, 8); // Limit number of items per page to 8
        ps.setInt(3, (page - 1) * 8); // Calculate offset for the current page
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Account user = new Account();
            user.setId(rs.getInt("ID"));
            user.setUserName(rs.getString("UserName"));
            user.setEmail(rs.getString("Email"));
            user.setName(rs.getString("Name"));
            user.setMoney(rs.getInt("Money"));
            user.setPhone(rs.getString("Phone"));
            user.setCreatedAt(rs.getDate("CreatedAt"));
            user.setIsDelete(rs.getBoolean("isDelete"));
            
            // Add each user to the list
            listAccount.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listAccount;
}

public List<Account> searchAccount(String emailSearch) {
    List<Account> listUsers = new ArrayList<>();
    String sql = "SELECT ID, UserName, Email, Name, Money, Phone, CreatedAt, isDelete FROM account WHERE Email LIKE ? AND RoleId = 2";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + emailSearch + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Account user = new Account();
            user.setId(rs.getInt("ID"));
            user.setUserName(rs.getString("UserName"));
            user.setEmail(rs.getString("Email"));
            user.setName(rs.getString("Name"));
            user.setMoney(rs.getInt("Money"));
            user.setPhone(rs.getString("Phone"));
            user.setCreatedAt(rs.getDate("CreatedAt"));
            user.setIsDelete(rs.getBoolean("isDelete"));
            listUsers.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listUsers;
}

    public void CreatAccount(String email, String userName, String password, int money) {
        String sql = "INSERT INTO account (UserName, PassWord, Name, Email, Phone, RoleId, Img, Money, CreatedBy, UpdateAt, UpdateBy, DeleteAt, DeleteBy, isDelete) " +
                         "VALUES (?, ?, Account, ?, 1111111111, 2, NULL, ?, NULL, NULL, NULL, NULL, NULL, 0)";
       
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
                // Tạo đối tượng User và thiết lập các thuộc tính từ kết quả truy vấn
               ps.setString(1, userName);
               ps.setString(2, password);
               ps.setString(3, email);
               ps.setInt(4, money); 
               ps.executeUpdate();

            
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }       
    }
   
public void updateStatus(int userId, boolean status, int price) {
    String sql = "UPDATE account SET isDelete = ? , Money = ? WHERE ID = ?";

    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        // Set the status as 1 if true, otherwise 0
        pstmt.setInt(1, status ? 1 : 0);
         pstmt.setInt(2, price);
        // Set the user ID
        pstmt.setInt(3, userId);
        // Execute the update
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
 public int getmaxPageproduct() {
    int totalProducts = 0;
    int maxPage = 0;
    String sql = "SELECT COUNT(*) AS TotalProducts FROM product";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalProducts = rs.getInt("TotalProducts");
        }
        maxPage = totalProducts / 8;
        if (totalProducts % 8 != 0) {
            maxPage++;
        }
    } catch (Exception e) {
        e.printStackTrace(); // Print the error if there is one
    }
    return maxPage;
}
 public int getMaxPageForSearchAccount(String nameSearch) {
    int totalAccount = 0;
    int maxPage = 0;
    String sql = "SELECT COUNT(*) AS totalAccount FROM account WHERE Name LIKE ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + nameSearch + "%");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalAccount = rs.getInt("TotalProducts");
        }
        maxPage = totalAccount / 8;
        if (totalAccount % 8 != 0) {
            maxPage++;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return maxPage;
}
public List<Product> getAllProducts() {
        List<Product> listProducts = new ArrayList<>();
        String sql = "SELECT ID, Name, Price, Quality, CateId, isDelete,  Img  FROM product";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("ID"));
                product.setName(rs.getString("Name"));
                product.setPrice(rs.getInt("Price"));
                product.setQuanlity(rs.getInt("Quality"));
                product.setCateID(rs.getInt("CateId"));
                product.setIsDelete(rs.getBoolean("isDelete"));
                product.setImg(rs.getString("Img"));
                listProducts.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProducts;
    }
public void updateStatusProduct(int Id, boolean status , int price ,String name) {
     String sql = "UPDATE product SET name = ?, price = ?, isDelete = ? WHERE id = ?";

 try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        // Cập nhật tên sản phẩm
        pstmt.setString(1, name);
        // Cập nhật giá sản phẩm
        pstmt.setInt(2, price);
        // Cập nhật trạng thái sản phẩm (1 nếu true, 0 nếu false)
        pstmt.setInt(3, status ? 1 : 0);
        // Cập nhật ID sản phẩm
        pstmt.setInt(4, Id);

        // Thực thi câu lệnh cập nhật
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public List<Product> searchProduct(String nameSearch) {
    List<Product> listProducts = new ArrayList<>();
    String sql = "SELECT ID, Name, Price, Quality, CateId, isDelete, Img FROM product WHERE Name LIKE ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + nameSearch + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("ID"));
            product.setName(rs.getString("Name"));
            product.setPrice(rs.getInt("Price"));
            product.setQuanlity(rs.getInt("Quality"));
            product.setCateID(rs.getInt("CateId"));
            product.setIsDelete(rs.getBoolean("isDelete"));
            product.setImg(rs.getString("Img"));
            listProducts.add(product);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listProducts;
}
public List<Product> searchProductByPage(String nameSearch, int page) {
    List<Product> listProducts = new ArrayList<>();
String sql = "SELECT ID, Name, Price, Quality, CateId, isDelete, CreatedAt, Img FROM product WHERE Name LIKE ? ORDER BY ID DESC LIMIT ? OFFSET ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + nameSearch + "%");
        ps.setInt(2, 8); // Giới hạn số lượng sản phẩm mỗi trang là 8
        ps.setInt(3, (page - 1) * 8); // Tính toán offset cho trang hiện tại
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("ID"));
            product.setName(rs.getString("Name"));
            product.setPrice(rs.getInt("Price"));
            product.setQuanlity(rs.getInt("Quality"));
            product.setCateID(rs.getInt("CateId"));
            product.setIsDelete(rs.getBoolean("isDelete"));
            product.setCreatedAt(rs.getDate("CreatedAt"));
            product.setImg(rs.getString("Img"));
            listProducts.add(product);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listProducts;
}

public int getMaxPageForSearch(String nameSearch) {
    int totalProducts = 0;
    int maxPage = 0;
    String sql = "SELECT COUNT(*) AS TotalProducts FROM product WHERE Name LIKE ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + nameSearch + "%");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalProducts = rs.getInt("TotalProducts");
        }
        maxPage = totalProducts / 8;
        if (totalProducts % 8 != 0) {
            maxPage++;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return maxPage;
}

public void createProduct(String name, int price, String img,int cateId) {
        String sql = "INSERT INTO product (Name, Price, Img,CateId, CreatedBy,UpdateAt,CreatedBy_copy1,DeleteAt,DeleteBy,isDelete) VALUES (?, ?, ?, ?, NULL,NULL,NULL,NULL,NULL, 0)";
        try {
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setString(3, img);
            ps.setInt(4, cateId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }       
    }
public void createCategory(String name) {
        String sql = "INSERT INTO categories (name, CreatedBy,UpdateAt,CreatedBy_copy1,DeleteAt,DeleteBy,isDelete) VALUES (?, NULL,NULL,NULL,NULL,NULL, 0)";
        try {
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }       
    }
public void createAdmin(String userName, String email, String name, String password, String phone) {
String sql = "INSERT INTO account (UserName, Email, Name, PassWord, Phone, Money, isDelete, RoleId, status) VALUES (?, ?, ?, ?, ?, 0, 0, 1, 1)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userName);
            ps.setString(2, email);
            ps.setString(3, name);
            ps.setString(4, password);
            ps.setString(5, phone);


            ps.executeUpdate(); // Execute the update
        } catch (SQLException e) {
            e.printStackTrace(); // Print any SQL exceptions
        }
    }


    public void changePassword(int userId, String newPassword) {
        String sql = "UPDATE account SET PassWord = ? WHERE ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public void insertProductDetailsFromExcel(String excelFilePath, int nameId) {
    String sql = "INSERT INTO productdetail (Name_ID, Seri, Pin, CreatedBy, UpdateAt, CreatedBy_copy1, DeleteAt, DeleteBy, isDelete) VALUES (?, ?, ?, NULL, NULL, NULL, NULL, NULL, 0)";

    try (
        PreparedStatement ps = connection.prepareStatement(sql);
        FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(fis)) {

        Sheet sheet = workbook.getSheetAt(0);  // Lấy bảng tính đầu tiên
        List<int[]> data = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {  // Bỏ qua hàng đầu tiên nếu là tiêu đề
                continue;
            }

            int[] rowData = new int[2];
            Cell cell1 = row.getCell(0); // Lấy giá trị từ cột A
            Cell cell2 = row.getCell(1); // Lấy giá trị từ cột B

            // Ép kiểu dữ liệu từ Cell sang int
            if (cell1 != null && cell1.getCellType() == CellType.NUMERIC) {
                rowData[0] = (int) cell1.getNumericCellValue();
            } else {
                rowData[0] = 0; // Hoặc giá trị mặc định nếu cell1 là null hoặc không phải số
            }

            if (cell2 != null && cell2.getCellType() == CellType.NUMERIC) {
                rowData[1] = (int) cell2.getNumericCellValue();
            } else {
                rowData[1] = 0; // Hoặc giá trị mặc định nếu cell2 là null hoặc không phải số
            }

            data.add(rowData);
        }

        for (int[] rowData : data) {
            ps.setInt(1, nameId);
            ps.setInt(2, rowData[0]); // Giá trị của Seri từ cột A
            ps.setInt(3, rowData[1]); // Giá trị của Pin từ cột B
            ps.addBatch();
        }

        
        ps.executeBatch();

        // Gọi hàm updateProductQuantity để cập nhật quantity
        updateProductQuantity(nameId);

    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
}


public List<Product> getProductByPage(int page) {
    List<Product> listProducts = new ArrayList<>();
String sql = "SELECT ID, Name, Price, Quality, CateId, isDelete, CreatedAt, Img FROM product ORDER BY ID DESC LIMIT ? OFFSET ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 8); // Giới hạn số lượng sản phẩm mỗi trang là 8
        ps.setInt(2, (page - 1) * 8); // Tính toán offset cho trang hiện tại
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("ID"));
            product.setName(rs.getString("Name"));
            product.setPrice(rs.getInt("Price"));
            product.setQuanlity(rs.getInt("Quality"));
            product.setCateID(rs.getInt("CateId"));
            product.setIsDelete(rs.getBoolean("isDelete"));
            product.setCreatedAt(rs.getDate("CreatedAt"));
            product.setImg(rs.getString("Img"));
            listProducts.add(product);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listProducts;
}
public List<Categories> getAllCategory() {
        List<Categories> listProducts = new ArrayList<>();
        String sql = "SELECT id, name, isDelete  FROM categories";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categories product = new Categories();
                product.setId(rs.getInt("ID"));
                product.setName(rs.getString("Name"));
              
                product.setIsDelete(rs.getBoolean("isDelete"));

                listProducts.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProducts;
    }
    public List<Categories> getCategoryByPage(int page) {
    List<Categories> listCategories = new ArrayList<>();
    String sql = "SELECT id, name, isDelete FROM categories LIMIT ? OFFSET ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 8); // Giới hạn số lượng sản phẩm mỗi trang là 8
        ps.setInt(2, (page - 1) * 8); // Tính toán offset cho trang hiện tại
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Categories categories = new Categories();
            categories.setId(rs.getInt("id"));
            categories.setName(rs.getString("name"));
            categories.setIsDelete(rs.getBoolean("isDelete"));
            listCategories.add(categories);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listCategories;
}
    public int getmaxPageCategory() {
    int totalCategory = 0;
    int maxPage = 0;
    String sql = "SELECT COUNT(*) AS TotalProducts FROM categories";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalCategory = rs.getInt("TotalProducts");
        }
        maxPage = totalCategory / 8;
        if (totalCategory % 8 != 0) {
            maxPage++;
        }
    } catch (Exception e) {
        e.printStackTrace(); // Print the error if there is one
    }
    return maxPage;
}
public void updateStatusCategory(int categoryId, boolean status, String name) {
    String sql = "UPDATE categories SET isDelete = ?, name = ? WHERE id = ?";

    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        // Set trạng thái là 1 nếu true, ngược lại là 0
        pstmt.setInt(1, status ? 1 : 0);
        // Set tên danh mục
        pstmt.setString(2, name);
        // Set ID danh mục
        pstmt.setInt(3, categoryId);

        // Thực thi câu lệnh cập nhật
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void updateAdmin(int userId, int money, String name) {
    String sql = "UPDATE Account SET Money = ?, Name = ? WHERE id = ?";

    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, money);
        pstmt.setString(2, name);
        pstmt.setInt(3, userId);

        // Thực thi câu lệnh cập nhật
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public int getOrder() {
    int totalOrder = 0;
    String sql = "SELECT COUNT(*) AS totalOrder FROM shopcard.order";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalOrder = rs.getInt("totalOrder"); // Đổi thành alias đã đặt trong câu SQL
        }
        // Đóng ResultSet và PreparedStatement
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
    return totalOrder;
}
public int getTotalOrderAmount() {
    int totalOrderAmount = 0;
    String sql = "SELECT SUM(Total) AS totalOrderAmount FROM shopcard.order";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalOrderAmount = rs.getInt("totalOrderAmount");
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
    return totalOrderAmount;
}
public int getTotalQuality() {
    int totalOrderAmount = 0;
    String sql = "SELECT SUM(Quality) AS totalOrderAmount FROM shopcard.order";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalOrderAmount = rs.getInt("totalOrderAmount");
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
    return totalOrderAmount;
}
public List<AccountWithTotal> getAccountWithTotals() {
    List<AccountWithTotal> listUsers = new ArrayList<>();
String sql = "SELECT a.ID, a.UserName, a.Name, a.Email, a.Phone, a.Money, " +
             "COALESCE(SUM(o.Total), 0) as TotalOrderAmount, " +
             "(COALESCE(SUM(o.Total), 0) + a.Money) as TotalAccount " +
             "FROM account a " +
             "LEFT JOIN `order` o ON a.ID = o.AccountID " +
             "GROUP BY a.ID, a.UserName, a.Name, a.Email, a.Phone, a.Money " +
             "ORDER BY TotalOrderAmount DESC"; // Sắp xếp giảm dần theo TotalOrderAmount


    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            AccountWithTotal user = new AccountWithTotal();
            user.setId(rs.getInt("ID"));
            user.setUserName(rs.getString("UserName"));
            user.setName(rs.getString("Name"));
            user.setEmail(rs.getString("Email"));
            user.setPhone(rs.getString("Phone"));
            user.setMoney(rs.getInt("Money"));
            user.setTotalOrderAmount(rs.getInt("TotalOrderAmount"));
            user.setTotalAccount(rs.getInt("TotalAccount"));
            listUsers.add(user);
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listUsers;
}

public List<AccountWithTotal> getAccountWithTotalsByPage(int page) {
    List<AccountWithTotal> listUsers = new ArrayList<>();
String sql = "SELECT a.ID, a.UserName, a.Name, a.Email, a.Phone, a.Money, " +
             "COALESCE(SUM(o.Total), 0) as TotalOrderAmount, " +
             "(COALESCE(SUM(o.Total), 0) + a.Money) as TotalAccount " +
             "FROM account a " +
             "LEFT JOIN `order` o ON a.ID = o.AccountID " +
             "GROUP BY a.ID, a.UserName, a.Name, a.Email, a.Phone, a.Money " +
             "ORDER BY TotalOrderAmount DESC " + // Sắp xếp theo TotalOrderAmount giảm dần
             "LIMIT ? OFFSET ?";

    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 8); // Limit the number of users per page to 8
        ps.setInt(2, (page - 1) * 8); // Calculate the offset for the current page
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            AccountWithTotal user = new AccountWithTotal();
            user.setId(rs.getInt("ID"));
            user.setUserName(rs.getString("UserName"));
            user.setName(rs.getString("Name"));
            user.setEmail(rs.getString("Email"));
            user.setPhone(rs.getString("Phone"));
            user.setMoney(rs.getInt("Money"));
            user.setTotalOrderAmount(rs.getInt("TotalOrderAmount"));
            user.setTotalAccount(rs.getInt("TotalAccount"));
            listUsers.add(user);
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listUsers;
}

public int getmaxPageAccountWithTotals() {
    int totalAccounts = 0;
    int maxPage = 0;
    String sql = "SELECT COUNT(DISTINCT a.ID) AS TotalAccounts FROM account a LEFT JOIN `order` o ON a.ID = o.AccountID";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalAccounts = rs.getInt("TotalAccounts");
        }
        maxPage = totalAccounts / 8;
        if (totalAccounts % 8 != 0) {
            maxPage++;
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return maxPage;
}
public List<AccountWithTotal> searchAccountWithTotalsByPage(String nameSearch, int page) {
    List<AccountWithTotal> listAccounts = new ArrayList<>();
    String sql = "SELECT a.ID, a.UserName, a.Name, a.Email, a.Phone, a.Money, " +
                 "COALESCE(SUM(o.Total), 0) as TotalOrderAmount, " +
                 "(COALESCE(SUM(o.Total), 0) + a.Money) as TotalAccount " +
                 "FROM account a " +
                 "LEFT JOIN `order` o ON a.ID = o.AccountID " +
                 "WHERE a.UserName LIKE ? OR a.Name LIKE ? " +
                 "GROUP BY a.ID, a.UserName, a.Name, a.Email, a.Phone, a.Money " +
                 "ORDER BY TotalOrderAmount DESC " + // Sắp xếp theo TotalOrderAmount giảm dần
                 "LIMIT ? OFFSET ?";

    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + nameSearch + "%");
        ps.setString(2, "%" + nameSearch + "%");
        ps.setInt(3, 8); // Limit number of items per page to 8
        ps.setInt(4, (page - 1) * 8); // Calculate offset for the current page
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            AccountWithTotal account = new AccountWithTotal();
            account.setId(rs.getInt("ID"));
            account.setUserName(rs.getString("UserName"));
            account.setName(rs.getString("Name"));
            account.setEmail(rs.getString("Email"));
            account.setPhone(rs.getString("Phone"));
            account.setMoney(rs.getInt("Money"));
            account.setTotalOrderAmount(rs.getInt("TotalOrderAmount"));
            account.setTotalAccount(rs.getInt("TotalAccount"));
            
            // Add each account to the list
            listAccounts.add(account);
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listAccounts;
}

public int getmaxPageAccountWithTotalsbySearch(String nameSearch) {
    int totalAccounts = 0;
    int maxPage = 0;
String sql = "SELECT a.ID, a.UserName, a.Name, a.Email, a.Phone, a.Money, " +
             "COALESCE(SUM(o.Total), 0) as TotalOrderAmount, " +
             "(COALESCE(SUM(o.Total), 0) + a.Money) as TotalAccount " +
             "FROM account a " +
             "LEFT JOIN `order` o ON a.ID = o.AccountID " +
             "GROUP BY a.ID, a.UserName, a.Name, a.Email, a.Phone, a.Money " +
             "ORDER BY TotalOrderAmount DESC " + // Sắp xếp theo TotalOrderAmount giảm dần
             "LIMIT ? OFFSET ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalAccounts = rs.getInt("TotalAccounts");
        }
        maxPage = totalAccounts / 8;
        if (totalAccounts % 8 != 0) {
            maxPage++;
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return maxPage;
}
public void updateProductQuantity(int productId) {
    String countSql = "SELECT COUNT(*) FROM productdetail WHERE Name_ID = ?";
    String updateSql = "UPDATE product SET Quality = ? WHERE id = ?";

    try (PreparedStatement countStmt = connection.prepareStatement(countSql);
         PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {

        // Đếm số lượng trong bảng productdetail
        countStmt.setInt(1, productId);
        ResultSet rs = countStmt.executeQuery();
        int quantity = 0;
        if (rs.next()) {
            quantity = rs.getInt(1);
        }

        // Cập nhật quanlity trong bảng product
        updateStmt.setInt(1, quantity);
        updateStmt.setInt(2, productId);
        updateStmt.executeUpdate();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {

 Scanner scanner = new Scanner(System.in);
        System.out.println("nhap");
int k =0 ;
 k = scanner.nextInt();
         int q= 3;

         System.out.println(k);
        
}
}