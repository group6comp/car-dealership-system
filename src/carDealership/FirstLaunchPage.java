package carDealership;

import static carDealership.User.Role.ADMIN;

import carDealership.IllegalCapacityException;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import javax.swing.JPasswordField;
import java.io.IOException;

public class FirstLaunchPage extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField adminUsernameField;
    private JPasswordField adminPasswordField;

    /**
     * Create the panel.
     */
    public FirstLaunchPage() {
        setLayout(new CardLayout());

        JPanel mainPanel = createMainPanel();
        JPanel adminPanel = createAdminPanel();

        add(mainPanel, "mainPanel");
        add(adminPanel, "adminPanel");

        Main.contentPane.add(this, "firstLaunchPanel");
        Main.cardLayout.show(Main.contentPane, "firstLaunchPanel");
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 230));
        panel.setLayout(null);

        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(6, 6, 6));
        sidePanel.setBounds(0, 0, 277, 377);
        panel.add(sidePanel);
        sidePanel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(FirstLaunchPage.class.getResource("/images/bg.jpg")));
        lblNewLabel_1.setBounds(-242, 0, 509, 256);
        sidePanel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Get Started");
        lblNewLabel_2.setFont(new Font("HP Simplified Hans", Font.PLAIN, 20));
        lblNewLabel_2.setForeground(new Color(192, 192, 192));
        lblNewLabel_2.setBounds(83, 299, 153, 42);
        sidePanel.add(lblNewLabel_2);

        JButton btnNextButton = new JButton("Next");
        btnNextButton.setForeground(Color.WHITE);
        btnNextButton.setBackground(new Color(241, 57, 83));
        btnNextButton.setBounds(356, 277, 203, 30);
        panel.add(btnNextButton);

        textField = new JTextField();
        textField.setBounds(319, 72, 291, 30);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Dealership Name");
        lblNewLabel.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblNewLabel.setBounds(319, 40, 151, 22);
        panel.add(lblNewLabel);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(319, 217, 291, 30);
        panel.add(textField_1);

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblLocation.setBounds(319, 112, 151, 22);
        panel.add(lblLocation);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(319, 149, 291, 30);
        panel.add(textField_2);

        JLabel lblInventoryCapacity = new JLabel("Inventory Capacity");
        lblInventoryCapacity.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblInventoryCapacity.setBounds(319, 185, 151, 22);
        panel.add(lblInventoryCapacity);

        JLabel lblNewLabel_3 = new JLabel("Inventory Capacity must be an integer");
        lblNewLabel_3.setForeground(new Color(255, 128, 128));
        lblNewLabel_3.setBounds(319, 249, 291, 16);
        panel.add(lblNewLabel_3);
        lblNewLabel_3.setVisible(false);

        JLabel lblNewLabel_4 = new JLabel("Capacity must be between 1 - 100");
        lblNewLabel_4.setForeground(new Color(255, 128, 128));
        lblNewLabel_4.setBounds(319, 249, 266, 16);
        panel.add(lblNewLabel_4);
        lblNewLabel_4.setVisible(false);

        btnNextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int capacity = Integer.parseInt(textField_1.getText());
                    if (capacity < 1 || capacity > 100) {
                        throw new IllegalCapacityException();
                    }
                    Main.createDealership(textField.getText(), textField_2.getText(), capacity);
                    CardLayout cl = (CardLayout) (FirstLaunchPage.this.getLayout());
                    cl.show(FirstLaunchPage.this, "adminPanel");
                } catch (NumberFormatException ex) {
                    textField_1.setText("");
                    lblNewLabel_4.setVisible(false);
                    lblNewLabel_3.setVisible(true);
                } catch (IllegalCapacityException ex2) {
                    textField_1.setText("");
                    lblNewLabel_3.setVisible(false);
                    lblNewLabel_4.setVisible(true);
                }
            }
        });

        return panel;
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 230));
        panel.setLayout(null);

        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(6, 6, 6));
        sidePanel.setBounds(0, 0, 277, 377);
        panel.add(sidePanel);
        sidePanel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(FirstLaunchPage.class.getResource("/images/bg.jpg")));
        lblNewLabel_1.setBounds(-242, 0, 509, 256);
        sidePanel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Get Started");
        lblNewLabel_2.setFont(new Font("HP Simplified Hans", Font.PLAIN, 20));
        lblNewLabel_2.setForeground(new Color(192, 192, 192));
        lblNewLabel_2.setBounds(83, 299, 153, 42);
        sidePanel.add(lblNewLabel_2);

        JLabel lblAdminUsername = new JLabel("Admin Username");
        lblAdminUsername.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblAdminUsername.setBounds(319, 72, 151, 22);
        panel.add(lblAdminUsername);

        adminUsernameField = new JTextField();
        adminUsernameField.setColumns(10);
        adminUsernameField.setBounds(319, 104, 291, 30);
        panel.add(adminUsernameField);

        JLabel lblAdminPassword = new JLabel("Admin Password");
        lblAdminPassword.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblAdminPassword.setBounds(319, 144, 151, 22);
        panel.add(lblAdminPassword);

        adminPasswordField = new JPasswordField();
        adminPasswordField.setBounds(319, 176, 291, 30);
        panel.add(adminPasswordField);

        JButton btnGoButton = new JButton("Go");
        btnGoButton.setForeground(Color.WHITE);
        btnGoButton.setBackground(new Color(241, 57, 83));
        btnGoButton.setBounds(356, 227, 203, 30);
        panel.add(btnGoButton);

        btnGoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User adminUser = new User(adminUsernameField.getText(), new String(adminPasswordField.getPassword()), ADMIN);
                Main.m_dealership.addUser(adminUser);
                try {
                    Main.save();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                new VisitorMainUI();
            }
        });

        return panel;
    }
}