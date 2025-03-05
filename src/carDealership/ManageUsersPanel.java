package carDealership;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

public class ManageUsersPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JPanel parentPanel;
    private CardLayout cardLayout;
    private Dealership dealership;

    /**
     * Create the panel.
     */
    public ManageUsersPanel(JPanel parentPanel, CardLayout cardLayout, Dealership dealership) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblTitle = new JLabel("Manage Users");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(250, 20, 150, 30);
        add(lblTitle);

        JButton btnCreateUser = new JButton("Create User");
        btnCreateUser.setBounds(50, 300, 150, 30);
        add(btnCreateUser);

        JButton btnEditUser = new JButton("Edit User");
        btnEditUser.setBounds(250, 300, 150, 30);
        add(btnEditUser);

        JButton btnDeleteUser = new JButton("Delete User");
        btnDeleteUser.setBounds(450, 300, 150, 30);
        add(btnDeleteUser);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(50, 350, 150, 30);
        add(btnBack);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 70, 550, 200);
        add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        btnCreateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Create User functionality
                System.out.println("Create User clicked");
            }
        });

        btnEditUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Edit User functionality
                System.out.println("Edit User clicked");
            }
        });

        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Delete User functionality
                System.out.println("Delete User clicked");
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(parentPanel, "mainPanel");
            }
        });
    }
}