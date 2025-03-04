package carDealership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    // Renamed "dealership" to "dealershipNew"
    private DealershipNew dealershipNew;

    private JButton manageUsersBtn, manageInventoryBtn, viewInventoryBtn, logoutBtn;

    public AdminUI(DealershipNew d) {
        this.dealershipNew = d;
        setTitle("Car Dealership - Admin");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        manageUsersBtn = new JButton("Manage Users");
        manageInventoryBtn = new JButton("Manage Inventory");
        viewInventoryBtn = new JButton("View Inventory");
        logoutBtn = new JButton("Logout");

        add(manageUsersBtn);
        add(manageInventoryBtn);
        add(viewInventoryBtn);
        add(logoutBtn);

        manageUsersBtn.addActionListener(this);
        manageInventoryBtn.addActionListener(this);
        viewInventoryBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == manageUsersBtn) {
            JOptionPane.showMessageDialog(this, "Manage Users - placeholder logic.");
        } else if(e.getSource() == manageInventoryBtn) {
            JOptionPane.showMessageDialog(this, "Add/Remove vehicles - placeholder for Admin operations.");
        } else if(e.getSource() == viewInventoryBtn) {
            JTextArea ta = new JTextArea(10,50);
            ta.setText(dealershipNew.displayInventory());
            JOptionPane.showMessageDialog(this, new JScrollPane(ta), 
                "Admin - Inventory", JOptionPane.PLAIN_MESSAGE);
        } else if(e.getSource() == logoutBtn) {
            JOptionPane.showMessageDialog(this, "Logging out Admin...");
            dispose();
            new MainUI(); // back to login
        }
    }
}

