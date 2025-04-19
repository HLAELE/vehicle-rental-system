package com.example.demo1;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("Database connection successful!");
            conn.close();
        } else {
            System.out.println("Database connection failed.");
        }
    }
}
