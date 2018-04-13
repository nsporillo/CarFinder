package views;

import main.Team01Driver;
import search.SaleSearch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;

public class SalesSearchView extends JFrame {

    private JPanel contentPane;

    private JComboBox<String> saleID;
    private JComboBox<String> dealerID;
    private JComboBox<String> customerID;
    private JComboBox<String> date;
    private JComboBox<String> vin;

    private SaleSearch saleSearch;

    public SalesSearchView() {
        setTitle("Search Sales");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 850, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 190, 370);
        contentPane.add(panel);

        JLabel lblSaleID = new JLabel("Sale ID");
        JLabel lblDealerID = new JLabel("Dealer ID");
        JLabel lblCustomerID = new JLabel("Customer ID");
        JLabel lblDate = new JLabel("Date");
        JLabel lblVIN = new JLabel("VIN");

        saleID = new JComboBox<>();
        saleID.addItemListener(e -> {
            SwingUtilities.invokeLater(() -> {
                saleSearch = createSearch();
                Connection dbConnection = Team01Driver.getDriver().getDB().getConnection();
                //setModels(saleSearch.findRemainingColumnRows(dbConnection, "BodyStyle"));
                //setEngines(saleSearch.findRemainingColumnRows(dbConnection, "Engine"));
                //setColors(saleSearch.findRemainingColumnRows(dbConnection, "Color"));
                //setTrannies(saleSearch.findRemainingColumnRows(dbConnection, "Transmission"));
            });

        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(lblSaleID);
        panel.add(lblDealerID);
        panel.add(lblCustomerID);
        panel.add(lblDate);
        panel.add(lblVIN);

    }

    public void fillInDealerID(int dealerID) {
        //TODO
    }

    private SaleSearch createSearch() {
        SaleSearch saleSearch = new SaleSearch();

        return saleSearch;
    }
}
