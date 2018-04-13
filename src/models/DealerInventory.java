package models;

/**
 * Class containing DealerInventory: VIN, DealerID
 */
public class DealerInventory {

    private String vin;
    private int dealerID;

    /**
     * Constructor for making a dealer Inventory object which holds a vin for a car
     * and the dealer that owns that car
     * @param vin       VIN of the car
     * @param dealerID  ID of dealer who owns car
     */
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
