package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Team01Driver;

public class DealerSearch extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public DealerSearch() {
		setTitle("Search Dealers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Team01Driver.WIDTH, Team01Driver.HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
