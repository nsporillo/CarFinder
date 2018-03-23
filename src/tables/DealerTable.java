package tables;

import models.Dealer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DealerTable {
	
	private static final String table = "Dealer";

  public static void populateDealerTableFromCSV(Connection conn, String filename) throws SQLException{
    ArrayList<Dealer> dealers = new ArrayList<>();
    try{
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String line;
      int dealerID = 0;
      while((line = br.readLine()) != null){
        String[] split = line.split(",");
        
        Dealer dealer = new Dealer();
        dealer.setName(nameGen(split[0]));
        dealer.setId(dealerID++);
        dealer.setStreet(split[1]);
        //TODO: Set city
        dealer.setState(split[2]);
        dealer.setZip(Integer.parseInt(split[3]));
        dealer.setPhone(split[4]);
        dealers.add(dealer);
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

    String query = String.format("INSERT INTO " + table + " "
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

	/**
	 * Finds a dealer given a dealerId
	 * @param conn
	 * @param dealerId
	 * @return dealer object 
	 */
	public static Dealer getById(Connection conn, int dealerId) {
		PreparedStatement ps = null;
		String query = "SELECT * FROM " + table + " WHERE ID = ?";

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, dealerId);
			ResultSet rs = ps.executeQuery();
			Dealer dealer = new Dealer();

			while (rs.next()) {
				dealer.setId(rs.getInt(0));
				dealer.setName(rs.getString(1));
				dealer.setState(rs.getString(2));
				dealer.setCity(rs.getString(3));
				dealer.setZip(rs.getInt(rs.getInt(4)));
				dealer.setState(rs.getString(5));
				dealer.setPhone(rs.getString(6));
			}
			
			ps.close();
			return dealer;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/**
	 * Filters dealers on a given name
	 * @param conn
	 * @param name
	 * @return list of dealer objects
	 */
	public static List<Dealer> filterDealersByName(Connection conn, String name) {
		List<Dealer> dealers = new ArrayList<>();
		PreparedStatement ps = null;
		String query = "SELECT * FROM " + table + " WHERE name LIKE %?%";

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			Dealer dealer = new Dealer();

			while (rs.next()) {
				dealer.setId(rs.getInt(0));
				dealer.setName(rs.getString(1));
				dealer.setState(rs.getString(2));
				dealer.setCity(rs.getString(3));
				dealer.setZip(rs.getInt(rs.getInt(4)));
				dealer.setState(rs.getString(5));
				dealer.setPhone(rs.getString(6));
				dealers.add(dealer);
			}

			ps.close();
			return dealers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
