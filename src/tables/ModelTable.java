package tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelTable {

  /**
   * Adds a single model to the database
   *
   * @param conn
   * @param modelID
   * @param brandName
   * @param bodyStyle
   */
  public static void addModel(Connection conn, int modelID, String brandName, String bodyStyle) {

    String query = String.format("INSERT INTO Model "
            + "VALUES(%d,\'%s\',\'%s\');", modelID, brandName, bodyStyle);

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
