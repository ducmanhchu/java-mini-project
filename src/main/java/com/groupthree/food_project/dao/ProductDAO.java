/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupthree.food_project.dao;
import com.groupthree.food_project.models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class ProductDAO {
    
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("image"),
                        rs.getInt("category_id")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    public Product getProductById(int productId) {
        String query = "SELECT * FROM product WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock"),
                            rs.getString("image"),
                            rs.getInt("category_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Product(-1, "Không xác định", "Không xác định", 0, 0, "Không xác định", 0);
    }
    
    public boolean deleteProductById(int productId) {
        String query = "DELETE FROM product WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, productId);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0; // Nếu xóa được ít nhất 1 dòng thì trả về true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateProduct(Product product) {
        String query = "UPDATE product SET name = ?, description = ?, price = ?, stock = ?, image = ?, category_id = ? WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, product.getName());
            st.setString(2, product.getDescription());
            st.setDouble(3, product.getPrice());
            st.setInt(4, product.getStock());
            st.setString(5, product.getImage());
            st.setInt(6, product.getCategoryId());
            st.setInt(7, product.getProductId());

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean insertProduct(Product product) {
        String query = "INSERT INTO product (name, description, price, stock, image, category_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {

            st.setString(1, product.getName());
            st.setString(2, product.getDescription());
            st.setDouble(3, product.getPrice());
            st.setInt(4, product.getStock());
            st.setString(5, product.getImage());
            st.setInt(6, product.getCategoryId());

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;  // Nếu insert thành công thì trả về true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Giảm số lượng sản phẩm trong kho dựa trên id và số lượng sản phẩm đó đã bán
    public static boolean reduceProductQuantity(int productId, int quantitySold) {
        String query = "UPDATE product SET stock = stock - ? WHERE product_id = ? AND stock >= ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, quantitySold);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantitySold); // Đảm bảo không giảm quá mức tồn kho

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Lấy số lượng sản phẩm có trong kho dựa vào id
    public static int getProductStock(int productId) {
        String query = "SELECT stock FROM product WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("stock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Nếu có lỗi hoặc không tìm thấy sản phẩm, trả về 0
    }
    
    
   // Lấy ra các sản phẩm bán chạy nhất
    public List<Object[]> getTopSellingProducts(int limit) {
        List<Object[]> productList = new ArrayList<>();
        String query = "SELECT p.product_id, p.name, SUM(od.quantity) AS total_sold " +
                       "FROM order_detail od " +
                       "JOIN `order` o ON od.order_id = o.order_id " +
                       "JOIN product p ON od.product_id = p.product_id " +
                       "WHERE o.status = 'completed' " +
                       "GROUP BY p.product_id, p.name " +
                       "ORDER BY total_sold DESC " +
                       "LIMIT ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getInt("total_sold")
                };
                productList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

}
