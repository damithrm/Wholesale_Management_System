package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.Main;
import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import edu.fct.wholesalemanagemetsystem.model.Customer;
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

public class SearchCustomerController implements Initializable {

    @FXML
    private AnchorPane customerPane;

    @FXML
    private TextField tfCustomerID;

    @FXML
    private TextField tfCustomerName;

    @FXML
    private TextField tfTeleNo;

    @FXML
    private TextField tfCustomerAddress;

    @FXML
    private TextField tfSearchID;

    @FXML
    private TextField tfSearchTele;

    @FXML
    void deleteCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = tfCustomerID.getText();
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("delete from customer where customer_id='" + id + "'");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Deleted!");
            alert.showAndWait();

            tfCustomerID.clear();
            tfCustomerName.clear();
            tfTeleNo.clear();
            tfCustomerAddress.clear();
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
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("viewCustomer.fxml"));
        customerPane.getChildren().add(newLoadedPane);
    }

    @FXML
    void resetFields(ActionEvent event) {
        tfCustomerName.clear();
        tfTeleNo.clear();
        tfCustomerAddress.clear();
    }

    @FXML
    void searchCustomer(ActionEvent event) {
        String searchID = tfSearchID.getText();
        String searchTele = tfSearchTele.getText();

        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from customer where customer_id='"+searchID+"' or telephone_no='"+searchTele+"'");
            if(rs.next()) {

                tfCustomerID.setText(rs.getString(1));
                tfCustomerName.setText(rs.getString(2));
                tfTeleNo.setText(rs.getString(3));
                tfCustomerAddress.setText(rs.getString(4));
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
    void updateCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = tfCustomerID.getText();
        String name = tfCustomerName.getText();
        String tele = tfTeleNo.getText();
        String address = tfCustomerAddress.getText();
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("update customer set customer_name='" + name + "', telephone_no='" + tele + "', customer_address='" + address + "' where customer_id='" + id + "'");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Updated!");
            alert.showAndWait();

            tfCustomerID.clear();
            tfCustomerName.clear();
            tfTeleNo.clear();
            tfCustomerAddress.clear();
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

    public static Customer searchCustomer(String id ) throws SQLException, ClassNotFoundException{
        Connection con = DBConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rst = st.executeQuery("select * from customer where customer_id='"+id+"'");
        if(rst.next()){
            return new Customer(
                    rst.getString("customer_id"),
                    rst.getString("customer_name"),
                    rst.getString("telephone_no"),
                    rst.getString("customer_address"));
        }else{
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
