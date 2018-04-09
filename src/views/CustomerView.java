package views;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.Team01Driver;
import main.UserType;
import models.Customer;

public class CustomerView extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JTextField txtIncome;
	private JTextField txtGender;

	private Customer customer;
	private JPanel vehiclePanel;
	private JSeparator separator;

	/**
	 * Create the frame.
	 */
	public CustomerView() {
		setResizable(false);
		setTitle("Customer View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 474, 263);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText("id");
		panel.add(txtId);
		txtId.setColumns(10);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setText("name");
		panel.add(txtName);
		txtName.setColumns(10);

		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setText("address");
		panel.add(txtAddress);
		txtAddress.setColumns(10);

		txtPhone = new JTextField();
		txtPhone.setEditable(false);
		txtPhone.setText("phone");
		panel.add(txtPhone);
		txtPhone.setColumns(10);

		txtGender = new JTextField();
		txtGender.setEditable(false);
		txtGender.setText("gender");
		panel.add(txtGender);
		txtGender.setColumns(10);

		txtIncome = new JTextField();
		txtIncome.setEditable(false);
		txtIncome.setText("income");
		panel.add(txtIncome);
		txtIncome.setColumns(10);

		/*
		 * Create scrollable result pane to be filled in with vehicle results
		 */
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 296, 474, 164);
		contentPane.add(scrollPane);

		vehiclePanel = new JPanel();
		vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.PAGE_AXIS));
		scrollPane.setViewportView(vehiclePanel);

		separator = new JSeparator();
		separator.setBounds(10, 283, 474, 2);
		contentPane.add(separator);

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;

		this.txtId.setText(String.valueOf(customer.getID()));
		this.txtName.setText(customer.getFirstName() + " " + customer.getLastName());
		this.txtGender.setText(customer.getGender() == 0 ? "Male" : "Female");
		this.txtPhone.setText(customer.getPhone());
		this.txtAddress.setText(customer.getAddress());
		this.txtIncome.setText(ViewConstants.CURRENCY.format(customer.getAnnualIncome()));

		// Load vehicle history if user is an analyst
		if (Team01Driver.getDriver().getUserType() == UserType.ANALYST) {

		}
	}
}
