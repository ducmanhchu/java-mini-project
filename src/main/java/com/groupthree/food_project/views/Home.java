/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.groupthree.food_project.views;

import com.groupthree.food_project.dao.*;
import com.groupthree.food_project.models.*;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dell
 */
public class Home extends javax.swing.JFrame {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private List<Product> productList;
    private CartView cartForm;
    private MyOrder myOrderForm;
    private static Home instance;

    /**
     * Creates new form Home
     */

    public Home() {
        cartForm = new CartView();
        if (UsersDAO.currentUser != null) {
            cartForm.loadCart(UsersDAO.currentUser.getUserId());
            myOrderForm = new MyOrder();
        }
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
        initComponents();
        setLocationRelativeTo(null);

        // Mặc định khi chưa đăng nhập thì ẩn nút giỏ hàng và đơn hàng
        cartBtn.setVisible(false);
        orderBtn.setVisible(false);
        checkLogin();

        loadCategoryBox();
        loadProductTable();
        setupTableSelectionListener(); // Hiển thị preview mỗi khi sản phẩm trong bảng được chọn
        categoryBox.addActionListener(e -> loadProductTable()); // Gọi hàm lọc mỗi khi danh mục được chọn

        // Tìm kiếm sản phẩm khi nhấn nút
        searchBtn.addActionListener(e -> loadProductTable());
    }
    
    public static Home getInstance() {
        if (instance == null) {
            instance = new Home();
        }
        return instance;
    }

    // Kiểm tra trạng thái đăng nhập
    private void checkLogin() {
        if (UsersDAO.currentUser != null) {
            accountBtn.setText(UsersDAO.currentUser.getUsername()); // Hiển thị tên tài khoản
            cartBtn.setVisible(true);
            orderBtn.setVisible(true);
        } else {
            accountBtn.setText("Đăng nhập");
            cartBtn.setVisible(false);
            orderBtn.setVisible(false);
        }
    }

    // Hiển thị danh sách sản phẩm
    private void loadProductTable() {
        String selectedCategory = (String) categoryBox.getSelectedItem(); // Lấy danh mục được chọn
        String searchText = searchTxt.getText().trim().toLowerCase();

        productList = productDAO.getAllProducts();
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);

        for (Product p : productList) {
            Category category = categoryDAO.getCategoryById(p.getCategoryId());
            String categoryName = category.getCategoryName();

            // Lọc theo tên sản phẩm hoặc danh mục
            boolean matchCategory = "Tất cả sản phẩm".equals(selectedCategory) || categoryName.equals(selectedCategory);
            boolean matchSearch = searchText.isEmpty() || p.getName().toLowerCase().contains(searchText);

            if (matchCategory && matchSearch) {
                model.addRow(
                        new Object[] { p.getProductId(), "  " + p.getName(), p.getPrice() + " VNĐ", categoryName });
            }
        }

        // Tạo renderer để căn giữa nội dung
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Căn giữa nội dung của các cột STT, Giá và Danh mục
        productTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // STT
        productTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Giá
        productTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Danh mục
    }

    // Xử lý sự kiện mỗi khi một sản phẩm trong bảng được chọn
    private void setupTableSelectionListener() {
        productTable.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting() && productTable.getSelectedRow() != -1) {
                int selectedRow = productTable.getSelectedRow();

                // Lấy productId từ bảng
                int productId = (int) productTable.getValueAt(selectedRow, 0);

                // Tìm sản phẩm trong danh sách đã lọc
                for (Product p : productList) {
                    if (p.getProductId() == productId) {
                        showProductDetails(p);
                        break;
                    }
                }
            }
        });
    }

    // Hiển thị chi tiết sản phẩm
    private void showProductDetails(Product product) {
        nameLabel.setText(product.getName());
        priceLabel.setText("Giá: " + product.getPrice() + " VNĐ");
        descriptionLabel.setText("<html>" + product.getDescription() + "</html>"); // Hỗ trợ xuống dòng
        stockTxt.setText("Trong kho: " + product.getStock());

        // Tải và hiển thị hình ảnh
        try {
            URL imageUrl = new URL(product.getImage());
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            Image originalImage = originalIcon.getImage();

            // Thay đổi kích thước để vừa với nhãn
            Image resizedImage = originalImage.getScaledInstance(305, 250, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            imageLabel.setIcon(resizedIcon);
        } catch (MalformedURLException ex) {
            imageLabel.setIcon(null);
            imageLabel.setText("Không thể tải hình ảnh");
        }
    }

    // Lấy thông tin về các danh mục
    private void loadCategoryBox() {
        categoryBox.removeAllItems();
        categoryBox.addItem("Tất cả sản phẩm");

        List<Category> categories = categoryDAO.getAllCategories();
        for (Category cate : categories) {
            categoryBox.addItem(cate.getCategoryName());
        }
        categoryBox.setSelectedIndex(0); // Mặc định chọn Tất cả sản phẩm
    }

    private double convertPriceStringToDouble(String priceString) {
        if (priceString == null || priceString.isEmpty()) {
            return 0.0;
        }

        // Loại bỏ "VNĐ" và khoảng trắng dư thừa
        String cleanPrice = priceString.replace(" VNĐ", "").trim();

        // Nếu có dấu phẩy, chuyển thành dấu chấm
        cleanPrice = cleanPrice.replace(",", ".");

        // Loại bỏ dấu chấm phân cách hàng nghìn (nếu có)
        if (cleanPrice.matches(".*\\..*\\d{3}")) { // Nếu có dấu chấm trước 3 chữ số cuối
            cleanPrice = cleanPrice.replace(".", "");
        }

        try {
            return Double.parseDouble(cleanPrice);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lỗi chuyển đổi giá: " + priceString);
            return 0.0;
        }
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

        jLabel1 = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        categoryBox = new javax.swing.JComboBox<>();
        categoryLabel = new javax.swing.JLabel();
        searchTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        cartBtn = new javax.swing.JButton();
        orderBtn = new javax.swing.JButton();
        accountBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        previewPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        addToCartBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        amountTxt = new javax.swing.JTextField();
        stockTxt = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        header.setBackground(new java.awt.Color(255, 255, 255));

        categoryBox.setBackground(new java.awt.Color(204, 204, 204));
        categoryBox.setForeground(new java.awt.Color(51, 51, 51));
        categoryBox.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        categoryBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryBoxActionPerformed(evt);
            }
        });

        categoryLabel.setText("Danh mục");

        searchBtn.setBackground(new java.awt.Color(102, 102, 102));
        searchBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setText("Tìm kiếm");

        cartBtn.setBackground(new java.awt.Color(153, 204, 255));
        cartBtn.setText("Giỏ hàng");
        cartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartBtnActionPerformed(evt);
            }
        });

        orderBtn.setBackground(new java.awt.Color(153, 204, 255));
        orderBtn.setText("Đơn hàng của tôi");
        orderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderBtnActionPerformed(evt);
            }
        });

        accountBtn.setBackground(new java.awt.Color(153, 204, 255));
        accountBtn.setText("Đăng nhập");
        accountBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
                headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(categoryLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(searchTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBtn)
                                .addGap(148, 148, 148)
                                .addComponent(cartBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(orderBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(accountBtn)
                                .addContainerGap()));
        headerLayout.setVerticalGroup(
                headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(headerLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(categoryLabel)
                                        .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchBtn)
                                        .addComponent(cartBtn)
                                        .addComponent(orderBtn)
                                        .addComponent(accountBtn)
                                        .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)));

        productTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        productTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "ID", "Tên món ăn", "Giá", "Danh mục"
                }) {
            Class[] types = new Class[] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        productTable.setRowHeight(30);
        productTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(productTable);
        if (productTable.getColumnModel().getColumnCount() > 0) {
            productTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            productTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            productTable.getColumnModel().getColumn(2).setPreferredWidth(125);
            productTable.getColumnModel().getColumn(3).setPreferredWidth(125);
        }

        previewPanel.setBackground(new java.awt.Color(255, 255, 255));

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nameLabel.setText("Tên sản phẩm");

        priceLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        priceLabel.setForeground(new java.awt.Color(51, 0, 255));
        priceLabel.setText("Giá sản phẩm");

        descriptionLabel.setText("Mô tả sản phẩm");

        addToCartBtn.setBackground(new java.awt.Color(0, 0, 0));
        addToCartBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addToCartBtn.setForeground(new java.awt.Color(255, 255, 255));
        addToCartBtn.setText("Thêm vào giỏ hàng");
        addToCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Số lượng:");

        amountTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        amountTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        amountTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        amountTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountTxtActionPerformed(evt);
            }
        });

        stockTxt.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        stockTxt.setText("Trong kho:");

        javax.swing.GroupLayout previewPanelLayout = new javax.swing.GroupLayout(previewPanel);
        previewPanel.setLayout(previewPanelLayout);
        previewPanelLayout.setHorizontalGroup(
                previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(previewPanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(previewPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nameLabel)
                                        .addGroup(previewPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(amountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(addToCartBtn))
                                        .addGroup(previewPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, previewPanelLayout
                                                        .createSequentialGroup()
                                                        .addComponent(priceLabel)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(stockTxt))
                                                .addComponent(descriptionLabel,
                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 305,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 30, Short.MAX_VALUE)));
        previewPanelLayout.setVerticalGroup(
                previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(previewPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(previewPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(priceLabel)
                                        .addComponent(stockTxt))
                                .addGap(18, 18, 18)
                                .addComponent(descriptionLabel)
                                .addGap(70, 70, 70)
                                .addGroup(previewPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addToCartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(amountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(previewPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542,
                                                Short.MAX_VALUE))));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void orderBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_orderBtnActionPerformed
        // TODO add your handling code here:
        myOrderForm.setVisible(true);
    }// GEN-LAST:event_orderBtnActionPerformed

    private void categoryBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_categoryBoxActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_categoryBoxActionPerformed

    private void amountTxtActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_amountTxtActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_amountTxtActionPerformed

    private void accountBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_accountBtnActionPerformed
        // TODO add your handling code here:
        if (UsersDAO.currentUser == null) {
            this.dispose();
            new Login(this, new Admin()).setVisible(true);
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                UsersDAO.currentUser = null; // Xóa thông tin đăng nhập
                checkLogin();
                JOptionPane.showMessageDialog(this, "Bạn đã đăng xuất!");
            }
        }
    }// GEN-LAST:event_accountBtnActionPerformed

    private void addToCartBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addToCartBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trước khi thêm vào giỏ hàng!");
            return;
        }

        int productId = (int) productTable.getValueAt(selectedRow, 0);
        int quantity = convertStringToInt(amountTxt.getText());
        int userId = UsersDAO.currentUser.getUserId();

        CartDAO.addToCart(userId, productId, quantity);
        cartForm.loadCart(userId);
    }

    private int convertStringToInt(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0; // Trả về 0 nếu chuỗi rỗng hoặc null
        }
        try {
            // Loại bỏ khoảng trắng và chuyển đổi sang số nguyên
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi chuyển đổi số lượng: " + text, "Lỗi", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private void cartBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cartBtnActionPerformed
        // TODO add your handling code here:
        cartForm.setVisible(true);
    }// GEN-LAST:event_cartBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accountBtn;
    private javax.swing.JButton addToCartBtn;
    private javax.swing.JTextField amountTxt;
    private javax.swing.JButton cartBtn;
    private javax.swing.JComboBox<String> categoryBox;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JPanel header;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton orderBtn;
    private javax.swing.JPanel previewPanel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTable productTable;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JLabel stockTxt;
    // End of variables declaration//GEN-END:variables

}
