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
}
