package main;

import models.Dealer;
import models.Option;
import models.Vehicle;
import tables.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBConnector {

	// The connection to the database
	private Connection conn;
	private String databasePath = new File("database/SQLTables.sql").getAbsolutePath();

	public DBConnector() {
		createConnection(databasePath, "admin", "password");
	}

	/**
	 * Create a database connection with the given params
	 *
	 * @param location: path of where to place the database
	 * @param user:     user name for the owner of the database
	 * @param password: password of the database owner
	 */
	private void createConnection(String location, String user, String password) {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:" + location, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * When your database program exits you should close the connection
	 */
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.conn;
	}

	public static void main(String[] args) {
		DBConnector main = new DBConnector();

		File file = new File("database/SQLTables.sql");
		String path = file.getAbsolutePath();

		try { // Reads and Executes SQL commands to create tables under /tables
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

			StringBuilder query = new StringBuilder();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				if (line.startsWith("--")) {
					continue; // skip sql comments
				}
				query.append(line);

				// found a query, execute it
				if (line.endsWith(";")) {
					Statement stmt = main.getConnection().createStatement();
					Team01Driver.log(query.toString().substring(0, query.indexOf("(")).toLowerCase());
					stmt.execute(query.toString());
					query = new StringBuilder();
				}
			}

			bufferedReader.close();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		try {
			CustomerTable.populateCustomerTableFromCSV(main.getConnection(), "newCustomerData.csv");
			List<Option> optionList = OptionTable.populateOptionTableFromCSV(main.getConnection(), "CarOptions.csv");
			ModelTable.populateModelTableFromCSV(main.getConnection(), "Vehicles.csv");
			List<Dealer> dealers = DealerTable.populateDealerTableFromCSV(main.getConnection(), "DealershipData.csv");
			List<Vehicle> vehicles = VehicleTable.populateVehicleTableFromCSV(optionList, main.getConnection(), "Vehicles.csv", "VehicleOptions.csv");
			DealerInventoryTable.addAllVehiclesToAllDealers(main.getConnection(), vehicles, dealers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
