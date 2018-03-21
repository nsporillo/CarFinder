package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
      String url = "jdbc:h2:" + location;

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

    // Location of the database
    String location = "~/database"; // TODO maybe change location/user/pw to not be hardcoded
    String user = "admin";
    String password = "password";

    main.createConnection(location, user, password);

    /**
    try {

    } catch (SQLException e) {
      e.printStackTrace();
    }
     */

  }
}
