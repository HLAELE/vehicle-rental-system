package com.example.demo1;

import java.time.LocalDate;

public class Booking {
    private int id;
    private String customerName;
    private String vehicleId;
    private LocalDate rentalStart;
    private LocalDate rentalEnd;

    public Booking(int id, String customerName, String vehicleId, LocalDate rentalStart, LocalDate rentalEnd) {
        this.id = id;
        this.customerName = customerName;
        this.vehicleId = vehicleId;
        this.rentalStart = rentalStart;
        this.rentalEnd = rentalEnd;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public LocalDate getRentalStart() {
        return rentalStart;
    }

    public LocalDate getRentalEnd() {
        return rentalEnd;
    }
}
