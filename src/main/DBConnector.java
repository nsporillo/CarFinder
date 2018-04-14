package main;

import models.Dealer;
import models.Option;
import models.Vehicle;
import tables.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * This class is run to create a database from given csv
 */
public class DBConnector {

    private static final File dbFile = new File("database/SQLTables.sql.mv.db");
    private static final File dbPath = new File("database/SQLTables.sql");

    // The connection to the database
    private Connection conn;

    /**
     * checks if there is a database already created if it is not created already it will populate a new database
     *
     * @param username Username used to log in
     * @param password Password to the database.
     *                 These params are usually admin and password for our basic implementation
     */
    DBConnector(String username, String password) {
        if (dbFile.exists()) {
            createConnection(dbPath.getAbsolutePath(), username, password);
            Team01Driver.log("Database exists: Skipping table creation and population");
        } else {
            Team01Driver.log("Existing database not found: Creating one and loading data from CSV.");
            createDatabase(dbPath.getAbsolutePath(), username, password);
            populateDatabase();
        }

    }

    /**
     * Executes the file with all the sql statements to create our tables
     *
     * @param path     Path to the file
     * @param username username to be used in database
     * @param password password to the database
     */
    private void createDatabase(String path, String username, String password) {
        createConnection(path, username, password);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            StringBuilder query = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("--")) {
                    continue; // skip sql comments
                }
                query.append(line);

                // found a query, execute it
                if (line.endsWith(";")) {
                    Statement stmt = conn.createStatement();
                    Team01Driver.log(query.toString().substring(0, query.indexOf("(")).toLowerCase());
                    stmt.execute(query.toString());
                    query = new StringBuilder();
                }
            }

            bufferedReader.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Populates all the tables with information from the csv or previously computed lists.
     * After this every table is populated into the database
     */
    private void populateDatabase() {
        try {
            CustomerTable.populateCustomerTableFromCSV(conn, "newCustomerData.csv");
            List<Option> optionList = OptionTable.populateOptionTableFromCSV(conn, "CarOptions.csv");
            ModelTable.populateModelTableFromCSV(conn, "Vehicles.csv");
            List<Dealer> dealers = DealerTable.populateDealerTableFromCSV(conn, "DealershipData.csv");
            List<Vehicle> vehicles = VehicleTable.populateVehicleTableFromCSV(optionList, conn, "Vehicles.csv", "VehicleOptions.csv");
            DealerInventoryTable.addAllVehiclesToAllDealers(conn, vehicles, dealers);
            SaleTable.populateSaleTable(conn, "smallVehicle.csv", "newCustomerData.csv", "CarOptions.csv");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a database connection with the given params
     *
     * @param location  path of where to place the database
     * @param user:     username
     * @param password: password
     */
    private void createConnection(String location, String user, String password) {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:file:" + location, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets connection to the database
     *
     * @return Connection to the database
     */
    public Connection getConnection() {
        return this.conn;
    }

    /**
     * Starts a new database with admin and password
     *
     * @param args empty
     */
    public static void main(String[] args) {
        DBConnector main = new DBConnector("admin", "password");
    }
}
