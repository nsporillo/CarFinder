package search;

import models.Model;
import models.Option;
import models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealerInventorySearch extends Search {

	private Map<String, Object> dealerFields = new HashMap<>();
	private Map<String, Object> vehicleFields = new HashMap<>();
	private Map<String, Object> modelFields = new HashMap<>();
	private Map<String, Object> optionFields = new HashMap<>();

	public void setDealerID(int dealerId) {
		dealerFields.put("DealerID", dealerId);
	}

	public void setDealerName(String dealerName) {
		if (!dealerFields.containsKey("DealerID")) {
			dealerFields.put("LikeDealerName", "%" + dealerName + "%");
		}
	}

	public void setNearZip(int zipCode) {
		dealerFields.put("NearZIP", zipCode);
	}

	public void setYear(int year) {
		vehicleFields.put("Year", year);
	}

	public void setMaxPrice(int maxPrice) {
		vehicleFields.put("MaxPrice", maxPrice);
	}

	public void setBrandName(String brandName) {
		modelFields.put("BrandName", brandName);
	}

	public void setBodyStyle(String bodyStyle) {
		modelFields.put("BodyStyle", bodyStyle);
	}

	public void setColor(String color) {
		optionFields.put("Color", color);
	}

	public void setEngine(String engine) {
		optionFields.put("Engine", engine);
	}

	public void setTransmission(String transmission) {
		optionFields.put("Transmission", transmission);
	}

	/**
	 * Test method for simulating PreparedStatement
	 * Do not call before .execute(), only replace .execute() with this.
	 * @return full SQL query with parameters injected
	 */
	private String injectParameters() {
		String sql = prepareSQL();

		for (Map.Entry<Integer, Object> entry : super.getParameterIndex().entrySet()) {
			Object e = entry.getValue();

			sql = sql.replaceFirst("\\?", e.toString());
		}

		return sql;
	}

	@Override
	public String prepareSQL() {
		String query = "SELECT * FROM DealerInventory " +
				"INNER JOIN Vehicle ON DealerInventory.VIN = Vehicle.VIN " +
				"INNER JOIN Model ON Vehicle.ModelID = Model.ModelID " +
				"INNER JOIN Option ON Vehicle.OptionID = Option.OptionID";

		if (dealerFields.size() > 0) {
			query += " INNER JOIN Dealer ON Dealer.DealerID = DealerInventory.DealerID";
		}

		/* Filter results by any number of vehicle or model fields */
		if (vehicleFields.size() > 0 || modelFields.size() > 0 || optionFields.size() > 0) {
			StringBuilder builder = new StringBuilder(" WHERE ");
			for (Map.Entry<String, Object> entry : vehicleFields.entrySet()) {
				/* Support for handling max price, max anything... */
				if (entry.getKey().startsWith("Max")) {
					String key = entry.getKey().replaceAll("Max", "");
					builder.append("Vehicle.").append(key).append("<=? AND ");
				} else {
					builder.append("Vehicle.").append(entry.getKey()).append("=? AND ");
				}

				super.setParameterIndex(entry.getValue());
			}

			for (Map.Entry<String, Object> entry : modelFields.entrySet()) {
				builder.append("Model.").append(entry.getKey()).append("=? AND ");
				super.setParameterIndex(entry.getValue());
			}

			for (Map.Entry<String, Object> entry : optionFields.entrySet()) {
				builder.append("Option.").append(entry.getKey()).append("=? AND ");
				super.setParameterIndex(entry.getValue());
			}

			/* Filter results by dealerID */
			if (dealerFields.containsKey("DealerID") && dealerFields.size() == 1) {
				query += "DealerInventory.DealerID=?";
				super.setParameterIndex(dealerFields.get("DealerID"));
			} else {
				for (Map.Entry<String, Object> entry : dealerFields.entrySet()) {
					if (entry.getKey().startsWith("Like")) {
						builder.append("Dealer.").append(entry.getKey().replaceAll("Like", "")).append(" LIKE ? AND ");
					} else {
						builder.append("Dealer.").append(entry.getKey()).append("=? AND ");
					}

					super.setParameterIndex(entry.getValue());
				}
			}

			query += builder.substring(0, builder.lastIndexOf("?") + 1);
		}

		return query + ";";
	}

	@Override
	public List<Vehicle> execute(Connection connection) {
		List<Vehicle> vehicles = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = super.prepareStatement(connection);
			rs = ps.executeQuery();
			super.clearParameterIndex();

			while (rs.next()) {
				Model model = new Model(
						rs.getInt("ModelID"),
						rs.getString("BrandName"),
						rs.getString("BodyStyle"));
				Option option = new Option(
						rs.getInt("OptionID"),
						rs.getString("Color"),
						rs.getString("Engine"),
						rs.getString("Transmission"));
				Vehicle vehicle = new Vehicle(
						rs.getString("VIN"),
						model,
						option,
						rs.getInt("Year"),
						rs.getInt("Price"));
				vehicles.add(vehicle);
			}

			return vehicles;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		DealerInventorySearch search = new DealerInventorySearch();
		search.setDealerName("Sick Autos");
		search.setColor("Silver");
		search.setBodyStyle("325xi");
		search.setBrandName("BMW");
		search.setEngine("V6");
		search.setTransmission("Automatic");
		search.setYear(2005);

		System.out.println(search.injectParameters());
	}
}
