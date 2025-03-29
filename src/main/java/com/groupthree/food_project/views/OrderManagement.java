/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.groupthree.food_project.views;

import com.groupthree.food_project.dao.OrderDAO;
import com.groupthree.food_project.models.Order;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class OrderManagement extends javax.swing.JFrame {
    
    OrderDAO orderDAO = new OrderDAO();
    List<Order> orders = orderDAO.getAllOrders();
    
    int orderId = -1;
    
    /**
     * Creates new form OrderManagement
     */
    public OrderManagement() {
        initComponents();
        setLocationRelativeTo(null);
        
        initTableOrderListener();
        loadTableOrder();
    }
    
    private void initTableOrderListener(){
        tableOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tableOrder.getSelectedRow(); // Lấy dòng được chọn
                if (row >= 0) {
                    orderId = (int) tableOrder.getValueAt(row, 0); // Lấy ID danh mục
                }
            }
        });
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
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOrder = new javax.swing.JTable();
        btnUpdateStatus = new javax.swing.JButton();
        btnBack2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Đơn hàng");

        tableOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Người nhận", "SDT", "Địa chỉ", "Tổng tiền", "Ngày tạo", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOrder.setRowHeight(30);
        tableOrder.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableOrder);
        if (tableOrder.getColumnModel().getColumnCount() > 0) {
            tableOrder.getColumnModel().getColumn(0).setResizable(false);
            tableOrder.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableOrder.getColumnModel().getColumn(1).setPreferredWidth(125);
            tableOrder.getColumnModel().getColumn(3).setPreferredWidth(150);
            tableOrder.getColumnModel().getColumn(4).setPreferredWidth(75);
            tableOrder.getColumnModel().getColumn(5).setPreferredWidth(125);
            tableOrder.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        btnUpdateStatus.setBackground(new java.awt.Color(0, 0, 204));
        btnUpdateStatus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnUpdateStatus.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateStatus.setText("Cập nhật trạng thái");
        btnUpdateStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateStatusActionPerformed(evt);
            }
        });

        btnBack2.setBackground(new java.awt.Color(102, 102, 102));
        btnBack2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBack2.setForeground(new java.awt.Color(255, 255, 255));
        btnBack2.setText("Trở lại");
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStatusActionPerformed
        // TODO add your handling code here:
        if(orderId == -1)
            JOptionPane.showMessageDialog(this, "Hãy chọn đơn hàng muốn cập nhật!");
        else{
            Order o = orderDAO.getOrderById(orderId);
            
            // Kiểm tra nếu đơn hàng đã bị hủy thì không cho cập nhật
            if ("cancelled".equalsIgnoreCase(o.getStatus())) {
                JOptionPane.showMessageDialog(this, "Đơn hàng đã bị hủy, không thể cập nhật trạng thái!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; 
            }
            
            UpdateStatus updateStatus = new UpdateStatus(o);
            updateStatus.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setVisible(false);
            updateStatus.setVisible(true);
        }
    }//GEN-LAST:event_btnUpdateStatusActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        FoodManagement foodManagement = new FoodManagement();
        foodManagement.setVisible(true);
        
    }//GEN-LAST:event_btnBack2ActionPerformed

    private void loadTableOrder(){
        // Lấy DefaultTableModel từ Jtable
        DefaultTableModel model = (DefaultTableModel) tableOrder.getModel();
       // Xóa dữ liệu trong JTable nếu có
        model.setRowCount(0);
        
        for(Order order : orders){
            model.addRow(new Object[] {
                order.getOrderId(),
                order.getName(),
                order.getPhoneNumber(),
                order.getAddress(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getStatus()
            });
        }
        
        // Tạo renderer để căn giữa nội dung
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Lấy số lượng cột trong bảng
        int columnCount = tableOrder.getColumnModel().getColumnCount();

        // Duyệt qua tất cả các cột và đặt renderer
        for (int i = 0; i < columnCount; i++) {
            tableOrder.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
    }
    
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
            java.util.logging.Logger.getLogger(OrderManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnUpdateStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableOrder;
    // End of variables declaration//GEN-END:variables
}
