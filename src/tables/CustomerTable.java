package tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.ResultSet;
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
   * @param conn     database connection to work with
   * @param fileName fileName of CSV file containing customer table data
   * @throws SQLException
   */
  public static void populateCustomerTableFromCSV(Connection conn, String fileName) throws SQLException {
    ArrayList<Customer> customer = new ArrayList<Customer>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      String line;
      while((line = br.readLine()) != null){
        String[] split = line.split(",");
        customer.add(new Customer(Integer.parseInt(split[0]), split[1], split[2], split[3], split[4],
            Integer.parseInt(split[5]), split[6], Integer.parseInt(split[7]), Integer.parseInt(split[8])));
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    String sql = createCustomerInsertSQL(customer);

    Statement stmt = conn.createStatement();
      stmt.execute(sql);
  }

  /**
   * Adds a single customer to the database
   *
   * @param conn        connection
   * @param customerID  customer primary key
   * @param firstName   first name
   * @param lastName    last name
   * @param street      street address
   * @param city        city of address
   * @param state       state of address
   * @param zip         zip code of address
   * @param phone       phone number
   * @param gender      gender
   * @param income      annual income
   */
  public static void addCustomer(Connection conn, int customerID, String firstName, String lastName,
      String street, String city, String state, int zip, int phone, int gender, int income) {

    String query = String.format("INSERT INTO Customer "
            + "VALUES(%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%d\',\'%s\',\'%d\',\'%d\');", customerID,
        firstName, lastName, street, city, state, zip, phone, gender, income);

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

  public static String createCustomerInsertSQL(ArrayList<Customer> customer) {
    StringBuilder sb = new StringBuilder();

    sb.append("INSERT INTO Customer (CustomerID, FirstName, LastName, Street, ZIP, State, Phone, Gender, Income) VALUES");

    for(int i = 0; i < customer.size(); i++){
      Customer c = customer.get(i);
      sb.append(String.format("(%d,\'%s\',\'%s\',\'%s\',\'%d\',\'%s\',\'%s\',\'%d\',\'%d\')",
          c.getID(), c.getFirstName(), c.getLastName(), c.getStreet(), c.getZip(),
          c.getState(), c.getPhone(), c.getGender(), c.getAnnualIncome()));
      if( i != customer.size()-1){
        sb.append(",");
      }
      else{
        sb.append(";");
      }
    }

    return sb.toString();
  }

  public static void printCustomerTable(Connection conn) {

    String query = "SELECT * FROM Customer;";
    try {
      Statement stmt = conn.createStatement();
      ResultSet result = stmt.executeQuery(query);

      while(result.next()){
        System.out.printf("Customer %d: %s %s %s %s %d %s %d %d\n",
            result.getInt(1),
            result.getString(2),
            result.getString(3),
            result.getString(4),
            result.getString(5),
            result.getInt(6),
            result.getString(7),
            result.getInt(8),
            result.getInt(9));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
