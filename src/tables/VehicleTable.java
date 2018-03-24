package tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VehicleTable {

    /**
     * Adds a single vehicle to the database
     *
     * @param conn
     * @param vin
     * @param modelID
     * @param optionID
     * @param saleID
     * @param year
     * @param price
     */
    public static void addVehicle(Connection conn, int vin, int modelID, int optionID,
                                  int saleID, int year, double price) {

        String query = String.format("INSERT INTO Vehicle "
                        + "VALUES(%d,\'%d\',\'%d\',\'%d\',\'%d\',\'%d\');", vin, modelID, optionID,
                saleID, year, price);

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

    /**
     * Prints out Vehicle
     *
     * @param conn connection
     */
    public static void printVehicleTable(Connection conn) {

        String query = "SELECT * FROM Vehicle;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                System.out.printf("Vehicle %d: %d %d %d %d %d\n",
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getInt(5),
                        result.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
