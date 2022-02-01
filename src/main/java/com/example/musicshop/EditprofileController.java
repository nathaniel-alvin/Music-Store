package com.example.musicshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditprofileController {

    @FXML
    private TextField nameTextfield;
    @FXML
    private TextField emailTextfield;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private TextField passwordTextfield;
    @FXML
    private TextField confirmTextfield;
    @FXML
    private Label errorLabel;

    public void cancelButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane root = loader.load(getClass().getResource("profileuser.fxml").openStream());
            Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 800,600));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateCustomerInfo() throws SQLException {

        Singleton holder = Singleton.getInstance();
        String currentUsername = holder.getUsername();

        Connection connectDB = DatabaseConnection.getConnection();

        String name = nameTextfield.getText().trim();
        String email = emailTextfield.getText().trim();
        String username = usernameTextfield.getText().trim();
        String password = passwordTextfield.getText().trim();

        String updateCustomer = "UPDATE customer SET customer_name = ?, email = ?, user_name = ?, password = ? WHERE user_name = ?";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(updateCustomer);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, currentUsername);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        holder.setUsername(name);
        System.out.println("profile updated");
    }

    public void updateButtonOnAction(ActionEvent event) throws SQLException {
        if (passwordTextfield.getText().trim().equals(confirmTextfield.getText().trim())) {
            updateCustomerInfo();
            try {
                FXMLLoader loader = new FXMLLoader();
                BorderPane root = loader.load(getClass().getResource("catalogue.fxml").openStream());
                Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
                registerStage.setScene(new Scene(root, 800,600));
                registerStage.show();
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            errorLabel.setText("Please complete all attributes!");
        }
    }
}
