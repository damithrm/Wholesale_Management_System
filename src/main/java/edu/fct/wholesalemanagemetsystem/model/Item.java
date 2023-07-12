package edu.fct.wholesalemanagemetsystem.model;

public class Item {
    String item_id;
    String item_name;
    String brand;
    String available_quantity;
    String unit_prize;

    public Item() {
    }

    public Item(String item_id, String item_name, String brand, String available_quantity, String unit_prize) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.brand = brand;
        this.available_quantity = available_quantity;
        this.unit_prize = unit_prize;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(String available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getUnit_prize() {
        return unit_prize;
    }

    public void setUnit_prize(String unit_prize) {
        this.unit_prize = unit_prize;
    }
}
