package models;

/**
 * Class containing DealerInventory: VIN, DealerID
 */
public class DealerInventory {

    private int vin;
    private int dealerID;

    public DealerInventory(int vin, int dealerID) {
        this.vin = vin;
        this.dealerID = dealerID;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public int getDealerID() {
        return dealerID;
    }

    public void setDealerID(int dealerID) {
        this.dealerID = dealerID;
    }
}
