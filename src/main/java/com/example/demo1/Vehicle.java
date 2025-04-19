package com.example.demo1;

public class Vehicle {
    private String vehicleId;
    private String brandModel;
    private String category;
    private double rentalPrice;
    private String availability;

    public Vehicle(String vehicleId, String brandModel, String category, double rentalPrice, String availability) {
        this.vehicleId = vehicleId;
        this.brandModel = brandModel;
        this.category = category;
        this.rentalPrice = rentalPrice;
        this.availability = availability;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public String getCategory() {
        return category;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public String getAvailability() {
        return availability;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
