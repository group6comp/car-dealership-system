package carDealership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private static DealershipNew dealershipNew;

    public MainUI() {
        setTitle("Car Dealership System - Login w/ BG");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load the background image for the login
        ImageIcon bgIcon = new ImageIcon("src/bg.jpg"); // your path
        Image bgImage = bgIcon.getImage();
        BgPanel backgroundPanel = new BgPanel(bgImage);
        backgroundPanel.setLayout(new GridLayout(3,1));
        setContentPane(backgroundPanel);

        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.setOpaque(false);
        userPanel.add(new JLabel("Username: "));
        usernameField = new JTextField(10);
        userPanel.add(usernameField);

        JPanel passPanel = new JPanel(new FlowLayout());
        passPanel.setOpaque(false);
        passPanel.add(new JLabel("Password: "));
        passwordField = new JPasswordField(10);
        passPanel.add(passwordField);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setOpaque(false);
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        btnPanel.add(loginButton);

        backgroundPanel.add(userPanel);
        backgroundPanel.add(passPanel);
        backgroundPanel.add(btnPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User u = UserData.validateLogin(username, password);
            if(u == null) {
                JOptionPane.showMessageDialog(this, "Invalid username/password!");
            } else {
                JOptionPane.showMessageDialog(this, "Login success! Role: " + u.getRole());
                dispose();
                showRoleUI(u);
            }
        }
    }

    private void showRoleUI(User u) {
        String role = u.getRole();
        switch(role.toLowerCase()) {
            case "admin":
                new AdminUI(dealershipNew).setVisible(true);
                break;
            case "manager":
                new ManagerUI(dealershipNew).setVisible(true);
                break;
            case "salesperson":
                new SalespersonUI(dealershipNew).setVisible(true);
                break;
            case "customer":
                new CustomerUI(dealershipNew).setVisible(true);
                break;
            default:
                new VisitorUI(dealershipNew).setVisible(true);
                break;
        }
    }

    public static void main(String[] args) {
        dealershipNew = new DealershipNew("My Dealer", "CityCenter");
        SampleDataLoader.loadSampleVehicles(dealershipNew);

        new MainUI();
    }
}
