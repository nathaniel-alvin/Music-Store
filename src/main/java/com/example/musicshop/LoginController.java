package com.example.musicshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private CheckBox employeeCheckbox;


    public void exitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent e) throws SQLException {

        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank() && !employeeCheckbox.isSelected() && validateLogin() ) {
//            loginMessageLabel.setText("You tried to login");
            changeToCatalogue(e);
        } else if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank() && employeeCheckbox.isSelected() && validateLoginEmployee() ){
            changeToProfile(e);
        } else {
            loginMessageLabel.setText("You tried to login");
        }
    }

    public boolean validateLoginEmployee() throws SQLException {
        Connection connectDB = DatabaseConnection.getConnection();

        String verifyLogin = "SELECT * FROM employee WHERE employee_name = ? AND password = ?";

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

    public void changeToProfile(ActionEvent event) throws SQLException{
        String role = null;

        Connection connectDB = DatabaseConnection.getConnection();
        try {
            String username = usernameTextField.getText();

            Singleton holder = Singleton.getInstance();
            holder.setUsername(username);

            String getRole = "SELECT r.role_name FROM employee e INNER JOIN role r on e.role_id = r.id WHERE e.employee_name = ?";

            PreparedStatement preparedStatement = connectDB.prepareStatement(getRole);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                role = resultSet.getString(1);
            }

            FXMLLoader loader = new FXMLLoader();

            if (Objects.equals(role, "manager")) {
                BorderPane root = loader.load(getClass().getResource("profilemanager.fxml").openStream());
                Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
                registerStage.setScene(new Scene(root, 800,600));
                registerStage.show();
            } else  if (Objects.equals(role, "seller")) {
                BorderPane root = loader.load(getClass().getResource("profileseller.fxml").openStream());
                Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
                registerStage.setScene(new Scene(root, 800,600));
                registerStage.show();
            } else {
                System.out.println("not manager nor seller");
            }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}