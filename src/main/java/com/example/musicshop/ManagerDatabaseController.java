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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerDatabaseController implements Initializable {

    @FXML
    private Label countEmployeeLabel;
    @FXML
    private Label countBranchLabel;
    @FXML
    private TableView<Employee> employeeTableview;
    @FXML
    private TableColumn<Employee, Integer> c1Tablecol;
    @FXML
    private TableColumn<Employee, String> c2Tablecol;
    @FXML
    private TableColumn<Employee, String> c3Tablecol;
    @FXML
    private TableColumn<Employee, String> c4Tablecol;
    @FXML
    private TableColumn<Employee, String> c5Tablecol;
    @FXML
    private TableColumn<Employee, Integer> c6Tablecol;
    @FXML
    private TableColumn<Employee, String> c7Tablecol;
    @FXML
    private TableColumn<Employee, String> c8Tablecol;
    @FXML
    private TableColumn<Employee, String> c9Tablecol;
    @FXML
    private TableColumn<Employee, String> c10Tablecol;
    @FXML
    private TableView<Branch> branchTableview;
    @FXML
    private TableColumn<Branch, Integer> idBranchTablecol;
    @FXML
    private TableColumn<Employee, Integer> nameBranchTablecol;
    @FXML
    private TableColumn<Employee, Integer> locationBranchTablecol;
    @FXML
    private TableColumn<Employee, Integer> employeeNumberBranchTablecol;
    @FXML
    private TableColumn<Employee, Integer> itemNumberBranchTablecol;

    ObservableList<Branch> branchList = FXCollections.observableArrayList();
    ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    ObservableList<Role> roleList = FXCollections.observableArrayList();

    public ObservableList<Branch> getBranchList() {
        try {
            String branchQuery = "SELECT * FROM branch";
            Connection connectDB = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connectDB.prepareStatement(branchQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            Branch branch;
            while (resultSet.next()) {
                branch = new Branch(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5));
                branchList.add(branch);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return branchList;
    }

    public ObservableList<Employee> getEmployeeList() {
        try {
            Connection connectDB = DatabaseConnection.getConnection();

            String query = "select e.id, e.employee_name, e.join_date, e.address, e.date_of_birth, r.role_name, b.branch_name, e.phonenumber, e.gender, e.password  from employee e inner join role r on e.role_id = r.id inner join branch b on e.branch_id = b.id";

            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employees;
            while (resultSet.next()) {
                employees = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5),resultSet.getString(6), resultSet.getString(7), "0" + resultSet.getInt(8), resultSet.getString(9), resultSet.getString(10));
                employeeList.add(employees);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public ObservableList<Role> getRoleList() {
        try {
            String branchQuery = "SELECT * FROM role";
            Connection connectDB = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connectDB.prepareStatement(branchQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            Role role;
            while (resultSet.next()) {
                role = new Role(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                roleList.add(role);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }

    public void showEmployee() {
        ObservableList<Employee> list = getEmployeeList();

        c1Tablecol.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2Tablecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        c3Tablecol.setCellValueFactory(new PropertyValueFactory<>("address"));
        c4Tablecol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        c5Tablecol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        c6Tablecol.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        c7Tablecol.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        c8Tablecol.setCellValueFactory(new PropertyValueFactory<>("branch"));
        c9Tablecol.setCellValueFactory(new PropertyValueFactory<>("role"));
        c10Tablecol.setCellValueFactory(new PropertyValueFactory<>("password"));

        employeeTableview.setItems(list);
        String countItem = "Item in the table: " + String.valueOf(list.size());
        countEmployeeLabel.setText(countItem);
    }

    public void showBranch() {
        ObservableList<Branch> list = getBranchList();

        idBranchTablecol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameBranchTablecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationBranchTablecol.setCellValueFactory(new PropertyValueFactory<>("location"));
        employeeNumberBranchTablecol.setCellValueFactory(new PropertyValueFactory<>("numberOfEmployee"));
        itemNumberBranchTablecol.setCellValueFactory(new PropertyValueFactory<>("numberOfItem"));

        branchTableview.setItems(list);
        String countItem = "Item in the table: " + String.valueOf(list.size());
        countBranchLabel.setText(countItem);
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
        showEmployee();
        showBranch();
    }
}
