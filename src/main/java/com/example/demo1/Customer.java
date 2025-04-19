package com.example.demo1;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String contact;
    private String license;
    private List<String> rentalHistory;

    public Customer(String name, String contact, String license) {
        this.name = name;
        this.contact = contact;
        this.license = license;
        this.rentalHistory = new ArrayList<>();
        rentalHistory.add("Toyota Corolla - 2024-01-05");
        rentalHistory.add("Honda Civic - 2024-03-12");
    }

    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getLicense() { return license; }
    public List<String> getRentalHistory() { return rentalHistory; }

    public void setName(String name) { this.name = name; }
    public void setContact(String contact) { this.contact = contact; }
    public void setLicense(String license) { this.license = license; }
}
