package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class NewUserController implements Initializable {

    @FXML
    private AnchorPane NewUserPane;

    @FXML
    private Label NULUserName;

    @FXML
    private TextField NUTFUserName;

    @FXML
    private PasswordField NUTFNewPassword;

    @FXML
    private PasswordField NUTFConfirmPassword;

    private Stage stage;


    public void NUBEditProfile(ActionEvent actionEvent) {
        String UserName = NUTFUserName.getText();
        String newpass = NUTFNewPassword.getText();
        String confirmpass = NUTFConfirmPassword.getText();
        try {
            if (newpass.equals(confirmpass)) {
                Connection con = DBConnection.getInstance().getConnection();
                Statement st = con.createStatement();
                st.executeUpdate("insert into user_info values('"+UserName+"','"+newpass+"')");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Successfully uptade");
                alert.setContentText("");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    this.stage = (Stage)this.NewUserPane.getScene().getWindow();
                    this.stage.close();
                }

            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning!");
                alert.setHeaderText("incorrect confirm password");
                alert.setContentText("Please Re-enter .");
                alert.showAndWait();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("incorrect password");
            alert.setContentText("Please Re-enter .");
            alert.showAndWait();
        }
    }

    public void closeNewUser(ActionEvent actionEvent) {
        this.stage = (Stage)this.NewUserPane.getScene().getWindow();
        this.stage.close();
    }

    public void minimizeEditProfile(MouseEvent mouseEvent) {
        stage = (Stage)this.NewUserPane.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeNewUser1(MouseEvent mouseEvent) {
        this.stage = (Stage)this.NewUserPane.getScene().getWindow();
        this.stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
