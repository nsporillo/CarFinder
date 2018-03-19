package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnSearchDealers = new JButton("Search Dealers");
		btnSearchDealers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnSearchDealers.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSearchDealers.setBackground(Color.PINK);
		btnSearchDealers.setForeground(Color.BLACK);
		contentPane.add(btnSearchDealers);
		
		JButton btnSearchCars = new JButton("Search Cars");
		btnSearchCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSearchCars.setForeground(Color.BLACK);
		btnSearchCars.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSearchCars.setBackground(Color.GREEN);
		contentPane.add(btnSearchCars);
	}

}