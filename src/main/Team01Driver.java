package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.DealerSearch;
import views.DealerView;
import views.MainMenu;
import views.MakeSearch;
import views.MakeView;
import views.SalesView;
import views.UserSelect;

public class Team01Driver {
	
	private static Team01Driver driver;
	private static DBConnector dbConnector;

	private UserSelect userSelect;
	private MainMenu mainMenu;
	private MakeSearch makeSearch;
	private DealerSearch dealerSearch;
	private MakeView makeView;
	private DealerView dealerView;
	private SalesView salesView;
	
	private UserType userType;
	
	public static void main(String[] args) {
		driver = new Team01Driver();
		//TODO: Get DB setup and maybe load credentials from a config file?
		//dbConnector = new DBConnector(null, null, null);
	}

	/*
	 * Singleton patterns for the driver and db connector.
	 */
	public static Team01Driver getDriver() {
		return driver;
	}
	
	public static DBConnector getDB() {
		return dbConnector;
	}
	
	public Team01Driver() {
		userSelect = new UserSelect();
		mainMenu = new MainMenu();
		makeSearch = new MakeSearch();
		dealerSearch = new DealerSearch();
		makeView = new MakeView();
		dealerView = new DealerView();
		salesView = new SalesView();
		
		// Adds listener for the button in user select menu
		userSelect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				userType = userSelect.getSelection(); // set the global userType
				userSelect.setVisible(false); // hide the user selection menu
				userSelect = null; // we can destroy this, wont be needed anymore
				mainMenu.setVisible(true); // show the main menu frame
			}
			
		});
		
		userSelect.setVisible(true);
	}

	public UserSelect getUserSelect() {
		return userSelect;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public MakeSearch getMakeSearch() {
		return makeSearch;
	}

	public DealerSearch getDealerSearch() {
		return dealerSearch;
	}

	public MakeView getMakeView() {
		return makeView;
	}

	public DealerView getDealerView() {
		return dealerView;
	}

	public SalesView getSalesView() {
		return salesView;
	}

	public UserType getUserType() {
		if (userType == null) {
			return UserType.NONE;
		}
		
		return userType;
	}
}
