package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {

    @FXML
    private Label UPLUserName;

    @FXML
    private TextField UPTFUserName;

    @FXML
    private PasswordField UPTFOldPassword;

    @FXML
    private PasswordField UPTFNewPassword;

    @FXML
    private PasswordField UPTFConfirmPassword;

    @FXML
    private AnchorPane EditProfilePane;
    private Stage stage;
    private Scene scene;
    private Parent root;


    String username;
    private ResultSet Accountrs;



    public void setUserDetails(String username1) throws SQLException, ClassNotFoundException {
        UPLUserName.setText(username1.toUpperCase());
        this.username = username1;
        Connection con = DBConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        Accountrs = st.executeQuery("select * from user_info where username='" + username + "'");
        if(Accountrs.next()) {
            UPTFUserName.setText(Accountrs.getString(1));
        }
    }

    public void UPBEditProfile(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String newUserName = UPTFUserName.getText();
        String oldpass = UPTFOldPassword.getText();
        String newpass = UPTFNewPassword.getText();
        String confirmpass = UPTFConfirmPassword.getText();
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user_info where username='" + username + "'");
            if (rs.next()) {
                if (rs.getString(2).equals(oldpass)) {
                    if (newpass.equals(confirmpass)) {
                        st.executeUpdate("update user_info set username='" + newUserName + "',password='" + newpass + "' where username='" + username + "' ");
//                        JOptionPane.showMessageDialog(null, "Successfully uptade");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText("Successfully uptade");
                        alert.setContentText("");
                        if (alert.showAndWait().get() == ButtonType.OK) {
                            this.stage = (Stage)this.EditProfilePane.getScene().getWindow();
                            this.stage.close();
                        }

                    }else{
//                        JOptionPane.showMessageDialog(null, "incorrect confirm password");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Warning!");
                        alert.setHeaderText("incorrect confirm password");
                        alert.setContentText("Please Re-enter .");
                        alert.showAndWait();
                    }
                } else {
//                  JOptionPane.showMessageDialog(null, "incorrect password");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Warning!");
                    alert.setHeaderText("incorrect password");
                    alert.setContentText("Please Re-enter .");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){
//            JOptionPane.showMessageDialog(null, "This user name is already exist");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("This user name is already exist");
            alert.setContentText("Please Re-enter .");
            alert.showAndWait();
        }
    }


    public void closeEditProfile(MouseEvent mouseEvent) {
        this.stage = (Stage)this.EditProfilePane.getScene().getWindow();
        this.stage.close();
    }

    public void minimizeEditProfile(MouseEvent mouseEvent) {
        stage = (Stage)this.EditProfilePane.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeEditProfile1(ActionEvent actionEvent) {
        this.stage = (Stage)this.EditProfilePane.getScene().getWindow();
        this.stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
