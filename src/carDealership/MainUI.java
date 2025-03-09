package carDealership;

import carDealership.User.Role;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;

    public MainUI() {
        contentPane = Main.contentPane;
        cardLayout = Main.cardLayout;

        setBackground(new Color(230, 230, 230));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblTitle = new JLabel(Main.role.toString() + " Main Interface");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(210, 20, 250, 30);
        add(lblTitle);

        if (Main.role == Role.ADMIN) {
            addAdminButtons();
        } else if (Main.role == Role.MANAGER) {
            addManagerButtons();
        } else if (Main.role == Role.SALESPERSON) {
            addSalespersonButtons();
        } else if (Main.role == Role.CUSTOMER) {
            addCustomerButtons();
        } else if (Main.role == Role.VISITOR) {
            addGuestButtons();
        }
    }

    private void addAdminButtons() {
        addButton("Manage Users", new int[]{225, 80, 200, 30}, ManageUsersPanel.class, "manageUsersPanel");
        addButton("Manage Inventory", new int[]{225, 130, 200, 30}, InventoryPanel.class, "manageInventoryPanel");
        addButton("View Sales", new int[]{225, 180, 200, 30}, ViewSalesPanel.class, "viewSalesPanel");
        addButton("Generate Reports", new int[]{225, 230, 200, 30}, GenerateReportsPanel.class, "generateReportsPanel");
        addButton("Logout", new int[]{225, 280, 200, 30}, e -> Main.logout());

        JLabel lblPendingTasks = new JLabel("Pending Tasks: 5 new user requests");
        lblPendingTasks.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblPendingTasks.setForeground(new Color(255, 128, 128));
        lblPendingTasks.setBounds(200, 330, 250, 30);
        add(lblPendingTasks);
    }

    private void addManagerButtons() {
        //addButton("Approve Transactions", new int[]{225, 80, 200, 30}, ApproveTransactionsPanel.class, "approveTransactionsPanel");
        addButton("Manage Inventory", new int[]{225, 130, 200, 30}, InventoryPanel.class, "manageInventoryPanel");
        addButton("View Sales", new int[]{225, 180, 200, 30}, ViewSalesPanel.class, "viewSalesPanel");
        addButton("Generate Reports", new int[]{225, 230, 200, 30}, GenerateReportsPanel.class, "generateReportsPanel");
        addButton("Logout", new int[]{225, 280, 200, 30}, e -> Main.logout());
    }

    private void addSalespersonButtons() {
        addButton("View Inventory", new int[]{225, 80, 200, 30}, InventoryPanel.class, "viewInventoryPanel");
        addButton("Sales History", new int[]{225, 130, 200, 30}, ViewSalesPanel.class, "salesHistoryPanel");
        addButton("Logout", new int[]{225, 180, 200, 30}, e -> Main.logout());
    }

    private void addCustomerButtons() {
        addButton("View Inventory", new int[]{225, 80, 200, 30}, InventoryPanel.class, "viewInventoryPanel");
        //addButton("View Wishlist", new int[]{225, 130, 200, 30}, WishlistPanel.class, "wishlistPanel");
        addButton("Logout", new int[]{225, 180, 200, 30}, e -> Main.logout());
    }

    private void addGuestButtons() {
        //addButton("View Inventory", new int[]{225, 80, 200, 30}, SearchInventoryPanel.class, "searchInventoryPanel");
        //addButton("Sign Up", new int[]{225, 130, 200, 30}, SignUpPanel.class, "signUpPanel");
        addButton("Login", new int[]{225, 180, 200, 30}, LoginPanel.class, "loginPanel");
    }


    private void addButton(String text, int[] bounds, Class<? extends JPanel> panelClass, String panelName) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        btn.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        add(btn);
        btn.addActionListener(e -> showPanel(panelClass, panelName));
    }

    private void addButton(String text, int[] bounds, ActionListener actionListener) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        btn.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        add(btn);
        btn.addActionListener(actionListener);
    }

    private void showPanel(Class<? extends JPanel> panelClass, String panelName) {
        if (contentPane.getComponentCount() > 0 && contentPane.getComponent(0).getClass().equals(panelClass)) {
            cardLayout.show(contentPane, panelName);
        } else {
            try {
                JPanel panel = panelClass.getDeclaredConstructor(JPanel.class, CardLayout.class).newInstance(contentPane, cardLayout);
                contentPane.add(panel, panelName);
                cardLayout.show(contentPane, panelName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}