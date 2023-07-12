package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.Main;
import edu.fct.wholesalemanagemetsystem.db.DBConnection;
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
import java.sql.Statement;
import java.util.ResourceBundle;

public class NewItemController implements Initializable {

    @FXML
    private AnchorPane itemPane;

    @FXML
    private TextField tfItemID;

    @FXML
    private TextField tfItemName;

    @FXML
    private TextField tfItemBrand;

    @FXML
    private TextField tfItemQty;

    @FXML
    private TextField tfItemUnitPrize;

    @FXML
    void addNewItem(ActionEvent event) {
        String id = tfItemID.getText();
        String name = tfItemName.getText();
        String brand = tfItemBrand.getText();
        String qty = tfItemQty.getText();
        String unitPrize = tfItemUnitPrize.getText();

            try {
                Connection con = DBConnection.getInstance().getConnection();
                Statement st = con.createStatement();
                st.executeUpdate("insert into item values ('" + id + "','" + name + "','" + brand + "','" + qty + "','" + unitPrize + "')");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");

                alert.showAndWait();
                tfItemID.clear();
                tfItemBrand.clear();
                tfItemName.clear();
                tfItemQty.clear();
                tfItemUnitPrize.clear();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("");
                alert.setContentText("Update Unsuccessful!");

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
        tfItemID.clear();
        tfItemBrand.clear();
        tfItemName.clear();
        tfItemQty.clear();
        tfItemUnitPrize.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
