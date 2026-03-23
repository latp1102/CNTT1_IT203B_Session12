package org.example.b4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class InsertFast {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/hospital_db";
        String user = "root";
        String password = "123456";

        return DriverManager.getConnection(url, user, password);
    }
    public static void insert(List<TestResult> list) throws Exception {
        Connection conn = getConnection();
        String sql = "INSERT INTO Results(data) VALUES(?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        long start = System.nanoTime();
        for (TestResult tr : list) {
            pstmt.setString(1, tr.getData());
            pstmt.executeUpdate();
        }
        long end = System.nanoTime();
        System.out.println("PreparedStatement time: " + (end - start) / 1_000_000 + " ms");
        conn.close();
    }
}