package org.example.b1;

import java.sql.*;
import java.util.Scanner;

public class LoginDoctor {
    private static final String URL = "jdbc:mysql://localhost:3306/b1";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã bác sĩ: ");
        String code = sc.nextLine();
        System.out.println("Nhập mật khẩu: ");
        String pass = sc.nextLine();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                System.out.println("Đăng nhập thành công");
            }else {
                System.out.println("Sai tài khoản hoặc mật khẩu");
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
