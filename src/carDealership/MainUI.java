package carDealership;

import carDealership.User.Role;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;

/**
 * The MainUI class represents the main user interface panel for the dealership system.
 * It displays different buttons and options based on the user's role.
 */
public class MainUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;

    /**
     * Constructor for creating the MainUI panel.
     */
    public MainUI() {
        contentPane = Main.contentPane;
        cardLayout = Main.cardLayout;

        setBackground(new Color(230, 230, 230));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        // Add title label
        JLabel lblTitle = new JLabel(Main.role.toString() + " Main Interface");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        add(buttonPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add buttons based on the user's role
        if (Main.role == Role.ADMIN) {
            addAdminButtons(buttonPanel, gbc);
        } else if (Main.role == Role.MANAGER) {
            addManagerButtons(buttonPanel, gbc);
        } else if (Main.role == Role.SALESPERSON) {
            addSalespersonButtons(buttonPanel, gbc);
        } else if (Main.role == Role.CUSTOMER) {
            addCustomerButtons(buttonPanel, gbc);
        } else if (Main.role == Role.VISITOR) {
            addGuestButtons(buttonPanel, gbc);
        }
    }

    /**
     * Add buttons for the admin role.
     */
    private void addAdminButtons(JPanel buttonPanel, GridBagConstraints gbc) {
        addButton("Manage Users", buttonPanel, gbc, 0, 0, ManageUsersPanel.class, "manageUsersPanel");
        addButton("Manage Inventory", buttonPanel, gbc, 0, 1, InventoryPanel.class, "manageInventoryPanel");
        addButton("View Sales", buttonPanel, gbc, 0, 2, ViewSalesPanel.class, "viewSalesPanel");
        addButton("Generate Reports", buttonPanel, gbc, 0, 3, GenerateReportsPanel.class, "generateReportsPanel");
        addButton("Logout", buttonPanel, gbc, 0, 4, e -> Main.logout());
    }

    /**
     * Add buttons for the manager role.
     */
    private void addManagerButtons(JPanel buttonPanel, GridBagConstraints gbc) {
        addButton("Approve Transactions", buttonPanel, gbc, 0, 0, ApproveTransactionsPanel.class, "approveTransactionsPanel");
        addButton("Manage Inventory", buttonPanel, gbc, 0, 1, InventoryPanel.class, "manageInventoryPanel");
        addButton("View Sales", buttonPanel, gbc, 0, 2, ViewSalesPanel.class, "viewSalesPanel");
        addButton("Generate Reports", buttonPanel, gbc, 0, 3, GenerateReportsPanel.class, "generateReportsPanel");
        addButton("Logout", buttonPanel, gbc, 0, 4, e -> Main.logout());
        
        // Add pending tasks label
        int pendingSales = Main.m_dealership.getPendingSales();
        JLabel lblPendingTasks = new JLabel("Pending Tasks: " + pendingSales + " pending sales transactions");
        lblPendingTasks.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblPendingTasks.setForeground(new Color(255, 128, 128));
        gbc.gridy = 5;
        buttonPanel.add(lblPendingTasks, gbc);
    }

    /**
     * Add buttons for the salesperson role.
     */
    private void addSalespersonButtons(JPanel buttonPanel, GridBagConstraints gbc) {
        addButton("View Inventory", buttonPanel, gbc, 0, 0, InventoryPanel.class, "viewInventoryPanel");
        addButton("Resolve Enquiries", buttonPanel, gbc, 0, 1, ManageEnquiriesPanel.class, "manageEnquiriesPanel");
        addButton("Sales History", buttonPanel, gbc, 0, 2, ViewSalesPanel.class, "salesHistoryPanel");
        addButton("Logout", buttonPanel, gbc, 0, 3, e -> Main.logout());

        // Add pending tasks label
        int pendingEnquiries = Main.m_dealership.getPendingEnquiries();
        JLabel lblPendingTasks = new JLabel("Pending Tasks: " + pendingEnquiries + " pending enquiries");
        lblPendingTasks.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblPendingTasks.setForeground(new Color(255, 128, 128));
        gbc.gridy = 5;
        buttonPanel.add(lblPendingTasks, gbc);
    }

    /**
     * Add buttons for the customer role.
     */
    private void addCustomerButtons(JPanel buttonPanel, GridBagConstraints gbc) {
        addButton("View Inventory", buttonPanel, gbc, 0, 0, InventoryPanel.class, "viewInventoryPanel");
        addButton("View Wishlist", buttonPanel, gbc, 0, 1, ViewWishlistPanel.class, "viewWishlistPanel");
        addButton("Logout", buttonPanel, gbc, 0, 2, e -> Main.logout());
    }

    /**
     * Add buttons for the guest role.
     */
    private void addGuestButtons(JPanel buttonPanel, GridBagConstraints gbc) {
        addButton("View Inventory", buttonPanel, gbc, 0, 0, InventoryPanel.class, "viewInventoryPanel");
        addButton("Sign Up", buttonPanel, gbc, 0, 1, SignUpPanel.class, "signUpPanel");
        addButton("Login", buttonPanel, gbc, 0, 2, LoginPanel.class, "loginPanel");
    }

    /**
     * Add a button to the panel that navigates to another panel.
     * 
     * @param text the text of the button
     * @param panel the panel to add the button to
     * @param gbc the GridBagConstraints for the button
     * @param x the x position of the button
     * @param y the y position of the button
     * @param panelClass the class of the panel to navigate to
     * @param panelName the name of the panel to navigate to
     */
    private void addButton(String text, JPanel panel, GridBagConstraints gbc, int x, int y, Class<? extends JPanel> panelClass, String panelName) {
        gbc.gridx = x;
        gbc.gridy = y;
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        panel.add(btn, gbc);
        btn.addActionListener(e -> showPanel(panelClass, panelName));
    }

    /**
     * Add a button to the panel with a custom action listener.
     * 
     * @param text the text of the button
     * @param panel the panel to add the button to
     * @param gbc the GridBagConstraints for the button
     * @param x the x position of the button
     * @param y the y position of the button
     * @param actionListener the action listener for the button
     */
    private void addButton(String text, JPanel panel, GridBagConstraints gbc, int x, int y, ActionListener actionListener) {
        gbc.gridx = x;
        gbc.gridy = y;
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        panel.add(btn, gbc);
        btn.addActionListener(actionListener);
    }

    /**
     * Show the specified panel.
     * 
     * @param panelClass the class of the panel to show
     * @param panelName the name of the panel to show
     */
    private void showPanel(Class<? extends JPanel> panelClass, String panelName) {
        if (contentPane.getComponentCount() > 0 && contentPane.getComponent(0).getClass().equals(panelClass)) {
            cardLayout.show(contentPane, panelName);
        } else {
            try {
                JPanel panel = panelClass.getDeclaredConstructor().newInstance();
                contentPane.add(panel, panelName);
                cardLayout.show(contentPane, panelName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}