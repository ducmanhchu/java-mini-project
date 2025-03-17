/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupthree.food_project.models;

/**
 *
 * @author dell
 */
public class Cart {
    private int cartId;
    private int quantity;
    private int userId;
    private int productId;

    public Cart() {}

    public Cart(int cartId, int quantity, int userId, int productId) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.userId = userId;
        this.productId = productId;
    }

    // Getter & Setter
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
}
