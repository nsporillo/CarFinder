package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import models.Model;
import models.Option;
import models.Vehicle;
import tables.*;

public class DBConnector {

  // The connection to the database
  private Connection conn;
  private String databasePath = new File("database/SQLTables.sql").getAbsolutePath();

  public DBConnector() {
	  createConnection(databasePath, "admin", "password");
  }

  /**
   * Create a database connection with the given params
   *
   * @param location: path of where to place the database
   * @param user:     user name for the owner of the database
   * @param password: password of the database owner
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
   * When your database program exits you should close the connection
   */
  public void closeConnection() {
    try {
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Connection getConnection() {
    return this.conn;
  }

  public static void main(String[] args) {
    DBConnector main = new DBConnector();
    CustomerTable customerTable = new CustomerTable();
    OptionTable optionTable = new OptionTable();

    File file = new File("database/SQLTables.sql");
    String path = file.getAbsolutePath();
    String line = null;

    // Location of the database
    String location = path;
    String user = "admin";
    String password = "password";

    main.createConnection(location, user, password);

    try { // Reads and Executes SQL commands to create tables under /tables
      FileReader fileReader = new FileReader(path);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      while ((line = bufferedReader.readLine()) != null) {
        Statement stmt = main.getConnection().createStatement();
        stmt.execute(line);
      }

      bufferedReader.close();

    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }

    try {
      CustomerTable.populateCustomerTableFromCSV(main.getConnection(), "newCustomerData.csv"); // TODO
      List<Option> optionList = OptionTable.populateOptionTableFromCSV(main.getConnection(), "CarOptions.csv");
      ModelTable.populateModelTableFromCSV(main.getConnection(), "Vehicles.csv");
      DealerTable.populateDealerTableFromCSV(main.getConnection(), "DealershipData.csv");
      VehicleTable.populateVehicleTableFromCSV(optionList, main.getConnection(), "Vehicles.csv", "VehicleOptions.csv");
    } catch (SQLException e) {
      e.printStackTrace();
    }

    //CustomerTable.printCustomerTable(main.getConnection());
    //OptionTable.printOptionTable(main.getConnection());
    //ModelTable.printModelTable(main.getConnection());
    //DealerTable.printDealerTables(main.getConnection());
    //VehicleTable.printVehicleTable(main.getConnection());
  }
}
