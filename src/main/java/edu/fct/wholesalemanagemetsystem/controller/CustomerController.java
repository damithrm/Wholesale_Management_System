package edu.fct.wholesalemanagemetsystem.controller;

import com.jfoenix.controls.JFXButton;
import edu.fct.wholesalemanagemetsystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private JFXButton btnNewCustomer;

    @FXML
    private JFXButton btnSearchCustomer;

    @FXML
    private AnchorPane customerPane;

    public void customerDetails() throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("customerDetails.fxml"));
        customerPane.getChildren().add(newLoadedPane);
    }

    public void newCustomer() throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("newCustomer.fxml"));
        customerPane.getChildren().add(newLoadedPane);
        btnNewCustomer.setDefaultButton(true);
        btnSearchCustomer.setDefaultButton(false);
    }

    public void searchCustomer() throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("searchCustomer.fxml"));
        customerPane.getChildren().add(newLoadedPane);
        btnNewCustomer.setDefaultButton(false);
        btnSearchCustomer.setDefaultButton(true);
    }

    @FXML
    void btnLoadNewCustomer(ActionEvent event) throws IOException {
        newCustomer();
    }

    @FXML
    void btnLordSearchCustomer(ActionEvent event) throws IOException {
        searchCustomer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(Main.class.getResource("viewCustomer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerPane.getChildren().add(newLoadedPane);
    }

}

