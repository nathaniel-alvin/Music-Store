module com.example.musicshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens com.example.musicshop to javafx.fxml;
    exports com.example.musicshop;
}