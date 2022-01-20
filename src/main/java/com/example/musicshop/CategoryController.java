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

public class CategoryController implements Initializable {
    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Category, String> categoryTablecol;

    @FXML
    private TableColumn<Category, Integer> quantityTablecol;

    @FXML
    private TableView<Category> categoryTableview;

    ObservableList<Category> catList = FXCollections.observableArrayList();

    public void showCategory() {

        try {
            Connection connectDB = DatabaseConnection.getConnection();
            String query = "SELECT category, count(id) FROM instrument GROUP BY category";

            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            Category categories;
            while (resultSet.next()) {
                categories = new Category(resultSet.getString(1), resultSet.getInt(2));
                catList.add(categories);
            }

            categoryTablecol.setCellValueFactory(new PropertyValueFactory<>("name"));
            quantityTablecol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            categoryTableview.setItems(catList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void backButtonOnAction(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCategory();
    }
}
