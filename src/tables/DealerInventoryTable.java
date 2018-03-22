package tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DealerInventoryTable {

  /**
   * Adds a single vehicle to the database
   *
   * @param conn
   * @param vin
   * @param dealerID
   */
  public static void addVehicle(Connection conn, int vin, int dealerID) {

    String query = String.format("INSERT INTO DealerInventory "
            + "VALUES(%d,\'%d\');", vin, dealerID);

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
