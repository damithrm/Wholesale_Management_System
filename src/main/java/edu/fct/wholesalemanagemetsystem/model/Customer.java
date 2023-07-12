package edu.fct.wholesalemanagemetsystem.model;

public class Customer {
    String customer_id;
    String customer_name;
    String telephone_no;
    String customer_address;

    public Customer() {
    }

    public Customer(String customer_id, String customer_name, String telephone_no, String customer_address) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.telephone_no = telephone_no;
        this.customer_address = customer_address;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getTelephone_no() {
        return telephone_no;
    }

    public void setTelephone_no(String telephone_no) {
        this.telephone_no = telephone_no;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }
}
