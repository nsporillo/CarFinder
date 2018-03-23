package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import main.Team01Driver;
import models.Dealer;

public class DealerSearchView extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField nameField; // dealer name search field
	private JFormattedTextField idField; // dealer id search field
	private JTextPane textArea; // main result display component

	/**
	 * Create the frame.
	 */
	public DealerSearchView() {
		setTitle("Search Dealers");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JButton btnFilterByName = new JButton("Search");
		JButton btnFindByID = new JButton("Search");
		
		// TODO: Add some additional error checking and validation on search fields
		
		nameField = new JFormattedTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		contentPane.add(nameField, gbc_textField);
		
		btnFilterByName.addActionListener(e -> {
            String value = nameField.getText();

            //TODO: Add more validation?
            if (value != null) {
                filterDealersByName(value);
            }
        });
		
		GridBagConstraints gbc_btnFilterByName = new GridBagConstraints();
		gbc_btnFilterByName.insets = new Insets(0, 0, 5, 5);
		gbc_btnFilterByName.gridx = 3;
		gbc_btnFilterByName.gridy = 0;
		contentPane.add(btnFilterByName, gbc_btnFilterByName);
		
		JLabel lblFilterById = new JLabel("Load Dealer by ID");
		GridBagConstraints gbc_lblFilterById = new GridBagConstraints();
		gbc_lblFilterById.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilterById.anchor = GridBagConstraints.EAST;
		gbc_lblFilterById.gridx = 5;
		gbc_lblFilterById.gridy = 0;
		contentPane.add(lblFilterById, gbc_lblFilterById);
		
		idField = new JFormattedTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 6;
		gbc_textField_1.gridy = 0;
		contentPane.add(idField, gbc_textField_1);
		
		btnFindByID.addActionListener(e -> {
            String value = idField.getText();

            //TODO: Add more validation?
            if (value != null) {
                loadDealerById(value);
            }
        });
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 7;
		gbc_btnSearch.gridy = 0;
		contentPane.add(btnFindByID, gbc_btnSearch);
		
		textArea = new JTextPane();
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 5;
		gbc_textArea.gridwidth = 7;
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 2;
		contentPane.add(textArea, gbc_textArea);
	}
	
	private void filterDealersByName(String name) {
		// TODO: Query database for all dealers by this name
		// TODO: use testArea to display all matching dealers
		// TODO: All entries will be clickable links which 
		// TODO: load a DealerView for the clicked dealer
		
		// Debug code
		String old = textArea.getText();
		textArea.setText(old + " -> " + name);
	}

	private void loadDealerById(String id) {
		// TODO: Load dealer view for the specified dealer
		
	}
}
