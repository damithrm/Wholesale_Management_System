package edu.fct.wholesalemanagemetsystem.model;

public class PlaceOrderTable {
    String item_id;
    String item_name;
    int quantity;
    double unit_price;
    double price;

    public PlaceOrderTable() {
    }

    public PlaceOrderTable(String item_id, String item_name, int quantity, double unit_price, double price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.price = price;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
