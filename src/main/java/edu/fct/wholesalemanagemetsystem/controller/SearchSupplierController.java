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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SearchSupplierController implements Initializable {
    @FXML
    private AnchorPane supplierPane;

    @FXML
    private TextField tfSupplierID;

    @FXML
    private TextField tfSupplierName;

    @FXML
    private TextField tfTeleNo;

    @FXML
    private TextField tfBAddress;

    @FXML
    private TextField tfSearchID;

    @FXML
    private TextField tfSearchTele;

    @FXML
    private TextField tfBrand;

    @FXML
    private TextField tfEmail;

    @FXML
    void deleteSupplier(ActionEvent event) {
        String id = tfSupplierID.getText();
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("delete from supplier where supplier_id='" + id + "'");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Deleted!");
            alert.showAndWait();

            tfSupplierID.clear();
            tfSupplierName.clear();
            tfBrand.clear();
            tfTeleNo.clear();
            tfEmail.clear();
            tfBAddress.clear();
            tfSearchTele.clear();
            tfSearchID.clear();
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
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("viewSupplier.fxml"));
        supplierPane.getChildren().add(newLoadedPane);
    }

    @FXML
    void resetFields(ActionEvent event) {
        tfSupplierName.clear();
        tfBrand.clear();
        tfTeleNo.clear();
        tfEmail.clear();
        tfBAddress.clear();
    }

    @FXML
    void searchSupplier(ActionEvent event) {
        String searchID = tfSearchID.getText();
        String searchTele = tfSearchTele.getText();

        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from supplier where supplier_id='"+searchID+"' or telephone_no='"+searchTele+"'");
            if(rs.next()) {

                tfSupplierID.setText(rs.getString(1));
                tfSupplierName.setText(rs.getString(2));
                tfBrand.setText(rs.getString(3));
                tfTeleNo.setText(rs.getString(4));
                tfEmail.setText(rs.getString(5));
                tfBAddress.setText(rs.getString(6));
            }
            else {
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
    void updateSupplier(ActionEvent event) {
        String id=tfSupplierID.getText();
        String name=tfSupplierName.getText();
        String brand=tfBrand.getText();
        String tele=tfTeleNo.getText();
        String email=tfEmail.getText();
        String bAddress=tfBAddress.getText();
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("update supplier set supplier_name='" + name + "', brand='" + brand + "', telephone_no='" + tele + "', email='" + email + "', buisiness_address='" + bAddress + "' where supplier_id='" + id + "'");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Updated!");
            alert.showAndWait();

            tfSupplierID.clear();
            tfSupplierName.clear();
            tfBrand.clear();
            tfTeleNo.clear();
            tfEmail.clear();
            tfBAddress.clear();
            tfSearchTele.clear();
            tfSearchID.clear();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Update Unsuccessful!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
