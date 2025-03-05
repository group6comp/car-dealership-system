package carDealership;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;

    public AdminMainUI() {
        contentPane = Main.contentPane;
        cardLayout = Main.cardLayout;

        setBackground(new Color(230, 230, 230));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblTitle = new JLabel("Admin Main Interface");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(200, 20, 250, 30);
        add(lblTitle);

        addButton("Manage Users", new int[]{225, 80, 200, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (contentPane.getComponentCount() > 0 && contentPane.getComponent(0) instanceof ManageUsersPanel) {
                    cardLayout.show(contentPane, "manageUsersPanel");
                } else {
                    contentPane.add(new ManageUsersPanel(contentPane, cardLayout, Main.m_dealership), "manageUsersPanel");
                    cardLayout.show(contentPane, "manageUsersPanel");
                }
            }
        });

        
        addButton("Manage Inventory", new int[]{225, 130, 200, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (contentPane.getComponentCount() > 0 && contentPane.getComponent(0) instanceof ManageInventoryPanel) {
                    cardLayout.show(contentPane, "manageInventoryPanel");
                } else {
                    contentPane.add(new ManageInventoryPanel(contentPane, cardLayout), "manageInventoryPanel");
                    cardLayout.show(contentPane, "manageInventoryPanel");
                }
            }
        });

        addButton("View Sales", new int[]{225, 180, 200, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (contentPane.getComponentCount() > 0 && contentPane.getComponent(0) instanceof ViewSalesPanel) {
                    cardLayout.show(contentPane, "viewSalesPanel");
                } else {
                    contentPane.add(new ViewSalesPanel(contentPane, cardLayout), "viewSalesPanel");
                    cardLayout.show(contentPane, "viewSalesPanel");
                }
            }
        });

        addButton("Generate Reports", new int[]{225, 230, 200, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (contentPane.getComponentCount() > 0 && contentPane.getComponent(0) instanceof GenerateReportsPanel) {
                    cardLayout.show(contentPane, "generateReportsPanel");
                } else {
                    contentPane.add(new GenerateReportsPanel(contentPane, cardLayout), "generateReportsPanel");
                    cardLayout.show(contentPane, "generateReportsPanel");
                }
            }
        });

        addButton("Logout", new int[]{225, 280, 200, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.user = null;
                Main.showRoleUI();
            }
        });

        JLabel lblPendingTasks = new JLabel("Pending Tasks: 5 new user requests");
        lblPendingTasks.setFont(new Font("Dubai Medium", Font.PLAIN, 15));
        lblPendingTasks.setForeground(new Color(255, 128, 128));
        lblPendingTasks.setBounds(200, 330, 250, 30);
        add(lblPendingTasks);

    }

    private void addButton(String text,  int[] bounds, ActionListener a ) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        btn.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        add(btn);
        btn.addActionListener(a);
    }
}