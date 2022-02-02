package com.example.musicshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Label nameLabel;

    String currentUsername;

    // Order button
    public void orderButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        BorderPane root = loader.load(getClass().getResource("order.fxml").openStream());

        Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root, 600,400));
        registerStage.show();
    }

    // Edit button
    public void editButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        BorderPane root = loader.load(getClass().getResource("editprofile.fxml").openStream());

        Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root, 800,600));
        registerStage.show();
    }

    // Logout button
    public void logoutButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 800,600));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // Catalogue button
    public void catalogueButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("catalogue.fxml"));
            Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 800,600));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void employeeButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("employee.fxml"));
            Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 800,600));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void showManagerDatabaseButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("managerdatabase.fxml"));
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
        Singleton holder = Singleton.getInstance();
        currentUsername = holder.getUsername();

        nameLabel.setText(currentUsername);
    }
}
