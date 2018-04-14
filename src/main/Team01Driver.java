package main;

import views.ViewManager;

import javax.swing.*;
import java.util.Date;

/**
 * This class is the main driver for the database
 * Launches the GUI to access the database
 */
public class Team01Driver {

    private static Team01Driver driver;
    private DBConnector dbConnector;
    private ViewManager viewManager;
    private UserType userType;

    /**
     * Creates a new driver to the database with admin and password
     *
     * @param args
     */
    public static void main(String[] args) {
        String user = "admin";
        String password = "password";
        driver = new Team01Driver(user, password); // Optional: Create .config file and place username/password/other settings
    }

    /*
     * Singleton patterns for the driver
     */
    public static Team01Driver getDriver() {
        return driver;
    }

    /**
     * Calls DB connector to make a database if one does not exist yet and starts the GUI
     *
     * @param username username to log in with
     * @param password password to log in with
     */
    public Team01Driver(String username, String password) {
        dbConnector = new DBConnector(username, password);

        SwingUtilities.invokeLater(() -> {
            viewManager = new ViewManager();
            viewManager.setupUserSelect();
        });

    }

    /**
     * Standard getters and setters below
     *
     * @return DBConnector object
     */
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

    public static void log(String message) {
        String date = String.format("(INFO) [%1$tH:%1$tM:%1$tS] ", new Date());
        System.out.println(date + message);
    }

    public static void debug(String message) {
        String date = String.format("(DEBUG) [%1$tH:%1$tM:%1$tS] ", new Date());
        System.out.println(date + message);
    }
}
