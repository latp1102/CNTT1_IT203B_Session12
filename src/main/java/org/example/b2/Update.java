package org.example.b2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Update {
    private static final String URL = "jdbc:mysql://localhost:3306/b2";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Nhập id bệnh nhân: ");
        int id = sc.nextInt();
        System.out.println("Nhập nhiệt độ: ");
        double temp = sc.nextDouble();
        System.out.println("Nhập nhip tim: ");
        int heartRate = sc.nextInt();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "UPDATE Patients SET temp = ?, heart_rate = ? WHERE patient_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1,temp);
            ps.setInt(2,heartRate);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            if(rows > 0){
                System.out.println("Cập nhật thành công");
            }else {
                System.out.println("Ko tìm thấy bệnh nhân");
            }
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
