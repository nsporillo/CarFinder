package models;

import java.sql.Timestamp;

public class Sale {

    private int id;
    private int dealerID;
    private int customerID;
    private Timestamp timestamp;
    private int vin;

    public Sale(int saleID, int dealerID, int customerID, Timestamp timestamp, int vin) {
        this.id = saleID;
        this.dealerID = dealerID;
        this.customerID = customerID;
        this.timestamp = timestamp;
        this.vin = vin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDealerID() { return dealerID; }

    public void setDealerID(int dealerID) { this.dealerID = dealerID; }

    public int getCustomerID() { return customerID; }

    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public Timestamp getTimestamp() { return timestamp; }

    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    public int getVin() { return vin; }

    public void setVin(int vin) { this.vin = vin; }

}
