package models;

/**
 * Class containing DealerInventory: VIN, DealerID
 */
public class DealerInventory {

    private String vin;
    private int dealerID;

    public DealerInventory(String vin, int dealerID) {
        this.vin = vin;
        this.dealerID = dealerID;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getDealerID() {
        return dealerID;
    }

    public void setDealerID(int dealerID) {
        this.dealerID = dealerID;
    }
}
