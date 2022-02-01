package com.example.musicshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label successfulLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private Label errorPasswordLabel;
    @FXML
    private TextField nameTextfield;
    @FXML
    private TextField emailTextfield;
    @FXML
    private TextField addressTextfield;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmField;

    public void cancelButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane root = loader.load(getClass().getResource("login.fxml").openStream());
            Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 800,600));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void registerButtonOnAction(ActionEvent event) throws SQLException {
        boolean empty = nameTextfield.getText().trim().isEmpty() && emailTextfield.getText().trim().isEmpty() && usernameTextfield.getText().trim().isEmpty() &&
                addressTextfield.getText().trim().isEmpty() && passwordField.getText().trim().isEmpty() && confirmField.getText().trim().isEmpty();

        if (empty) {
            errorLabel.setText("Please complete all fields");
        } else if (!passwordField.getText().equals(confirmField.getText())) {
            errorPasswordLabel.setText("Password does not match");
        } else {
            registerUser();
            changeToLogin(event);
        }
    }

    public void registerUser() throws SQLException {
        Connection connectDB = DatabaseConnection.getConnection();

        String name = nameTextfield.getText().trim();
        String email = emailTextfield.getText().trim();
        String address = addressTextfield.getText().trim();
        String username = usernameTextfield.getText().trim();
        String password = passwordField.getText().trim();

        String addNewCustomer = "INSERT INTO customer (customer_name, email, user_name, password, address) VALUES (?, ?, ?, ?, ?)";

        // adding new customer to the database
        try {
            PreparedStatement preparedStatement2 = connectDB.prepareStatement(addNewCustomer);
            preparedStatement2.setString(1, name);
            preparedStatement2.setString(2, email);
            preparedStatement2.setString(3, username);
            preparedStatement2.setString(4, password);
            preparedStatement2.setString(5, address);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        successfulLabel.setText("User Registered Successfully");
    }

    public void changeToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane root = loader.load(getClass().getResource("login.fxml").openStream());
            Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 800,600));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        successfulLabel.setText(" ");
        errorLabel.setText(" ");
        errorPasswordLabel.setText(" ");
    }
}
