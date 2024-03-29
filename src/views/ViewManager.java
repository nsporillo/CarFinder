package views;

import main.Team01Driver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewManager {

    private UserSelectView userSelect;
    private MainMenuView mainMenu;
    private VehicleSearchView vehicleSearch;
    private DealerSearchView dealerSearch;
    private VehicleView makeView;
    private DealerView dealerView;
    private SalesView salesView;
    private SalesSearchView salesSearchView;
    private CustomerView customerView;

    public ViewManager() {
        userSelect = new UserSelectView();
        mainMenu = new MainMenuView();
        vehicleSearch = new VehicleSearchView();
        dealerSearch = new DealerSearchView();
        makeView = new VehicleView();
        dealerView = new DealerView();
        dealerView.prepareView();
        salesView = new SalesView();
        salesSearchView = new SalesSearchView();
        customerView = new CustomerView();
    }

    public void setupUserSelect() {
        // Adds listener for the button in user select menu
        userSelect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Team01Driver.getDriver().setUserType(userSelect.getSelection());
                userSelect.setVisible(false); // hide the user selection menu
                userSelect = null; // we can destroy this, wont be needed anymore
                mainMenu.setVisible(true); // show the main menu frame
            }

        });

        userSelect.setVisible(true);
    }

    public VehicleSearchView getVehicleSearch() {
        return vehicleSearch;
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

    public SalesSearchView getSalesSearchView() {
        return salesSearchView;
    }
}
