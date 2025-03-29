/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupthree.food_project.dao;

import com.groupthree.food_project.models.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class CartDAO {
    public static List<Cart> getCartItems(int userId) {
        List<Cart> cartItems = new ArrayList<>();
        String sql = "SELECT cart.cart_id, cart.product_id, product.name, cart.quantity, product.price " +
                "FROM cart JOIN product ON cart.product_id = product.product_id " +
                "WHERE cart.user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cartItems.add(new Cart(
                        rs.getInt("cart_id"),
                        rs.getInt("product_id"), // Thêm product_id
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }
    public static void clearCart(int userId) {
        String sql = "DELETE FROM cart WHERE user_id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Cập nhật số lượng của một sản phẩm trong giỏ
    public static void updateCartItem(int cartId, int quantity) {
        String query = "UPDATE cart SET quantity = ? WHERE cart_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, cartId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addToCart(int userId, int productId, int quantity) {
        String sqlCheck = "SELECT cart_id, quantity FROM cart WHERE user_id = ? AND product_id = ?";
        String sqlInsert = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE cart SET quantity = ? WHERE cart_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement checkStmt = conn.prepareStatement(sqlCheck)) {

            checkStmt.setInt(1, userId);
            checkStmt.setInt(2, productId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) { // Nếu sản phẩm đã có trong giỏ
                int cartId = rs.getInt("cart_id");

                if (quantity == 0) {
                    removeFromCart(cartId); // Gọi hàm xóa sản phẩm khỏi giỏ
                    JOptionPane.showMessageDialog(null, "Sản phẩm đã được xóa khỏi giỏ hàng!", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdate)) {
                        updateStmt.setInt(1, quantity);
                        updateStmt.setInt(2, cartId);
                        updateStmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Đã cập nhật số lượng sản phẩm!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else { // Nếu sản phẩm chưa có trong giỏ và số lượng > 0, thêm mới
                if (quantity > 0) {
                    try (PreparedStatement insertStmt = conn.prepareStatement(sqlInsert)) {
                        insertStmt.setInt(1, userId);
                        insertStmt.setInt(2, productId);
                        insertStmt.setInt(3, quantity);
                        insertStmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm vào giỏ hàng!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không hợp lệ!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật giỏ hàng: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void removeFromCart(int cartId) {
        String sqlDelete = "DELETE FROM cart WHERE cart_id = ?";
        String sqlResetIds = "SET @row_number = 0";
        String sqlUpdateIds = "UPDATE cart SET cart_id = (@row_number := @row_number + 1) ORDER BY cart_id";
        String sqlResetAutoIncrement = "ALTER TABLE cart AUTO_INCREMENT = 1";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete);
                Statement stmtReset = conn.createStatement()) {

            // Xóa sản phẩm theo cart_id
            stmtDelete.setInt(1, cartId);
            stmtDelete.executeUpdate();

            // Đặt biến @row_number
            stmtReset.executeUpdate(sqlResetIds);

            // Cập nhật lại ID tuần tự
            stmtReset.executeUpdate(sqlUpdateIds);

            // Reset AUTO_INCREMENT về đúng giá trị
            stmtReset.executeUpdate(sqlResetAutoIncrement);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa khỏi giỏ hàng: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
