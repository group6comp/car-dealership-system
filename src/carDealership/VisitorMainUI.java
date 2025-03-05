package carDealership;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisitorMainUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;

    /**
     * Create the frame.
     */
    public VisitorMainUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VisitorMainUI.class.getResource("/images/icon.jpg")));
        setTitle("Dealership System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(6, 6, 6));
        panel.setBounds(0, 0, 277, 377);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(VisitorMainUI.class.getResource("/images/bg.jpg")));
        lblNewLabel_1.setBounds(-242, 0, 509, 256);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Get Started");
        lblNewLabel_2.setFont(new Font("HP Simplified Hans", Font.PLAIN, 20));
        lblNewLabel_2.setForeground(new Color(192, 192, 192));
        lblNewLabel_2.setBounds(83, 299, 153, 42);
        panel.add(lblNewLabel_2);

        JButton btnBrowseInventory = new JButton("Browse Inventory");
        btnBrowseInventory.setForeground(Color.WHITE);
        btnBrowseInventory.setBackground(new Color(241, 57, 83));
        btnBrowseInventory.setBounds(356, 100, 203, 30);
        contentPane.add(btnBrowseInventory);

        JButton btnSearch = new JButton("Search");
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBackground(new Color(241, 57, 83));
        btnSearch.setBounds(356, 150, 203, 30);
        contentPane.add(btnSearch);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setBackground(new Color(241, 57, 83));
        btnSignUp.setBounds(356, 200, 203, 30);
        contentPane.add(btnSignUp);

        JButton btnLogin = new JButton("Login");
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(241, 57, 83));
        btnLogin.setBounds(356, 250, 203, 30);
        contentPane.add(btnLogin);

        btnBrowseInventory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Implement Browse Inventory functionality
                System.out.println("Browse Inventory clicked");
            }
        });

        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Implement Search functionality
                System.out.println("Search clicked");
            }
        });

        btnSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Implement Sign Up functionality
                System.out.println("Sign Up clicked");
            }
        });

        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Implement Login functionality
                LoginPage();
                Main.showRoleUI();
                dispose();
            }
        });

        setVisible(true);
    }

    public void LoginPage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VisitorMainUI.class.getResource("/images/icon.jpg")));
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblUsername.setBounds(50, 50, 100, 30);
        contentPane.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblPassword.setBounds(50, 100, 100, 30);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(241, 57, 83));
        btnLogin.setBounds(150, 150, 200, 30);
        contentPane.add(btnLogin);

        JLabel lblErrorMessage = new JLabel("Invalid username or password");
        lblErrorMessage.setForeground(new Color(255, 128, 128));
        lblErrorMessage.setBounds(150, 190, 200, 30);
        contentPane.add(lblErrorMessage);
        lblErrorMessage.setVisible(false);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (UserData.validateLogin(username, password) != null) {
                    System.out.println("Login successful!");
                    Main.user = UserData.getUser(username);
                    Main.showRoleUI();
                } else {
                    lblErrorMessage.setVisible(true);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new VisitorMainUI();
    }
}
