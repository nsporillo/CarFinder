package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.DealerSearchView;
import views.DealerView;
import views.MainMenuView;
import views.MakeSearchView;
import views.VehicleView;
import views.SalesView;
import views.UserSelectView;

public class Team01Driver {
	
	private static Team01Driver driver;
	private static DBConnector dbConnector;

	private UserSelectView userSelect;
	private MainMenuView mainMenu;
	private MakeSearchView makeSearch;
	private DealerSearchView dealerSearch;
	private VehicleView makeView;
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
		userSelect = new UserSelectView();
		mainMenu = new MainMenuView();
		makeSearch = new MakeSearchView();
		dealerSearch = new DealerSearchView();
		makeView = new VehicleView();
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

	public UserSelectView getUserSelect() {
		return userSelect;
	}

	public MainMenuView getMainMenu() {
		return mainMenu;
	}

	public MakeSearchView getMakeSearch() {
		return makeSearch;
	}

	public DealerSearchView getDealerSearch() {
		return dealerSearch;
	}

	public VehicleView getMakeView() {
		return makeView;
	}

	public DealerView getDealerView() {
		return dealerView;
	}

	public SalesView getSalesView() {
		return salesView;
	}

	public UserType getUserType() {
		return userType;
	}
}
