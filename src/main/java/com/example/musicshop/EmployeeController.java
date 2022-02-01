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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private ComboBox<String> branchCombobox;
    @FXML
    private CheckBox nameCheckbox;
    @FXML
    private CheckBox addressCheckbox;
    @FXML
    private CheckBox dobCheckbox;
    @FXML
    private CheckBox roleCheckbox;
    @FXML
    private CheckBox genderCheckbox;
    @FXML
    private CheckBox phoneCheckbox;
    @FXML
    private ComboBox<String> genderCombobox;
    @FXML
    private ComboBox<String> roleCombobox;
    @FXML
    private TableView<Employee> employeeTableview;
    @FXML
    private TableColumn<Employee, String> nameTablecolumn;
    @FXML
    private TableColumn<Employee, String> addressTablecolumn;
    @FXML
    private TableColumn<Employee, String> dobTablecolumn;
    @FXML
    private TableColumn<Employee, String> roleTablecolumn;
    @FXML
    private TableColumn<Employee, String> genderTablecolumn;
    @FXML
    private TableColumn<Employee, Integer> phoneTablecolumn;
    @FXML
    private Button deleteButton;

    ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    ObservableList<Employee> newlist = FXCollections.observableArrayList();

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

    public List<String> getGender() {
        List<String> genders = new ArrayList<>();
        try {
            Connection connectDB = DatabaseConnection.getConnection();

            String query = "SELECT DISTINCT gender FROM employee";
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                genders.add(resultSet.getString(1));
            }
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genders;
    }

    public List<String> getRole() {
        List<String> roles = new ArrayList<>();
        try {
            Connection connectDB = DatabaseConnection.getConnection();

            String query = "SELECT role_name FROM role";
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                roles.add(resultSet.getString(1));
            }
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public ObservableList<Employee> getEmployeeList() {
        try {
            Connection connectDB = DatabaseConnection.getConnection();

            String query = "select e.employee_name, e.address, e.date_of_birth, r.role_name, e.gender, e.phonenumber, b.branch_name from employee e inner join role r on e.role_id = r.id inner join branch b on e.branch_id = b.id";

            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employees;
            while (resultSet.next()) {
                employees = new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),resultSet.getString(5), "0" + resultSet.getInt(6), resultSet.getString(7));
                employeeList.add(employees);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void showEmployee() throws SQLException {
        ObservableList<Employee> list = getEmployeeList();

        nameTablecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressTablecolumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        dobTablecolumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        roleTablecolumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        genderTablecolumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneTablecolumn.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));

        employeeTableview.setItems(list);
    }

    public void showNewTable() {
        newlist.clear();

        if (!nameCheckbox.isSelected()) {
            nameTablecolumn.setVisible(false);
        } if (!addressCheckbox.isSelected()) {
            addressTablecolumn.setVisible(false);
        } if (!dobCheckbox.isSelected()) {
            dobTablecolumn.setVisible(false);
        } if (!roleCheckbox.isSelected()) {
            roleTablecolumn.setVisible(false);
        } if (!genderCheckbox.isSelected()) {
            genderTablecolumn.setVisible(false);
        } if (!phoneCheckbox.isSelected()) {
            phoneTablecolumn.setVisible(false);
        } if (nameCheckbox.isSelected()) {
            nameTablecolumn.setVisible(true);
        } if (addressCheckbox.isSelected()) {
            addressTablecolumn.setVisible(true);
        } if (dobCheckbox.isSelected()) {
            dobTablecolumn.setVisible(true);
        } if (roleCheckbox.isSelected()) {
            roleTablecolumn.setVisible(true);
        } if (genderCheckbox.isSelected()) {
            genderTablecolumn.setVisible(true);
        } if (phoneCheckbox.isSelected()) {
            phoneTablecolumn.setVisible(true);
        }

        boolean isBranchSelected =  branchCombobox.getSelectionModel().getSelectedIndex() != 0;
        String branch = "";
        if (isBranchSelected) {
            branch = branchCombobox.getSelectionModel().getSelectedItem();
        }
        boolean isGenderSelected = genderCombobox.getSelectionModel().getSelectedIndex() != 0;
        String gender = "";
        if (isGenderSelected) {
            gender = genderCombobox.getSelectionModel().getSelectedItem();
        }
        boolean isRoleSelected = roleCombobox.getSelectionModel().getSelectedIndex() != 0;
        String role = "";
        if (isRoleSelected) {
            role = roleCombobox.getSelectionModel().getSelectedItem();
        }

        boolean querySet = false;
        System.out.println(isBranchSelected);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("select e.employee_name, e.address, e.date_of_birth, r.role_name, e.gender, e.phonenumber, b.branch_name from employee e inner join role r on e.role_id = r.id inner join branch b on e.branch_id = b.id ");
        if (isBranchSelected || isGenderSelected || isRoleSelected) {
            stringBuilder.append("WHERE ");
            if (isBranchSelected) {
                stringBuilder.append("b.branch_name = ").append("\"").append(branch).append("\"");
                querySet = true;
            }
            if (isGenderSelected) {
                if (querySet) {
                    stringBuilder.append("AND ");
                }
                stringBuilder.append("e.gender= ").append("\"").append(gender).append("\"");
                querySet = true;
            }
            if (isRoleSelected) {
                if (querySet) {
                    stringBuilder.append("AND ");
                }
                stringBuilder.append("r.role_name= ").append("\"").append(role).append("\"");
            }
        }
        String query = stringBuilder.toString();
        System.out.println(query);


        try {
            Connection connectDB = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employees;
            while (resultSet.next()) {
                employees = new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),resultSet.getString(5), "0" + resultSet.getInt(6), resultSet.getString(7));
                newlist.add(employees);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(newlist.size());


        nameTablecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressTablecolumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        dobTablecolumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        roleTablecolumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        genderTablecolumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneTablecolumn.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));

        employeeTableview.setItems(newlist);
    }

    public void addButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("createemployee.fxml"));
            Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 800,600));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void editButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("updateemployee.fxml"));
            Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 800,600));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void deleteEmployee(int index) throws SQLException {
        String name;
        if (branchCombobox.getSelectionModel().getSelectedIndex() != 0 || roleCombobox.getSelectionModel().getSelectedIndex() != 0 || genderCombobox.getSelectionModel().getSelectedIndex() != 0) {
            name = newlist.get(index).getName();
        } else {
            name = employeeList.get(index).getName();
        }
        String query = "DELETE FROM employee WHERE employee_name = ?";
        try {
            Connection connectDB = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (branchCombobox.getSelectionModel().getSelectedIndex() != 0 || roleCombobox.getSelectionModel().getSelectedIndex() != 0 || genderCombobox.getSelectionModel().getSelectedIndex() != 0) {
            showNewTable();
        } else {
            showEmployee();
        }
    }

    public void backButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("profilemanager.fxml"));
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

        branchCombobox.getItems().add("All");
        branchCombobox.getItems().addAll(FXCollections.observableArrayList(getBranch()));
        branchCombobox.getSelectionModel().selectFirst();

        genderCombobox.getItems().add("All");
        genderCombobox.getItems().addAll(FXCollections.observableArrayList(getGender()));
        genderCombobox.getSelectionModel().selectFirst();

        roleCombobox.getItems().add("All");
        roleCombobox.getItems().addAll(FXCollections.observableArrayList(getRole()));
        roleCombobox.getSelectionModel().selectFirst();

        try {
            showEmployee();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employeeTableview.setOnMousePressed(e -> {
            if (e.getClickCount() == 1 && e.isPrimaryButtonDown()) {
                int index = employeeTableview.getSelectionModel().getSelectedIndex();
                deleteButton.setOnAction(event -> {
                    try {
                        deleteEmployee(index);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        });
    }
}
