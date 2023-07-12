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

public class AccountController implements Initializable {

    @FXML
    private JFXButton btnOrderDetails;

    @FXML
    private JFXButton btnPurchaseDetails;

    @FXML
    private AnchorPane accountPane;

    @FXML
    void btnLoadOrderDetails(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("orderDetails.fxml"));
        accountPane.getChildren().add(newLoadedPane);
        btnOrderDetails.setDefaultButton(true);
        btnPurchaseDetails.setDefaultButton(false);
    }

    @FXML
    void btnLordPurchaseDetails(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("purchaseDetails.fxml"));
        accountPane.getChildren().add(newLoadedPane);
        btnOrderDetails.setDefaultButton(false);
        btnPurchaseDetails.setDefaultButton(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(Main.class.getResource("orderDetails.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        accountPane.getChildren().add(newLoadedPane);
        btnOrderDetails.setDefaultButton(true);
    }
}