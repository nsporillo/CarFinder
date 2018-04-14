package models;

import java.sql.Timestamp;

/**
 * Class containing Sale details: SaleID, DealerID, CustomerID, Date, and VIN
 */
public class Sale {

    private int id;
    private int dealerID;
    private int customerID;
    private Timestamp timestamp;
    private String vin;

    public Sale(int saleID, int dealerID, int customerID, Timestamp timestamp, String vin) {
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

    public int getDealerID() {
        return dealerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getVin() {
        return vin;
    }

}
