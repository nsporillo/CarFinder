package tables;

import models.Model;
import models.Option;
import models.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VehicleTable {

    /**
     * Reads a cvs file for data and adds them to the model table
     *
     * Does not create the table. It must already be created
     *
     * @param conn     database connection to work with
     * @param fileName fileName of CSV file containing model table data
     * @throws SQLException
     */
    public static void populateVehicleTableFromCSV(Connection conn, String fileName) throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null){
                String[] split = line.split(",");
                vehicles.add(new Vehicle(Integer.parseInt(split[0]),
                        new Model(Integer.parseInt(split[1]), split[2],split[3]),
                        new Option(Integer.parseInt(split[4]), split[5], split[6], split[7]),
                        Integer.parseInt(split[8]),
                        Integer.parseInt(split[9])));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String sql = createVehicleInsertSQL(vehicles);

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    /**
     * Adds a single vehicle to the database
     *
     * @param conn
     * @param vin
     * @param modelID
     * @param optionID
     * @param year
     * @param price
     */
    public static void addVehicle(Connection conn, int vin, int modelID, int optionID,
                                  int year, double price) {

        String query = String.format("INSERT INTO Vehicle "
                        + "VALUES(%d,\'%d\',\'%d\',\'%d\',\'%d\');", vin, modelID, optionID,
                year, price);
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

    public static String createVehicleInsertSQL(ArrayList<Vehicle> vehicles) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO Vehicle (VIN, modelID, optionID, year, price) VALUES");

        for(int i = 0; i < vehicles.size(); i++){
            Vehicle vehicle = vehicles.get(i);
            sb.append(String.format("(%d,\'%d\',\'%d\',\'%d\',\'%d\')",
                    vehicle.getVin(), vehicle.getModelID(), vehicle.getOptionID(), vehicle.getYear(), vehicle.getPrice()));
            if( i != vehicles.size()-1){
                sb.append(",");
            }
            else{
                sb.append(";");
            }
        }

        return sb.toString();
    }

}
