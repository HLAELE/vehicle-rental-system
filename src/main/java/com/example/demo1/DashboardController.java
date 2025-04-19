package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML private Label welcomeLabel;
    @FXML private Button vehicleBtn;
    @FXML private Button bookingBtn;
    @FXML private Button reportBtn;

    private static String currentRole = "";

    public static void setCurrentRole(String role) {
        currentRole = role;
    }

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome, " + currentRole + "!");

        if ("Employee".equalsIgnoreCase(currentRole)) {
            vehicleBtn.setDisable(true);
            reportBtn.setDisable(true);
        }
    }

    @FXML
    private void onManageVehicles() {
        try {
            HelloApplication.setRoot("vehicle-view.fxml", 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onManageBookings() {
        try {
            HelloApplication.setRoot("booking-view.fxml", 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onViewReports() {
        try {
            HelloApplication.setRoot("report-view.fxml", 800, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
