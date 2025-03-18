package carDealership;

import carDealership.User.Role;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.awt.BorderLayout;

/**
 * The MainUI class represents the main user interface panel for the dealership
 * system.
 * It displays different buttons and options based on the user's role.
 */
public class MainUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private CardLayout contentCardLayout;
    private JPanel contentDisplayPanel;
    private JButton activeButton = null;

    public MainUI() {
        setBackground(new Color(230, 230, 230));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        // Create the main content panel with a split layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(200); // Width of the left menu panel
        splitPane.setDividerSize(5);
        splitPane.setContinuousLayout(true);
        add(splitPane, BorderLayout.CENTER);

        // Create the left menu panel with two sections: main menu and auth buttons
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuPanel.setBackground(new Color(220, 220, 220));

        // Top section for main menu buttons
        JPanel topMenuPanel = new JPanel();
        topMenuPanel.setLayout(new BoxLayout(topMenuPanel, BoxLayout.Y_AXIS));
        topMenuPanel.setBackground(new Color(220, 220, 220));

        // Bottom section for auth buttons
        JPanel bottomMenuPanel = new JPanel();
        bottomMenuPanel.setLayout(new BoxLayout(bottomMenuPanel, BoxLayout.Y_AXIS));
        bottomMenuPanel.setBackground(new Color(220, 220, 220));

        // Add the sections to the menu panel
        menuPanel.add(topMenuPanel, BorderLayout.CENTER);
        menuPanel.add(bottomMenuPanel, BorderLayout.SOUTH);

        // Create the right content panel with CardLayout
        contentCardLayout = new CardLayout();
        contentDisplayPanel = new JPanel(contentCardLayout);
        contentDisplayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add panels to the split pane
        splitPane.setLeftComponent(menuPanel);
        splitPane.setRightComponent(contentDisplayPanel);

        // Add buttons based on the user's role
        if (Main.role == Role.ADMIN) {
            addAdminButtons(topMenuPanel, bottomMenuPanel);
        } else if (Main.role == Role.MANAGER) {
            addManagerButtons(topMenuPanel, bottomMenuPanel);
        } else if (Main.role == Role.SALESPERSON) {
            addSalespersonButtons(topMenuPanel, bottomMenuPanel);
        } else if (Main.role == Role.CUSTOMER) {
            addCustomerButtons(topMenuPanel, bottomMenuPanel);
        } else if (Main.role == Role.VISITOR) {
            addGuestButtons(topMenuPanel, bottomMenuPanel);
        }

        loadOrShowContentPanel("inventory", InventoryPanel.class);
    }

    /**
     * Add buttons for the admin role.
     */
    private void addAdminButtons(JPanel topMenuPanel, JPanel bottomMenuPanel) {
        addMenuButton("Inventory", topMenuPanel, e -> loadOrShowContentPanel("inventory", InventoryPanel.class));
        addMenuButton("Users", topMenuPanel, e -> loadOrShowContentPanel("users", ManageUsersPanel.class));

        // Add logout button at the bottom
        addMenuButton("Logout", bottomMenuPanel, e -> {
            // Reset active button highlight
            if (activeButton != null) {
                setInactiveButtonStyle(activeButton);
                activeButton = null;
            }
            Main.logout();
        });
    }

    /**
     * Add buttons for the manager role.
     */
    private void addManagerButtons(JPanel topMenuPanel, JPanel bottomMenuPanel) {
        addMenuButton("Inventory", topMenuPanel, e -> loadOrShowContentPanel("inventory", InventoryPanel.class));
        addMenuButton("Transactions", topMenuPanel,
                e -> loadOrShowContentPanel("transactions", ApproveTransactionsPanel.class));
        addMenuButton("Sales", topMenuPanel, e -> loadOrShowContentPanel("sales", ViewSalesPanel.class));
        addMenuButton("Reports", topMenuPanel,
                e -> loadOrShowContentPanel("reports", GenerateReportsPanel.class));

        // Add pending tasks label
        int pendingSales = Main.m_dealership.getPendingSales();
        JLabel lblPendingTasks = new JLabel("Pending Tasks: " + pendingSales);
        lblPendingTasks.setFont(new Font("Dubai Medium", Font.PLAIN, 14));
        lblPendingTasks.setForeground(new Color(255, 128, 128));
        lblPendingTasks.setAlignmentX(Component.LEFT_ALIGNMENT);
        topMenuPanel.add(Box.createVerticalStrut(15));
        topMenuPanel.add(lblPendingTasks);

        // Add logout button at the bottom
        addMenuButton("Logout", bottomMenuPanel, e -> {
            // Reset active button highlight
            if (activeButton != null) {
                setInactiveButtonStyle(activeButton);
                activeButton = null;
            }
            Main.logout();
        });
    }

    /**
     * Add buttons for the salesperson role.
     */
    private void addSalespersonButtons(JPanel topMenuPanel, JPanel bottomMenuPanel) {
        addMenuButton("Inventory", topMenuPanel, e -> loadOrShowContentPanel("inventory", InventoryPanel.class));
        addMenuButton("Enquiries", topMenuPanel,
                e -> loadOrShowContentPanel("enquiries", ManageEnquiriesPanel.class));
        addMenuButton("Sales", topMenuPanel, e -> loadOrShowContentPanel("salesHistory", ViewSalesPanel.class));

        // Add pending tasks label
        int pendingEnquiries = Main.m_dealership.getPendingEnquiries();
        JLabel lblPendingTasks = new JLabel("Pending Tasks: " + pendingEnquiries);
        lblPendingTasks.setFont(new Font("Dubai Medium", Font.PLAIN, 14));
        lblPendingTasks.setForeground(new Color(255, 128, 128));
        lblPendingTasks.setAlignmentX(Component.LEFT_ALIGNMENT);
        topMenuPanel.add(Box.createVerticalStrut(15));
        topMenuPanel.add(lblPendingTasks);

        // Add logout button at the bottom
        addMenuButton("Logout", bottomMenuPanel, e -> {
            // Reset active button highlight
            if (activeButton != null) {
                setInactiveButtonStyle(activeButton);
                activeButton = null;
            }
            Main.logout();
        });
    }

    /**
     * Add buttons for the customer role.
     */
    private void addCustomerButtons(JPanel topMenuPanel, JPanel bottomMenuPanel) {
        addMenuButton("Inventory", topMenuPanel, e -> loadOrShowContentPanel("inventory", InventoryPanel.class));
        addMenuButton("Wishlist", topMenuPanel, e -> loadOrShowContentPanel("wishlist", ViewWishlistPanel.class));

        // Add logout button at the bottom
        addMenuButton("Logout", bottomMenuPanel, e -> {
            // Reset active button highlight
            if (activeButton != null) {
                setInactiveButtonStyle(activeButton);
                activeButton = null;
            }
            Main.logout();
        });
    }

    /**
     * Add buttons for the guest role.
     */
    private void addGuestButtons(JPanel topMenuPanel, JPanel bottomMenuPanel) {
        addMenuButton("Inventory", topMenuPanel, e -> loadOrShowContentPanel("inventory", InventoryPanel.class));

        // Add auth buttons at the bottom
        addMenuButton("Sign Up", bottomMenuPanel, e -> loadOrShowContentPanel("signup", SignUpPanel.class));
        addMenuButton("Login", bottomMenuPanel, e -> loadOrShowContentPanel("login", LoginPanel.class));
    }

    /**
     * Add a vertical menu button to the panel with a custom action listener.
     * 
     * @param text           the text of the button
     * @param panel          the panel to add the button to
     * @param actionListener the action listener for the button
     */
    private void addMenuButton(String text, JPanel panel, ActionListener originalListener) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        btn.setFont(new Font("Dubai", Font.PLAIN, 14));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(180, 40));
        btn.setPreferredSize(new Dimension(180, 40));
        btn.setMargin(new Insets(5, 10, 5, 10));
        btn.setFocusPainted(false);

        // Create a wrapper for the action listener to handle button styling
        btn.addActionListener(e -> {
            // Reset previous active button (if any)
            if (activeButton != null) {
                setInactiveButtonStyle(activeButton);
            }

            // Set this button as active
            activeButton = btn;
            setActiveButtonStyle(btn);

            // Call the original action listener
            originalListener.actionPerformed(e);
        });

        panel.add(btn);
        panel.add(Box.createVerticalStrut(10)); // Add spacing between buttons

        // If this is the first button (welcome screen) or we're opening a specific
        // panel,
        // we might want to set it as active initially
        if (activeButton == null && text.equals("Inventory")) {
            activeButton = btn;
            setActiveButtonStyle(btn);
        }
    }

    /**
     * Loads or shows a content panel in the right side of the split pane.
     * If the panel has been previously loaded, it will be shown again.
     * Otherwise, a new instance will be created and added to the card layout.
     * 
     * @param panelKey   the key to identify the panel in the card layout
     * @param panelClass the class of the panel to create if needed
     */
    private void loadOrShowContentPanel(String panelKey, Class<? extends JPanel> panelClass) {
        try {
            // Remove existing panel if it exists
            Component[] components = contentDisplayPanel.getComponents();
            for (Component comp : components) {
                if (comp.getName() != null && comp.getName().equals(panelKey)) {
                    contentDisplayPanel.remove(comp);
                    break;
                }
            }

            // Create a new instance of the panel
            JPanel newPanel = panelClass.getDeclaredConstructor().newInstance();
            newPanel.setName(panelKey); // Set name for identification
            contentDisplayPanel.add(newPanel, panelKey);

            // Show the newly created panel
            contentCardLayout.show(contentDisplayPanel, panelKey);
            contentDisplayPanel.revalidate();
            contentDisplayPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the button style to indicate it's the active/selected button
     * 
     * @param button The button to style as active
     */
    private void setActiveButtonStyle(JButton button) {
        // Highlight color for active button
        button.setBackground(new Color(180, 30, 50));
        button.setFont(new Font("Dubai", Font.BOLD, 14));
    }

    /**
     * Sets the button style to indicate it's inactive
     * 
     * @param button The button to style as inactive
     */
    private void setInactiveButtonStyle(JButton button) {
        // Regular color for inactive buttons
        button.setBackground(new Color(241, 57, 83));
        button.setFont(new Font("Dubai", Font.PLAIN, 14));
    }
}