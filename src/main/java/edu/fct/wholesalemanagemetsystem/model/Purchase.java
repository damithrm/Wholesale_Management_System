package edu.fct.wholesalemanagemetsystem.model;

public class Purchase {
    String purchase_id;
    String supplier_id;
    String date;
    String total_cost;

    public Purchase() {
    }

    public Purchase(String purchase_id, String supplier_id, String date, String total_cost) {
        this.purchase_id = purchase_id;
        this.supplier_id = supplier_id;
        this.date = date;
        this.total_cost = total_cost;
    }

    public String getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(String purchase_id) {
        this.purchase_id = purchase_id;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }
}
