/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.groupthree.food_project.views;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.groupthree.food_project.dao.CartDAO;
import com.groupthree.food_project.dao.DatabaseConnection;
import com.groupthree.food_project.dao.OrderDAO;
import com.groupthree.food_project.dao.OrderDetailDAO;
import com.groupthree.food_project.dao.UsersDAO;
import com.groupthree.food_project.models.Cart;
import com.groupthree.food_project.models.Order;
import com.groupthree.food_project.models.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Alone
 */
public class CheckOut extends javax.swing.JFrame {
    private List<Cart> cartItems;
    private MyOrder myOrderPage;// Danh sách sản phẩm trong giỏ hàng

    /**
     * Creates new form CheckOut
     */
    public CheckOut() {
        this.cartItems = new ArrayList<>(); // Tạo danh sách rỗng để tránh lỗi NullPointerException
        initComponents();
    }

    public CheckOut(List<Cart> cartItems) {
        this.cartItems = cartItems;
        initComponents();
        loadCoMethod();
        checkMethod();
        coMethod.addActionListener(e -> checkMethod());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        CusName = new javax.swing.JTextField();
        CusPhone = new javax.swing.JTextField();
        JLabel2 = new javax.swing.JLabel();
        JLabel3 = new javax.swing.JLabel();
        CusAddress = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        coMethod = new javax.swing.JComboBox<>();
        addOrderButton = new javax.swing.JToggleButton();
        QRImg = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("THANH TOÁN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Họ và tên");

        CusName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CusNameActionPerformed(evt);
            }
        });

        JLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabel2.setText("SĐT");

        JLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLabel3.setText("Giao tới");

        CusAddress.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Phương thức thanh toán");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Thông tin khách hàng");

        coMethod.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        coMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coMethodActionPerformed(evt);
            }
        });

        addOrderButton.setBackground(new java.awt.Color(0, 0, 0));
        addOrderButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addOrderButton.setForeground(new java.awt.Color(255, 255, 255));
        addOrderButton.setText("Đặt món");
        addOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator2)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(134, 134, 134)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(JLabel3)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(CusName,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                172,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel2))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(JLabel2)
                                                                        .addComponent(CusPhone,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                178,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(CusAddress)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel3))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(coMethod, 0,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE))
                                                        .addComponent(jLabel4)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(141, 141, 141)
                                                .addComponent(addOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(128, 128, 128)
                                                .addComponent(QRImg, javax.swing.GroupLayout.PREFERRED_SIZE, 141,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(18, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(JLabel2)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(CusName, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CusPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(JLabel3)
                                .addGap(3, 3, 3)
                                .addComponent(CusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(coMethod, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(QRImg, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(addOrderButton)
                                .addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String cusName = CusName.getText();
        String phone = CusPhone.getText();
        String address = CusAddress.getText();

        if (cusName.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double totalPrice = calculateTotalPrice();
        int userId = UsersDAO.currentUser.getUserId();
        Timestamp createdAt = new Timestamp(new Date().getTime());

        Order order = new Order("pending", cusName, phone, address, totalPrice, createdAt, userId);
        OrderDAO orderDAO = new OrderDAO();
        int orderId = orderDAO.addOrder(order);

        if (orderId > 0) {
            List<Cart> cartItems = CartDAO.getCartItems(userId);
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            boolean allDetailsAdded = true;

            for (Cart item : cartItems) {
                int productId = item.getProductId();
                if (!isProductExists(productId)) {
                    System.out.println("Error: Product ID " + productId + " does not exist!");
                    allDetailsAdded = false;
                    break;
                }
                System.out.println(item.getProductId());
                OrderDetail orderDetail = new OrderDetail(orderId, item.getProductId(), item.getQuantity(),
                        item.getPrice());
                boolean detailAdded = orderDetailDAO.addOrderDetail(orderDetail);
                if (!detailAdded) {
                    allDetailsAdded = false;
                    break;
                }
            }

            if (allDetailsAdded) {
                JOptionPane.showMessageDialog(this, "Đặt hàng thành công!", "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
                CartDAO.clearCart(userId);
                if (myOrderPage != null) {
                    myOrderPage.refreshOrderList();
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Đặt hàng thất bại!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thêm đơn hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    // GEN-LAST:event_addOrderButtonActionPerformed
    public boolean isProductExists(int productId) {
    String sql = "SELECT COUNT(*) FROM product WHERE product_id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu sản phẩm tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private double calculateTotalPrice() {
        if (cartItems == null || cartItems.isEmpty()) {
            return 0.0; // Trả về 0 nếu giỏ hàng rỗng
        }

        return cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity()) // Nhân giá với số lượng
                .sum();
    }

    private void CusNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CusNameActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_CusNameActionPerformed

    private void coMethodActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_coMethodActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_coMethodActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckOut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CusAddress;
    private javax.swing.JTextField CusName;
    private javax.swing.JTextField CusPhone;
    private javax.swing.JLabel JLabel2;
    private javax.swing.JLabel JLabel3;
    private javax.swing.JLabel QRImg;
    private javax.swing.JToggleButton addOrderButton;
    private javax.swing.JComboBox<String> coMethod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

    private void loadCoMethod() {
        coMethod.removeAllItems();
        coMethod.addItem("Thanh toán trực tiếp");
        coMethod.addItem("Chuyển khoản ngân hàng");
    }

    private void checkMethod() {
        String selectedCategory = (String) coMethod.getSelectedItem();
        if ("Chuyển khoản ngân hàng".equals(selectedCategory)) {
            try {
                URL imageUrl = new URL(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSLanrSLn-Z-2l3iJKxDbwYtC_BH5CqJov5SLdNIcImJ_-m1m7f9ijPARDFxeohlDU2ZM&usqp=CAU");
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image originalImage = originalIcon.getImage();

                // Thay đổi kích thước để vừa với nhãn
                Image resizedImage = originalImage.getScaledInstance(141, 131, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);

                QRImg.setIcon(resizedIcon);
                QRImg.revalidate();
                QRImg.repaint();
            } catch (MalformedURLException ex) {
                QRImg.setIcon(null);
                QRImg.setText("Không thể tải hình ảnh");
            }
        } else {
            QRImg.setIcon(null);
            QRImg.setText("");
        }
    }
}
