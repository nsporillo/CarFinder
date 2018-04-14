package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SalesView extends JFrame {

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public SalesView() {
        setTitle("Sales Report");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSalesReportFor = new JLabel("Sales report for %dealer%");
        lblSalesReportFor.setBounds(10, 11, 160, 14);
        contentPane.add(lblSalesReportFor);

        JTable textPane = new JTable();
        textPane.setBounds(10, 36, 624, 324);
        contentPane.add(textPane);
    }
}
