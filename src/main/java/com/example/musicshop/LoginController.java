package com.example.musicshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class LoginController {

    @FXML
    private Button exitButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;


    public void exitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent e) throws SQLException {

        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank() && validateLogin()) {
//            loginMessageLabel.setText("You tried to login");
            changeToCatalogue(e);
        } else {
            loginMessageLabel.setText("Invalid Login. Please try again.");
        }
    }



    public boolean validateLogin() throws SQLException {

        Connection connectDB = DatabaseConnection.getConnection();

        // check if user_name and password matches (returns 1 if true)
        String verifyLogin = "SELECT * FROM customer WHERE user_name = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, usernameTextField.getText().trim());
            preparedStatement.setString(2, passwordPasswordField.getText().trim());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return false;
            } else {
                loginMessageLabel.setText("Welcome!");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void changeToCatalogue(ActionEvent event) {
        try {
            String username = usernameTextField.getText();

            Singleton holder = Singleton.getInstance();
            holder.setUsername(username);

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
    }
    public void createAccountForm(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane root = loader.load(getClass().getResource("register.fxml").openStream());
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