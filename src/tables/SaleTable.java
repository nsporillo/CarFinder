package tables;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class SaleTable {

  /**
   * Adds a single sale to the database
   *
   * @param conn
   * @param saleID
   * @param dealerID
   * @param customerID
   * @param timestamp
   * @param vin
   */
  public static void addSale(Connection conn, int saleID, int dealerID, int customerID, Date timestamp, int vin) {

    String query = String.format("INSERT INTO Sale "
            + "VALUES(%d,\'%d\',\'%d\',\'%tD\',\'%d\');", saleID, dealerID, customerID, timestamp, vin);

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
