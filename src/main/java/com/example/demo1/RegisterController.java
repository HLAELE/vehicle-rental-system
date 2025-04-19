package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterController {
    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> roleComboBox;

    // Initialize method sets ComboBox options
    @FXML
    public void initialize() {
        roleComboBox.setItems(FXCollections.observableArrayList("Admin", "Employee"));
    }

    @FXML
    protected void onRegisterClick(ActionEvent event) {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || role == null) {
            showAlert("Missing Fields", "Please fill in all fields.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users (name, surname, email, password, role) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, role);
            stmt.executeUpdate();

            showAlert("Success", "Registration successful! You can now login.");
            HelloApplication.setRoot("login-view.fxml");

        } catch (Exception e) {
            showAlert("Error", "Could not register: " + e.getMessage());
        }
    }

    @FXML
    protected void onLoginLinkClick(ActionEvent event) {
        try {
            HelloApplication.setRoot("login-view.fxml");
        } catch (Exception e) {
            showAlert("Error", "Could not load login view: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
