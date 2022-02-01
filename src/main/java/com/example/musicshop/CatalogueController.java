package com.example.musicshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CatalogueController implements Initializable{

    @FXML
    private TableView<Instrument> instrumentTableView;

    @FXML
    private TableColumn<Instrument, Integer> idCol;

    @FXML
    private TableColumn<Instrument, String> nameCol;

    @FXML
    private TableColumn<Instrument, String> categoryCol;

    @FXML
    private TableColumn<Instrument, Integer> quantityCol;

    @FXML
    private TableColumn<Instrument, Float> priceCol;

    @FXML
    private TableColumn<Instrument, String> descriptionCol;

    @FXML
    private TableView<Instrument> binTableView;

    @FXML
    private TableColumn<Instrument, String> nameColBin;

    @FXML
    private TableColumn<Instrument, Integer> quantityColBin;

    @FXML
    private TableColumn<Instrument, Integer> priceColBin;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField descTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField yopTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button orderButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button editButton;

    @FXML
    private Label nameLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<Instrument> instrumentList = FXCollections.observableArrayList();
    ObservableList<Instrument> binList = FXCollections.observableArrayList();
    float newPrice;

    String currentUsername;

    public void displayName(String username) {
        nameLabel.setText(username);
    }

    public ObservableList<Instrument> getBinList() {
        return binList;
    }

    public ObservableList<Instrument> getInstrumentList() {

        try {
        Connection connectDB = DatabaseConnection.getConnection();

        String query = "SELECT item.id, instrument.instrument_name, instrument.category, item.quantity, item.price, instrument.description, item.year_of_production FROM item INNER JOIN instrument ON item.instrument_id = instrument.id";

            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            Instrument instruments;
            while (resultSet.next()) {
                instruments = new Instrument(resultSet.getInt("id"), resultSet.getString("instrument_name"),
                        resultSet.getString("category"), resultSet.getInt("quantity"),
                        resultSet.getFloat("price"), resultSet.getString("description"), resultSet.getInt("year_of_production"));
                instrumentList.add(instruments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instrumentList;
    }

    public void showInstruments() throws SQLException {
        ObservableList<Instrument> list = getInstrumentList();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        instrumentTableView.setItems(list);
    }

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

    public Instrument getInstrumentBin(int index) {

        int id = instrumentList.get(index).getId();
        String name = instrumentList.get(index).getName();
        int quantity = 1;
        float price = instrumentList.get(index).getPrice();

//        System.out.println(id);
        return new Instrument(id, name, quantity, price);
    }

    public void addBin(int index) {
        Instrument instrument = getInstrumentBin(index);
        int maxQuantity = instrumentList.get(index).getQuantity();
        float price = instrumentList.get(index).getPrice();

//        System.out.println(binList.size());

        if (binList.size() > 0) {

            Instrument replacedInstrument = null;

            for (Instrument instrumentInList : binList) {
                if (instrument.getId() == instrumentInList.getId()) {
                    replacedInstrument = instrumentInList;
                    break;
                }
            }

            if (replacedInstrument != null) {
//                System.out.println("Hello");
                if (maxQuantity > replacedInstrument.getQuantity()) {
                    replacedInstrument.addQuantity();
                    newPrice = price * replacedInstrument.getQuantity();
                    replacedInstrument.setPrice(newPrice);
                    binList.set(binList.indexOf(replacedInstrument), replacedInstrument);
                }
            } else {
//                System.out.println("Not hello");
                binList.add(instrument);
            }
            nameColBin.setCellValueFactory(new PropertyValueFactory<>("name"));
            quantityColBin.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            priceColBin.setCellValueFactory(new PropertyValueFactory<>("price"));

            binTableView.setItems(binList);
        } else {
//            System.out.println("Not not hello");
            binList.add(instrument);
        }

        nameColBin.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColBin.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColBin.setCellValueFactory(new PropertyValueFactory<>("price"));

        binTableView.setItems(binList);
    }

    public void removeBin(int index) {
        binList.remove(index);

        nameColBin.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColBin.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColBin.setCellValueFactory(new PropertyValueFactory<>("price"));

        binTableView.setItems(binList);
    }

    public void orderButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane root = loader.load(getClass().getResource("checkout.fxml").openStream());

            Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 800,600));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // profile button
    public void profileButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        BorderPane root = loader.load(getClass().getResource("profileuser.fxml").openStream());

        Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root, 800,600));
        registerStage.show();
    }

    public void showButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        BorderPane root = loader.load(getClass().getResource("category.fxml").openStream());

        Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root, 600,400));
        registerStage.show();
    }

    public void showOrderButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        BorderPane root = loader.load(getClass().getResource("order.fxml").openStream());

        Stage registerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root, 600,400));
        registerStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Singleton holder = Singleton.getInstance();
        currentUsername = holder.getUsername();
        holder.setList(binList);


        try {
            showInstruments();
            nameLabel.setText(currentUsername);

            FilteredList<Instrument> filteredData = new FilteredList<>(instrumentList, b -> true);

            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Instrument -> {
                    if (newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();

                    if (Instrument.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true; // Found a match in item name;
                    } else if (Instrument.getCategory().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    } else if (Instrument.getDescription().indexOf(searchKeyword) > -1) {
                        return true;
                    } else {
                        return false; // no match found
                    }
                });
            });

            SortedList<Instrument> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(instrumentTableView.comparatorProperty());

            instrumentTableView.setItems(sortedData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instrumentTableView.setOnMousePressed(e ->{
            if (e.getClickCount() == 1 && e.isPrimaryButtonDown() ){
                int index = instrumentTableView.getSelectionModel().getSelectedIndex();
//                System.out.println("" + index);
//                System.out.println(instrumentList.get(index).getName());

                idTextField.setText(String.valueOf(instrumentList.get(index).getId()));
                nameTextField.setText(instrumentList.get(index).getName());
                yopTextField.setText(String.valueOf(instrumentList.get(index).getYop()));
                categoryTextField.setText(instrumentList.get(index).getCategory());
                descTextField.setText(instrumentList.get(index).getDescription());

                addButton.setOnAction(event -> {
                    addBin(index);
                });
            }
        });

        binTableView.setOnMousePressed(e ->{
            if (e.getClickCount() == 1 && e.isPrimaryButtonDown() ){
                int index2 = binTableView.getSelectionModel().getSelectedIndex();
                removeButton.setOnAction(event -> {
                    removeBin(index2);
                });
            }
        });
    }
}
