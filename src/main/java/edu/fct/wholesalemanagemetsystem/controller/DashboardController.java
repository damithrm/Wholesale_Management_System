package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label totalCustomers;

    @FXML
    private Label totalItems;

    @FXML
    private Label totalSuppliers;

    @FXML
    private Label totalOrders;

    @FXML
    private Label todaySales;

    @FXML
    private Label todayPurchase;

    @FXML
    private PieChart pieChartLastMonthReport;

    @FXML
    private Label purchaseValue;

    @FXML
    private Label salesValue;

    @FXML
    private BarChart<String, Number> barChartDailyOrders;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;



    public void statLoading()  {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String todayDate = dtf.format(now);

        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(customer_id) from customer");
            rs.next();
            int count = rs.getInt(1);
            totalCustomers.setText(String.valueOf(count));
        }
        catch (Exception e) {

        }
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(item_name) from item");
            rs.next();
            int count = rs.getInt(1);
            totalItems.setText(String.valueOf(count));
        }
        catch (Exception e) {

        }
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(supplier_id) from supplier");
            rs.next();
            int count = rs.getInt(1);
            totalSuppliers.setText(String.valueOf(count));
        }
        catch (Exception e) {

        }
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(order_id) from orders");
            rs.next();
            int count = rs.getInt(1);
            totalOrders.setText(String.valueOf(count));
        }
        catch (Exception e) {

        }
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select sum(total_price) from orders where date='"+todayDate+"'");
            rs.next();
            double total = rs.getDouble(1);
            todaySales.setText(String.valueOf(total));
        }
        catch (Exception e) {

        }

        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select sum(total_cost) from purchase where date='"+todayDate+"'");
            rs.next();
            double total = rs.getDouble(1);
            todayPurchase.setText(String.valueOf(total));
        }
        catch (Exception e) {

        }
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery("select sum(total_cost) from purchase where date>= DATE_FORMAT( CURRENT_DATE - INTERVAL 1 MONTH, '%Y/%m/01' ) and date < DATE_FORMAT( CURRENT_DATE, '%Y/%m/01' )");
            rs1.next();
            double totalPurchaseValues = rs1.getDouble(1);

            ResultSet rs2 = st.executeQuery("select sum(total_price) from orders where date>= DATE_FORMAT( CURRENT_DATE - INTERVAL 1 MONTH, '%Y/%m/01' ) and date < DATE_FORMAT( CURRENT_DATE, '%Y/%m/01' )");
            rs2.next();
            double totalSalesValue = rs2.getDouble(1);

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Purchases", totalPurchaseValues),
                    new PieChart.Data("Sales", totalSalesValue));

            String s1 = String.valueOf(totalPurchaseValues);
            String s2 = String.valueOf(totalSalesValue);

            pieChartLastMonthReport.setData(pieChartData);
            purchaseValue.setText(s1);
            salesValue.setText(s2);

        }
        catch (Exception e) {

        }

        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select date, count(order_id) from orders group by date order by date desc limit 7");

            XYChart.Series<String, Number> seriesDays = new XYChart.Series<String, Number>();
            seriesDays.setName("Days");
            while(rs.next()) {
                seriesDays.getData().add(new XYChart.Data<String, Number>(rs.getString(1), rs.getInt(2)));
            }


            ObservableList<XYChart.Series<String, Number>> data = FXCollections.<XYChart.Series<String, Number>>observableArrayList();
            data.add(seriesDays);
            barChartDailyOrders.setData(data);

        }
        catch (Exception e) {

        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
