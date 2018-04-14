package views;

import main.UserType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserSelectView extends JFrame {

    private JPanel contentPane;
    private JButton btnBegin;
    private JComboBox<UserType> comboBox;
    private JLabel lblSelectAUser;

    /**
     * Create the frame.
     */
    public UserSelectView() {
        setResizable(false);
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
