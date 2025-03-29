/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.groupthree.food_project.views;
import com.groupthree.food_project.dao.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dell
 */
public class Report extends javax.swing.JFrame {
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    /**
     * Creates new form Report
     */
    public Report() {
        orderDAO = new OrderDAO();
        productDAO = new ProductDAO();
        initComponents();
        setLocationRelativeTo(null);
        loadReportData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        totalRevenueLabel = new javax.swing.JLabel();
        totalOrdersLabel = new javax.swing.JLabel();
        completedOrdersLabel = new javax.swing.JLabel();
        cancelledOrdersLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bestSellingTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("Thống kê bán hàng");

        backBtn.setBackground(new java.awt.Color(153, 153, 153));
        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("Quay lại");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        totalRevenueLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        totalRevenueLabel.setForeground(new java.awt.Color(0, 102, 255));
        totalRevenueLabel.setText("Tổng doanh thu:");

        totalOrdersLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        totalOrdersLabel.setForeground(new java.awt.Color(0, 153, 153));
        totalOrdersLabel.setText("Tổng số đơn hàng:");

        completedOrdersLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        completedOrdersLabel.setForeground(new java.awt.Color(0, 153, 51));
        completedOrdersLabel.setText("Số đơn hoàn thành:");

        cancelledOrdersLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cancelledOrdersLabel.setForeground(new java.awt.Color(204, 0, 0));
        cancelledOrdersLabel.setText("Số đơn bị hủy:");

        bestSellingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Sản phẩm", "Sản phẩm", "Lượt bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bestSellingTable.setRowHeight(30);
        jScrollPane1.setViewportView(bestSellingTable);
        if (bestSellingTable.getColumnModel().getColumnCount() > 0) {
            bestSellingTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            bestSellingTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            bestSellingTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Top 10 sản phẩm bán chạy nhất");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(totalRevenueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                                .addComponent(totalOrdersLabel)
                                .addGap(105, 105, 105)
                                .addComponent(completedOrdersLabel)
                                .addGap(105, 105, 105)
                                .addComponent(cancelledOrdersLabel)
                                .addGap(91, 91, 91))
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalRevenueLabel)
                    .addComponent(totalOrdersLabel)
                    .addComponent(completedOrdersLabel)
                    .addComponent(cancelledOrdersLabel))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(backBtn)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void loadReportData() {
        totalRevenueLabel.setText("Tổng doanh thu: " + String.format("%,.0f", orderDAO.getTotalRevenue()) + " VND");
        totalOrdersLabel.setText("Tổng số đơn hàng: " + orderDAO.getTotalOrders());
        completedOrdersLabel.setText("Số đơn hoàn thành: " + orderDAO.getOrderCountByStatus("completed"));
        cancelledOrdersLabel.setText("Số đơn bị hủy: " + orderDAO.getOrderCountByStatus("cancelled"));
        loadBestSellingProducts();
    }
    
    // Hiển thị sản phẩm bán chạy vào bảng
    private void loadBestSellingProducts() {
        List<Object[]> topProducts = productDAO.getTopSellingProducts(10);

        DefaultTableModel model = (DefaultTableModel) bestSellingTable.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trước khi load mới

        for (Object[] row : topProducts) {
            model.addRow(row); // Thêm dữ liệu vào bảng
        }
    }

    
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        FoodManagement food = new FoodManagement();
        this.setVisible(false);
        food.setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Report().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JTable bestSellingTable;
    private javax.swing.JLabel cancelledOrdersLabel;
    private javax.swing.JLabel completedOrdersLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel totalOrdersLabel;
    private javax.swing.JLabel totalRevenueLabel;
    // End of variables declaration//GEN-END:variables
}
