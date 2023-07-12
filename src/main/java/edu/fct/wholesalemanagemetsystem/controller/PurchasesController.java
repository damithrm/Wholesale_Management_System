package edu.fct.wholesalemanagemetsystem.controller;

import com.jfoenix.controls.JFXButton;
import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PurchasesController implements Initializable {

    @FXML
    private JFXButton remove_1;
    @FXML
    private JFXButton view;
    @FXML
    private JFXButton generate;
    @FXML
    private TextField purchaseid;

    @FXML
    private Label txtsupplierID;

    @FXML
    private Label txtitemdescription;

    @FXML
    private Label txtquantity;

    @FXML
    private TextField Supplierid;

    @FXML
    private TextField quantity;

    @FXML
    private Label txtunitprice;

    @FXML
    private TextField unitprice;

    @FXML
    private TextField itemdescription;

    @FXML
    private Label txtbrand;

    @FXML
    private TextField brand;

    @FXML
    private JFXButton save;
    @FXML
    private TextField realid;

    @FXML
    private Label realid2;

    @FXML
    private JFXButton save1;

    @FXML
    private Label notice;

  /*  @FXML
    private TableView<PurchaseTableModel> table;

    @FXML
    private TableColumn<PurchaseTableModel, String> tableitemdescription;

    @FXML
    private TableColumn<PurchaseTableModel, String> tablebrand;

    @FXML
    private TableColumn<PurchaseTableModel, String> tablequantity;

    @FXML
    private TableColumn<PurchaseTableModel, String> tableunitprice;

    @FXML
    private TableColumn<PurchaseTableModel, String> tabletotal;

   */
    @FXML
    private TextField supplier_name;

    @FXML
    private JFXButton display;

    @FXML
    void generate_id(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select count(purchase_id) from purchase");
        if(rs.next()) {
            int id = rs.getInt(1);
            purchaseid.setText(String.valueOf(id+1));
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("");
            alert.setContentText("Not Found!");
            alert.showAndWait();

        }
    }

    private String add_purchase_1() throws SQLException, ClassNotFoundException {
        String Item = itemdescription.getText();
        String Brand= brand.getText();
        Connection con = DBConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select item_id from item where item_name='"+Item+"' and brand ='"+Brand+"'");
        if(rs.next()) {
            String id = rs.getString(1);
            return id;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("");
            alert.setContentText("Not Found 1!");
            alert.showAndWait();
            return null;
        }
    }

    private void add_purchase_2(String id, String Quantity, String price,String purchase) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        int qty = Integer.parseInt(Quantity);
        Float price1=Float.parseFloat(price);
        st.executeUpdate("insert into purchase_detail values ('" + purchase + "','" +id  + "',"+ qty +", "+ price1+")");
    }

    @FXML
    void add_purchase(ActionEvent event) {
        String supplier = Supplierid.getText();
        String Item = itemdescription.getText();
        String Brand= brand.getText();
        String price= unitprice.getText();
        String Quantity =quantity.getText();
        String pur= realid.getText();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String todayDate = dtf.format(now);


        try {
                Connection con = DBConnection.getInstance().getConnection();
                Statement st = con.createStatement();
                String full;
                if(Integer.parseInt(pur) < 10) {
                    String num1 = "P00";
                    full = num1.concat(pur);
                }else if(Integer.parseInt(pur) < 100){
                    String num1 = "P0";
                    full = num1.concat(pur);
                }else{
                    String num1 = "P";
                    full = num1.concat(pur);
                }
                double total = Float.parseFloat(Quantity) * Float.parseFloat(price);
                st.executeUpdate("insert into purchase values ('" + full + "','" + supplier + "','"+todayDate+"',"+total+")");
                String id = add_purchase_1();
                add_purchase_2(id,Quantity,price,full);
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
                alert.setContentText("Add Unsuccessful!");
                alert.showAndWait();
            }
        }

    @FXML
    void add_purchasesame(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplier = Supplierid.getText();
        String Item = itemdescription.getText();
        String Brand= brand.getText();
        String price= unitprice.getText();
        String Quantity =quantity.getText();
        String pur= realid.getText();

        Connection con = DBConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        String full;
        if(Integer.parseInt(pur) < 10) {
            String num1 = "P00";
            full = num1.concat(pur);
        }else if(Integer.parseInt(pur) < 100){
            String num1 = "P0";
            full = num1.concat(pur);
        }else{
            String num1 = "P";
            full = num1.concat(pur);
        }
        String id = add_purchase_1();
        add_purchase_2(id,Quantity,price,full);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Updated!");

        alert.showAndWait();
        resetTextFields();
    }

    private void resetTextFields() {
        Supplierid.clear();
        itemdescription.clear();
        brand.clear();
        unitprice.clear();
        quantity.clear();
        purchaseid.clear();
        supplier_name.clear();
        realid.clear();
    }
    @FXML
    void display_name(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplier = Supplierid.getText();
        Connection con = DBConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select supplier_name from supplier where supplier_id ='"+supplier+"'");
        if(rs.next()) {
            String id = rs.getString(1);
            supplier_name.setText(id);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("");
            alert.setContentText("Not Found!");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}




