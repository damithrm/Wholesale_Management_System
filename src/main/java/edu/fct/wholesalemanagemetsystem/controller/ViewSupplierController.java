package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import edu.fct.wholesalemanagemetsystem.model.Supplier;
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

public class ViewSupplierController implements Initializable {
    @FXML
    private TableView<Supplier> tableSupplierDetails;

    @FXML
    private TableColumn<Supplier, String> col1SupplierID;

    @FXML
    private TableColumn<Supplier, String> col2SupplierName;

    @FXML
    private TableColumn<Supplier, String> col3Brand;

    @FXML
    private TableColumn<Supplier, String> col4TeleNumber;

    @FXML
    private TableColumn<Supplier, String> col5Email;

    @FXML
    private TableColumn<Supplier, String> col6BusinessAddress;

    ObservableList<Supplier> supplierdatalist = FXCollections.observableArrayList();

    @FXML
    void showSupplierDetails(ActionEvent event) {

    }
    public void loadSupplier(){

        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from supplier");
            while (rs.next()){
                supplierdatalist.add(new Supplier(
                        rs.getString("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("brand"),
                        rs.getString("telephone_no"),
                        rs.getString("email"),
                        rs.getString("buisiness_address")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        col1SupplierID.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        col2SupplierName.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        col3Brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        col4TeleNumber.setCellValueFactory(new PropertyValueFactory<>("telephone_no"));
        col5Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col6BusinessAddress.setCellValueFactory(new PropertyValueFactory<>("business_address"));
        tableSupplierDetails.setItems(supplierdatalist);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSupplier();

    }
}
