package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Vehicle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VehicleView extends JFrame {

	private JPanel contentPane;
	private Vehicle vehicle;
	
	private JTextField vinField;
	private JTextField makeField;
	private JTextField yearField;
	private JTextField modelField;
	private JTextField priceField;
	private JTextField extColorField;
	private JTextField intColorField;
	private JTextField transmissionField;
	private JTextField drivetrainField;
	private JTextField trimField;
	private JTextField engineField;

	/**
	 * Create the frame.
	 */
	public VehicleView() {
		setTitle("Vehicle View");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVin = new JLabel("VIN:");
		lblVin.setBounds(5, 10, 50, 14);
		contentPane.add(lblVin);
		
		vinField = new JTextField();
		vinField.setBounds(55, 7, 200, 20);
		contentPane.add(vinField);
		vinField.setColumns(10);
		vinField.setEditable(false);
		
		JLabel lblMake = new JLabel("Make: ");
		lblMake.setBounds(5, 35, 60, 14);
		contentPane.add(lblMake);
		
		makeField = new JTextField();
		makeField.setBounds(55, 35, 100, 20);
		contentPane.add(makeField);
		makeField.setColumns(10);
		makeField.setEditable(false);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(342, 38, 46, 14);
		contentPane.add(lblYear);
		
		yearField = new JTextField();
		yearField.setEditable(false);
		yearField.setBounds(386, 32, 60, 20);
		contentPane.add(yearField);
		yearField.setColumns(10);
		
		JLabel lblModel = new JLabel("Model: ");
		lblModel.setBounds(165, 38, 60, 14);
		contentPane.add(lblModel);
		
		modelField = new JTextField();
		modelField.setEditable(false);
		modelField.setBounds(205, 35, 127, 20);
		contentPane.add(modelField);
		modelField.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(275, 10, 46, 14);
		contentPane.add(lblPrice);
		
		priceField = new JTextField();
		priceField.setEditable(false);
		priceField.setBounds(315, 7, 131, 20);
		contentPane.add(priceField);
		priceField.setColumns(10);
		
		JLabel lblExteriorColor = new JLabel("Exterior Color:");
		lblExteriorColor.setBounds(5, 66, 100, 14);
		contentPane.add(lblExteriorColor);
		
		extColorField = new JTextField();
		extColorField.setEditable(false);
		extColorField.setBounds(85, 63, 140, 20);
		contentPane.add(extColorField);
		extColorField.setColumns(10);
		
		JLabel lblInteriorColor = new JLabel("Interior Color:");
		lblInteriorColor.setBounds(256, 66, 100, 14);
		contentPane.add(lblInteriorColor);
		
		intColorField = new JTextField();
		intColorField.setEditable(false);
		intColorField.setBounds(346, 60, 100, 20);
		contentPane.add(intColorField);
		intColorField.setColumns(10);
		
		JLabel lblTransmission = new JLabel("Transmission:");
		lblTransmission.setBounds(5, 91, 100, 14);
		contentPane.add(lblTransmission);
		
		transmissionField = new JTextField();
		transmissionField.setToolTipText("Vehicle Transmission");
		transmissionField.setEditable(false);
		transmissionField.setBounds(85, 91, 140, 20);
		contentPane.add(transmissionField);
		transmissionField.setColumns(10);
		
		JLabel lblDrivetrain = new JLabel("Drivetrain: ");
		lblDrivetrain.setBounds(256, 91, 100, 14);
		contentPane.add(lblDrivetrain);
		
		drivetrainField = new JTextField();
		drivetrainField.setToolTipText("Vehicle Drivetrain");
		drivetrainField.setEditable(false);
		drivetrainField.setBounds(346, 88, 100, 20);
		contentPane.add(drivetrainField);
		drivetrainField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 150, 444, 2);
		contentPane.add(separator);
		
		JLabel lblTrim = new JLabel("Trim: ");
		lblTrim.setBounds(288, 122, 100, 14);
		contentPane.add(lblTrim);
		
		trimField = new JTextField();
		trimField.setToolTipText("Vehicle Trim type");
		trimField.setEditable(false);
		trimField.setBounds(346, 119, 100, 20);
		contentPane.add(trimField);
		trimField.setColumns(10);
		
		JButton btnDisplaySales = new JButton("Display Sales");
		btnDisplaySales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Open sales view to show the total sales for this type of car?
			}
		});
		btnDisplaySales.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDisplaySales.setBounds(260, 159, 190, 93);
		contentPane.add(btnDisplaySales);
		
		JButton btnNewButton = new JButton("View Owner");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: Open either customer view if the car is owned by a customer
				//TODO: Or a dealer view if the car isnt sold yet
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(5, 159, 190, 93);
		contentPane.add(btnNewButton);
		
		JLabel lblEngine = new JLabel("Engine: ");
		lblEngine.setBounds(5, 122, 100, 14);
		contentPane.add(lblEngine);
		
		engineField = new JTextField();
		engineField.setEditable(false);
		engineField.setBounds(55, 119, 200, 20);
		contentPane.add(engineField);
		engineField.setColumns(10);
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		this.vinField.setText(vehicle.getVin());
		this.makeField.setText(vehicle.getBrand());
		this.engineField.setText(vehicle.getEngine());
		this.modelField.setText(vehicle.getModel());
		this.priceField.setText(String.valueOf(vehicle.getPrice()));
		
	}
}
