package tables;

import models.Dealer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class DealerTable {

  public static void populateDealerTableFromCSV(Connection conn, String filename) throws SQLException{
    ArrayList<Dealer> dealers = new ArrayList<>();
    try{
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String line;
      int dealerID = 0;
      while((line = br.readLine()) != null){
        String[] split = line.split(",");
        dealers.add(new Dealer(nameGen(split[0]), dealerID, split[1],
                split[2], Integer.parseInt(split[3]), split[4]));
        dealerID ++;
      }
      br.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private static String nameGen(String name){
    Random r = new Random();
    int coolified = r.nextInt() % 5;
    switch(coolified){
      case(0):
        name += " Motors";
        return name;
      case(1):
        name += " Auto";
        return name;
      case(2):
        name += " OmegaCar";
        return name;
      case(3):
        name += " Automotive Group";
        return name;
      case(4):
        name += " Vehicles";
        return name;
    }
    return "errored";
  }

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
