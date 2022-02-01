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
import java.util.Objects;
import java.util.ResourceBundle;

public class UpdateEmployeeController implements Initializable {

    @FXML
    private ComboBox<String> branchCombobox;
    @FXML
    private Label errorLabel;
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
    private PasswordField passField;
    @FXML
    private PasswordField confirmField;
    @FXML
    private CheckBox managerCheckbox;
    @FXML
    private CheckBox sellerCheckbox;

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

    public void updateEmployee() {
        int roleId = 0;
        int branch_id = 0;

        if (managerCheckbox.isSelected()) {
            roleId = 1;
        } else if (sellerCheckbox.isSelected()) {
            roleId = 2;
        }

        boolean isNameNotFilled = nameTextfield.getText().isBlank();
        String name = "";
        if (!isNameNotFilled) {
            name = nameTextfield.getText();
        }
        boolean isGenderNotFilled = genderTextfield.getText().isBlank();
        String gender = "";
        if (!isGenderNotFilled) {
            gender = genderTextfield.getText();
        }
        boolean isAddressNotFilled = addressTextfield.getText().isBlank();
        String address = "";
        if (!isAddressNotFilled) {
            address = addressTextfield.getText();
        }
        boolean isDobNotFilled = dobTextfield.getText().isBlank();
        String dob = "";
        if (!isDobNotFilled) {
            dob = dobTextfield.getText();
        }
        boolean isPhoneNotFilled = phoneTextfield.getText().isBlank();
        int phone = 0;
        if (!isPhoneNotFilled) {
            phone = Integer.parseInt(phoneTextfield.getText());
        }
        boolean isPassNotFilled = passField.getText().isBlank();
        boolean isPassConfirmed = Objects.equals(passField.getText(), confirmField.getText());
        String pass = "";
        if (!isPassNotFilled && isPassConfirmed) {
            pass = passField.getText();
        }


        String branch = "SELECT id FROM branch WHERE branch_name = ?";

        try {
            Connection connectDB = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connectDB.prepareStatement(branch);
            preparedStatement.setString(1, String.valueOf(branchCombobox.getSelectionModel().getSelectedItem()));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                branch_id = resultSet.getInt(1);
            }
            System.out.println(branch_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean querySet = false;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE employee ");
        if (!isNameNotFilled || !isGenderNotFilled || !isAddressNotFilled || !isDobNotFilled || !isPhoneNotFilled || roleId != 0 || branch_id != 0) {
            stringBuilder.append("SET ");
            if (!isNameNotFilled) {
                stringBuilder.append("employee_name = ").append("\"").append(name).append("\"");
                querySet = true;
            }
            if (!isGenderNotFilled) {
                if (querySet) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append("gender = ").append("\"").append(gender).append("\"");
                querySet = true;
            }
            if (!isAddressNotFilled) {
                if (querySet) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append("address = ").append("\"").append(address).append("\"");
                querySet = true;
            }
            if (!isDobNotFilled) {
                if (querySet) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append("date_of_birth = ").append("\"").append(dob).append("\"");
                querySet = true;
            }
            if (!isPhoneNotFilled) {
                if (querySet) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append("phonenumber = ").append(phone).append(" ");
                querySet = true;
            }
            if (roleId != 0) {
                if (querySet) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append("role_id = ").append(roleId).append(" ");
                querySet = true;
            }
            if (branch_id != 0) {
                if (querySet) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append("branch_id = ").append(branch_id).append(" ");
                querySet = true;
            }
            if (!isPassNotFilled) {
                if (querySet) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append("password = ").append("\"").append(pass).append("\"");
                querySet = true;
            }
        }
        stringBuilder.append("WHERE employee_name = ").append("\"").append(name).append("\"");
        String query = stringBuilder.toString();
        System.out.println(query);
        try {
            Connection connectDB = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateButtonOnAction(ActionEvent event) {
        if (nameTextfield.getText().isBlank()) {
            errorLabel.setText("Please fill the name field");
        } else {
            updateEmployee();
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
