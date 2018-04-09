package views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DealerSearchView extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField streetField;
	private JTextField cityField;

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

		JFormattedTextField zipField = new JFormattedTextField();
		panel.add(zipField);

		JLabel phoneLabel = new JLabel("Phone:");
		phoneLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		phoneLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(phoneLabel);

		JFormattedTextField phoneField = new JFormattedTextField();
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

		JFormattedTextField dealerIDField = new JFormattedTextField();
		panel.add(dealerIDField);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(220, 11, 551, 400);
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.PAGE_AXIS));

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
