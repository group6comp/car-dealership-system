package carDealership;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public LoginPanel() {
        setBackground(new Color(230, 230, 230));
        setLayout(new GridBagLayout()); // Use GridBagLayout for centering

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(230, 230, 230));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 15, 10); // Increased spacing
        gbc.anchor = GridBagConstraints.CENTER;

        // Header label (larger font)
        JLabel lblHeader = new JLabel("User Login");
        lblHeader.setFont(new Font("Dubai Medium", Font.BOLD, 22)); // Bigger and bold
        formPanel.add(lblHeader, gbc);

        // Reset grid width for form fields
        gbc.gridwidth = 1;
        gbc.gridy++;

        // Username label
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Dubai Medium", Font.PLAIN, 16)); // Bigger font
        formPanel.add(lblUsername, gbc);

        // Username text field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField usernameField = new JTextField(15);
        usernameField.setFont(new Font("Dubai Medium", Font.PLAIN, 16)); // Bigger text
        usernameField.setPreferredSize(new Dimension(220, 30)); // Increased height
        formPanel.add(usernameField, gbc);

        // Password label
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Dubai Medium", Font.PLAIN, 16)); // Bigger font
        formPanel.add(lblPassword, gbc);

        // Password field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Dubai Medium", Font.PLAIN, 16)); // Bigger text
        passwordField.setPreferredSize(new Dimension(220, 30)); // Increased height
        formPanel.add(passwordField, gbc);

        // Error message label
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel lblErrorMessage = new JLabel("Invalid username or password");
        lblErrorMessage.setFont(new Font("Dubai Medium", Font.PLAIN, 14)); // Bigger error text
        lblErrorMessage.setForeground(new Color(255, 80, 80)); // Slightly darker red
        lblErrorMessage.setVisible(false);
        formPanel.add(lblErrorMessage, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 5)); // More spacing
        buttonPanel.setBackground(new Color(230, 230, 230));

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Dubai Medium", Font.BOLD, 16)); // Bigger button text
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(241, 57, 83));
        btnLogin.setPreferredSize(new Dimension(130, 35)); // Bigger button

        buttonPanel.add(btnLogin);

        // Add button panel to form
        gbc.gridy++;
        formPanel.add(buttonPanel, gbc);

        // Add form panel to main panel
        add(formPanel);

        // Action Listeners
        btnLogin.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (validateLogin(username, password) != null) {
                Main.user = Main.m_dealership.getUser(username);
                Main.role = Main.user.getRole();
                Main.showMainUI();
            } else {
                lblErrorMessage.setVisible(true);
            }
        });
    }

    public User validateLogin(String username, String password) {
        User user = Main.m_dealership.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
