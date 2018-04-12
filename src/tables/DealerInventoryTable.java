package tables;

import main.Team01Driver;
import models.Dealer;
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


	public static void addAllVehiclesToAllDealers(Connection conn, List<Vehicle> vehicles, List<Dealer> dealers) throws SQLException {
		Team01Driver.log("Distributing " + vehicles.size() + " vehicles across " + dealers.size() + " dealers");
		int dealerSize = dealers.size();

		for (int i = 0; i < dealerSize; i++) {
			int mod = (int)(vehicles.size() / dealerSize);

			Team01Driver.debug(String.format("Putting vehicles (%d,%d) into Dealer %d", (i * mod), (i * mod + mod), dealers.get(i).getId()));
			PreparedStatement ps = createDealerInventoryInsert(conn, vehicles.subList(i * mod, i * mod + mod), dealers.get(i).getId());
			ps.executeUpdate();
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

	public static int getDealerInventorySize(Connection conn, int dealerID) {
		int size = 0;

		String query = "SELECT COUNT(*) FROM DealerInventory WHERE DealerID=?";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, dealerID);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				size = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return size;
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
