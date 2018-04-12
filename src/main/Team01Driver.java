package main;

import views.ViewManager;

import javax.swing.*;
import java.util.Date;

public class Team01Driver {

    private static Team01Driver driver;
    private DBConnector dbConnector;
    private ViewManager viewManager;
    private UserType userType;

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

    public Team01Driver(String username, String password) {
        dbConnector = new DBConnector(username, password);

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

    public static void log(String message) {
        String date = String.format("(INFO) [%1$tH:%1$tM:%1$tS] ", new Date());
        System.out.println(date + message);
    }

    public static void debug(String message) {
        String date = String.format("(DEBUG) [%1$tH:%1$tM:%1$tS] ", new Date());
        System.out.println(date + message);
    }
}
