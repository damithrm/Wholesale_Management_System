package edu.fct.wholesalemanagemetsystem.model;

public class OrderDetail {

    String order_id;
    String item_id;
    String quantity;
    String unit_price;

    public OrderDetail() {
    }

    public OrderDetail(String order_id, String item_id, String quantity, String unit_price) {
        this.order_id = order_id;
        this.item_id = item_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }
}
