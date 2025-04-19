package com.example.demo1;

import javafx.fxml.FXML;

public class HelloController {

    @FXML
    protected void onManageCustomersClick() {
        try {
            HelloApplication.setRoot("customer-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onManageVehiclesClick() {
        try {
            HelloApplication.setRoot("vehicle-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onManageReportsClick() {
        try {
            HelloApplication.setRoot("report-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onManageBookingsClick() {
        try {
            HelloApplication.setRoot("booking-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onManagePaymentsClick() {
        try {
            HelloApplication.setRoot("payment-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAvailableVehiclesReportClick() {
        try {
            HelloApplication.setRoot("available-vehicles-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLogoutClick() {
        try {
            HelloApplication.setRoot("login-view.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
