package tables;

import models.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SaleTable {

    /**
     * Reads a cvs file for data and adds them to the Sale table
     * <p>
     * Does not create the table. It must already be created
     *
     * @param conn     database connection to work with
     * @param fileName fileName of CSV file containing model table data
     * @throws SQLException
     */
    public static void populateSaleTableFromCSV(Connection conn, String fileName) throws SQLException {
        ArrayList<Sale> sales = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                sales.add(new Sale(Integer.parseInt(split[0]),
                        Integer.parseInt(split[1]),
                        Integer.parseInt(split[2]),
                        new Timestamp(Long.parseLong(split[3])),
                        Integer.parseInt(split[4])));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String sql = createSaleInsertSQL(sales);

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
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
    public static void addSale(Connection conn, int saleID, int dealerID, int customerID, Date timestamp, int vin) {

        String query = String.format("INSERT INTO Sale "
                + "VALUES(%d,\'%d\',\'%d\',\'%tD\',\'%d\');", saleID, dealerID, customerID, timestamp, vin);

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


    public static String createSaleInsertSQL(ArrayList<Sale> sale) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO Sale (SaleID, DealerID, CustomerID, Timestamp, VIN) VALUES");

        for (int i = 0; i < sale.size(); i++) {
            Sale s = sale.get(i);
            sb.append(String.format("(%d,\'%d\',\'%d\',\'%tD\',\'%d\')",
                    s.getId(), s.getDealerID(), s.getCustomerID(), s.getTimestamp(), s.getVin()));
            if (i != sale.size() - 1) {
                sb.append(",");
            } else {
                sb.append(";");
            }
        }

        return sb.toString();
    }

    /**
     * Prints out SaleTable
     *
     * @param conn connection
     */
    public static void printSaleTable(Connection conn) {

        String query = "SELECT * FROM Sale;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                System.out.printf("Sale %d: %d %d %tD %d\n",
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getDate(4),
                        result.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
