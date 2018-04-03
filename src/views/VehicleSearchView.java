package views;

import main.Team01Driver;
import models.Model;
import models.Option;
import models.Vehicle;
import search.DealerInventorySearch;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VehicleSearchView extends JFrame {

	private JPanel mainContentPane;

	private static JComboBox<String> makeBox;
	private static JComboBox<String> modelBox;
	private static JComboBox<String> priceBox;
	private static JComboBox<String> yearBox;
	private static JComboBox<String> engineBox;
	private static JComboBox<String> colorBox;
	private static JComboBox<String> trannyBox;
	private JScrollPane searchScroll;
	private JPanel searchResultPanel;
	private JLabel lblDisplayResults;

	private DealerInventorySearch dealerInventorySearch;

	/**
	 * Create the frame.
	 */
	public VehicleSearchView() {
		setResizable(false);
		setTitle("Search By Make");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		mainContentPane = new JPanel();
		mainContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainContentPane);
		mainContentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 190, 380);
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
		lblMake.setAlignmentX(Component.LEFT_ALIGNMENT);

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

		// TODO: Add zip code validation
		JFormattedTextField zipField = new JFormattedTextField();
		// TODO: Add dealer name (or id) validation
		JFormattedTextField dealerField = new JFormattedTextField();

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

		//TODO: Add better detection for range of dealer inventories for year and price
		setPrices(5304, 16540);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(5, 396, 190, 64);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dealerInventorySearch = createSearch();
					List<Vehicle> results = dealerInventorySearch.execute(Team01Driver.getDriver().getDB().getConnection());

					if (results == null || results.size() == 0) {
						System.out.println("No results");
						return;
					} else if (results.size() > 1000) {
						results = results.subList(0, 1000);
					}

					lblDisplayResults.setText("Displaying " + results.size() + " Results");

					// Remove any possible previous results
					searchResultPanel.removeAll();

					// Add header
					searchResultPanel.add(fromVehicle(Vehicle.label(), false));
					
					for (Vehicle v : results) {
						searchResultPanel.add(fromVehicle(v, true));
					}

					searchResultPanel.revalidate();
				} catch(Exception ex) {
					ex.printStackTrace(System.err);
				}
			}
		});

		mainContentPane.add(btnSearch);

		JSeparator vertSeparator = new JSeparator();
		vertSeparator.setOrientation(SwingConstants.VERTICAL);
		vertSeparator.setBounds(200, 0, 2, 470);
		mainContentPane.add(vertSeparator);

		lblDisplayResults = new JLabel("Displaying 0 Results");
		lblDisplayResults.setBounds(210, 8, 140, 14);
		mainContentPane.add(lblDisplayResults);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(210, 30, 575, 430);
		mainContentPane.add(scrollPane);

		searchResultPanel = new JPanel();
		searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.PAGE_AXIS));
		scrollPane.setViewportView(searchResultPanel);
	}

	private JButton fromVehicle(final Vehicle vehicle, boolean clickable) {
		JButton jButton = new JButton(vehicle.getSearchView());

		if (clickable) {
			jButton.addActionListener(e -> {
				VehicleView view = Team01Driver.getDriver().getViewManager().getMakeView();
				view.setVehicle(vehicle);
				view.setVisible(true);
			});
		}

		return jButton;
	}

	private static DealerInventorySearch createSearch() {
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

		if (maxPrice != null && !maxPrice.equals("No Max Price")) {
			dealerInventorySearch.setMaxPrice(Integer.parseInt(maxPrice.substring(0, maxPrice.length()-1)));
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
		makeBox.setModel(new DefaultComboBoxModel<String>(base.toArray(new String[base.size()])));
	}

	public void setModels(List<String> models) {
		List<String> base = new ArrayList<>();
		base.add("All Models");
		base.addAll(models);
		modelBox.setModel(new DefaultComboBoxModel<String>(base.toArray(new String[base.size()])));
	}

	public void setPrices(int smallestPrice, int largestPrice) {
		List<String> base = new ArrayList<>();
		base.add("No Max Price");

		if (smallestPrice > 0 && largestPrice > 0) {
			int start = (int) (Math.ceil(smallestPrice / 1000) * 1000);
			int end = (int) (Math.ceil(largestPrice / 1000) * 1000);

			for (int i = start; i <= end; i += Math.min(2000, end - start)) {
				String amount = NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(i);
				base.add(amount);
			}
		}

		priceBox.setModel(new DefaultComboBoxModel<String>(base.toArray(new String[base.size()])));
	}

	public void setYears(int start, int end) {
		List<String> base = new ArrayList<>();
		base.add("Any");

		for (int i = start; i <= end; i++) {
			base.add(String.valueOf(i));
		}

		yearBox.setModel(new DefaultComboBoxModel<String>(base.toArray(new String[base.size()])));
	}

	public void setYears(List<String> years) {
		List<String> base = new ArrayList<>();
		base.add("Any");
		base.addAll(years);
		yearBox.setModel(new DefaultComboBoxModel<String>(base.toArray(new String[base.size()])));
	}

	public void setColors(List<String> colors) {
		List<String> base = new ArrayList<>();
		base.add("Any");
		base.addAll(colors);
		colorBox.setModel(new DefaultComboBoxModel<String>(base.toArray(new String[base.size()])));
	}

	public void setEngines(List<String> engines) {
		List<String> base = new ArrayList<>();
		base.add("Any");
		base.addAll(engines);
		engineBox.setModel(new DefaultComboBoxModel<String>(base.toArray(new String[base.size()])));
	}

	public void setTrannies(List<String> trannies) {
		List<String> base = new ArrayList<>();
		base.add("Any");
		base.addAll(trannies);
		trannyBox.setModel(new DefaultComboBoxModel<String>(base.toArray(new String[base.size()])));
	}
}
