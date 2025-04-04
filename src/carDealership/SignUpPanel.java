package carDealership;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

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
                String password = generateRandomPassword();

                // Create the new user
                User newUser = new User(username, password, User.Role.CUSTOMER);
                Main.m_dealership.addUser(newUser);

                // Send an email to the user with their credentials
                sendCredentialsEmail(email, username, password);

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

    /**
     * Sends a welcome email to the new user containing their login credentials.
     * 
     * @param recipientEmail The email address of the new user
     * @param username       The username of the new user
     * @param password       The generated password
     */
    private void sendCredentialsEmail(String recipientEmail, String username, String password) {
        try {
            // Email configuration - should be moved to a configuration file
            final String senderEmail = "lefrancmathis@gmail.com";

            // Store credentials securely, e.g., in environment variables or a secure vault
            final String senderPassword = "llhsvaasliosvxtz"; // no spaces!

            // Set mail server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            // Create a session with authentication
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Welcome to Our Dealership!");

            // Create the email body
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("Dear ").append(username).append(",\n\n");
            emailBody.append("Welcome to Our Dealership! Here are your login credentials:\n");
            emailBody.append("Username: ").append(username).append("\n");
            emailBody.append("Password: ").append(password).append("\n\n");
            emailBody.append("Best regards,\n");
            emailBody.append("The Dealership Team");

            // Set the email body
            message.setText(emailBody.toString());
            // Set the email content type
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            // Set the email encoding
            message.setHeader("Content-Transfer-Encoding", "8bit");

            // Send message
            Transport.send(message);

            System.out.println("Welcome email sent successfully to " + recipientEmail);

        } catch (Exception ex) {
            System.err.println("Failed to send welcome email: " + ex.getMessage());
            ex.printStackTrace();

            // Log the error properly rather than just printing to console
            // logger.error("Failed to send welcome email to " + recipientEmail, ex);

            // Optionally show an error message to the user
            JOptionPane.showMessageDialog(null,
                    "Account created but failed to send email. Please contact support.",
                    "Email Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Generates a random password with at least one uppercase letter, one lowercase
     * letter,
     * one number, and one special character.
     * 
     * @return A secure random password
     */
    private String generateRandomPassword() {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()-_=+";

        String allChars = upperAlphabet + lowerAlphabet + numbers + specialChars;
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        // Ensure at least one character from each category
        password.append(upperAlphabet.charAt(random.nextInt(upperAlphabet.length())));
        password.append(lowerAlphabet.charAt(random.nextInt(lowerAlphabet.length())));
        password.append(numbers.charAt(random.nextInt(numbers.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // Add additional random characters to reach desired length (10 characters)
        for (int i = 0; i < 6; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Shuffle the password to avoid predictable pattern
        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int j = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }

        return new String(passwordArray);
    }
}
