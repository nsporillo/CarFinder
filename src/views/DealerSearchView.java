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
import java.util.List;

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
	 * Create the frame.
	 */
	public DealerSearchView() {
		setResizable(false);
		setTitle("Search Dealers");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 200, 324);
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
		scrollPane.setBounds(220, 11, 551, 400);
		contentPane.add(scrollPane);

		resultPanel = new JPanel();
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.PAGE_AXIS));
		scrollPane.setViewportView(resultPanel);

		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		searchButton.addActionListener(arg0 -> {
			try {
				dealerSearch = fromFields();
				List<Dealer> results = dealerSearch.execute(Team01Driver.getDriver().getDB().getConnection());


				// Remove any possible previous results
				resultPanel.removeAll();
				resultPanel.revalidate();

				// Add header
				resultPanel.add(fromDealer(Dealer.label(), false));

				// Display all vehicle results
				for (Dealer d : results) {
					resultPanel.add(fromDealer(d, true));
				}

				resultPanel.revalidate();
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}
		});

		searchButton.setBounds(10, 359, 200, 52);
		contentPane.add(searchButton);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 346, 200, 2);
		contentPane.add(separator_1);
	}

	private JButton fromDealer(final Dealer dealer, boolean clickable) {
		JButton jButton = new JButton(dealer.getSearchView());

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

	private void filterDealersByName(String name) {

	}

	private void loadDealerById(String id) {
		// TODO: Load dealer view for the specified dealer

	}
}
