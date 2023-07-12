package edu.fct.wholesalemanagemetsystem.controller;

import com.jfoenix.controls.JFXButton;
import edu.fct.wholesalemanagemetsystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    @FXML
    private AnchorPane scenePane;

    @FXML
    private AnchorPane showPane;

    @FXML
    private Label lUsername;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnItem;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private JFXButton btnPurchases;

    @FXML
    private JFXButton btnAccount;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnClose;

    @FXML
    private Pane userEditPane;

    @FXML
    private JFXButton btnMinimize;
    private Stage stage;
    private Scene scene;

    public int  num;
    String userName;


    @FXML
    void closeApplication(ActionEvent event)  {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setHeaderText("Do you want to close.");
        if (alert.showAndWait().get() == ButtonType.OK) {
            this.stage = (Stage)this.scenePane.getScene().getWindow();
            this.stage.close();
        }
    }

    @FXML
    void loginOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        scene = new Scene(fxmlLoader.load());
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(this.scene);
        this.stage.setTitle("Login");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        stage.show();

        stage.setX((dimension.width/2) - (stage.getWidth()/2));
        stage.setY((dimension.height/2) - (stage.getHeight()/2));
    }

    @FXML
    void minimizeApplication(ActionEvent event) {
        stage = (Stage)this.scenePane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void btnLoadDashboard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dashboard.fxml"));
        Pane newLoadedPane = fxmlLoader.load();
        showPane.getChildren().add(newLoadedPane);
        DashboardController user = fxmlLoader.getController();
        user.statLoading();

        btnDashboard.setDefaultButton(true);
        btnPlaceOrder.setDefaultButton(false);
        btnItem.setDefaultButton(false);
        btnCustomer.setDefaultButton(false);
        btnSupplier.setDefaultButton(false);
        btnPurchases.setDefaultButton(false);
        btnAccount.setDefaultButton(false);
    }

    @FXML
    void btnLordPlaceOrder(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("placeOrder.fxml"));
        showPane.getChildren().add(newLoadedPane);
        btnDashboard.setDefaultButton(false);
        btnPlaceOrder.setDefaultButton(true);
        btnItem.setDefaultButton(false);
        btnCustomer.setDefaultButton(false);
        btnSupplier.setDefaultButton(false);
        btnPurchases.setDefaultButton(false);
        btnAccount.setDefaultButton(false);
    }

    @FXML
    void btLordCustomer(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("customer.fxml"));
        showPane.getChildren().add(newLoadedPane);
        btnDashboard.setDefaultButton(false);
        btnPlaceOrder.setDefaultButton(false);
        btnItem.setDefaultButton(false);
        btnCustomer.setDefaultButton(true);
        btnSupplier.setDefaultButton(false);
        btnPurchases.setDefaultButton(false);
        btnAccount.setDefaultButton(false);
    }


    @FXML
    void btnLordAccount(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("account.fxml"));
        showPane.getChildren().add(newLoadedPane);
        btnDashboard.setDefaultButton(false);
        btnPlaceOrder.setDefaultButton(false);
        btnItem.setDefaultButton(false);
        btnCustomer.setDefaultButton(false);
        btnSupplier.setDefaultButton(false);
        btnPurchases.setDefaultButton(false);
        btnAccount.setDefaultButton(true);
    }

    @FXML
    void btnLordItem(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("item.fxml"));
        showPane.getChildren().add(newLoadedPane);
        btnDashboard.setDefaultButton(false);
        btnPlaceOrder.setDefaultButton(false);
        btnItem.setDefaultButton(true);
        btnCustomer.setDefaultButton(false);
        btnSupplier.setDefaultButton(false);
        btnPurchases.setDefaultButton(false);
        btnAccount.setDefaultButton(false);
    }


    @FXML
    void btnLordPurchases(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("purchases.fxml"));
        showPane.getChildren().add(newLoadedPane);
        btnDashboard.setDefaultButton(false);
        btnPlaceOrder.setDefaultButton(false);
        btnItem.setDefaultButton(false);
        btnCustomer.setDefaultButton(false);
        btnSupplier.setDefaultButton(false);
        btnPurchases.setDefaultButton(true);
        btnAccount.setDefaultButton(false);
    }

    @FXML
    void btnLordSupplier(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("supplier.fxml"));
        showPane.getChildren().add(newLoadedPane);
        btnDashboard.setDefaultButton(false);
        btnPlaceOrder.setDefaultButton(false);
        btnItem.setDefaultButton(false);
        btnCustomer.setDefaultButton(false);
        btnSupplier.setDefaultButton(true);
        btnPurchases.setDefaultButton(false);
        btnAccount.setDefaultButton(false);
    }

    public void displayUserName(String username) {
        lUsername.setText(username);
        userName=username;
        userEditPane.setVisible(false);
        if(Objects.equals(userName, "admin") || Objects.equals(userName, "Admin")) {btnAccount.setDisable(false);}
    }

    public void btnLoadDashboard1() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dashboard.fxml"));
        Pane newLoadedPane = fxmlLoader.load();
        showPane.getChildren().add(newLoadedPane);
        DashboardController user = fxmlLoader.getController();
        user.statLoading();
        btnDashboard.setDefaultButton(true);
    }

    public void changeSelectedButton() {
        String[] buttons = {"btnDashboard", "btnPlaceOrder", "btnItem", "btnCustomer", "btnSupplier", "btnOrders", "btnPurchases", "btnAccount"};
        for (int i = 0; i<8; i++) {
            JFXButton jfxButton = new JFXButton(buttons[i]);
            jfxButton.setDefaultButton(false);
        }
        JFXButton jfxButton2 = new JFXButton(buttons[num]);
        jfxButton2.setDefaultButton(true);
    }

    public void OMCLoadUserPane(MouseEvent mouseEvent) {
            userEditPane.setVisible(true);

    }

    public void OMCLoadUserPane1(MouseEvent mouseEvent) {
        userEditPane.setVisible(false);
    }

    public void btnLoadUserProfile(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        Stage stage =new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserProfile.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        UserProfileController userprofilecontrol = fxmlLoader.getController();
        userprofilecontrol.setUserDetails(userName);
        stage.initStyle(StageStyle.UNDECORATED);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
         stage.showAndWait();


        stage.setX((dimension.width/2) - (stage.getWidth()/2));
        stage.setY((dimension.height/2) - (stage.getHeight()/2));
    }

    public void btnLoadNewUser(ActionEvent actionEvent) throws IOException {
        Stage stage =new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(Main.class.getResource("newUser.fxml"));
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        stage.showAndWait();

        stage.setX((dimension.width/2) - (stage.getWidth()/2));
        stage.setY((dimension.height/2) - (stage.getHeight()/2));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

