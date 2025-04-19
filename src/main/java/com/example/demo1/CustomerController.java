package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class CustomerController {

    @FXML private TextField nameField;
    @FXML private TextField contactField;
    @FXML private TextField licenseField;
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> nameColumn;
    @FXML private TableColumn<Customer, String> contactColumn;
    @FXML private TableColumn<Customer, String> licenseColumn;
    @FXML private ListView<String> rentalHistoryList;

    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        contactColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getContact()));
        licenseColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLicense()));
        customerTable.setItems(customerList);

        customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nameField.setText(newSelection.getName());
                contactField.setText(newSelection.getContact());
                licenseField.setText(newSelection.getLicense());
                rentalHistoryList.getItems().clear();
                rentalHistoryList.getItems().addAll(newSelection.getRentalHistory());
            }
        });

        loadCustomerData();
    }

    private void loadCustomerData() {
        String url = "jdbc:mysql://localhost:3306/vehicle_rental";
        String user = "root";
        String password = "59921347";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM customers";
            try (Statement stmt = connection.createStatement(); ResultSet resultSet = stmt.executeQuery(query)) {
                customerList.clear();
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String contact = resultSet.getString("contact");
                    String license = resultSet.getString("license");
                    Customer customer = new Customer(name, contact, license);
                    customerList.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAddCustomerClick() {
        String name = nameField.getText();
        String contact = contactField.getText();
        String license = licenseField.getText();

        if (name.isEmpty() || contact.isEmpty() || license.isEmpty()) {
            showAlert("Please fill in all fields!");
            return;
        }

        if (addCustomerToDatabase(name, contact, license)) {
            loadCustomerData();
            clearFields();
            showAlert("Customer added successfully.");
        } else {
            showAlert("Failed to add customer to database.");
        }
    }

    private boolean addCustomerToDatabase(String name, String contact, String license) {
        String url = "jdbc:mysql://localhost:3306/vehicle_rental";
        String user = "root";
        String password = "59921347";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO customers (name, contact, license) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, contact);
                preparedStatement.setString(3, license);
                int result = preparedStatement.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void onUpdateCustomerClick() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String name = nameField.getText();
            String contact = contactField.getText();
            String license = licenseField.getText();

            if (updateCustomerInDatabase(selected, name, contact, license)) {
                selected.setName(name);
                selected.setContact(contact);
                selected.setLicense(license);
                customerTable.refresh();
                showAlert("Customer updated.");
            } else {
                showAlert("Failed to update customer in database.");
            }
        } else {
            showAlert("Please select a customer to update.");
        }
    }

    private boolean updateCustomerInDatabase(Customer customer, String name, String contact, String license) {
        String url = "jdbc:mysql://localhost:3306/vehicle_rental";
        String user = "root";
        String password = "59921347";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE customers SET name = ?, contact = ?, license = ? WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, contact);
                preparedStatement.setString(3, license);
                preparedStatement.setString(4, customer.getName());
                int result = preparedStatement.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void onDeleteCustomerClick() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirm Delete");
            confirm.setHeaderText("Delete Customer?");
            confirm.setContentText("Are you sure you want to delete this customer?");
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    if (deleteCustomerFromDatabase(selected)) {
                        customerList.remove(selected);
                        rentalHistoryList.getItems().clear();
                        clearFields();
                        showAlert("Customer deleted.");
                    } else {
                        showAlert("Failed to delete customer from database.");
                    }
                }
            });
        } else {
            showAlert("Please select a customer to delete.");
        }
    }

    private boolean deleteCustomerFromDatabase(Customer customer) {
        String url = "jdbc:mysql://localhost:3306/vehicle_rental";
        String user = "root";
        String password = "59921347";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM customers WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, customer.getName());
                int result = preparedStatement.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void clearFields() {
        nameField.clear();
        contactField.clear();
        licenseField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goBack() {
        try {
            HelloApplication.setRoot("hello-view.fxml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
