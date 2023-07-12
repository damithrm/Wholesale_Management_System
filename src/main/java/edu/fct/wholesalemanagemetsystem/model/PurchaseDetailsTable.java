package edu.fct.wholesalemanagemetsystem.model;

public class PurchaseDetailsTable {
    String col1Date;
    String col2PurchaseID;
    String col3TotalCost;

    public PurchaseDetailsTable() {
    }

    public PurchaseDetailsTable(String col1Date, String col2PurchaseID, String col3TotalCost) {
        this.col1Date = col1Date;
        this.col2PurchaseID = col2PurchaseID;
        this.col3TotalCost = col3TotalCost;
    }

    public String getCol1Date() {
        return col1Date;
    }

    public void setCol1Date(String col1Date) {
        this.col1Date = col1Date;
    }

    public String getCol2PurchaseID() {
        return col2PurchaseID;
    }

    public void setCol2PurchaseID(String col2PurchaseID) {
        this.col2PurchaseID = col2PurchaseID;
    }

    public String getCol3TotalCost() {
        return col3TotalCost;
    }

    public void setCol3TotalCost(String col3TotalCost) {
        this.col3TotalCost = col3TotalCost;
    }
}
