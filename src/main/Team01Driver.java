package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.DealerSearch;
import views.MainMenu;
import views.MakeSearch;
import views.UserSelect;

public class Team01Driver {
	
	// Default width and height of our GUI
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;

	private UserSelect userSelect;
	private MainMenu mainMenu;
	private MakeSearch makeSearch;
	private DealerSearch dealerSearch;
	
	private UserType userType;
	
	
	public static void main(String[] args) {
		Team01Driver driver = new Team01Driver();
	}
	
	private void initializeViews() {
		userSelect = new UserSelect();
		mainMenu = new MainMenu();
		makeSearch = new MakeSearch();
		dealerSearch = new DealerSearch();
	}
	
	public Team01Driver() {
		initializeViews();
		
		// Adds listener for the button in user select menu
		userSelect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				userType = userSelect.getSelection();
				userSelect.setVisible(false);
				mainMenu.setVisible(true);
			}
			
		});
		
		userSelect.setVisible(true);
	}

}
