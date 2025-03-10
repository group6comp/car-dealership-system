package carDealership;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LoginPanel class represents the login panel for the dealership system.
 * It allows users to enter their username and password to log in.
 */
public class LoginPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for creating the LoginPanel.
     */
    public LoginPanel() {

        setBackground(new Color(230, 230, 230));
        setLayout(null);

        // Add header label
        JLabel lblHeader = new JLabel("User Login");
        lblHeader.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblHeader.setBounds(150, 10, 200, 30);
        add(lblHeader);

        // Add username label and text field
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblUsername.setBounds(50, 50, 100, 30);
        add(lblUsername);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        add(usernameField);
        usernameField.setColumns(10);

        // Add password label and password field
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblPassword.setBounds(50, 100, 100, 30);
        add(lblPassword);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        add(passwordField);

        // Add login button
        JButton btnLogin = new JButton("Login");
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(241, 57, 83));
        btnLogin.setBounds(150, 150, 90, 30);
        add(btnLogin);

        // Add back button
        JButton btnBack = new JButton("Back");
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(241, 57, 83));
        btnBack.setBounds(260, 150, 90, 30);
        add(btnBack);

        // Add error message label
        JLabel lblErrorMessage = new JLabel("Invalid username or password");
        lblErrorMessage.setForeground(new Color(255, 128, 128));
        lblErrorMessage.setBounds(150, 190, 200, 30);
        add(lblErrorMessage);
        lblErrorMessage.setVisible(false);

        // Add action listener to the login button
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (validateLogin(username, password) != null) {
                    System.out.println("Login successful!");
                    Main.user = Main.m_dealership.getUser(username);
                    Main.role = Main.user.getRole();
                    Main.showMainUI();
                } else {
                    lblErrorMessage.setVisible(true);
                }
            }
        });

        // Add action listener to the back button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.showMainUI();
            }
        });
    }

    /**
     * Validate the login credentials.
     * 
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @return the User object if the credentials are valid, null otherwise
     */
    public User validateLogin(String username, String password) {
        User user = Main.m_dealership.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}