package carDealership;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class ManagerMainUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout = new CardLayout();

    /**
     * Create the frame.
     */
    public ManagerMainUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerMainUI.class.getResource("/images/icon.jpg")));
        setTitle("Manager Main Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Manager Main Interface");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(200, 20, 250, 30);
        contentPane.add(lblTitle);

        JButton btnViewInventory = new JButton("View Inventory");
        btnViewInventory.setForeground(Color.WHITE);
        btnViewInventory.setBackground(new Color(241, 57, 83));
        btnViewInventory.setBounds(225, 80, 200, 30);
        contentPane.add(btnViewInventory);

        JButton btnManageInventory = new JButton("Manage Inventory");
        btnManageInventory.setForeground(Color.WHITE);
        btnManageInventory.setBackground(new Color(241, 57, 83));
        btnManageInventory.setBounds(225, 130, 200, 30);
        contentPane.add(btnManageInventory);

        JButton btnViewSales = new JButton("View Sales");
        btnViewSales.setForeground(Color.WHITE);
        btnViewSales.setBackground(new Color(241, 57, 83));
        btnViewSales.setBounds(225, 180, 200, 30);
        contentPane.add(btnViewSales);

        JButton btnGenerateReports = new JButton("Generate Reports");
        btnGenerateReports.setForeground(Color.WHITE);
        btnGenerateReports.setBackground(new Color(241, 57, 83));
        btnGenerateReports.setBounds(225, 230, 200, 30);
        contentPane.add(btnGenerateReports);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBackground(new Color(241, 57, 83));
        btnLogout.setBounds(225, 280, 200, 30);
        contentPane.add(btnLogout);

        JLabel lblKeyStats = new JLabel("Key Stats: 10 vehicles in inventory, 5 sales this month");
        lblKeyStats.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblKeyStats.setForeground(new Color(255, 128, 128));
        lblKeyStats.setBounds(150, 330, 350, 30);
        contentPane.add(lblKeyStats);

        btnViewInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement View Inventory functionality
                System.out.println("View Inventory clicked");
            }
        });

        btnManageInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (contentPane.getComponentCount() > 0 && contentPane.getComponent(0) instanceof ManageInventoryPanel) {
                    cardLayout.show(contentPane, "manageInventoryPanel");
                } else {
                    contentPane.add(new ManageInventoryPanel(contentPane, cardLayout), "manageInventoryPanel");
                    cardLayout.show(contentPane, "manageInventoryPanel");
                }
            }
        });

        btnViewSales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement View Sales functionality
                System.out.println("View Sales clicked");
            }
        });

        btnGenerateReports.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Generate Reports functionality
                System.out.println("Generate Reports clicked");
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
        new ManagerMainUI();
    }
}