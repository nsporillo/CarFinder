package tables;

import main.Team01Driver;
import models.Sale;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SaleTable {

    /**
     * Reads a cvs file for data and adds them to the Sale table
     * <p>
     * Does not create the table. It must already be created
     *
     * @param conn     database connection to work with
     * @param smallVehicleCsv of CSV file containing model table data
     * @throws SQLException
     */

    public static void populateSaleTable(Connection conn, String smallVehicleCsv, String customerCsv, String carOptionsCsv){
        List<Sale> sales = new ArrayList<>();
        Random random = new Random();
        String vin;
        try {
            ArrayList<Sale> s = new ArrayList<>();
            BufferedReader vehicle = new BufferedReader(new FileReader(smallVehicleCsv));
            BufferedReader customer = new BufferedReader(new FileReader(customerCsv));
            String vehicleLine;
            String customerLine;
            String optionLine;
            int saleID = 0;
            boolean dealerInc = true;
            int dealerID = 0;
            while((vehicleLine = vehicle.readLine()) != null){
                String[] VSplit = vehicleLine.split(",");
                BufferedReader option = new BufferedReader(new FileReader(carOptionsCsv));
                while((optionLine = option.readLine())!= null){
                    String[] OSplit = optionLine.split(",");
                    customerLine = customer.readLine();
                    if(customerLine == null){
                        break;
                    }
                    String[] CSplit = customerLine.split(",");
                    // for every vehicle in the short csv add to table and make sale to customer
                    int modelID = ModelTable.getModelId(conn, VSplit[1], VSplit[2]);
                    vin = Integer.toString(random.nextInt(10000000));
                    VehicleTable.addVehicle(conn,vin,modelID,Integer.parseInt(OSplit[0]),Integer.parseInt(VSplit[0]), Integer.parseInt(VSplit[3]));
                    long time = 123456L;
                    Timestamp date = new Timestamp(time);
                    Sale saleRecord = new Sale(saleID,dealerID,Integer.parseInt(CSplit[0]),date,"" + vin);
                    if(dealerInc && dealerID < 249){
                        dealerID ++;
                        dealerInc = false;
                    }
                    else
                        dealerInc = true;
                    s.add(saleRecord);
                    saleID++;
                }
                option.close();
            }
            vehicle.close();
            customer.close();
            String sql = createSaleInsertSQL(s);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Adds a single sale to the database
     *
     * @param conn
     * @param saleID
     * @param dealerID
     * @param customerID
     * @param timestamp
     * @param vin
     */
    public static void addSale(Connection conn, int saleID, int dealerID, int customerID, Date timestamp, String vin) {

        String query = String.format("INSERT INTO Sale "
                + "VALUES('%d\',\'%d\',\'%tD\',\'%s\');", dealerID, customerID, timestamp, vin);

        try {
            /**
             * create and execute the query
             */
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static String createSaleInsertSQL(List<Sale> sales){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO Sale (SaleID, DealerID, CustomerID, Date, VIN) VALUES ");
        for (Sale i : sales) {
            sb.append(String.format("(%d,\'%d\',\'%d\',CURRENT_TIMESTAMP(),\'%s\')",
                    i.getId(), i.getDealerID(), i.getCustomerID(),
                    i.getVin()));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");
        return sb.toString();

    }

    /**
     * Prints out SaleTable
     *
     * @param conn connection
     */
    public static void printSaleTable(Connection conn) {
        Team01Driver.debug("Listing all Sale records");
        String query = "SELECT * FROM Sale;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                Team01Driver.debug(String.format("Sale %d: %d %d %tD %s",
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getDate(4),
                        result.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
