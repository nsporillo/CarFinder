package models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void populateSaleTable(Connection conn, String smallVehicleCsv, String customerCsv, String carOptionsCsv){
        List<Sale> sales = new ArrayList<>();
        Random random = new Random();
        int vin = random.nextInt(10000000);
        try {
            BufferedReader vehicle = new BufferedReader(new FileReader(smallVehicleCsv));
            BufferedReader customer = new BufferedReader(new FileReader(customerCsv));
            String vehicleLine;
            String customerLine;
            String optionLine;
            int saleID;
            while((vehicleLine = vehicle.readLine()) != null){
                String[] VSplit = vehicleLine.split(",");
                BufferedReader option = new BufferedReader(new FileReader(carOptionsCsv));
                while((optionLine = option.readLine())!= null){
                    String[] OSplit = optionLine.split(",");
                    customerLine = customer.readLine();
                    String[] CSplit = customerLine.split(",");
                    // for every vehicle in the short csv add to table and make sale to customer
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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

    public String getVin() { return vin; }

    public void setVin(String vin) { this.vin = vin; }

}
