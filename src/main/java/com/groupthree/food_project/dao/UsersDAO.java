/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupthree.food_project.dao;

import com.groupthree.food_project.models.*;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author dell
 */
public class UsersDAO {
    public static Users currentUser = null; // Lưu trạng thái xem người dùng có đang đăng nhập hay không    
    
    // Hàm xử lý đăng nhập
    public boolean login(String phoneNumber, String password) {
        String sql = "SELECT username, phone_number, password, role FROM users WHERE phone_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, phoneNumber);
            ResultSet rs = stmt.executeQuery(); // Interface chứa kết quả truy vấn từ CSDL

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    // Nếu đúng mật khẩu, lưu thông tin người dùng vào biến `currentUser`
                    currentUser = new Users(
                        rs.getString("username"),
                        rs.getString("phone_number"),
                        rs.getString("password"), // Mật khẩu đã hash
                        rs.getString("role")
                    );
                    return true; // Đăng nhập thành công
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Đăng nhập thất bại
    }
    
    
    // Kiểm tra xem tài khoản đã tồn tại chưa (Kiểm tra xem sđt đã được sử dụng cho tài khoản nào chưa)
    public boolean isUserExist (String phoneNumber) {
        String sql = "SELECT * FROM users WHERE phone_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
             stmt.setString(1, phoneNumber);
             ResultSet rs = stmt.executeQuery();
             return rs.next();  // Trả về true nếu user có tồn tại
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Đăng ký tài khoản mới
    public boolean registerUser(Users user) {
        if (isUserExist(user.getPhoneNumber())) {
            return false;   // User đã tồn tại
        }
        String sql = "INSERT INTO users (username, phone_number, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
             stmt.setString(1, user.getUsername());
             stmt.setString(2, user.getPhoneNumber());
             stmt.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));    // Mã hóa mật khẩu
             stmt.setString(4, user.getRole());
             
             return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
