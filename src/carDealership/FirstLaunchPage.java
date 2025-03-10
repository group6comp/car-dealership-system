package carDealership;

import static carDealership.User.Role.ADMIN;

import javax.swing.JPanel;
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

/**
 * The FirstLaunchPage class represents the initial setup page for the dealership system.
 * It allows the user to enter dealership information and create an admin account.
 */
public class FirstLaunchPage extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField dealershipNameField;
    private JTextField locationField;
    private JTextField inventoryCapacityField;
    private JTextField adminUsernameField;
    private JPasswordField adminPasswordField;

    /**
     * Create the panel.
     */
    public FirstLaunchPage() {
        setLayout(new CardLayout());

        // Create the main panel and admin panel
        JPanel mainPanel = createMainPanel();
        JPanel adminPanel = createAdminPanel();

        // Add the panels to the CardLayout
        add(mainPanel, "mainPanel");
        add(adminPanel, "adminPanel");

        // Show the first launch panel
        Main.contentPane.add(this, "firstLaunchPanel");
        Main.cardLayout.show(Main.contentPane, "firstLaunchPanel");
    }

    /**
     * Create the main panel for entering dealership information.
     * 
     * @return the main panel
     */
    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 230));
        panel.setLayout(null);

        // Create and configure the side panel
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(6, 6, 6));
        sidePanel.setBounds(0, 0, 277, 377);
        panel.add(sidePanel);
        sidePanel.setLayout(null);

        // Add background image to the side panel
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(FirstLaunchPage.class.getResource("/images/bg.jpg")));
        lblNewLabel_1.setBounds(-242, 0, 509, 256);
        sidePanel.add(lblNewLabel_1);

        // Add "Get Started" label to the side panel
        JLabel lblNewLabel_2 = new JLabel("Get Started");
        lblNewLabel_2.setFont(new Font("HP Simplified Hans", Font.PLAIN, 20));
        lblNewLabel_2.setForeground(new Color(192, 192, 192));
        lblNewLabel_2.setBounds(83, 299, 153, 42);
        sidePanel.add(lblNewLabel_2);

        // Create and configure the "Next" button
        JButton btnNextButton = new JButton("Next");
        btnNextButton.setForeground(Color.WHITE);
        btnNextButton.setBackground(new Color(241, 57, 83));
        btnNextButton.setBounds(356, 277, 203, 30);
        panel.add(btnNextButton);

        // Create and configure the dealership name field
        dealershipNameField = new JTextField();
        dealershipNameField.setBounds(319, 72, 291, 30);
        panel.add(dealershipNameField);
        dealershipNameField.setColumns(10);

        // Add label for dealership name
        JLabel lblNewLabel = new JLabel("Dealership Name");
        lblNewLabel.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblNewLabel.setBounds(319, 40, 151, 22);
        panel.add(lblNewLabel);

        // Create and configure the inventory capacity field
        inventoryCapacityField = new JTextField();
        inventoryCapacityField.setColumns(10);
        inventoryCapacityField.setBounds(319, 217, 291, 30);
        panel.add(inventoryCapacityField);

        // Add label for location
        JLabel lblLocation = new JLabel("Location");
        lblLocation.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblLocation.setBounds(319, 112, 151, 22);
        panel.add(lblLocation);

        // Create and configure the location field
        locationField = new JTextField();
        locationField.setColumns(10);
        locationField.setBounds(319, 149, 291, 30);
        panel.add(locationField);

        // Add label for inventory capacity
        JLabel lblInventoryCapacity = new JLabel("Inventory Capacity");
        lblInventoryCapacity.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblInventoryCapacity.setBounds(319, 185, 151, 22);
        panel.add(lblInventoryCapacity);

        // Add error labels for inventory capacity validation
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

        // Add action listener to the "Next" button
        btnNextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int capacity = Integer.parseInt(inventoryCapacityField.getText());
                    Main.createDealership(dealershipNameField.getText(), locationField.getText(), capacity);
                    CardLayout cl = (CardLayout) (FirstLaunchPage.this.getLayout());
                    cl.show(FirstLaunchPage.this, "adminPanel");
                } catch (NumberFormatException ex) {
                    inventoryCapacityField.setText("");
                    lblNewLabel_4.setVisible(false);
                    lblNewLabel_3.setVisible(true);
                }
            }
        });

        return panel;
    }

    /**
     * Create the admin panel for creating an admin account.
     * 
     * @return the admin panel
     */
    private JPanel createAdminPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 230));
        panel.setLayout(null);

        // Create and configure the side panel
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(6, 6, 6));
        sidePanel.setBounds(0, 0, 277, 377);
        panel.add(sidePanel);
        sidePanel.setLayout(null);

        // Add background image to the side panel
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(FirstLaunchPage.class.getResource("/images/bg.jpg")));
        lblNewLabel_1.setBounds(-242, 0, 509, 256);
        sidePanel.add(lblNewLabel_1);

        // Add "Get Started" label to the side panel
        JLabel lblNewLabel_2 = new JLabel("Get Started");
        lblNewLabel_2.setFont(new Font("HP Simplified Hans", Font.PLAIN, 20));
        lblNewLabel_2.setForeground(new Color(192, 192, 192));
        lblNewLabel_2.setBounds(83, 299, 153, 42);
        sidePanel.add(lblNewLabel_2);

        // Add label for admin username
        JLabel lblAdminUsername = new JLabel("Admin Username");
        lblAdminUsername.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblAdminUsername.setBounds(319, 72, 151, 22);
        panel.add(lblAdminUsername);

        // Create and configure the admin username field
        adminUsernameField = new JTextField();
        adminUsernameField.setColumns(10);
        adminUsernameField.setBounds(319, 104, 291, 30);
        panel.add(adminUsernameField);

        // Add label for admin password
        JLabel lblAdminPassword = new JLabel("Admin Password");
        lblAdminPassword.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblAdminPassword.setBounds(319, 144, 151, 22);
        panel.add(lblAdminPassword);

        // Create and configure the admin password field
        adminPasswordField = new JPasswordField();
        adminPasswordField.setBounds(319, 176, 291, 30);
        panel.add(adminPasswordField);

        // Create and configure the "Go" button
        JButton btnGoButton = new JButton("Go");
        btnGoButton.setForeground(Color.WHITE);
        btnGoButton.setBackground(new Color(241, 57, 83));
        btnGoButton.setBounds(356, 227, 203, 30);
        panel.add(btnGoButton);

        // Add action listener to the "Go" button
        btnGoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User adminUser = new User(adminUsernameField.getText(), new String(adminPasswordField.getPassword()), ADMIN);
                Main.m_dealership.addUser(adminUser);
                Main.showMainUI();
            }
        });

        return panel;
    }
}