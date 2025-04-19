package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.print.PrinterJob;
import javafx.scene.layout.VBox;


public class PaymentController {

    @FXML private TextField bookingIdField;
    @FXML private TextField rentalDaysField;
    @FXML private TextField vehicleRateField;
    @FXML private CheckBox lateFeeCheckBox;
    @FXML private CheckBox additionalServiceCheckBox;
    @FXML private RadioButton cashRadio;
    @FXML private RadioButton cardRadio;
    @FXML private RadioButton onlineRadio;
    @FXML private RadioButton bankRadio;
    @FXML private TextArea invoiceArea;

    @FXML private VBox bankFields; // VBox for Bank Payment Fields
    @FXML private TextField accountNumberField; // Bank account field
    @FXML private PasswordField pinField; // PIN field

    private ToggleGroup paymentToggleGroup;

    @FXML
    public void initialize() {
        // Setup ToggleGroup programmatically
        paymentToggleGroup = new ToggleGroup();
        cashRadio.setToggleGroup(paymentToggleGroup);
        cardRadio.setToggleGroup(paymentToggleGroup);
        onlineRadio.setToggleGroup(paymentToggleGroup);
        bankRadio.setToggleGroup(paymentToggleGroup);

        // Hide Bank payment fields initially
        bankFields.setVisible(false);

        // Add listener to show/hide bank fields when Bank is selected
        paymentToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == bankRadio) {
                bankFields.setVisible(true);
            } else {
                bankFields.setVisible(false);
            }
        });
    }

    @FXML
    protected void onGenerateInvoice() {
        try {
            String bookingId = bookingIdField.getText();
            int days = Integer.parseInt(rentalDaysField.getText());
            double rate = Double.parseDouble(vehicleRateField.getText());

            double total = days * rate;
            if (lateFeeCheckBox.isSelected()) total += 50;
            if (additionalServiceCheckBox.isSelected()) total += 100;

            Toggle selectedToggle = paymentToggleGroup.getSelectedToggle();
            if (selectedToggle == null) {
                invoiceArea.setText("Please select a payment method.");
                return;
            }

            String paymentMethod = ((RadioButton) selectedToggle).getText();

            // Validate Bank Payment method
            if (paymentMethod.equals("Bank")) {
                String accountNumber = accountNumberField.getText();
                String pin = pinField.getText();
                if (accountNumber.isEmpty() || pin.isEmpty()) {
                    invoiceArea.setText("Please enter your bank account number and PIN.");
                    return;
                }
                // Here, you can add real validation logic (for demo, we use length check)
                if (accountNumber.length() != 10 || pin.length() != 4) {
                    invoiceArea.setText("Invalid account number or PIN.");
                    return;
                }
            }

            StringBuilder invoice = new StringBuilder();
            invoice.append("---- HLAELE VEHICLE RENTAL INVOICE ----\n");
            invoice.append("Booking ID: ").append(bookingId).append("\n");
            invoice.append("Rental Days: ").append(days).append("\n");
            invoice.append("Rate per Day: ").append(rate).append(" LSL\n");
            if (lateFeeCheckBox.isSelected()) invoice.append("Late Fee: 50 LSL\n");
            if (additionalServiceCheckBox.isSelected()) invoice.append("Service Fee: 100 LSL\n");
            invoice.append("Payment Method: ").append(paymentMethod).append("\n");
            invoice.append("--------------------------------------\n");
            invoice.append("Total Amount: ").append(total).append(" LSL\n");

            invoiceArea.setText(invoice.toString());

        } catch (NumberFormatException e) {
            invoiceArea.setText("Please enter valid numbers for rental days and rate.");
        } catch (Exception e) {
            invoiceArea.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    protected void onPrintInvoice() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(invoiceArea.getScene().getWindow())) {
            boolean success = job.printPage(invoiceArea);
            if (success) {
                job.endJob();
            }
        }
    }
    @FXML
    private void goBack() {
        try {
            HelloApplication.setRoot("hello-view.fxml", 600, 400);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
