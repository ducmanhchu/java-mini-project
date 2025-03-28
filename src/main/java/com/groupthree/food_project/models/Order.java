/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.groupthree.food_project.models;

import java.sql.Timestamp;

/**
 *
 * @author dell
 */
public class Order {
    private int orderId;
    private String status;
    private String name;
    private String phoneNumber;
    private String address;
    private double totalPrice;
    private Timestamp createdAt;
    private int userId;

    public Order() {}

    public Order(int orderId, String status, String name, String phoneNumber, String address, double totalPrice, Timestamp createdAt, int userId) {
        this.orderId = orderId;
        this.status = status;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    // Getter & Setter
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
