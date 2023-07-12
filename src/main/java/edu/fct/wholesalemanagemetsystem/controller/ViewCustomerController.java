package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import edu.fct.wholesalemanagemetsystem.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ViewCustomerController implements Initializable {
    @FXML
    private TableView<Customer> tableCustomerDetails;

    @FXML
    private TableColumn<Customer, String> col1CustomerID;

    @FXML
    private TableColumn<Customer, String> col2CustomerName;

    @FXML
    private TableColumn<Customer, String> col3TeleNumber;

    @FXML
    private TableColumn<Customer, String> col4CustomerAddress;

    ObservableList<Customer> customerdatalist = FXCollections.observableArrayList();

    @FXML
    void showCustomerDetails(ActionEvent event) {

    }
    public void loadCustomer(){
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from customer");
            while (rs.next()){
                customerdatalist.add(new Customer(
                        rs.getString("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("telephone_no"),
                        rs.getString("customer_address")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        col1CustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        col2CustomerName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        col3TeleNumber.setCellValueFactory(new PropertyValueFactory<>("telephone_no"));
        col4CustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        tableCustomerDetails.setItems(customerdatalist);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCustomer();
    }


}
