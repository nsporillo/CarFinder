package tables;

import models.Model;
import models.Option;
import models.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class VehicleTable {

	public static List<Vehicle> populateVehicleTableFromCSV(List<Option> options, Connection conn, String carCSV, String opCSV) throws SQLException {
		List<Vehicle> vehicles = new ArrayList<>();

		try {
			BufferedReader carReader = Files.newBufferedReader(Paths.get(carCSV), Charset.forName("US-ASCII"));
			BufferedReader opReader = Files.newBufferedReader(Paths.get(opCSV), Charset.forName("US-ASCII"));

			String car = carReader.readLine();
			while (car != null && car.length() != 0) {
				String op = opReader.readLine();
				String[] carSplit = car.split(",");

				while (op != null && op.length() != 0 && carSplit[0].equals(op.split(",")[0])) {
					String[] optionSplit = op.split(",");
					Model model = new Model(ModelTable.getModelId(conn, carSplit[2], carSplit[3]), carSplit[2], carSplit[3]);
					int lineOpId = Integer.parseInt(optionSplit[1]);
					int year = Integer.parseInt(carSplit[1]);
					int price = Integer.parseInt(carSplit[4]);

					for (Option option : options) {
						if (lineOpId == option.getId()) {
							Vehicle vehicle = new Vehicle();
							vehicle.setVin(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 17));
							vehicle.setYear(year);
							vehicle.setPrice(price);
							vehicle.setModel(model);
							vehicle.setOption(option);
							vehicles.add(vehicle);
							//System.out.println(vehicle.toString());
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

		for (List<Vehicle> lst : partition(vehicles, 999)) {
			PreparedStatement stmt = createVehicleInsertSQL(conn, lst);
			stmt.executeUpdate();
		}

		return vehicles;
	}

	private static List<List<Vehicle>> partition(List<Vehicle> vehicles, Integer size) {
		if (vehicles == null) {
			return Collections.emptyList();
		}

		List<Vehicle> list = new ArrayList<>(vehicles);

		if (size == null || size <= 0 || size > list.size()) {
			size = list.size();
		}

		int numPages = (int) Math.ceil((double) list.size() / (double) size);
		List<List<Vehicle>> pages = new ArrayList<>(numPages);

		for (int pageNum = 0; pageNum < numPages; ) {
			pages.add(list.subList(pageNum * size, Math.min(++pageNum * size, list.size())));
		}
		return pages;
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

	public static PreparedStatement createVehicleInsertSQL(Connection conn, List<Vehicle> vehicles) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO Vehicle (VIN, ModelID, OptionID, Year, Price) VALUES ");

		for (int i = 0; i < vehicles.size(); i++) {
			sb.append("(?,?,?,?,?)");

			if (i != vehicles.size() - 1) {
				sb.append(",");
			} else {
				sb.append(";");
			}
		}

		PreparedStatement stmt = conn.prepareStatement(sb.toString());

		for (int i = 0; i < vehicles.size(); i++) {
			Vehicle vehicle = vehicles.get(i);
			stmt.setString(1 + (i * 5), vehicle.getVin());
			stmt.setInt(2 + (i * 5), vehicle.getModelID());
			stmt.setInt(3 + (i * 5), vehicle.getOptionID());
			stmt.setInt(4 + (i * 5), vehicle.getYear());
			stmt.setInt(5 + (i * 5), vehicle.getPrice());
		}

		return stmt;
	}
}
