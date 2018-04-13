package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.Team01Driver;
import main.UserType;
import models.Dealer;
import tables.DealerInventoryTable;

public class DealerView extends JFrame {

	private JPanel contentPane;
	private Dealer dealer;
	
	private JButton btnSales;
	private JTextField nameField;
	private JTextField idField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField invField;

	/**
	 * Create the frame.
	 */
	public DealerView() {
		if (dealer != null) {
			setTitle("Viewing " + dealer.getName());
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(5, 13, 55, 14);
		contentPane.add(lblNewLabel);
		
		nameField = new JTextField();
		nameField.setBounds(60, 10, 300, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		nameField.setEditable(false);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(261, 38, 35, 14);
		contentPane.add(lblId);
		
		idField = new JTextField();
		idField.setBounds(295, 35, 65, 20);
		contentPane.add(idField);
		idField.setColumns(10);
		idField.setEditable(false);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(5, 38, 55, 14);
		contentPane.add(lblPhone);
		
		phoneField = new JTextField();
		phoneField.setBounds(60, 35, 180, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		phoneField.setEditable(false);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(5, 63, 55, 14);
		contentPane.add(lblAddress);
		
		addressField = new JTextField();
		addressField.setBounds(60, 60, 300, 20);
		contentPane.add(addressField);
		addressField.setColumns(10);
		addressField.setEditable(false);
		
		JButton btnSearchInventory = new JButton("Search Inventory");
		btnSearchInventory.setBackground(SystemColor.inactiveCaption);
		btnSearchInventory.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSearchInventory.addActionListener(e -> {
			VehicleSearchView search = Team01Driver.getDriver().getViewManager().getVehicleSearch();
			search.fillInDealerName(dealer.getName());
			search.setVisible(true);
			setVisible(false);
		});
		btnSearchInventory.setBounds(0, 138, 484, 123);
		contentPane.add(btnSearchInventory);
		
		btnSales = new JButton("Sales");
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Load SalesView with dealer specified
			}
		});
		btnSales.setBackground(new Color(255, 222, 173));
		btnSales.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSales.setBounds(384, 0, 100, 80);
		contentPane.add(btnSales);
		
		
		JLabel lblTotalCarInventory = new JLabel("Total Inventory:");
		lblTotalCarInventory.setBounds(5, 100, 120, 14);
		contentPane.add(lblTotalCarInventory);
		
		invField = new JTextField();
		invField.setBounds(100, 96, 120, 20);
		invField.setEditable(false);
		contentPane.add(invField);
	}

	public Dealer getDealer() {
		return dealer;
	}
	
	public void prepareView() {
		UserType userType = Team01Driver.getDriver().getUserType();
		// If the user isn't an analyst, dont let them see Sales view
		if (userType != null && userType != UserType.ANALYST) {
			btnSales.setEnabled(false);
			btnSales.setVisible(false);
		}
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
		setTitle("Viewing " + dealer.getName());
		this.nameField.setText(dealer.getName());
		this.idField.setText(String.valueOf(dealer.getId()));
		this.phoneField.setText(dealer.getPhone());
		this.addressField.setText(dealer.getAddress());

		String template = "%d Vehicles";
		int size = DealerInventoryTable.getDealerInventorySize(Team01Driver.getDriver().getDB().getConnection(), dealer.getId());
		this.invField.setText(String.format(template, size));
	}
}
