package edu.fct.wholesalemanagemetsystem.model;

public class PurchaseDetail {

    String purchase_id;
    String item_id;
    String quantity;
    String unit_price;

    public PurchaseDetail() {
    }

    public PurchaseDetail(String purchase_id, String item_id, String quantity, String unit_price) {
        this.purchase_id = purchase_id;
        this.item_id = item_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public String getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(String purchase_id) {
        this.purchase_id = purchase_id;
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
