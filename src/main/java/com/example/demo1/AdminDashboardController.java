package com.example.demo1;

        import javafx.fxml.FXML;

        public class AdminDashboardController {
        @FXML
        protected void onManageCustomersClick() {
        try {
        HelloApplication.setRoot("customer-view.fxml", 600, 400);
        } catch (Exception e) {
        e.printStackTrace();
        }
        }

        @FXML
        protected void onManageVehiclesClick() {
        try {
        HelloApplication.setRoot("vehicle-view.fxml", 600, 400);
        } catch (Exception e) {
        e.printStackTrace();
        }
        }

        @FXML
        protected void onManageReportsClick() {
        try {
        HelloApplication.setRoot("report-view.fxml", 600, 400);
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
