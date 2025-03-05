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

public class SalespersonMainUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public SalespersonMainUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(SalespersonMainUI.class.getResource("/images/icon.jpg")));
        setTitle("Salesperson Main Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Salesperson Main Interface");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(200, 20, 250, 30);
        contentPane.add(lblTitle);

        JButton btnViewInventory = new JButton("View Inventory");
        btnViewInventory.setForeground(Color.WHITE);
        btnViewInventory.setBackground(new Color(241, 57, 83));
        btnViewInventory.setBounds(225, 80, 200, 30);
        contentPane.add(btnViewInventory);

        JButton btnSellVehicle = new JButton("Sell Vehicle");
        btnSellVehicle.setForeground(Color.WHITE);
        btnSellVehicle.setBackground(new Color(241, 57, 83));
        btnSellVehicle.setBounds(225, 130, 200, 30);
        contentPane.add(btnSellVehicle);

        JButton btnAssistCustomer = new JButton("Assist Customer");
        btnAssistCustomer.setForeground(Color.WHITE);
        btnAssistCustomer.setBackground(new Color(241, 57, 83));
        btnAssistCustomer.setBounds(225, 180, 200, 30);
        contentPane.add(btnAssistCustomer);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBackground(new Color(241, 57, 83));
        btnLogout.setBounds(225, 230, 200, 30);
        contentPane.add(btnLogout);

        JLabel lblRealTimeInfo = new JLabel("Real-Time Stock Info: 10 vehicles available, Top Deal: 20% off on SUVs");
        lblRealTimeInfo.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblRealTimeInfo.setForeground(new Color(255, 128, 128));
        lblRealTimeInfo.setBounds(100, 280, 450, 30);
        contentPane.add(lblRealTimeInfo);

        btnViewInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement View Inventory functionality
                System.out.println("View Inventory clicked");
            }
        });

        btnSellVehicle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Sell Vehicle functionality
                System.out.println("Sell Vehicle clicked");
            }
        });

        btnAssistCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Assist Customer functionality
                System.out.println("Assist Customer clicked");
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
        new SalespersonMainUI();
    }
}