/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupthree.food_project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.groupthree.food_project.models.Order;

/**
 *
 * @author dell
 */
public class OrderDAO {
    public int addOrder(Order order) {
        String sql = "INSERT INTO `order` (status, name, phone_number, address, total_price, created_at, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, order.getStatus());
            stmt.setString(2, order.getName());
            stmt.setString(3, order.getPhoneNumber());
            stmt.setString(4, order.getAddress());
            stmt.setDouble(5, order.getTotalPrice());
            stmt.setTimestamp(6, new java.sql.Timestamp(order.getCreatedAt().getTime()));
            stmt.setInt(7, order.getUserId());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                try (var generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Lấy order_id vừa tạo
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu thất bại
    }
}
