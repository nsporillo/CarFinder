package tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerTable {

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
            + "VALUES(%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%d\'\'%s\'\'%d\'\'%d\'\'%d\');", customerID,
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
