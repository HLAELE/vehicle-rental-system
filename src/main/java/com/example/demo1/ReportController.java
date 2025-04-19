package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.Map;

public class ReportController {

    @FXML private PieChart availabilityPieChart;
    @FXML private BarChart<String, Number> vehicleBarChart;
    @FXML private LineChart<String, Number> revenueLineChart;

    @FXML
    public void initialize() {
        loadAvailabilityPieChart();
        loadVehicleBarChart();
        loadRevenueLineChart();
    }

    private void loadAvailabilityPieChart() {
        int available = 0;
        int notAvailable = 0;
        for (Vehicle v : VehicleData.getAllVehicles()) {
            if ("Available".equalsIgnoreCase(v.getAvailability())) {
                available++;
            } else {
                notAvailable++;
            }
        }

        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Available", available),
                new PieChart.Data("Not Available", notAvailable)
        );
        availabilityPieChart.setTitle("Vehicle Availability");
        availabilityPieChart.setData(pieData);
    }

    private void loadVehicleBarChart() {
        Map<String, Integer> categoryCount = new HashMap<>();
        for (Vehicle v : VehicleData.getAllVehicles()) {
            if ("Available".equalsIgnoreCase(v.getAvailability())) {
                categoryCount.put(v.getCategory(), categoryCount.getOrDefault(v.getCategory(), 0) + 1);
            }
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Available Vehicles");

        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        vehicleBarChart.getData().clear();
        vehicleBarChart.getData().add(series);
    }

    private void loadRevenueLineChart() {
        Map<String, Double> monthlyRevenue = new HashMap<>();
        monthlyRevenue.put("Jan", 1200.0);
        monthlyRevenue.put("Feb", 2000.0);
        monthlyRevenue.put("Mar", 1800.0);
        monthlyRevenue.put("Apr", 2400.0);

        XYChart.Series<String, Number> revenueSeries = new XYChart.Series<>();
        revenueSeries.setName("Monthly Revenue");

        for (Map.Entry<String, Double> entry : monthlyRevenue.entrySet()) {
            revenueSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        revenueLineChart.getData().clear();
        revenueLineChart.getData().add(revenueSeries);
    }

    @FXML
    private void goBack() {
        try {
            HelloApplication.setRoot("hello-view.fxml", 800, 600);
        } catch (Exception e) {
            showError("Failed to load main screen.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
