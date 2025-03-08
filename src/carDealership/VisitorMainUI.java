package carDealership;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.io.IOException;

public class VisitorMainUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private JPanel loginPanel;

    /**
     * Create the panel.
     */
    public VisitorMainUI() {
        setLayout(new CardLayout());

        mainPanel = createMainPanel();
        loginPanel = createLoginPanel();

        add(mainPanel, "mainPanel");
        add(loginPanel, "loginPanel");

        Main.contentPane.add(this, "visitorPanel");
        Main.cardLayout.show(Main.contentPane, "visitorPanel");
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
        lblNewLabel_1.setIcon(new ImageIcon(VisitorMainUI.class.getResource("/images/bg.jpg")));
        lblNewLabel_1.setBounds(-242, 0, 509, 256);
        sidePanel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Get Started");
        lblNewLabel_2.setFont(new Font("HP Simplified Hans", Font.PLAIN, 20));
        lblNewLabel_2.setForeground(new Color(192, 192, 192));
        lblNewLabel_2.setBounds(83, 299, 200, 42);
        sidePanel.add(lblNewLabel_2);

        JButton btnBrowseInventory = new JButton("Browse Inventory");
        btnBrowseInventory.setForeground(Color.WHITE);
        btnBrowseInventory.setBackground(new Color(241, 57, 83));
        btnBrowseInventory.setBounds(356, 100, 203, 30);
        panel.add(btnBrowseInventory);

        JButton btnSearch = new JButton("Search");
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBackground(new Color(241, 57, 83));
        btnSearch.setBounds(356, 150, 203, 30);
        panel.add(btnSearch);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setBackground(new Color(241, 57, 83));
        btnSignUp.setBounds(356, 200, 203, 30);
        panel.add(btnSignUp);

        JButton btnLogin = new JButton("Login");
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(241, 57, 83));
        btnLogin.setBounds(356, 250, 203, 30);
        panel.add(btnLogin);

        btnBrowseInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Browse Inventory functionality
                System.out.println("Browse Inventory clicked");
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Search functionality
                System.out.println("Search clicked");
            }
        });

        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Sign Up functionality
                System.out.println("Sign Up clicked");
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (VisitorMainUI.this.getLayout());
                cl.show(VisitorMainUI.this, "loginPanel");
            }
        });

        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 230));
        panel.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblUsername.setBounds(50, 50, 100, 30);
        panel.add(lblUsername);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        panel.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblPassword.setBounds(50, 100, 100, 30);
        panel.add(lblPassword);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        panel.add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(241, 57, 83));
        btnLogin.setBounds(150, 150, 200, 30);
        panel.add(btnLogin);

        JLabel lblErrorMessage = new JLabel("Invalid username or password");
        lblErrorMessage.setForeground(new Color(255, 128, 128));
        lblErrorMessage.setBounds(150, 190, 200, 30);
        panel.add(lblErrorMessage);
        lblErrorMessage.setVisible(false);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (validateLogin(username, password) != null) {
                    System.out.println("Login successful!");
                    Main.user = validateLogin(username, password);
                    Main.showRoleUI();
                } else {
                    lblErrorMessage.setVisible(true);
                }
            }
        });

        return panel;
    }

    public User validateLogin(String username, String password) {
        User user = Main.m_dealership.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}