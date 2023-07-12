package edu.fct.wholesalemanagemetsystem.controller;

import com.jfoenix.controls.JFXButton;
import edu.fct.wholesalemanagemetsystem.Main;
import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import edu.fct.wholesalemanagemetsystem.model.Customer;
import edu.fct.wholesalemanagemetsystem.model.Item;
import edu.fct.wholesalemanagemetsystem.model.OrderDetail;
import edu.fct.wholesalemanagemetsystem.model.PlaceOrderTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {
    @FXML
    private AnchorPane placeOrderPane;

    @FXML
    private ComboBox <String> cmdCustomerID;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtDate;

    @FXML
    private TableView<PlaceOrderTable> tableOrderDesc;

    @FXML
    private TableColumn<PlaceOrderTable, String> col1ItemID;

    @FXML
    private TableColumn<PlaceOrderTable, String> col2ItemDescription;

    @FXML
    private TableColumn<PlaceOrderTable, Integer> col3ItemQty;

    @FXML
    private TableColumn<PlaceOrderTable, Double> col4ItemUnitPrice;

    @FXML
    private TableColumn<PlaceOrderTable, Double> col5Price;

    @FXML
    private ComboBox<String > cmdItemID;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtBrand;

    @FXML
    private JFXButton btnPrint;

    @FXML
    private JFXButton btnRemove;

    @FXML
    private JFXButton btnClear;

    ObservableList customerIdList = FXCollections.observableArrayList();
    ObservableList itemIdList = FXCollections.observableArrayList();
    ObservableList<PlaceOrderTable> placeOrderTableObservableList = FXCollections.observableArrayList();
    ObservableList<OrderDetail> orderDetails = FXCollections.observableArrayList();

    @FXML
    void txtAddItemToTableOnAction(ActionEvent event) {
        String itemCode = cmdItemID.getValue();
        String descriton = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double price = qty * unitPrice;
        PlaceOrderTable placeOrderTable = new PlaceOrderTable(itemCode,descriton,qty,unitPrice,price);

        placeOrderTableObservableList.add(placeOrderTable);
        col1ItemID.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        col2ItemDescription.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        col3ItemQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col4ItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        col5Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableOrderDesc.setItems(placeOrderTableObservableList);

        double total = 0 ;
        for (PlaceOrderTable pot : tableOrderDesc.getItems()) {
            total = total + pot.getPrice();
        }
        txtTotal.setText(String.valueOf(total));

        try {
            updateQtyOnHand();
            addOrderDetail();

            Notifications.create().darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(1)).text("Added").showInformation();
        } catch (SQLException|ClassNotFoundException e) {
            Notifications.create().darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(1)).text("Add Failed").showError();
        }

    }

    @FXML
    void btnClearAllFieldsOnAction(ActionEvent event) {
        try {
            clearAll();
            Notifications.create().darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(2)).text("Cleared All").showInformation();
        } catch (IOException e) {
            Notifications.create().darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(2)).text("Error Occurred").showError();
        }

    }
    public void clearAll() throws IOException {

        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("placeOrder.fxml"));
        placeOrderPane.getChildren().add(newLoadedPane);
//            txtOrderID.clear();
//            cmdCustomerID.setValue(null);
//            txtCustomerName.clear();
//            txtDescription.clear();
//            txtQty.clear();
//            txtQtyOnHand.clear();
//            txtTotal.clear();
//            txtUnitPrice.clear();
//            cmdItemID.setValue(null);
//            tableOrderDesc.getItems().clear();

    }

    @FXML
    void btnPrintAndSaveOnAction(ActionEvent event) {
        String order_id=txtOrderID.getText();
        String total=(String) txtTotal.getText();

            try {
                Connection con = DBConnection.getInstance().getConnection();
                Statement st = con.createStatement();
                st.executeUpdate("update orders set total_price='" + total + "' where order_id='" + order_id + "'");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Order Successfully Added!");
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("");
                alert.setContentText("Order Failed!");
                alert.showAndWait();
            }

        try {
            clearAll();
            Notifications.create().darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(2)).text("Cleared All").showInformation();
        } catch (IOException e) {
            Notifications.create().darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(2)).text("Error Occurred").showError();
        }
    }

    @FXML
    void btnRemoveFieldOnAction(ActionEvent event) {
        try {
            updateCascadeQtyOnHand();
            deleteCascadeOrderDetail();
            tableOrderDesc.getItems().removeAll(tableOrderDesc.getSelectionModel().getSelectedItem());

            Notifications.create().darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(1)).text("Removed").showInformation();
        } catch (SQLException|ClassNotFoundException e) {
            Notifications.create().darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(1)).text("Remove Failed").showError();
        }
    }

    @FXML
    void tblShowOrderDescOnAction(ActionEvent event) {

    }
    @FXML
    void cmbCustomerIDOnAction(ActionEvent event) {
        try {
        String id = cmdCustomerID.getValue();
        Customer customer  = SearchCustomerController.searchCustomer(id);
        txtCustomerName.setText(customer.getCustomer_name());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbItemIDOnAction(ActionEvent event) {
        try {
            String id = cmdItemID.getValue();
            Item item  = SearchItemController.searchItem(id);
            txtDescription.setText(item.getItem_name());
            txtBrand.setText(item.getBrand());
            txtUnitPrice.setText("" + item.getUnit_prize());
            txtQtyOnHand.setText(item.getAvailable_quantity() + "");
            txtUnitPrice.requestFocus();
            txtUnitPrice.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtItemUnitPriceOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    private void setOrderDate() {
        LocalDate date = LocalDate.now();
        String sDate = date.toString();
        txtDate.setText(sDate);
    }

    public void loadCustomerIds() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("select customer_id from customer");
            while (rst.next()) {
                customerIdList.add(rst.getString("customer_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmdCustomerID.setItems(customerIdList);
    }

    public void loadItemIds() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("select item_id from item");
            while (rst.next()) {
                itemIdList.add(rst.getString("item_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmdItemID.setItems(itemIdList);
    }

    public void updateQtyOnHand() throws SQLException, ClassNotFoundException {
        String item_id = cmdItemID.getValue();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        int qty = Integer.parseInt(txtQty.getText());
        qtyOnHand = qtyOnHand - qty;

            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("update item set available_quantity='" + qtyOnHand + "' where item_id='" + item_id + "'");


    }

    public void addOrderDetail() throws SQLException, ClassNotFoundException {
        String order_id=txtOrderID.getText();
        String item_id=cmdItemID.getValue();
        String quantity= txtQty.getText();
        String unit_price=txtUnitPrice.getText();
        int qty = Integer.parseInt(quantity);
        float unitPrice= Float.parseFloat(unit_price);


        String customer_id=cmdCustomerID.getValue();
        String date=(String) txtDate.getText();
        String total=(String) txtTotal.getText();

        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("insert into orders values ('" + order_id + "','" + customer_id + "','" + date + "','" + total + "')");

        } catch (Exception e) {

        }

        Connection con = DBConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        st.executeUpdate("insert into order_detail values ('" + order_id + "','" + item_id + "'," + qty + "," + unitPrice + ")");



    }

    public void updateCascadeQtyOnHand() throws SQLException, ClassNotFoundException {
        String item_id;
        int qty;
        if (tableOrderDesc.getSelectionModel().getSelectedItem() != null) {
            PlaceOrderTable selectedField = tableOrderDesc.getSelectionModel().getSelectedItem();
            item_id = selectedField.getItem_id();
            qty = selectedField.getQuantity();

                Connection con = DBConnection.getInstance().getConnection();
                Statement st = con.createStatement();
                st.executeUpdate("update item set available_quantity=available_quantity+" + qty + " where item_id='" + item_id + "'");

        }
    }

    public void deleteCascadeOrderDetail() throws SQLException, ClassNotFoundException {
        String order_id=txtOrderID.getText();
        String item_id;

        if (tableOrderDesc.getSelectionModel().getSelectedItem() != null) {
            PlaceOrderTable selectedField = tableOrderDesc.getSelectionModel().getSelectedItem();
            item_id = selectedField.getItem_id();

                Connection con = DBConnection.getInstance().getConnection();
                Statement st = con.createStatement();
                st.executeUpdate("delete from order_detail where order_id='" + order_id + "' and item_id='" + item_id + "'");

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrderDate();
        loadCustomerIds();
        loadItemIds();
    }
}

