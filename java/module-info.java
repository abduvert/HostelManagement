module com.example.hostelmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    opens com.example.hostelmanagement to javafx.fxml;
    exports com.example.hostelmanagement;
}