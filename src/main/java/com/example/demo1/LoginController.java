package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    @FXML
    protected void onLoginClick(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                showAlert("Error", "Database connection failed.");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");


                showAlert("Welcome", "Login successful. You are logged in as: " + role);


                HelloApplication.setRoot("main-menu-view.fxml");
            } else {
                showAlert("Login Failed", "Invalid email or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Error connecting to database: " + e.getMessage());
        }
    }

    @FXML
    protected void onRegisterLinkClick(ActionEvent event) throws Exception {
        HelloApplication.setRoot("register-view.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
