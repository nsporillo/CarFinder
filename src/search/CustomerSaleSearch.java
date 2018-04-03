package search;

import models.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Search wrapper class for querying DB for sales for customers
 * Can support any number of queries involving finding sale objects
 */
public class CustomerSaleSearch extends Search {

	private Map<String, Object> customerFields = new HashMap<>();
	private Map<String, Object> vehicleFields = new HashMap<>();
	private Map<String, Object> saleFields = new HashMap<>();
	private Map<String, Object> optionFields = new HashMap<>();
	private Map<Integer, Object> parameterIndex = new TreeMap<>();

	public void setIncome(int income) {
		customerFields.put("Income", income);
	}

	public void setFirstName(String firstName) {
		customerFields.put("FirstName", firstName);
	}

	public void setLastName(String lastName) {
		customerFields.put("LastName", lastName);
	}


	@Override
	public String prepareSQL() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM Sale ");

		if (customerFields.size() > 0) {

		}

		return query.toString();
	}

	@Override
	public List<Sale> execute(Connection connection) {
		List<Sale> sales = new ArrayList<>();

		try {
			PreparedStatement ps = super.prepareStatement(connection, prepareSQL());
			ResultSet rs = ps.executeQuery();
			super.clearParameterIndex();

			while (rs.next()) {

			}

			ps.close();
			return sales;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
