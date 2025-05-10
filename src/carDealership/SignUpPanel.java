package carDealership;

import javax.swing.*;
import java.awt.*;

public class SignUpPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public SignUpPanel() {
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
        JLabel lblHeader = new JLabel("Create an Account");
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

        // Email label
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Dubai Medium", Font.PLAIN, 16)); // Bigger font
        formPanel.add(lblEmail, gbc);

        // Email field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        JTextField emailField = new JTextField(15);
        emailField.setFont(new Font("Dubai Medium", Font.PLAIN, 16)); // Bigger text
        emailField.setPreferredSize(new Dimension(220, 30)); // Increased height
        formPanel.add(emailField, gbc);

        // Error message label
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel lblErrorMessage = new JLabel("Username already taken");
        lblErrorMessage.setFont(new Font("Dubai Medium", Font.PLAIN, 14)); // Bigger error text
        lblErrorMessage.setForeground(new Color(255, 80, 80)); // Slightly darker red
        lblErrorMessage.setVisible(false);
        formPanel.add(lblErrorMessage, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 5)); // More spacing
        buttonPanel.setBackground(new Color(230, 230, 230));

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setFont(new Font("Dubai Medium", Font.BOLD, 16)); // Bigger button text
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setBackground(new Color(241, 57, 83));
        btnSignUp.setPreferredSize(new Dimension(130, 35)); // Bigger button

        buttonPanel.add(btnSignUp);

        // Add button panel to form
        gbc.gridy++;
        formPanel.add(buttonPanel, gbc);

        // Add form panel to main panel
        add(formPanel);

        // Action Listeners
        btnSignUp.addActionListener(e -> {
            String username = usernameField.getText();
            String email = new String(emailField.getText());

            if (Main.m_dealership.getUser(username) == null) {
                // Generate a random password
                String password = User.generateRandomPassword();

                // Create the new user
                User newUser = new User(username, email, password, User.Role.CUSTOMER);
                Main.m_dealership.addUser(newUser);

                // Send an email to the user with their credentials
                User.sendEmail(email, "Welcome to Car Dealership!",
                        "Hello " + username + ",\n\n"
                                + "Welcome to Car Dealership! Here are your login credentials:\n"
                                + "Username: " + username + "\n"
                                + "Password: " + password + "\n\n"
                                + "Please log in to your account to start using our services.");

                // Show a success message
                JOptionPane.showMessageDialog(null,
                        "Account successfully created! An email has been sent to " + email
                                + " with your login credentials.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                Main.showMainUI();
            } else {
                lblErrorMessage.setVisible(true);
            }
        });
    }
}
