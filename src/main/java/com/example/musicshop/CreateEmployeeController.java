package com.example.musicshop;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateEmployeeController implements Initializable {

    @FXML
    private ComboBox<String> branchCombobox;
    @FXML
    private TextField nameTextfield;
    @FXML
    private TextField genderTextfield;
    @FXML
    private TextField addressTextfield;
    @FXML
    private TextField dobTextfield;
    @FXML
    private TextField phoneTextfield;
    @FXML
    private CheckBox managerCheckbox;
    @FXML
    private CheckBox sellerCheckbox;
    @FXML
    private Label errorLabel;

    public List<String> getBranch() {
        List<String> branches = new ArrayList<>();
        try {
            Connection connectDB = DatabaseConnection.getConnection();

            String query = "SELECT branch_name FROM branch";
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                branches.add(resultSet.getString(1));
            }
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return branches;
    }

    public void createEmployee() {
        int roleId = 0;
        int branch_id = 0;

        if (managerCheckbox.isSelected()) {
            roleId = 1;
        } else if (sellerCheckbox.isSelected()) {
            roleId = 2;
        } else {
            errorLabel.setText("Please fill all fields");
        }

        String branch = "SELECT id FROM branch WHERE branch_name = ?";

        String query = "INSERT INTO employee(employee_name, join_date, address, date_of_birth, role_id, branch_id, phonenumber, gender) VALUES (?, SYSDATE(), ?,?,?,?,?,?)";

        try {
            Connection connectDB = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connectDB.prepareStatement(branch);
            preparedStatement.setString(1, String.valueOf(branchCombobox.getSelectionModel().getSelectedItem()));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                branch_id = resultSet.getInt(1);
            }
            System.out.println(branch_id);

            PreparedStatement preparedStatement1 = connectDB.prepareStatement(query);
            preparedStatement1.setString(1, nameTextfield.getText());
            preparedStatement1.setString(2, addressTextfield.getText());
            preparedStatement1.setString(3, dobTextfield.getText());
            preparedStatement1.setInt(4, roleId);
            preparedStatement1.setInt(5, branch_id);
            preparedStatement1.setInt(6, Integer.parseInt(phoneTextfield.getText()));
            preparedStatement1.setString(7, genderTextfield.getText());
            preparedStatement1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createButtonOnAction(ActionEvent event) {
        if (nameTextfield.getText().isBlank() || genderTextfield.getText().isBlank() || addressTextfield.getText().isBlank() || dobTextfield.getText().isBlank() || phoneTextfield.getText().isBlank() || branchCombobox.getItems().isEmpty()) {
            errorLabel.setText("Please fill all fields");
        } else {
            createEmployee();
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
    }

    public void backButtonOnAction(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setText("");
        branchCombobox.getItems().addAll(FXCollections.observableArrayList(getBranch()));
    }
}
