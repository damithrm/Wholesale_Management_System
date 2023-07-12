package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import edu.fct.wholesalemanagemetsystem.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ViewItemController implements Initializable {

    @FXML
    private TableView<Item> tableItemDetails;

    @FXML
    private TableColumn<Item, String> col1ItemID;

    @FXML
    private TableColumn<Item, String> col2ItemName;

    @FXML
    private TableColumn<Item, String> col3Brand;

    @FXML
    private TableColumn<Item, String> col4Qty;

    @FXML
    private TableColumn<Item, String> col5UnitPrize;

    ObservableList<Item> itemdatalist = FXCollections.observableArrayList();

    public void loadItem(){
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from item");
            while (rs.next()){
                itemdatalist.add(new Item(
                        rs.getString("item_id"),
                        rs.getString("item_name"),
                        rs.getString("brand"),
                        rs.getString("available_quantity"),
                        rs.getString("unit_prize")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        col1ItemID.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        col2ItemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        col3Brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        col4Qty.setCellValueFactory(new PropertyValueFactory<>("available_quantity"));
        col5UnitPrize.setCellValueFactory(new PropertyValueFactory<>("unit_prize"));
        tableItemDetails.setItems(itemdatalist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadItem();
    }
}

