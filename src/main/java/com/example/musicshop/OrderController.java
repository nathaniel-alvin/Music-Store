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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private TableColumn<Order, String> addressTablecol;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Order, Integer> idTablecol;

    @FXML
    private TableView<Order> orderTableview;

    @FXML
    private TableColumn<Order, String> statusTablecol;

    String username;
    ObservableList<Order> list = FXCollections.observableArrayList();

    public void showTable() throws SQLException {
        Connection connectDB = DatabaseConnection.getConnection();
        String getCustomerOrderId = "SELECT id FROM customer WHERE user_name = ?";
        String query = "SELECT id, delivery_address, order_status FROM customer_order WHERE customer_id = ?";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(getCustomerOrderId);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int customerId = resultSet.getInt(1);

            PreparedStatement preparedStatement1 = connectDB.prepareStatement(query);
            preparedStatement1.setInt(1, customerId);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            Order orders;
            while (resultSet1.next()) {
                orders = new Order(resultSet1.getInt(1), resultSet1.getString(2), resultSet1.getString(3));

                list.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        idTablecol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addressTablecol.setCellValueFactory(new PropertyValueFactory<>("address"));
        statusTablecol.setCellValueFactory(new PropertyValueFactory<>("status"));

        orderTableview.setItems(list);
    }

    public void backButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("catalogue.fxml"));
            Stage registerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 800, 600));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Singleton holder = Singleton.getInstance();
        username = holder.getUsername();
        try {
            showTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
