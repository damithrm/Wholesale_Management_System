package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.Main;
import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import edu.fct.wholesalemanagemetsystem.model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SearchItemController implements Initializable {
    @FXML
    private AnchorPane itemPane;

    @FXML
    private TextField tfSearchName;

    @FXML
    private TextField tfSearchBrand;

    @FXML
    private TextField tfItemID;

    @FXML
    private TextField tfItemName;

    @FXML
    private TextField tfItemBrand;

    @FXML
    private TextField tfSearchID;

    @FXML
    private TextField tfItemQty;

    @FXML
    private TextField tfItemUnitPrice;

    @FXML
    void deleteCustomer(ActionEvent event) {
        String id = tfItemID.getText();
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("delete from item where item_id='" + id + "'");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Deleted!");
            alert.showAndWait();

            tfItemID.clear();
            tfItemName.clear();
            tfItemBrand.clear();
            tfItemQty.clear();
            tfItemUnitPrice.clear();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Delete Unsuccessful!");
            alert.showAndWait();
        }
    }

    @FXML
    void loadBack(MouseEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("viewItem.fxml"));
        itemPane.getChildren().add(newLoadedPane);
    }

    @FXML
    void resetFields(ActionEvent event) {
        tfItemName.clear();
        tfItemBrand.clear();
        tfItemQty.clear();
        tfItemUnitPrice.clear();
    }

    @FXML
    void searchItem(ActionEvent event) {
        String searchID = tfSearchID.getText();
        String searchName = tfSearchName.getText();
        String searchBrand = tfSearchBrand.getText();


        try {

            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from item where item_id='" + searchID + "' or (item_name='" + searchName + "' and brand='" + searchBrand + "')");

            if (rs.next()) {
                tfItemID.setText(rs.getString(1));
                tfItemName.setText(rs.getString(2));
                tfItemBrand.setText(rs.getString(3));
                tfItemQty.setText(rs.getString(4));
                tfItemUnitPrice.setText(rs.getString(5));
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("");
                alert.setContentText("Not Found!");
                alert.showAndWait();
            }

        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("");
            alert.setContentText("Not Found!");

            alert.showAndWait();
        }
    }

    @FXML
    void updateItem(ActionEvent event) {
        String id = tfItemID.getText();
        String name = tfItemName.getText();
        String brand = tfItemBrand.getText();
        String qty = tfItemQty.getText();
        String unitPrice = tfItemUnitPrice.getText();
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("update item set item_name='" + name + "', brand='" + brand + "', available_quantity='" + qty + "', unit_prize='" + unitPrice + "' where item_id='" + id + "'");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Updated!");
            alert.showAndWait();

            tfSearchID.clear();
            tfSearchName.clear();
            tfSearchBrand.clear();
            tfItemID.clear();
            tfItemName.clear();
            tfItemBrand.clear();
            tfItemQty.clear();
            tfItemUnitPrice.clear();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Update Unsuccessful!");
            alert.showAndWait();
        }
    }

    public static Item searchItem(String id ) throws SQLException, ClassNotFoundException{
        Connection con = DBConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rst = st.executeQuery("select * from item where item_id='"+id+"'");
        if(rst.next()){
            return new Item(
                    rst.getString("item_id"),
                    rst.getString("item_name"),
                    rst.getString("brand"),
                    rst.getString("available_quantity"),
                    rst.getString("unit_prize"));
        }else{
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

