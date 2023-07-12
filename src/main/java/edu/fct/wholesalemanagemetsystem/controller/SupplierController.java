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

public class SupplierController implements Initializable {

    @FXML
    private JFXButton btnNewSupplier;

    @FXML
    private JFXButton btnSearchSupplier;

    @FXML
    private AnchorPane supplierPane;

    @FXML
    void btnLoadNewSupplier(ActionEvent event) throws IOException{
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("newSupplier.fxml"));
        supplierPane.getChildren().add(newLoadedPane);
        btnNewSupplier.setDefaultButton(true);
        btnSearchSupplier.setDefaultButton(false);
    }

    @FXML
    void btnLoadSearchSupplier(ActionEvent event) throws IOException{
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("searchSupplier.fxml"));
        supplierPane.getChildren().add(newLoadedPane);
        btnNewSupplier.setDefaultButton(false);
        btnSearchSupplier.setDefaultButton(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(Main.class.getResource("viewSupplier.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        supplierPane.getChildren().add(newLoadedPane);
    }
}
