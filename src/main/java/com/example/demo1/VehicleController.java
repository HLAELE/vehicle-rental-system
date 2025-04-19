package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class VehicleController {

    @FXML private TextField vehicleIdField;
    @FXML private TextField brandModelField;
    @FXML private TextField categoryField;
    @FXML private TextField rentalPriceField;
    @FXML private ComboBox<String> availabilityBox;
    @FXML private TableView<Vehicle> vehicleTable;
    @FXML private TableColumn<Vehicle, String> idColumn;
    @FXML private TableColumn<Vehicle, String> brandColumn;
    @FXML private TableColumn<Vehicle, String> categoryColumn;
    @FXML private TableColumn<Vehicle, Double> priceColumn;
    @FXML private TableColumn<Vehicle, String> availabilityColumn;

    private final ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        availabilityBox.setItems(FXCollections.observableArrayList("Available", "Not Available"));

        idColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brandModel"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("rentalPrice"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

        vehicleList.setAll(VehicleData.getAllVehicles());
        vehicleTable.setItems(vehicleList);
    }

    @FXML
    private void onAddVehicleClick() {
        try {
            String id = vehicleIdField.getText();
            String brand = brandModelField.getText();
            String category = categoryField.getText();
            double price = Double.parseDouble(rentalPriceField.getText());
            String availability = availabilityBox.getValue();

            if (id.isEmpty() || brand.isEmpty() || category.isEmpty() || availability == null) {
                showAlert("Please fill in all fields!");
                return;
            }

            Vehicle newVehicle = new Vehicle(id, brand, category, price, availability);
            VehicleData.addVehicle(newVehicle);
            vehicleList.setAll(VehicleData.getAllVehicles());

            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Rental price must be a number.");
        }
    }

    @FXML
    private void onDeleteVehicleClick() {
        Vehicle selected = vehicleTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            VehicleData.removeVehicle(selected);
            vehicleList.setAll(VehicleData.getAllVehicles());
        } else {
            showAlert("Select a vehicle to delete.");
        }
    }

    @FXML
    private void onUpdateVehicleClick() {
        Vehicle selected = vehicleTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                selected.setBrandModel(brandModelField.getText());
                selected.setCategory(categoryField.getText());
                selected.setRentalPrice(Double.parseDouble(rentalPriceField.getText()));
                selected.setAvailability(availabilityBox.getValue());

                VehicleData.updateVehicle(selected);
                vehicleList.setAll(VehicleData.getAllVehicles());
                vehicleTable.refresh();
            } catch (NumberFormatException e) {
                showAlert("Rental price must be a number.");
            }
        } else {
            showAlert("Select a vehicle to update.");
        }
    }

    private void clearFields() {
        vehicleIdField.clear();
        brandModelField.clear();
        categoryField.clear();
        rentalPriceField.clear();
        availabilityBox.setValue(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
