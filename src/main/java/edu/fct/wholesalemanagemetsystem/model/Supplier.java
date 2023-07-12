package edu.fct.wholesalemanagemetsystem.model;

public class Supplier {
    String supplier_id;
    String supplier_name;
    String brand;
    String telephone_no;
    String email;
    String business_address;

    public Supplier() {
    }

    public Supplier(String supplier_id, String supplier_name, String brand, String telephone_no, String email, String business_address) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.brand = brand;
        this.telephone_no = telephone_no;
        this.email = email;
        this.business_address = business_address;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTelephone_no() {
        return telephone_no;
    }

    public void setTelephone_no(String telephone_no) {
        this.telephone_no = telephone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusiness_address() {
        return business_address;
    }

    public void setBusiness_address(String business_address) {
        this.business_address = business_address;
    }
}
