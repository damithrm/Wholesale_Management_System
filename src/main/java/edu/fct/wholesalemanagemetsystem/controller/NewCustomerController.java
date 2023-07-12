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

public class NewCustomerController implements Initializable {

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
    void addNewCustomer(ActionEvent event) {
        String id = tfCustomerID.getText();
        String name = tfCustomerName.getText();
        String tele = tfTeleNo.getText();
        String address = tfCustomerAddress.getText();
        if(tele.length() == 10) {

            try {
                Connection con = DBConnection.getInstance().getConnection();
                Statement st = con.createStatement();
                st.executeUpdate("insert into customer values ('" + id + "','" + name + "','" + tele + "','" + address + "')");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");

                alert.showAndWait();
                resetTextFields();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("");
                alert.setContentText("Update Unsuccessful!");

                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Invalid Phone Number!");

            alert.showAndWait();
        }
    }

    @FXML
    void loadBack(MouseEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("viewCustomer.fxml"));
        customerPane.getChildren().add(newLoadedPane);

    }

    public void resetTextFields() {
        tfCustomerID.clear();
        tfCustomerName.clear();
        tfTeleNo.clear();
        tfCustomerAddress.clear();
    }

    @FXML
    void resetFields(ActionEvent event) {
        resetTextFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
