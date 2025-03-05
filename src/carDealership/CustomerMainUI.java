package carDealership;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class CustomerMainUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public CustomerMainUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(CustomerMainUI.class.getResource("/images/icon.jpg")));
        setTitle("Customer Main Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Customer Main Interface");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(200, 20, 250, 30);
        contentPane.add(lblTitle);

        JButton btnBrowseInventory = new JButton("Browse Inventory");
        btnBrowseInventory.setForeground(Color.WHITE);
        btnBrowseInventory.setBackground(new Color(241, 57, 83));
        btnBrowseInventory.setBounds(225, 80, 200, 30);
        contentPane.add(btnBrowseInventory);

        JButton btnSearch = new JButton("Search");
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBackground(new Color(241, 57, 83));
        btnSearch.setBounds(225, 130, 200, 30);
        contentPane.add(btnSearch);

        JButton btnWishlist = new JButton("Wishlist");
        btnWishlist.setForeground(Color.WHITE);
        btnWishlist.setBackground(new Color(241, 57, 83));
        btnWishlist.setBounds(225, 180, 200, 30);
        contentPane.add(btnWishlist);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBackground(new Color(241, 57, 83));
        btnLogout.setBounds(225, 230, 200, 30);
        contentPane.add(btnLogout);

        JLabel lblRecommended = new JLabel("Recommended Vehicles: SUVs, Sedans, Trucks");
        lblRecommended.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblRecommended.setForeground(new Color(255, 128, 128));
        lblRecommended.setBounds(150, 280, 350, 30);
        contentPane.add(lblRecommended);

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

        btnWishlist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Wishlist functionality
                System.out.println("Wishlist clicked");
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Logout functionality
                System.out.println("Logout clicked");
                dispose();
                // new LoginPage(); // Uncomment and replace with actual login page
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerMainUI();
    }
}