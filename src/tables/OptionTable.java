package tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Customer;
import models.Option;

public class OptionTable {

  /**
   * Reads a cvs file for data and adds them to the option table
   *
   * Does not create the table. It must already be created
   *
   * @param conn     database connection to work with
   * @param fileName fileName of CSV file containing option table data
   * @throws SQLException
   */
  public static void populateOptionTableFromCSV(Connection conn, String fileName) throws SQLException {
    ArrayList<Option> option = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      String line;
      while((line = br.readLine()) != null){
        String[] split = line.split(",");
        option.add(new Option(Integer.parseInt(split[0]), split[1], split[2], split[3]));
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

    String sql = createCustomerInsertSQL(option);

    Statement stmt = conn.createStatement();
    stmt.execute(sql);
  }

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

  public static String createCustomerInsertSQL(ArrayList<Option> option) {
    StringBuilder sb = new StringBuilder();

    sb.append("INSERT INTO Option (OptionID, Color, Engine, Transmission) VALUES");

    for(int i = 0; i < option.size(); i++){
      Option o = option.get(i);
      sb.append(String.format("(%d,\'%s\',\'%s\',\'%s\')",
          o.getId(), o.getColor(), o.getEngine(), o.getTransmission()));
      if( i != option.size()-1){
        sb.append(",");
      }
      else{
        sb.append(";");
      }
    }

    return sb.toString();
  }

  public static void printOptionTable(Connection conn) {

    String query = "SELECT * FROM Option;";
    try {
      Statement stmt = conn.createStatement();
      ResultSet result = stmt.executeQuery(query);

      while(result.next()){
        System.out.printf("Option %d: %s %s %s\n",
            result.getInt(1),
            result.getString(2),
            result.getString(3),
            result.getString(4));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
