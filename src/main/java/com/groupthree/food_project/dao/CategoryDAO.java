/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupthree.food_project.dao;

import com.groupthree.food_project.models.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class CategoryDAO {
    
    public List<Category> getAllCategories() {
        
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM category";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    
    public Category getCategoryById(int categoryId) {
        String query = "SELECT * FROM category WHERE category_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Category(
                            rs.getInt("category_id"),
                            rs.getString("category_name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Category(0, "Không xác định");
    }
}
