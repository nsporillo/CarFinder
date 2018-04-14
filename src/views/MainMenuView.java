package views;

import main.Team01Driver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenuView extends JFrame {

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public MainMenuView() {
        setResizable(false);
        setTitle("Main Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(0, 1, 0, 0));

        JButton btnSearchDealers = new JButton("Search Dealers");
        btnSearchDealers.addActionListener(e -> {
            Team01Driver.getDriver().getViewManager().getDealerSearch().setVisible(true);
        });
        btnSearchDealers.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSearchDealers.setBackground(Color.PINK);
        btnSearchDealers.setForeground(Color.BLACK);
        contentPane.add(btnSearchDealers);

        JButton btnSearchCars = new JButton("Search Cars");
        btnSearchCars.addActionListener(e -> {
            Team01Driver.getDriver().getViewManager().getVehicleSearch().setVisible(true);
        });
        btnSearchCars.setForeground(Color.BLACK);
        btnSearchCars.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSearchCars.setBackground(Color.GREEN);
        contentPane.add(btnSearchCars);

        setContentPane(contentPane);
    }

}
