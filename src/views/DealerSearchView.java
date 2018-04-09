package views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

public class DealerSearchView extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField streetField;
	private JTextField cityField;
	private JFormattedTextField zipField;
	private JFormattedTextField phoneField;
	private JFormattedTextField dealerIDField;
	
	private JPanel resultPanel;

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
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		searchButton.setBounds(10, 359, 200, 52);
		contentPane.add(searchButton);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 346, 200, 2);
		contentPane.add(separator_1);
	}

	private void filterDealersByName(String name) {

	}

	private void loadDealerById(String id) {
		// TODO: Load dealer view for the specified dealer

	}
}
