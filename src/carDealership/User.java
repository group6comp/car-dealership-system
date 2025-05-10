package carDealership;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.mail.*;

import java.io.Serializable;

/**
 * The User class represents a user in the dealership system.
 * It includes details about the user's username, password, role, and wishlist.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String email;
    private String password;
    private Role role; // Admin, Manager, Salesperson, Customer
    private boolean isActive;
    private List<Vehicle> wishlist;

    /**
     * The Role enum represents the different roles a user can have.
     */
    public enum Role {
        ADMIN, MANAGER, SALESPERSON, CUSTOMER, VISITOR;

        @Override
        public String toString() {
            return switch (this) {
                case ADMIN -> "Admin";
                case MANAGER -> "Manager";
                case SALESPERSON -> "Salesperson";
                case CUSTOMER -> "Customer";
                case VISITOR -> "Visitor";
            };
        }
    }

    /**
     * Constructor for creating a User object.
     * 
     * @param username the username of the user
     * @param email    the email of the user
     * @param password the password of the user
     * @param role     the role of the user
     */
    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = true;
        this.wishlist = new ArrayList<>();
    }

    // Getters and setters for User attributes

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void resetPassword() {
        this.password = generateRandomPassword();
        User.sendEmail(this.email, "Password Reset", "Your new password is: " + password);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(String role) {
        this.role = roleFromString(role);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Get the user's wishlist.
     * 
     * @return the filtered wishlist
     */
    public List<Vehicle> getWishlist() {
        return wishlist;
    }

    /**
     * Add a vehicle to the user's wishlist.
     * 
     * @param vehicle the vehicle to add to the wishlist
     */
    public void addToWishlist(Vehicle vehicle) {
        wishlist.add(vehicle);
    }

    /**
     * Convert a string to a Role enum.
     * 
     * @param role the role as a string
     * @return the corresponding Role enum, or null if the role is invalid
     */
    public static Role roleFromString(String role) {
        if (role == null) {
            return null;
        }
        switch (role.toUpperCase()) {
            case "ADMIN":
                return Role.ADMIN;
            case "MANAGER":
                return Role.MANAGER;
            case "SALESPERSON":
                return Role.SALESPERSON;
            case "CUSTOMER":
                return Role.CUSTOMER;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return username;
    }

    /**
     * Generates a random password with at least one uppercase letter, one lowercase
     * letter,
     * one number, and one special character.
     * 
     * @return A secure random password
     */
    public static String generateRandomPassword() {
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

    /**
     * Send an email to the specified recipient with the given subject and message.
     * 
     * @param recipientEmail
     * @param subject
     * @param message
     */
    public static void sendEmail(String recipientEmail, String subject, String message) {
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
            Message message1 = new MimeMessage(session);
            message1.setFrom(new InternetAddress(senderEmail));
            message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message1.setSubject(subject);
            message1.setText(message);

            // Send message
            Transport.send(message1);

            System.out.println("Email sent successfully to " + recipientEmail);

        } catch (Exception ex) {
            System.err.println("Failed to send email: " + ex.getMessage());
            ex.printStackTrace();

            // Log the error properly rather than just printing to console
            // logger.error("Failed to send email to " + recipientEmail, ex);

            // Optionally show an error message to the user
            JOptionPane.showMessageDialog(null,
                    "Failed to send email. Please contact support.",
                    "Email Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}