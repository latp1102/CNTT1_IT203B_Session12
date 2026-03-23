package org.example.b3;

import java.sql.*;

public class GetSur {
    private static final String URL = "jdbc:mysql://localhost:3306/b3";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            CallableStatement callableStatement = connection.prepareCall("{call getSur(?,?)}");
            callableStatement.setInt(1,505);
            callableStatement.registerOutParameter(2, Types.DECIMAL);
            callableStatement.execute();
            double sur = callableStatement.getDouble(2);
            System.out.println("Chi phí phẫu thuật: " + sur);
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
