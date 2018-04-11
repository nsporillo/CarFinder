package tables;

import main.Team01Driver;
import models.Dealer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DealerTable {

	private static final String table = "Dealer";

	public static List<Dealer> populateDealerTableFromCSV(Connection conn, String filename) throws SQLException {
		Team01Driver.log("Populating Dealer table from " + filename);
		List<Dealer> dealers = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			int dealerID = 0;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(",");

				Dealer dealer = new Dealer();
				dealer.setName(nameGen(split[0]));
				dealer.setId(dealerID++);
				dealer.setStreet(split[1]);
				dealer.setCity(split[2]);
				dealer.setState(split[3]);
				dealer.setZip(Integer.parseInt(split[4]));
				dealer.setPhone(split[5]);
				dealers.add(dealer);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String sql = createDealerInsertSQL(dealers);
		Statement stmt = conn.createStatement();
		stmt.execute(sql);

		return dealers;
	}

	public static String createDealerInsertSQL(List<Dealer> dealers) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO Dealer (DealerID, Name, Street, City, State, ZIP, Phone) VALUES ");
		for (Dealer i : dealers) {
			sb.append(String.format("(%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%d\',\'%s\')",
					i.getId(), i.getName(), i.getStreet(),
					i.getCity(), i.getState(), i.getZip(),
					i.getPhone()));
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(";");
		return sb.toString();
	}

	public static void printDealerTables(Connection conn) {
		String query = "SELECT * FROM Dealer;";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				System.out.printf("Dealer %d: %s %s %s %s %d %s\n",
						result.getInt(1),
						result.getString(2),
						result.getString(3),
						result.getString(4),
						result.getString(5),
						result.getInt(6),
						result.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static String nameGen(String name) {
		Random r = new Random();
		int coolified = Math.abs(r.nextInt() % 6);
		switch (coolified) {
			case (0):
				name += " Motors";
				return name;
			case (1):
				name += " Auto";
				return name;
			case (2):
				name += " OmegaCar";
				return name;
			case (3):
				name += " Automotive Group";
				return name;
			case (4):
				name += " Vehicles";
				return name;
			case (5):
				name += " Customs";
				return name;
		}
		return "errored";
	}

	public static Dealer getById(Connection conn, int dealerId) {
		PreparedStatement ps = null;
		String query = "SELECT * FROM " + table + " WHERE DealerID = ?";

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
	 *
	 * @param conn
	 * @param name
	 * @return list of dealer objects
	 */
	public static List<Dealer> filterDealersByName(Connection conn, String name) {
		List<Dealer> dealers = new ArrayList<>();
		PreparedStatement ps = null;
		String query = "SELECT * FROM " + table + " WHERE name LIKE ?";

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, "%name%");
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
