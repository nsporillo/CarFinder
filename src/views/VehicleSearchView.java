package views;

import main.Team01Driver;
import models.Vehicle;
import search.DealerInventorySearch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VehicleSearchView extends JFrame {

    private JPanel mainContentPane;

    private JComboBox<String> makeBox;
    private JComboBox<String> modelBox;
    private JComboBox<String> priceBox;
    private JComboBox<String> yearBox;
    private JComboBox<String> engineBox;
    private JComboBox<String> colorBox;
    private JComboBox<String> trannyBox;
    private JFormattedTextField zipField;
    private JFormattedTextField dealerField;
    private JPanel searchResultPanel;
    private JLabel lblDisplayResults;

    private DealerInventorySearch dealerInventorySearch;

    /**
     * Column Toggling Variables
     */
    protected Map<String, Boolean> columnToggle = new LinkedHashMap<>(); // LinkedHashMap preserves insertion order
    private List<Vehicle> displayedVehicles;

    /**
     * Create the frame.
     */
    public VehicleSearchView() {
        setResizable(false);
        setTitle("Search By Make");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 850, 550);
        mainContentPane = new JPanel();
        mainContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(mainContentPane);
        mainContentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 190, 370);
        mainContentPane.add(panel);

        JLabel lblMake = new JLabel("Make");
        JLabel lblModel = new JLabel("Model");
        JLabel lblMaxPrice = new JLabel("Max Price");
        JLabel lblNearZip = new JLabel("Near ZIP");
        JLabel lblDealer = new JLabel("Dealer");
        JLabel lblColor = new JLabel("Color");
        JLabel lblEngine = new JLabel("Engine");
        JLabel lblYear = new JLabel("Year");
        JLabel lblTransmission = new JLabel("Transmission");

		/* TODO
		  Currently this assumes the user selects search options from top to bottom
		  We should be able to start from an arbitrary search option and continue refining
		  until we want to search. The idea is to dynamically update the search options in the
		  comboboxes as the user makes more selections. If any conflicts arise when narrowing
		  down options, we should open an error popup detailing the issue.
		 */
        makeBox = new JComboBox<>();
        makeBox.addItemListener(e -> {
            SwingUtilities.invokeLater(() -> {
                dealerInventorySearch = createSearch();
                Connection dbConnection = Team01Driver.getDriver().getDB().getConnection();
                setModels(dealerInventorySearch.findRemainingColumnRows(dbConnection, "BodyStyle"));
                setEngines(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Engine"));
                setColors(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Color"));
                setTrannies(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Transmission"));
            });

        });
        modelBox = new JComboBox<>();
        modelBox.addItemListener(e -> {
            dealerInventorySearch = createSearch();
            Connection dbConnection = Team01Driver.getDriver().getDB().getConnection();
            setEngines(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Engine"));
            setColors(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Color"));
            setTrannies(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Transmission"));
        });
        priceBox = new JComboBox<>();
        priceBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //TODO: Update all other comboboxes to only display
                // valid options
            }
        });
        yearBox = new JComboBox<>();
        yearBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                dealerInventorySearch = createSearch();
                Connection dbConnection = Team01Driver.getDriver().getDB().getConnection();
                setColors(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Color"));
                setEngines(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Engine"));
                setTrannies(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Transmission"));
            }
        });
        colorBox = new JComboBox<>();
        colorBox.addItemListener(e -> {
            dealerInventorySearch = createSearch();
            Connection dbConnection = Team01Driver.getDriver().getDB().getConnection();
            setEngines(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Engine"));
            setTrannies(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Transmission"));
        });
        engineBox = new JComboBox<>();
        engineBox.addItemListener(e -> {
            dealerInventorySearch = createSearch();
            Connection dbConnection = Team01Driver.getDriver().getDB().getConnection();
            setTrannies(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Transmission"));
        });
        trannyBox = new JComboBox<>();
        trannyBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //TODO
            }
        });

        /*
         * Uses constant formatted text field formatters
         */
        zipField = new JFormattedTextField(ViewConstants.ZIP_FORMAT);
        dealerField = new JFormattedTextField(ViewConstants.DEALER_FORMAT);

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(lblMake);
        panel.add(makeBox);
        panel.add(lblModel);
        panel.add(modelBox);
        panel.add(lblMaxPrice);
        panel.add(priceBox);
        panel.add(lblNearZip);
        panel.add(zipField);
        panel.add(lblDealer);
        panel.add(dealerField);
        panel.add(lblYear);
        panel.add(yearBox);
        panel.add(lblColor);
        panel.add(colorBox);
        panel.add(lblEngine);
        panel.add(engineBox);
        panel.add(lblTransmission);
        panel.add(trannyBox);

        dealerInventorySearch = createSearch();
        Connection dbConnection = Team01Driver.getDriver().getDB().getConnection();

        setMakes(dealerInventorySearch.findRemainingColumnRows(dbConnection, "BrandName"));
        setModels(dealerInventorySearch.findRemainingColumnRows(dbConnection, "BodyStyle"));
        setYears(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Year"));
        setEngines(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Engine"));
        setColors(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Color"));
        setTrannies(dealerInventorySearch.findRemainingColumnRows(dbConnection, "Transmission"));
        setPrices(dealerInventorySearch.findPrice(dbConnection, "MIN"), dealerInventorySearch.findPrice(dbConnection, "MAX"));

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(5, 396, 190, 64);
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSearch.addActionListener(searchListener);

        mainContentPane.add(btnSearch);

        JSeparator vertSeparator = new JSeparator();
        vertSeparator.setOrientation(SwingConstants.VERTICAL);
        vertSeparator.setBounds(200, 0, 2, 520);
        mainContentPane.add(vertSeparator);

        lblDisplayResults = new JLabel("Displaying 0 Results");
        lblDisplayResults.setBounds(210, 8, 200, 14);
        mainContentPane.add(lblDisplayResults);

        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(210, 30, 625, 430);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainContentPane.add(scrollPane);

        searchResultPanel = new JPanel();
        searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.PAGE_AXIS));
        scrollPane.setViewportView(searchResultPanel);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 464, 850, 2);
        mainContentPane.add(separator);

        JLabel lblNewLabel = new JLabel("Toggle Columns ");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(5, 468, 190, 52);
        mainContentPane.add(lblNewLabel);

        JCheckBox columnMake = new JCheckBox("Make");
        columnMake.setSelected(true);
        columnToggle.put("Make", true);
        columnMake.setBounds(300, 467, 100, 23);
        columnMake.addActionListener(new ColumnListener("Make"));
        mainContentPane.add(columnMake);

        JCheckBox columnModel = new JCheckBox("Model");
        columnModel.setSelected(true);
        columnToggle.put("Model", true);
        columnModel.setBounds(300, 497, 100, 23);
        columnModel.addActionListener(new ColumnListener("Model"));
        mainContentPane.add(columnModel);

        JCheckBox columnYear = new JCheckBox("Year");
        columnYear.setSelected(true);
        columnToggle.put("Year", true);
        columnYear.setBounds(200, 497, 100, 23);
        columnYear.addActionListener(new ColumnListener("Year"));
        mainContentPane.add(columnYear);

        JCheckBox columnPrice = new JCheckBox("Price");
        columnPrice.setSelected(true);
        columnToggle.put("Price", true);
        columnPrice.addActionListener(new ColumnListener("Price"));
        columnPrice.setBounds(500, 467, 100, 23);
        mainContentPane.add(columnPrice);

        JCheckBox columnColor = new JCheckBox("Color");
        columnColor.setSelected(true);
        columnToggle.put("Color", true);
        columnColor.setBounds(400, 467, 100, 23);
        columnColor.addActionListener(new ColumnListener("Color"));
        mainContentPane.add(columnColor);

        JCheckBox columnEngine = new JCheckBox("Engine");
        columnEngine.setBounds(400, 497, 100, 23);
        columnToggle.put("Engine", false);
        columnEngine.addActionListener(new ColumnListener("Engine"));
        mainContentPane.add(columnEngine);

        JCheckBox columnTransmission = new JCheckBox("Transmission");
        columnTransmission.setBounds(500, 497, 150, 23);
        columnToggle.put("Transmission", false);
        columnTransmission.addActionListener(new ColumnListener("Transmission"));
        mainContentPane.add(columnTransmission);

        JCheckBox columnDealer = new JCheckBox("Dealer");
        columnDealer.setSelected(true);
        columnToggle.put("Dealer", true);
        columnDealer.setBounds(200, 467, 100, 23);
        columnDealer.addActionListener(new ColumnListener("Dealer"));
        mainContentPane.add(columnDealer);
    }

    public void fillInDealerName(String dealerName) {
        this.dealerField.setText(dealerName);
    }

    protected void renderResults() {
        if (displayedVehicles != null) {
            // Remove any possible previous results
            searchResultPanel.removeAll();
            searchResultPanel.validate();

            // Add header
            searchResultPanel.add(fromVehicle(Vehicle.label(), false));

            // Display all vehicle results
            for (Vehicle v : displayedVehicles) {
                searchResultPanel.add(fromVehicle(v, true));
            }

            searchResultPanel.updateUI();
        }
    }

    class ColumnListener implements ActionListener {

        private String column;

        ColumnListener(String column) {
            this.column = column;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Boolean oldValue = columnToggle.get(column);
            columnToggle.put(column, !oldValue);
            SwingUtilities.invokeLater(VehicleSearchView.this::renderResults);
        }
    }

    final ActionListener searchListener = new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            try {
                dealerInventorySearch = createSearch();
                displayedVehicles = dealerInventorySearch.execute(Team01Driver.getDriver().getDB().getConnection());

                // Remove any possible previous results
                searchResultPanel.removeAll();
                searchResultPanel.validate();

                // Add header
                searchResultPanel.add(fromVehicle(Vehicle.label(), false));

                int resultSize = displayedVehicles.size();

                // Arbitrary results limit to avoid overloading JScrollPane
                if (resultSize == 0) {
                    System.out.println("No results");
                    lblDisplayResults.setText("Displaying 0 Results");
                    return;
                } else if (resultSize > 1000) {
                    displayedVehicles = displayedVehicles.subList(0, 1000);
                }

                if (resultSize != displayedVehicles.size()) {
                    lblDisplayResults.setText("Displaying " + displayedVehicles.size() + " / " + resultSize + " Results");
                } else {
                    lblDisplayResults.setText("Displaying " + displayedVehicles.size() + " Results");
                }


                // Display all vehicle results
                for (Vehicle v : displayedVehicles) {
                    searchResultPanel.add(fromVehicle(v, true));
                }

                searchResultPanel.updateUI();
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        }
    };

    private JButton fromVehicle(final Vehicle vehicle, boolean clickable) {
        JButton jButton = new JButton(vehicle.getSearchView(columnToggle));

        if (clickable) {
            jButton.addActionListener(e -> {
                VehicleView view = Team01Driver.getDriver().getViewManager().getMakeView();
                view.setVehicle(vehicle);
                if (!view.isVisible()) {
                    view.setVisible(true);
                }
            });
        }

        return jButton;
    }

    private DealerInventorySearch createSearch() {
        DealerInventorySearch dealerInventorySearch = new DealerInventorySearch();
        String make = (String) makeBox.getSelectedItem();
        String model = (String) modelBox.getSelectedItem();
        String maxPrice = (String) priceBox.getSelectedItem();
        String year = (String) yearBox.getSelectedItem();
        String color = (String) colorBox.getSelectedItem();
        String engine = (String) engineBox.getSelectedItem();
        String tranny = (String) trannyBox.getSelectedItem();

        if (make != null && !make.equals("All Makes")) {
            dealerInventorySearch.setBrandName(make);
        }

        if (model != null && !model.equals("All Models")) {
            dealerInventorySearch.setBodyStyle(model);
        }

        /* Need to parse price back from pretty $ form */
        if (maxPrice != null && !maxPrice.equals("No Max Price")) {
            Number number;
            try {
                number = ViewConstants.CURRENCY.parse(maxPrice);
                dealerInventorySearch.setMaxPrice(number.intValue());
            } catch (ParseException ex) {
                ex.printStackTrace(System.err);
            }
        }

        Integer dealer = null;

        try {
            /* Parse dealer number from the dealer field*/
            dealer = (Integer) dealerField.getFormatter().stringToValue(dealerField.getText());
        } catch (ParseException e) {
            if (dealerField.getText().length() > 0) {
                dealerInventorySearch.setDealerName(dealerField.getText());
            }
        }

        if (dealer != null) {
            dealerInventorySearch.setDealerID(dealer);
        }

        if (year != null && !year.equals("Any")) {
            dealerInventorySearch.setYear(Integer.parseInt(year));
        }

        if (color != null && !color.equals("Any")) {
            dealerInventorySearch.setColor(color);
        }

        if (engine != null && !engine.equals("Any")) {
            dealerInventorySearch.setEngine(engine);
        }

        if (tranny != null && !tranny.equals("Any")) {
            dealerInventorySearch.setTransmission(tranny);
        }

        return dealerInventorySearch;
    }

    public void setMakes(List<String> makes) {
        List<String> base = new ArrayList<>();
        base.add("All Makes");
        base.addAll(makes);
        makeBox.setModel(new DefaultComboBoxModel<>(base.toArray(new String[base.size()])));
    }

    public void setModels(List<String> models) {
        List<String> base = new ArrayList<>();
        base.add("All Models");
        base.addAll(models);
        modelBox.setModel(new DefaultComboBoxModel<>(base.toArray(new String[base.size()])));
    }

    public void setPrices(int smallestPrice, int largestPrice) {
        List<String> base = new ArrayList<>();
        base.add("No Max Price");

        if (smallestPrice > 0 && largestPrice > 0) {
            int start = (int) (Math.ceil(smallestPrice / 1000) * 1000);
            int end = (int) (Math.ceil(largestPrice / 1000) * 1000);

            for (int i = start; i <= end; i += Math.min(2000, end - start)) {
                String amount = ViewConstants.CURRENCY.format(i);
                base.add(amount);
            }
        }

        priceBox.setModel(new DefaultComboBoxModel<>(base.toArray(new String[base.size()])));
    }

    public void setYears(List<String> years) {
        List<String> base = new ArrayList<>();
        base.add("Any");
        base.addAll(years);
        yearBox.setModel(new DefaultComboBoxModel<>(base.toArray(new String[base.size()])));
    }

    public void setColors(List<String> colors) {
        List<String> base = new ArrayList<>();
        base.add("Any");
        base.addAll(colors);
        colorBox.setModel(new DefaultComboBoxModel<>(base.toArray(new String[base.size()])));
    }

    public void setEngines(List<String> engines) {
        List<String> base = new ArrayList<>();
        base.add("Any");
        base.addAll(engines);
        engineBox.setModel(new DefaultComboBoxModel<>(base.toArray(new String[base.size()])));
    }

    public void setTrannies(List<String> trannies) {
        List<String> base = new ArrayList<>();
        base.add("Any");
        base.addAll(trannies);
        trannyBox.setModel(new DefaultComboBoxModel<>(base.toArray(new String[base.size()])));
    }
}
