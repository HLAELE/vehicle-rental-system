package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuController {

    @FXML
    protected void handleVehicleManagement(ActionEvent event) throws Exception {
        HelloApplication.setRoot("vehicle-view.fxml");
    }

    @FXML
    protected void handleBookingManagement(ActionEvent event) throws Exception {
        HelloApplication.setRoot("booking-view.fxml");
    }

    @FXML
    protected void handleCustomerManagement(ActionEvent event) throws Exception {
        HelloApplication.setRoot("customer-view.fxml");
    }

    @FXML
    protected void handleReports(ActionEvent event) throws Exception {
        HelloApplication.setRoot("report-view.fxml");
    }

    @FXML
    protected void handlePayments(ActionEvent event) throws Exception {
        HelloApplication.setRoot("payment-view.fxml");
    }

    @FXML
    protected void handleAvailableReports(ActionEvent event) throws Exception {
        HelloApplication.setRoot("available-vehicles-view.fxml");
    }
}
