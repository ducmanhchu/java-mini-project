/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupthree.food_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.groupthree.food_project.models.OrderDetail;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class OrderDetailDAO {
    // Lưu danh sách chi tiết đơn hàng vào bảng order_detail
    public boolean addOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO order_detail (quantity, unit_price, order_id, product_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderDetail.getQuantity());
            stmt.setDouble(2, orderDetail.getUnitPrice());
            stmt.setInt(3, orderDetail.getOrderId());
            stmt.setInt(4, orderDetail.getProductId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Integer> getOrderIds(int userId) {
        List<Integer> orderIds = new ArrayList<>();
        String sql = "SELECT DISTINCT order_id FROM `order` WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderIds.add(rs.getInt("order_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderIds;
    }

    public static List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT od.order_detail_id, od.order_id, od.product_id, p.name AS product_name, od.quantity, od.unit_price "
                +
                "FROM order_detail od " +
                "JOIN product p ON od.product_id = p.product_id " + // Chỉ cần JOIN với product
                "WHERE od.order_id = ?"; // Điều kiện đúng

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderDetails.add(new OrderDetail(
                        rs.getInt("order_detail_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getString("product_name"), // Lấy tên sản phẩm
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    public static String getOrderStatus(int orderId) {
        String query = "SELECT status FROM `order` WHERE order_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean cancelOrder(int orderId) {
        String deleteOrderDetailQuery = "DELETE FROM order_detail WHERE order_id = ?";
        String updateOrderQuery = "UPDATE `order` SET status = 'cancelled' WHERE order_id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteOrderDetailQuery);
                    PreparedStatement updateStmt = conn.prepareStatement(updateOrderQuery)) {

                // Xóa các mục trong order_detail trước
                deleteStmt.setInt(1, orderId);
                deleteStmt.executeUpdate();

                // Cập nhật trạng thái đơn hàng
                updateStmt.setInt(1, orderId);
                int rowsAffected = updateStmt.executeUpdate();

                conn.commit(); // Xác nhận thay đổi

                return rowsAffected > 0;
            } catch (SQLException e) {
                conn.rollback(); // Hoàn tác nếu có lỗi
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static List<OrderDetail> getOrderList(int userId) {
        List<OrderDetail> orderList = new ArrayList<>();
        String sql = "SELECT DISTINCT od.order_detail_id, od.order_id, od.product_id, p.name, od.quantity, od.unit_price "
                +
                "FROM order_detail od " +
                "JOIN `order` o ON o.order_id = od.order_id " +
                "JOIN product p ON od.product_id = p.product_id " +
                "WHERE o.user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderList.add(new OrderDetail(
                        rs.getInt("order_detail_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getString("name"), // ✅ Lấy tên sản phẩm
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

}
