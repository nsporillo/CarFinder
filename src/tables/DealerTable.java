package tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DealerTable {

  /**
   * Adds a single dealer to the database
   *
   * @param conn
   * @param dealerID
   * @param name
   * @param street
   * @param city
   * @param zip
   * @param state
   * @param phone
   */
  public static void addDealer(Connection conn, int dealerID, String name,
      String street, String city, int zip, String state, int phone) {

    String query = String.format("INSERT INTO Dealer "
            + "VALUES(%d,\'%s\',\'%s\',\'%s\',\'%d\',\'%s\',\'%d\');", dealerID,
        name, state, city, zip, state, phone);

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
