package tables;

import main.Team01Driver;
import models.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerTable {

	/**
	 * Reads a cvs file for data and adds them to the customer table
	 * <p>
	 * Does not create the table. It must already be created
	 *
	 * @param conn     database connection to work with
	 * @param fileName fileName of CSV file containing customer table data
	 * @throws SQLException
	 */
	public static void populateCustomerTableFromCSV(Connection conn, String fileName) throws SQLException {
		Team01Driver.log("Populating Customer table from " + fileName);
		List<Customer> customer = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(",");
				customer.add(new Customer(Integer.parseInt(split[0]), split[1], split[2], (split[3] + " " + split[4]), split[5],
						split[6], Integer.parseInt(split[7]), split[8], Integer.parseInt(split[9]), Integer.parseInt(split[10])));
			}
			br.close();
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}

		String sql = createCustomerInsertSQL(customer);

		Statement stmt = conn.createStatement();
		stmt.execute(sql);
	}

	/**
	 * Adds a single customer to the database
	 *
	 * @param conn       connection
	 * @param customerID customer primary key
	 * @param firstName  first name
	 * @param lastName   last name
	 * @param street     street address
	 * @param city       city of address
	 * @param state      state of address
	 * @param zip        zip code of address
	 * @param phone      phone number
	 * @param gender     gender
	 * @param income     annual income
	 */
	public static void addCustomer(Connection conn, int customerID, String firstName, String lastName,
	                               String street, String city, String state, int zip, String phone, int gender, int income) {

		String query = String.format("INSERT INTO Customer "
						+ "VALUES(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%d\',\'%s\',\'%d\',\'%d\');",
				firstName, lastName, street, city, state, zip, phone, gender, income);

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
	 * Creates SQL command for inserting Customers into Customer table
	 *
	 * @param customer list of customers to be added into Customer table
	 * @return SQL command
	 */
	public static String createCustomerInsertSQL(List<Customer> customer) {
		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO Customer (CustomerID, FirstName, LastName, Street, City, State, ZIP, Phone, Gender, Income) VALUES");

		for (int i = 0; i < customer.size(); i++) {
			Customer c = customer.get(i);
			sb.append(String.format("(%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%d\',\'%s\',\'%d\',\'%d\')",
					c.getID(), c.getFirstName(), c.getLastName(), c.getStreet(), c.getCity(), c.getState(),
					c.getZip(), c.getPhone(), c.getGender(), c.getAnnualIncome()));
			if (i != customer.size() - 1) {
				sb.append(",");
			} else {
				sb.append(";");
			}
		}

		return sb.toString();
	}

	/**
	 * Prints out CustomerTable
	 *
	 * @param conn connection
	 */
	public static void printCustomerTable(Connection conn) {

		String query = "SELECT * FROM Customer;";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);

			while (result.next()) {
				System.out.printf("Customer %d: %s %s %s %s %s %d %s %d %d\n",
						result.getInt(1),
						result.getString(2),
						result.getString(3),
						result.getString(4),
						result.getString(5),
						result.getString(6),
						result.getInt(7),
						result.getString(8),
						result.getInt(9),
						result.getInt(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
