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

public class ItemController implements Initializable {

    @FXML
    private JFXButton btnNewItem;

    @FXML
    private JFXButton btnSearchItem;

    @FXML
    private AnchorPane itemPane;

    @FXML
    void btnLoadNewItem(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("newItem.fxml"));
        itemPane.getChildren().add(newLoadedPane);
        btnNewItem.setDefaultButton(true);
        btnSearchItem.setDefaultButton(false);
    }

    @FXML
    void btnLordSearchItem(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("searchItem.fxml"));
        itemPane.getChildren().add(newLoadedPane);
        btnNewItem.setDefaultButton(false);
        btnSearchItem.setDefaultButton(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(Main.class.getResource("viewItem.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        itemPane.getChildren().add(newLoadedPane);
    }
}