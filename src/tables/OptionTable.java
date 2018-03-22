package tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OptionTable {

  /**
   * Adds a single option to the database
   *
   * @param conn
   * @param optionID
   * @param color
   * @param engine
   * @param transmission
   */
  public static void addOption(Connection conn, int optionID, String color, String engine, String transmission) {

    String query = String.format("INSERT INTO Option "
            + "VALUES(%d,\'%s\',\'%s\',\'%s\');", optionID, color, engine, transmission);

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
