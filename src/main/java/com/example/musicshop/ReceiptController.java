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
import java.util.ResourceBundle;

public class ReceiptController implements Initializable {

    @FXML
    private Label addressLabel;
    @FXML
    private Label orderLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private TableColumn<Instrument, String> nameTablecol;
    @FXML
    private TableColumn<Instrument, Float> priceTablecol;
    @FXML
    private TableColumn<Instrument, Integer> quantityTablecol;
    @FXML
    private TableView<Instrument> receiptTableview;
    @FXML
    private Button nextButton;

    String username;


    private ObservableList<Instrument> binList = FXCollections.observableArrayList();
    private ObservableList<Instrument> list = FXCollections.observableArrayList();

    public void showList(ObservableList<Instrument> list) {

        nameTablecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityTablecol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceTablecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        receiptTableview.setItems(list);
    }

    public void getOrderDetails() throws SQLException {
        Connection connectDB = DatabaseConnection.getConnection();

        String getCustomerOrderId = "SELECT id, delivery_address, total_price FROM customer_order ORDER BY id DESC LIMIT 1";
        String query = "SELECT item.description as name, order_item.quantity as qty, order_item.price as ind_price, order_item.quantity * order_item.price as total_price FROM order_item JOIN item on order_item.item_id = item.id WHERE order_item.customer_order_id = ? ORDER BY item.description";
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(getCustomerOrderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int customerOrderId = resultSet.getInt(1);
            String address = resultSet.getString(2);
            float total_price = resultSet.getFloat(3);
            orderLabel.setText(String.valueOf(customerOrderId));
            addressLabel.setText(address);
            priceLabel.setText(String.valueOf(total_price));

            PreparedStatement preparedStatement1 = connectDB.prepareStatement(query);
            preparedStatement1.setInt(1, customerOrderId);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            Instrument instruments;
            while (resultSet1.next()) {
                instruments = new Instrument(resultSet1.getString("name"), resultSet1.getInt("qty"), resultSet1.getFloat("ind_price"));
                binList.add(instruments);
                System.out.println("added instrument");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        showList(binList);
    }

    public void nextButtonOnAction (ActionEvent event) {
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
        list = holder.getList();

        try {
            getOrderDetails();
            showList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
