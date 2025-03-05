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

public class AdminMainUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;

    /**
     * Create the frame.
     */
    public AdminMainUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(AdminMainUI.class.getResource("/images/icon.jpg")));
        setTitle("Admin Main Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        setContentPane(contentPane);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setLayout(null);

        JLabel lblTitle = new JLabel("Admin Main Interface");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(200, 20, 250, 30);
        mainPanel.add(lblTitle);

        JButton btnManageUsers = new JButton("Manage Users");
        btnManageUsers.setForeground(Color.WHITE);
        btnManageUsers.setBackground(new Color(241, 57, 83));
        btnManageUsers.setBounds(225, 80, 200, 30);
        mainPanel.add(btnManageUsers);

        JButton btnManageInventory = new JButton("Manage Inventory");
        btnManageInventory.setForeground(Color.WHITE);
        btnManageInventory.setBackground(new Color(241, 57, 83));
        btnManageInventory.setBounds(225, 130, 200, 30);
        mainPanel.add(btnManageInventory);

        JButton btnViewSales = new JButton("View Sales");
        btnViewSales.setForeground(Color.WHITE);
        btnViewSales.setBackground(new Color(241, 57, 83));
        btnViewSales.setBounds(225, 180, 200, 30);
        mainPanel.add(btnViewSales);

        JButton btnGenerateReports = new JButton("Generate Reports");
        btnGenerateReports.setForeground(Color.WHITE);
        btnGenerateReports.setBackground(new Color(241, 57, 83));
        btnGenerateReports.setBounds(225, 230, 200, 30);
        mainPanel.add(btnGenerateReports);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBackground(new Color(241, 57, 83));
        btnLogout.setBounds(225, 280, 200, 30);
        mainPanel.add(btnLogout);

        JLabel lblPendingTasks = new JLabel("Pending Tasks: 5 new user requests");
        lblPendingTasks.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblPendingTasks.setForeground(new Color(255, 128, 128));
        lblPendingTasks.setBounds(200, 330, 250, 30);
        mainPanel.add(lblPendingTasks);

        contentPane.add(mainPanel, "mainPanel");

        btnManageUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.add(new ManageUsersPanel(contentPane, cardLayout, Main.m_dealership), "manageUsersPanel");
                cardLayout.show(contentPane, "manageUsersPanel");
            }
        });

        btnManageInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.add(new ManageInventoryPanel(contentPane, cardLayout), "manageInventoryPanel");
                cardLayout.show(contentPane, "manageInventoryPanel");
            }
        });

        btnViewSales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.add(new ViewSalesPanel(contentPane, cardLayout), "viewSalesPanel");
                cardLayout.show(contentPane, "viewSalesPanel");
            }
        });

        btnGenerateReports.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.add(new GenerateReportsPanel(contentPane, cardLayout), "generateReportsPanel");
                cardLayout.show(contentPane, "generateReportsPanel");
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VisitorMainUI();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminMainUI();
    }
}