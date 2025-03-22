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
                            rs.getString("image"),
                            rs.getInt("category_id")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return products;
        }
  
}
