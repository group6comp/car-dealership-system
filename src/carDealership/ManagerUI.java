package carDealership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private DealershipNew dealershipNew;

    private JButton viewInventoryBtn, manageInventoryBtn, viewSalesBtn, generateReportsBtn, logoutBtn;

    public ManagerUI(DealershipNew d) {
        this.dealershipNew = d;
        setTitle("Car Dealership - Manager");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        viewInventoryBtn = new JButton("View Inventory");
        manageInventoryBtn = new JButton("Manage Inventory?");
        viewSalesBtn = new JButton("View Sales");
        generateReportsBtn = new JButton("Generate Reports");
        logoutBtn = new JButton("Logout");

        add(viewInventoryBtn);
        add(manageInventoryBtn);
        add(viewSalesBtn);
        add(generateReportsBtn);
        add(logoutBtn);

        viewInventoryBtn.addActionListener(this);
        manageInventoryBtn.addActionListener(this);
        viewSalesBtn.addActionListener(this);
        generateReportsBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewInventoryBtn) {
            JTextArea ta = new JTextArea(10,50);
            ta.setText(dealershipNew.displayInventory());
            JOptionPane.showMessageDialog(this, new JScrollPane(ta),
                "Manager - Inventory", JOptionPane.PLAIN_MESSAGE);
        } else if(e.getSource() == manageInventoryBtn) {
            JOptionPane.showMessageDialog(this, "Manager-limited inventory mgmt - placeholder.");
        } else if(e.getSource() == viewSalesBtn) {
            JOptionPane.showMessageDialog(this, "View Sales - placeholder logic.");
        } else if(e.getSource() == generateReportsBtn) {
            JOptionPane.showMessageDialog(this, "Generate Manager Reports - placeholder.");
        } else if(e.getSource() == logoutBtn) {
            JOptionPane.showMessageDialog(this, "Logging out Manager...");
            dispose();
            new MainUI();
        }
    }
}

