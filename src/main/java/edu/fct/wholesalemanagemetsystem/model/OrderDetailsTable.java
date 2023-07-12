package edu.fct.wholesalemanagemetsystem.model;

public class OrderDetailsTable {
    String col1Date;
    String col2OrderID;
    String col3TotalPrice;

    public OrderDetailsTable() {
    }

    public OrderDetailsTable(String col1Date, String col2OrderID, String col3TotalPrice) {
        this.col1Date = col1Date;
        this.col2OrderID = col2OrderID;
        this.col3TotalPrice = col3TotalPrice;
    }

    public String getCol1Date() {
        return col1Date;
    }

    public void setCol1Date(String col1Date) {
        this.col1Date = col1Date;
    }

    public String getCol2OrderID() {
        return col2OrderID;
    }

    public void setCol2OrderID(String col2OrderID) {
        this.col2OrderID = col2OrderID;
    }

    public String getCol3TotalPrice() {
        return col3TotalPrice;
    }

    public void setCol3TotalPrice(String col3TotalPrice) {
        this.col3TotalPrice = col3TotalPrice;
    }
}
