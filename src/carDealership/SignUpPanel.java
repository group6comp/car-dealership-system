package carDealership;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The SignUpPanel class represents the sign-up panel for the dealership system.
 * It allows users to create a new account by entering a username and password.
 */
public class SignUpPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for creating the SignUpPanel.
     */
    public SignUpPanel() {

        setBackground(new Color(230, 230, 230));
        setLayout(null);

        // Add header label
        JLabel lblHeader = new JLabel("Create an Account");
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

        // Add sign-up button
        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setBackground(new Color(241, 57, 83));
        btnSignUp.setBounds(150, 150, 90, 30);
        add(btnSignUp);

        // Add back button
        JButton btnBack = new JButton("Back");
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(241, 57, 83));
        btnBack.setBounds(260, 150, 90, 30);
        add(btnBack);

        // Add error message label
        JLabel lblErrorMessage = new JLabel("Username already taken");
        lblErrorMessage.setForeground(new Color(255, 128, 128));
        lblErrorMessage.setBounds(150, 190, 200, 30);
        add(lblErrorMessage);
        lblErrorMessage.setVisible(false);

        // Add action listener to the sign-up button
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if the username is already taken
                if (Main.m_dealership.getUser(username) == null) {
                    // Create a new user and add to the dealership
                    User newUser = new User(username, password, User.Role.CUSTOMER);
                    Main.m_dealership.addUser(newUser);
                    JOptionPane.showMessageDialog(null, "Account successfully created! You are now being logged in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Sign up successful!");
                    Main.user = newUser;
                    Main.role = newUser.getRole();
                    Main.showMainUI();
                } else {
                    // Show error message if username is taken
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
}