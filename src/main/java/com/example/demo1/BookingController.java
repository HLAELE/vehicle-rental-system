package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;

public class BookingController {

    @FXML private TextField bookingIdField;
    @FXML private TextField customerNameField;
    @FXML private ComboBox<String> vehicleBox;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;

    @FXML private TableView<Booking> bookingTable;
    @FXML private TableColumn<Booking, Number> idColumn;
    @FXML private TableColumn<Booking, String> customerColumn;
    @FXML private TableColumn<Booking, String> vehicleColumn;
    @FXML private TableColumn<Booking, String> startColumn;
    @FXML private TableColumn<Booking, String> endColumn;

    private final ObservableList<Booking> bookings = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()));
        customerColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomerName()));
        vehicleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getVehicleId()));
        startColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRentalStart().toString()));
        endColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRentalEnd().toString()));

        bookingTable.setItems(bookings);
        loadVehicleOptions();
        loadBookingsFromDatabase();
    }

    private void loadVehicleOptions() {
        vehicleBox.getItems().clear();
        String query = "SELECT vehicle_id FROM vehicles WHERE availability = 'Available'";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                vehicleBox.getItems().add(rs.getString("vehicle_id"));
            }
        } catch (SQLException e) {
            showAlert("Error loading vehicles: " + e.getMessage());
        }
    }

    @FXML
    private void onAddBookingClick() {
        String customerName = customerNameField.getText();
        String vehicleId = vehicleBox.getValue();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (customerName == null || customerName.isEmpty() ||
                vehicleId == null || startDate == null || endDate == null) {
            showAlert("Please fill in all fields.");
            return;
        }

        String insertSQL = "INSERT INTO bookings (customer_name, vehicle_id, rental_start, rental_end) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            stmt.setString(1, customerName);
            stmt.setString(2, vehicleId);
            stmt.setDate(3, Date.valueOf(startDate));
            stmt.setDate(4, Date.valueOf(endDate));
            stmt.executeUpdate();

            showAlert("Booking added successfully.");
            loadBookingsFromDatabase();
            clearFields();

        } catch (SQLException e) {
            showAlert("Error adding booking: " + e.getMessage());
        }
    }

    private void loadBookingsFromDatabase() {
        bookings.clear();
        String query = "SELECT * FROM bookings";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("booking_id");
                String customerName = rs.getString("customer_name");
                String vehicleId = rs.getString("vehicle_id");
                LocalDate start = rs.getDate("rental_start").toLocalDate();
                LocalDate end = rs.getDate("rental_end").toLocalDate();

                bookings.add(new Booking(id, customerName, vehicleId, start, end));
            }

        } catch (SQLException e) {
            showAlert("Error loading bookings: " + e.getMessage());
        }
    }

    private void clearFields() {
        bookingIdField.clear();
        customerNameField.clear();
        vehicleBox.getSelectionModel().clearSelection();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle_rental", "root", "59921347");
    }

    @FXML
    private void goBack() {
        try {
            HelloApplication.setRoot("hello-view.fxml", 600, 400);
        } catch (Exception e) {
            showAlert("Error loading main view.");
        }
    }
}
