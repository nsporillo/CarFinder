package tables;

import models.Model;
import models.Option;
import models.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class VehicleTable {

	public static void populateVehicleTableFromCSV(List<Option> options, Connection conn, String carCSV, String opCSV) throws SQLException {
		List<Vehicle> vehicles = new ArrayList<>();

		try {
			BufferedReader carReader = Files.newBufferedReader(Paths.get(carCSV), Charset.forName("US-ASCII"));
			BufferedReader opReader = Files.newBufferedReader(Paths.get(opCSV), Charset.forName("US-ASCII"));

			String car = carReader.readLine();
			while(car != null && car.length()!= 0) {
				String op = opReader.readLine();
				String[] carSplit = car.split(",");

				while (op != null && op.length() != 0 && carSplit[0].equals(op.split(",")[0])) {
					String[] optionSplit = op.split(",");

					for (Option option : options) {
						if (Integer.parseInt(optionSplit[1]) == option.getId()) {
							Vehicle vehicle = new Vehicle();
							vehicle.setVin(UUID.randomUUID().toString().replaceAll("-", "").substring(0,17));
							vehicle.setYear(Integer.parseInt(carSplit[1]));
							vehicle.setPrice(Integer.parseInt(carSplit[4]));
							vehicle.setModel(new Model(ModelTable.getModelId(conn, carSplit[2], carSplit[3]), carSplit[2], carSplit[3]));
							vehicle.setOption(option);
							vehicles.add(vehicle);
							System.out.println(vehicle.toString());
						}
					}

					op = opReader.readLine();
				}

				car = carReader.readLine();
			}

			opReader.close();
			carReader.close();
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}

		String sql = createVehicleInsertSQL(vehicles);
		System.out.println(sql);
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
	}

	/**
	 * Prints out Vehicle
	 *
	 * @param conn connection
	 */
	public static void printVehicleTable(Connection conn) {

		String query = "SELECT * FROM Vehicle;";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);

			while (result.next()) {
				System.out.printf("Vehicle %d: %d %d %d %d %d\n",
						result.getInt(1),
						result.getInt(2),
						result.getInt(3),
						result.getInt(4),
						result.getInt(5),
						result.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String createVehicleInsertSQL(List<Vehicle> vehicles) {
		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO Vehicle (VIN, modelID, optionID, year, price) VALUES");

		for (int i = 0; i < vehicles.size(); i++) {
			Vehicle vehicle = vehicles.get(i);
			sb.append(String.format("(%s,\'%d\',\'%d\',\'%d\',\'%d\')",
					vehicle.getVin(), vehicle.getModelID(), vehicle.getOptionID(), vehicle.getYear(), vehicle.getPrice()));
			if (i != vehicles.size() - 1) {
				sb.append(",");
			} else {
				sb.append(";");
			}
		}

		return sb.toString();
	}

}
