package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleData {

    public static List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vehicles")) {

            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getString("vehicle_id"),
                        rs.getString("brand_model"),
                        rs.getString("category"),
                        rs.getDouble("rental_price"),
                        rs.getString("availability")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public static void addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (vehicle_id, brand_model, category, rental_price, availability) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVehicleId());
            stmt.setString(2, vehicle.getBrandModel());
            stmt.setString(3, vehicle.getCategory());
            stmt.setDouble(4, vehicle.getRentalPrice());
            stmt.setString(5, vehicle.getAvailability());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeVehicle(Vehicle vehicle) {
        String sql = "DELETE FROM vehicles WHERE vehicle_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVehicleId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateVehicle(Vehicle vehicle) {
        String sql = "UPDATE vehicles SET brand_model = ?, category = ?, rental_price = ?, availability = ? WHERE vehicle_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getBrandModel());
            stmt.setString(2, vehicle.getCategory());
            stmt.setDouble(3, vehicle.getRentalPrice());
            stmt.setString(4, vehicle.getAvailability());
            stmt.setString(5, vehicle.getVehicleId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
