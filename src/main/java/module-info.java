module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;  // Add this line to include access to java.sql

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}
