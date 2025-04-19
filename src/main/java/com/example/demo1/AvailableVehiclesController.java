package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AvailableVehiclesController {

    @FXML private TableView<Vehicle> availableVehiclesTable;
    @FXML private TableColumn<Vehicle, String> idColumn;
    @FXML private TableColumn<Vehicle, String> brandModelColumn;
    @FXML private TableColumn<Vehicle, String> categoryColumn;
    @FXML private TableColumn<Vehicle, Double> priceColumn;
    @FXML private TableColumn<Vehicle, String> availabilityColumn;

    @FXML
    public void initialize() {
        // Set up the column bindings
        idColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        brandModelColumn.setCellValueFactory(new PropertyValueFactory<>("brandModel"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("rentalPrice"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        // ✅ Ensure we wrap the list in an ObservableList
        availableVehiclesTable.setItems(FXCollections.observableArrayList(getAvailableVehicles()));
    }

    private java.util.List<Vehicle> getAvailableVehicles() {
        java.util.List<Vehicle> allVehicles = VehicleData.getAllVehicles();
        java.util.List<Vehicle> available = new java.util.ArrayList<>();

        for (Vehicle v : allVehicles) {
            if ("Available".equalsIgnoreCase(v.getAvailability())) {
                available.add(v);
            }
        }
        return available;
    }

    @FXML
    private void goBack() {
        try {
            HelloApplication.setRoot("hello-view.fxml", 600, 400);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
