package com.example.musicshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {
    @FXML
    private Button cancelButton;

    @FXML
    private Button orderButton;

    @FXML
    private TextArea addressTextarea;

    @FXML
    private TextField priceTextfield;

    @FXML
    private TableView<Instrument> checkoutTableview;

    @FXML
    private TableColumn<Instrument, String> nameCol;

    @FXML
    private TableColumn<Instrument, Integer> quantityCol;

    @FXML
    private TableColumn<Instrument, Integer> priceCol;

    String username;
    float totalPrice = 0;
    Singleton holder = Singleton.getInstance();

    ObservableList<Instrument> binList = FXCollections.observableArrayList();

    public void showList(ObservableList<Instrument> list) {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        checkoutTableview.setItems(list);
    }

    public void cancelButtonOnAction(ActionEvent event) {
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
    }

    public void orderButtonOnAction(ActionEvent event) throws SQLException {
        orderItem();
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane root = loader.load(getClass().getResource("receipt.fxml").openStream());
            Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 400,600));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void orderItem() throws SQLException {


        String address = addressTextarea.getText().trim();
        System.out.println(address);


        Connection connectDB = DatabaseConnection.getConnection();
        connectDB.setAutoCommit(false);

        String getCustomerId = "SELECT id FROM customer WHERE user_name = ?";
        String addCustomerOrder = "INSERT INTO customer_order (customer_id, delivery_address, order_status, total_price) VALUES (?, ?, ?, ?)";
        String getCustomerOrderId = "SELECT id FROM customer_order WHERE customer_id = ?";
        String addOrderItem = "INSERT INTO order_item (customer_order_id, item_id, price, quantity) VALUES (?, ?, ?, ?)";


        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(getCustomerId);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int customerId = resultSet.getInt(1);

            PreparedStatement preparedStatement1 = connectDB.prepareStatement(addCustomerOrder);
            preparedStatement1.setInt(1, customerId);
            preparedStatement1.setString(2, address);
            preparedStatement1.setString(3, "In Process");
            preparedStatement1.setFloat(4, totalPrice);
            preparedStatement1.executeUpdate();

            PreparedStatement preparedStatement2 = connectDB.prepareStatement(getCustomerOrderId);
            preparedStatement2.setInt(1, customerId);
            ResultSet resultSet1 = preparedStatement2.executeQuery();
            resultSet1.next();
            int customerOrderId = resultSet1.getInt(1);

            PreparedStatement preparedStatement3 = connectDB.prepareStatement(addOrderItem);

            for (Instrument instrument : binList) {
                preparedStatement3.setInt(1, customerOrderId);
                preparedStatement3.setInt(2, instrument.getId());
                preparedStatement3.setFloat(3, instrument.getPrice());
                preparedStatement3.setInt(4, instrument.getQuantity());

                preparedStatement3.addBatch();
            }

            preparedStatement3.executeBatch();

            connectDB.commit();
            preparedStatement.close();
            preparedStatement1.close();
            preparedStatement2.close();
            preparedStatement3.close();

            System.out.println("Added to database");

        } catch (SQLException e) {
            connectDB.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        binList = holder.getList();
        username = holder.getUsername();

        showList(binList);
        for (Instrument instrument : binList) {
            totalPrice += instrument.getPrice();
        }

        priceTextfield.setText(String.valueOf(totalPrice));
    }
}
