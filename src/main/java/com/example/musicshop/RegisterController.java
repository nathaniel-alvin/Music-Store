package com.example.musicshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private TextField nameTextfield;
    @FXML
    private TextField emailTextfield;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private TextField passwordTextfield;
    @FXML
    private ComboBox<String> countryCombobox;

    private ObservableList<String> countryList = FXCollections.observableArrayList();

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
                passwordTextfield.getText().trim().isEmpty() && countryCombobox.getSelectionModel().isEmpty();
        if (empty) {
            errorLabel.setText("Please complete all fields");
        } else {
            registerUser();
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
    }

    public void registerUser() throws SQLException {
        Connection connectDB = DatabaseConnection.getConnection();

        String name = nameTextfield.getText().trim();
        String email = emailTextfield.getText().trim();
        String username = usernameTextfield.getText().trim();
        String password = passwordTextfield.getText().trim();
        String country = String.valueOf(countryCombobox.getSelectionModel().getSelectedItem());
        System.out.println(country);
        int countryId = 0;

        String addNewCountry = "INSERT INTO country (country_name) VALUES (?)";
        String getCountryId = "SELECT id FROM country WHERE country_name = ?";
        String addNewCustomer = "INSERT INTO customer (customer_name, email, user_name, password, country_id) VALUES (?, ?, ?, ?, ?)";

        // check if country exist in the list; add if it doesn't exist
        if (!countryList.contains(country)) {
            try {
                PreparedStatement preparedStatement = connectDB.prepareStatement(addNewCountry);
                preparedStatement.setString(1, country);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // getting the country id
        try{
            PreparedStatement preparedStatement1 = connectDB.prepareStatement(getCountryId);
            preparedStatement1.setString(1, country);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            countryId = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // adding new customer to the database
        try {
            PreparedStatement preparedStatement2 = connectDB.prepareStatement(addNewCustomer);
            preparedStatement2.setString(1, name);
            preparedStatement2.setString(2, email);
            preparedStatement2.setString(3, username);
            preparedStatement2.setString(4, password);
            preparedStatement2.setInt(5, countryId);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        successfulLabel.setText("User Registered Successfully");
    }

    public void setCountryList() throws SQLException {

        Connection connectDB = DatabaseConnection.getConnection();

        String countryNames = "SELECT country_name FROM country";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(countryNames);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                countryList.add(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            successfulLabel.setText(" ");
            errorLabel.setText(" ");
            setCountryList();
            countryCombobox.setItems(countryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
