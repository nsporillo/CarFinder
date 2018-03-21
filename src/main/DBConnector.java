package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	// The connection to the database
	private Connection conn;

	/**
	 * Create a database connection with the given params
	 * 
	 * @param location:
	 *            path of where to place the database
	 * @param user:
	 *            user name for the owner of the database
	 * @param password:
	 *            password of the database owner
	 */
	public DBConnector(String location, String user, String password) {
		try {
			// This needs to be on the front of your location
			String url = "jdbc:h2:" + location;

			// This tells it to use the h2 driver
			Class.forName("org.h2.Driver");

			// creates the connection
			conn = DriverManager.getConnection(url, user, password);
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
}
