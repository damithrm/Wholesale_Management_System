package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import edu.fct.wholesalemanagemetsystem.model.OrderDetailsTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class OrderDetailsController implements Initializable {

    @FXML
    private TableView<OrderDetailsTable> tableOrderDetails;

    @FXML
    private TableColumn<OrderDetailsTable, String> col1Date;

    @FXML
    private TableColumn<OrderDetailsTable, String> col2OrderID;

    @FXML
    private TableColumn<OrderDetailsTable, String> col3TotalPrice;

    @FXML
    private Label tfTotalSales;

    ObservableList<OrderDetailsTable> orderdetaillist = FXCollections.observableArrayList();

    public void loadOrderDetails(){
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select order_id,date,total_price from orders order by(date) desc");
            while (rs.next()){
                orderdetaillist.add(new OrderDetailsTable(rs.getString("date"),rs.getString("order_id"),rs.getString("total_price")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        col1Date.setCellValueFactory(new PropertyValueFactory<>("col1Date"));
        col2OrderID.setCellValueFactory(new PropertyValueFactory<>("col2OrderID"));
        col3TotalPrice.setCellValueFactory(new PropertyValueFactory<>("col3TotalPrice"));
        tableOrderDetails.setItems(orderdetaillist);

        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select sum(total_price) from orders where date>= DATE_FORMAT( CURRENT_DATE, '%Y/%m/01' ) and date < DATE_FORMAT( CURRENT_DATE + INTERVAL 1 MONTH, '%Y/%m/01' )");
            rs.next();
            double total = rs.getDouble(1);
            tfTotalSales.setText(String.valueOf(total));
        }
        catch(Exception e) {

        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOrderDetails();
    }


}
