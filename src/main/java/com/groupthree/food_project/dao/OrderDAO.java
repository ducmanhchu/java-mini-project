/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupthree.food_project.dao;

import com.groupthree.food_project.models.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class OrderDAO {
    
    public List<Order> getAllOrders() {
            List<Order> orders = new ArrayList<>();
            String query = "SELECT * FROM orders";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    orders.add(new Order(
                            rs.getInt("order_id"),
                            rs.getString("status"),
                            rs.getString("name"),
                            rs.getString("phone_number"),
                            rs.getString("address"),
                            rs.getDouble("total_price"),
                            rs.getTimestamp("created_at"),
                            rs.getInt("user_id")   
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return orders;
        }
    
    public Order getOrderById(int orderID) {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                            rs.getInt("order_id"),
                            rs.getString("status"),
                            rs.getString("name"),
                            rs.getString("phone_number"),
                            rs.getString("address"),
                            rs.getDouble("total_price"),
                            rs.getTimestamp("created_at"),
                            rs.getInt("user_id") 
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Order(0, null, null, null, null, 0, null, 0);
    }
    
    public boolean updateOrder(Order order) {
        String query = "UPDATE orders SET status = ?, name = ?, phone_number = ?, address = ?, total_price = ?, created_at = ?, user_id = ? WHERE order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, order.getStatus());
            st.setString(2, order.getPhoneNumber());
            st.setString(3, order.getName());
            st.setString(4, order.getAddress());
            st.setDouble(5, order.getTotalPrice());
            st.setTimestamp(6, order.getCreatedAt());
            st.setInt(7, order.getUserId());
            st.setInt(8, order.getOrderId());

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<String> getOrderStatuses() {
        List<String> statuses = new ArrayList<>();
        String sql = "SHOW COLUMNS FROM orders LIKE 'status'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                String enumValues = rs.getString("Type"); // Lấy chuỗi "enum('pending','confirmed',...)"
                enumValues = enumValues.substring(5, enumValues.length() - 1); // Loại bỏ "enum(" và ")"
                String[] values = enumValues.split("','"); // Tách từng giá trị

                for (String value : values) {
                    statuses.add(value.replace("'", "")); // Loại bỏ dấu nháy đơn nếu còn
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statuses;
    }

    
}
