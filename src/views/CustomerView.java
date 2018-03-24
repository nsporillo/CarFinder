package views;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class CustomerView extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtGender;
	private JTextField txtIncome_1;
	private JTextField txtIncome;
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
		panel.setBounds(10, 11, 474, 300);
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
		
		txtGender = new JTextField();
		txtGender.setEditable(false);
		txtGender.setText("phone");
		panel.add(txtGender);
		txtGender.setColumns(10);
		
		txtIncome = new JTextField();
		txtIncome.setEditable(false);
		txtIncome.setText("gender");
		panel.add(txtIncome);
		txtIncome.setColumns(10);
		
		txtIncome_1 = new JTextField();
		txtIncome_1.setEditable(false);
		txtIncome_1.setText("income");
		panel.add(txtIncome_1);
		txtIncome_1.setColumns(10);
		
		JTextPane txtpnVehiclenvehicle = new JTextPane();
		txtpnVehiclenvehicle.setText("Vehicle 1\r\nVehicle 2\r\n....");
		txtpnVehiclenvehicle.setBounds(10, 322, 474, 138);
		contentPane.add(txtpnVehiclenvehicle);
	}
}
