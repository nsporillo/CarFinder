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
import tables.CustomerTable;

public class DBConnector {

  // The connection to the database
  private Connection conn;

  /**
   * Create a database connection with the given params
   *
   * @param location: path of where to place the database
   * @param user: user name for the owner of the database
   * @param password: password of the database owner
   */
  private void createConnection(String location, String user, String password) {
    try {
      // This needs to be on the front of your location
      String url = "jdbc:h2:file:" + location;

      // This tells it to use the h2 driver
      Class.forName("org.h2.Driver");

      // creates the connection
      conn = DriverManager.getConnection(url, user, password);
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

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      customerTable.populateCustomerTableFromCSV(main.getConnection(), "tempCustomerData.csv");
    } catch (SQLException e) {
      e.printStackTrace();
    }

    customerTable.printCustomerTable(main.getConnection());

  }
}
