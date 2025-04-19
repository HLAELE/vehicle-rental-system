package com.example.demo1;

import javafx.fxml.FXML;

public class EmployeeDashboardController {
    @FXML
    protected void onManageBookingsClick() {
        try {
            HelloApplication.setRoot("booking-view.fxml", 600, 400);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onManagePaymentsClick() {
        try {
            HelloApplication.setRoot("payment-view.fxml", 600, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAvailableVehiclesReportClick() {
        try {
            HelloApplication.setRoot("available-vehicles-view.fxml", 700, 500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLogoutClick() {
        try {
            HelloApplication.setRoot("login-view.fxml", 600, 400);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
