package tables;

import models.Vehicle;

import java.sql.*;
import java.util.List;

public class DealerInventoryTable {

	public static void addVehicle(Connection conn, String vin, int dealerID) {
		String query = String.format("INSERT INTO DealerInventory VALUES(%s,\'%d\');", vin, dealerID);

		try {
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param conn     Database connection
	 * @param vehicles List of vehicles to insert
	 * @param dealerID DealerID for the dealer which owns the vehicles
	 * @return a PreparedStatement ready to be executed
	 * @throws SQLException on SQL errors
	 */
	public static PreparedStatement createDealerInventoryInsert(Connection conn, List<Vehicle> vehicles, int dealerID) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO DealerInventory (VIN, DealerID) VALUES ");

		for (int i = 0; i < vehicles.size(); i++) {
			sb.append("(?,?)");

			if (i != vehicles.size() - 1) {
				sb.append(",");
			} else {
				sb.append(";");
			}
		}

		PreparedStatement stmt = conn.prepareStatement(sb.toString());

		for (int i = 0; i < vehicles.size(); i++) {
			Vehicle vehicle = vehicles.get(i);
			stmt.setString(1 + (i * 2), vehicle.getVin());
			stmt.setInt(2 + (i * 2), dealerID);
		}

		return stmt;
	}

	/**
	 * Prints out DealerInventory
	 *
	 * @param conn connection
	 */
	public static void printDealerInventoryTable(Connection conn) {
		String query = "SELECT * FROM DealerInventory;";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);

			while (result.next()) {
				System.out.printf("DealerInventory %s: %d\n",
						result.getString(1),
						result.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
