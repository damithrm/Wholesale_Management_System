package edu.fct.wholesalemanagemetsystem.controller;

import com.jfoenix.controls.JFXButton;
import edu.fct.wholesalemanagemetsystem.Main;
import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private AnchorPane scenePane;

    @FXML
    private JFXButton btnClose;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private JFXButton btnLogin;
    private Stage stage;
    private Scene scene;

    @FXML
    void closeApplication(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Do you want to close.");
        if (alert.showAndWait().get() == ButtonType.OK) {
            this.stage = (Stage)this.scenePane.getScene().getWindow();
            this.stage.close();
        }
    }


    @FXML
    void logIntoSystem(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String username = this.tfUsername.getText();
        String password = this.pfPassword.getText();
        Connection con = DBConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from user_info where username='" + username + "'");
        if (rs.next()) {
            if (rs.getString(2).equals(password)) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
                scene = new Scene(fxmlLoader.load());
                HomeController home = fxmlLoader.getController();
                home.displayUserName(username);
                home.btnLoadDashboard1();
                this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                this.stage.setScene(this.scene);
                this.stage.setTitle("Home");
                //stage.setScene(scene);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                stage.show();

                stage.setX((dimension.width/2) - (stage.getWidth()/2));
                stage.setY((dimension.height/2) - (stage.getHeight()/2));
            } else {
                //JOptionPane.showMessageDialog((Component) null, "invalid password");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Your Password is Incorrect.");
                alert.setContentText("Please Re-enter your Password.");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Your Username is Incorrect.");
            alert.setContentText("Please Re-enter your Username.");

            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}