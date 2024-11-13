package com.example.thi_module3.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static final String DB_Url="jdbc:mysql://localhost:3306/quanlyphongtro";
    private static final String  DB_User= "root";
    private static  final String DB_PASSWORD="732004";
    public  static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_Url, DB_User, DB_PASSWORD);
            System.out.println("connection successfully ");
        } catch (ClassNotFoundException sqlConnection) {
            System.out.println("Failed to load JDBC driver");
        } catch (SQLException e) {
            System.out.println( e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }
}
