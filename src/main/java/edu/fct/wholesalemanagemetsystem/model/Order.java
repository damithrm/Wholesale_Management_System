package edu.fct.wholesalemanagemetsystem.model;

public class Order {
    String order_id;
    String customer_id;
    String date;
    String total_price;

    public Order() {
    }

    public Order(String order_id, String customer_id, String date, String total_price) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.date = date;
        this.total_price = total_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
