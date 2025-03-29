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
    private int productId; 
    private String productName;
    private int quantity;
    private double price;

    public Cart(int cartId, int productId, String productName, int quantity, double price) {
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter & Setter
    public int getCartId() {
        return cartId;
    }
    public int getProductId() { return productId; }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
