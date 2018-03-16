package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.Team01Driver;

public class DealerSearch extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public DealerSearch() {
		setTitle("Search Dealers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 204, 0, 75, 74, 131, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblFilterByName = new JLabel("Filter by name");
		GridBagConstraints gbc_lblFilterByName = new GridBagConstraints();
		gbc_lblFilterByName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilterByName.anchor = GridBagConstraints.EAST;
		gbc_lblFilterByName.gridx = 1;
		gbc_lblFilterByName.gridy = 0;
		contentPane.add(lblFilterByName, gbc_lblFilterByName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnFilterByName = new JButton("Search");
		btnFilterByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GridBagConstraints gbc_btnFilterByName = new GridBagConstraints();
		gbc_btnFilterByName.insets = new Insets(0, 0, 5, 5);
		gbc_btnFilterByName.gridx = 3;
		gbc_btnFilterByName.gridy = 0;
		contentPane.add(btnFilterByName, gbc_btnFilterByName);
		
		JLabel lblFilterById = new JLabel("Filter by ID");
		GridBagConstraints gbc_lblFilterById = new GridBagConstraints();
		gbc_lblFilterById.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilterById.anchor = GridBagConstraints.EAST;
		gbc_lblFilterById.gridx = 5;
		gbc_lblFilterById.gridy = 0;
		contentPane.add(lblFilterById, gbc_lblFilterById);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 6;
		gbc_textField_1.gridy = 0;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 7;
		gbc_btnSearch.gridy = 0;
		contentPane.add(btnSearch, gbc_btnSearch);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 5;
		gbc_textArea.gridwidth = 7;
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 2;
		contentPane.add(textArea, gbc_textArea);
	}

}
