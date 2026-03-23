package org.example.b5;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/b5";
    private static final String user = "root";
    private static final String password = "1234";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== menu =====");
            System.out.println("1. danh sách bệnh nhân");
            System.out.println("2. thêm bệnh nhân");
            System.out.println("3. cập nhật bệnh án");
            System.out.println("4. xuất viện & tính phí");
            System.out.println("5. thoát");
            System.out.print("chọn: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    listPatients();
                    break;
                case 2:
                    addPatient(sc);
                    break;
                case 3:
                    updateDisease(sc);
                    break;
                case 4:
                    discharge(sc);
                    break;
                case 5:
                    System.out.println("Thoát chương trình");
                    break;
                default:
                    System.out.println("Lựa chọn ko hợp lệ");
            }

        } while (choice != 5);
    }
    private static void listPatients() {
        String sql = "select code, name, age, department from patients";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                System.out.println(rs.getString("code") + " | " + rs.getString("name") + " | " + rs.getInt("age") + " | " + rs.getString("department")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addPatient(Scanner sc) {
        String sql = "insert into patients(code, name, age, department, disease, admission_days) values(?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            System.out.print("Nhập mã bệnh nhân: ");
            String code = sc.nextLine();

            System.out.print("Nhập tên bệnh nhân: ");
            String name = sc.nextLine();

            System.out.print("Nhập tuổi: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Nhập khoa điều trị: ");
            String dept = sc.nextLine();

            System.out.print("Nhập bệnh lý: ");
            String disease = sc.nextLine();

            System.out.print("Nhập số ngày nhập viện: ");
            int days = sc.nextInt();
            sc.nextLine();
            ps.setString(1, code);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, dept);
            ps.setString(5, disease);
            ps.setInt(6, days);

            ps.executeUpdate();
            System.out.println("thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void updateDisease(Scanner sc) {
        String sql = "update patients set disease = ? where id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            System.out.print("nhập id: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("disease mới: ");
            String disease = sc.nextLine();

            ps.setString(1, disease);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            System.out.println(rows > 0 ? "cập nhật thành công" : "không tìm thấy");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void discharge(Scanner sc) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.print("nhập id: ");
            int id = sc.nextInt();
            sc.nextLine();
            String sql = "select admission_days from patients where id=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    System.out.println("không tồn tại!");
                    return;
                }
                int days = rs.getInt("admission_days");
                try (CallableStatement cs = conn.prepareCall("{call calculate_discharge_fee(?, ?)}")) {
                    cs.setInt(1, days);
                    cs.registerOutParameter(2, Types.DECIMAL);
                    cs.execute();
                    double fee = cs.getDouble(2);
                    System.out.println("viện phí: " + fee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}