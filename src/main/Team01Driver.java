package main;

import views.ViewManager;

import javax.swing.*;

public class Team01Driver {

	private static Team01Driver driver;
	private DBConnector dbConnector;
	private ViewManager viewManager;
	private UserType userType;

	public static void main(String[] args) {
		driver = new Team01Driver();
		//TODO: Get DB setup and maybe load credentials from a config file?
	}

	/*
	 * Singleton patterns for the driver
	 */
	public static Team01Driver getDriver() {
		return driver;
	}

	public Team01Driver() {
		dbConnector = new DBConnector();

		SwingUtilities.invokeLater(() -> {
			viewManager = new ViewManager();
			viewManager.setupUserSelect();
		});

	}

	public DBConnector getDB() {
		return dbConnector;
	}

	public ViewManager getViewManager() {
		return viewManager;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
