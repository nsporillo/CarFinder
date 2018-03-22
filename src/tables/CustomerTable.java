package tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Customer;

public class CustomerTable {

  /**
   * Reads a cvs file for data and adds them to the customer table
   *
   * Does not create the table. It must already be created
   *
   * @param conn: database connection to work with
   * @param fileName
   * @throws SQLException
   */
  public static void populateCustomerTableFromCSV(Connection conn, String fileName) throws SQLException {
    ArrayList<Customer> customers = new ArrayList<Customer>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      String line;
      while((line = br.readLine()) != null){
        String[] split = line.split(",");
        customers.add(new Customer(split[0], split[1], split[2], split[3], split[4],
            split[5], Integer.parseInt(split[6])));
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Adds a single customer to the database
   *
   * @param conn
   * @param customerID
   * @param firstName
   * @param lastName
   * @param street
   * @param city
   * @param zip
   * @param state
   * @param phone
   * @param gender
   * @param income
   */
  public static void addCustomer(Connection conn, int customerID, String firstName, String lastName,
      String street, String city, int zip, String state, int phone, int gender, int income) {

    String query = String.format("INSERT INTO Customer "
            + "VALUES(%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%d\',\'%s\',\'%d\',\'%d\',\'%d\');", customerID,
        firstName, lastName, street, city, zip, state, phone, gender, income);

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

}
