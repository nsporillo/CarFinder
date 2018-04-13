package views;

import main.Team01Driver;
import models.Dealer;
import models.Vehicle;
import search.DealerSearch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DealerSearchView extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField streetField;
	private JTextField cityField;
	private JTextField stateField;
	private JFormattedTextField zipField;
	private JFormattedTextField phoneField;
	private JFormattedTextField dealerIDField;

	private JPanel resultPanel;
	private DealerSearch dealerSearch;

	/**
	 * Column Toggling Variables
	 */
	protected Map<String, Boolean> columnToggle = new LinkedHashMap<>(); // LinkedHashMap preserves insertion order
	private List<Dealer> displayedDealers;

	/**
	 * Create the frame.
	 */
	public DealerSearchView() {
		setResizable(false);
		setTitle("Search Dealers");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 200, 314);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));


		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(nameLabel);

		nameField = new JTextField();
		panel.add(nameField);
		nameField.setColumns(10);

		JLabel streetLabel = new JLabel("Street:");
		streetLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		streetLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		streetLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(streetLabel);

		streetField = new JTextField();
		panel.add(streetField);
		streetField.setColumns(10);

		JLabel cityLabel = new JLabel("City:   ");
		cityLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		cityLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(cityLabel);

		cityField = new JTextField();
		panel.add(cityField);
		cityField.setColumns(10);

		JLabel stateLabel = new JLabel("State:");
		stateLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		stateLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		stateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(stateLabel);

		stateField = new JTextField();
		panel.add(stateField);
		stateField.setColumns(10);

		JLabel zipLabel = new JLabel("ZIP:    ");
		zipLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		zipLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		zipLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(zipLabel);


		zipField = new JFormattedTextField(ViewConstants.ZIP_FORMAT);
		panel.add(zipField);

		JLabel phoneLabel = new JLabel("Phone:");
		phoneLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		phoneLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(phoneLabel);

		phoneField = new JFormattedTextField();
		panel.add(phoneField);

		Component rigidArea = Box.createRigidArea(new Dimension(150, 20));
		panel.add(rigidArea);

		JSeparator separator = new JSeparator();
		panel.add(separator);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(150, 20));
		panel.add(rigidArea_1);

		JLabel dealerIdLabel = new JLabel("Find by Dealer ID:");
		dealerIdLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		dealerIdLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		dealerIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(dealerIdLabel);

		dealerIDField = new JFormattedTextField(ViewConstants.DEALER_FORMAT);
		panel.add(dealerIDField);
		
		/*
		 * Create scrollable result pane to be filled in with dealer results
		 */
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(220, 11, 551, 390);
		contentPane.add(scrollPane);

		resultPanel = new JPanel();
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.PAGE_AXIS));
		scrollPane.setViewportView(resultPanel);

		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		searchButton.addActionListener(arg0 -> {
			try {
				dealerSearch = fromFields();
				//List<Dealer> results = dealerSearch.execute(Team01Driver.getDriver().getDB().getConnection());
				displayedDealers = dealerSearch.execute(Team01Driver.getDriver().getDB().getConnection());

				// Remove any possible previous results
				resultPanel.removeAll();
				resultPanel.revalidate();

				// Add header
				resultPanel.add(fromDealer(Dealer.label(), false));

				// Display all vehicle results
				for (Dealer d : displayedDealers) {
					resultPanel.add(fromDealer(d, true));
				}

				resultPanel.updateUI();
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}
		});

		searchButton.setBounds(10, 359, 200, 52);
		contentPane.add(searchButton);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 346, 200, 2);
		contentPane.add(separator_1);


		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 464, 850, 2);
		contentPane.add(separator_2);

		JLabel lblNewLabel = new JLabel("Toggle Columns ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(5, 468, 190, 52);
		contentPane.add(lblNewLabel);

		JCheckBox columnName = new JCheckBox("Name");
		columnName.setSelected(true);
		columnToggle.put("Name", true);
		columnName.setBounds(300, 467, 100, 23);
		columnName.addActionListener(new DealerSearchView.ColumnListener("Name"));
		contentPane.add(columnName);

		JCheckBox columnID = new JCheckBox("ID");
		columnToggle.put("ID", false);
		columnID.setBounds(300, 497, 100, 23);
		columnID.addActionListener(new DealerSearchView.ColumnListener("ID"));
		contentPane.add(columnID);

		JCheckBox columnStreet = new JCheckBox("Street");
		columnStreet.setSelected(true);
		columnToggle.put("Street", true);
		columnStreet.setBounds(200, 497, 100, 23);
		columnStreet.addActionListener(new DealerSearchView.ColumnListener("Street"));
		contentPane.add(columnStreet);

		JCheckBox columnCity = new JCheckBox("City");
		columnCity.setSelected(true);
		columnToggle.put("City", true);
		columnCity.addActionListener(new DealerSearchView.ColumnListener("City"));
		columnCity.setBounds(500, 467, 100, 23);
		contentPane.add(columnCity);

		JCheckBox columnState = new JCheckBox("State");
		columnState.setSelected(true);
		columnToggle.put("State", true);
		columnState.setBounds(400, 467, 100, 23);
		columnState.addActionListener(new DealerSearchView.ColumnListener("State"));
		contentPane.add(columnState);

		JCheckBox columnZip = new JCheckBox("Zip");
		columnZip.setSelected(true);
		columnZip.setBounds(400, 497, 100, 23);
		columnToggle.put("Zip", true);
		columnZip.addActionListener(new DealerSearchView.ColumnListener("Zip"));
		contentPane.add(columnZip);

		JCheckBox columnPhone = new JCheckBox("Phone");
		columnPhone.setSelected(true);
		columnPhone.setBounds(500, 497, 150, 23);
		columnToggle.put("Phone", true);
		columnPhone.addActionListener(new DealerSearchView.ColumnListener("Phone"));
		contentPane.add(columnPhone);

	}

	protected void renderResults() {
		if (displayedDealers != null) {
			// Remove any possible previous results
			resultPanel.removeAll();
			resultPanel.validate();

			// Add header
			resultPanel.add(fromDealer(Dealer.label(), false));

			// Display all vehicle results
			for (Dealer d : displayedDealers) {
				resultPanel.add(fromDealer(d, true));
			}

			resultPanel.updateUI();
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
			SwingUtilities.invokeLater(DealerSearchView.this::renderResults);
		}
	}

	private JButton fromDealer(final Dealer dealer, boolean clickable) {
		JButton jButton = new JButton(dealer.getSearchView(columnToggle));

		if (clickable) {
			jButton.addActionListener(e -> {
				DealerView view = Team01Driver.getDriver().getViewManager().getDealerView();
				view.setDealer(dealer);
				if (!view.isVisible()) {
					view.setVisible(true);
				}
			});
		}

		return jButton;
	}

	private DealerSearch fromFields() {
		DealerSearch dealerSearch = new DealerSearch();
		dealerSearch.setName(nameField.getText());
		dealerSearch.setStreet(streetField.getText());
		dealerSearch.setCity(cityField.getText());
		dealerSearch.setState(stateField.getText());

		Integer dealer = null;

		try {
			/* Parse dealer number from the dealer field*/
			dealer = (Integer) dealerIDField.getFormatter().stringToValue(dealerIDField.getText());
		} catch (ParseException e) {
			e.printStackTrace(System.err);
		}

		dealerSearch.setDealerID(dealer);

		Integer zip = null;

		try {
			/* Parse dealer number from the dealer field*/
			zip = (Integer) zipField.getFormatter().stringToValue(zipField.getText());
		} catch (ParseException e) {
			e.printStackTrace(System.err);
		}

		dealerSearch.setZIP(zip);

		dealerSearch.setPhone(phoneField.getText());

		return dealerSearch;
	}

}
