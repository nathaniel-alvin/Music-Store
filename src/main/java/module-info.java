module com.example.musicshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.musicshop to javafx.fxml;
    exports com.example.musicshop;
}