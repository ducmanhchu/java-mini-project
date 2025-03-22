/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.groupthree.food_project;

import com.groupthree.food_project.views.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author dell
 */
public class Food_project {

    public static void main(String[] args) {
        Home frame = new Home();
        frame.setVisible(true);
        
        // Tạo mật khẩu mã hóa cho admin (Tạo tài khoản admin trên MySQLAdmin với mật khẩu này để có thể đăng nhập với quyền admin)
//        String hashedPassword = BCrypt.hashpw("admin1", BCrypt.gensalt());
//        System.out.println("Mật khẩu đã mã hóa: " + hashedPassword);
    }
}
