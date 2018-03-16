package views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Team01Driver;
import main.UserType;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SpringLayout;

public class UserSelect extends JFrame {

	private JPanel contentPane;
	private JButton btnBegin;
	private JComboBox<UserType> comboBox;
	private JLabel lblSelectAUser;

	/**
	 * Create the frame.
	 */
	public UserSelect() {
		setTitle("User Selection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 150);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		comboBox = new JComboBox<>();
		comboBox.setToolTipText("Select your user type");

		// Set default item selected to be customer
		DefaultComboBoxModel<UserType> defCombo = new DefaultComboBoxModel<>(UserType.values());
		defCombo.setSelectedItem(UserType.CUSTOMER);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblSelectAUser = new JLabel("Select user:");
		contentPane.add(lblSelectAUser);
		comboBox.setModel(defCombo);
		contentPane.add(comboBox);

		btnBegin = new JButton("Begin");
		contentPane.add(btnBegin);

		setContentPane(contentPane);
	}

	public void addActionListener(ActionListener listener) {
		btnBegin.addActionListener(listener);
	}

	public UserType getSelection() {
		return (UserType) comboBox.getSelectedItem();
	}
}
