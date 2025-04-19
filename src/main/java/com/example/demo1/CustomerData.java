package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerData {
    private static final String URL = "jdbc:mysql://localhost:3306/vehicle_rental";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, contact, license) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getContact());
            stmt.setString(3, customer.getLicense());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT name, contact, license FROM customers";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                String license = rs.getString("license");
                list.add(new Customer(name, contact, license));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
