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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VehicleSearchView extends JFrame {

	private JPanel mainContentPane;

	private JComboBox<String> makeBox;
	private JComboBox<String> modelBox;
	private JComboBox<String> priceBox;
	private JComboBox<String> yearBox;
	private JComboBox<String> engineBox;
	private JComboBox<String> colorBox;
	private JComboBox<String> trannyBox;
	private JScrollPane searchScroll;
	private JPanel searchResultPanel;
	private JLabel lblDisplayResults;

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
		makeBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//TODO: Update all other comboboxes to only display
				// valid options
			}
		});
		modelBox = new JComboBox<>();
		modelBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//TODO: Update all other comboboxes to only display
				// valid options
			}
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
				//TODO: Update all other comboboxes to only display
				// valid options
			}
		});
		trannyBox = new JComboBox<>();
		trannyBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//TODO: Update all other comboboxes to only display
				// valid options
			}
		});
		colorBox = new JComboBox<>();
		colorBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//TODO: Update all other comboboxes to only display
				// valid options
			}
		});
		engineBox = new JComboBox<>();
		engineBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//TODO: Update all other comboboxes to only display
				// valid options
			}
		});

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

		// Supply the UI with some defaults that will be overrided later
		// Simply for UI testing without DB connection
		setMakes(Arrays.asList("BMW"));
		setModels(Arrays.asList("325xi", "540i"));
		setPrices(5304, 16540);
		setYears(1985, 2018);
		setEngines(Arrays.asList("I4", "V6", "V8"));
		setColors(Arrays.asList("Black", "White", "Silver"));
		setTrannies(Arrays.asList("Automatic", "Manual"));

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(5, 396, 190, 64);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DealerInventorySearch dealerInventorySearch = createSearch();
					List<Vehicle> results = dealerInventorySearch.execute(Team01Driver.getDriver().getDB().getConnection());

					if (results == null || results.size() == 0) {
						System.out.println("No results");
						return;
					} else if (results.size() > 1000) {
						results = results.subList(0, 1000);
					}

					lblDisplayResults.setText("Displaying " + results.size() + " Results");

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
