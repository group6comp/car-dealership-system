package carDealership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SalespersonUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private DealershipNew dealershipNew;

    private JButton viewInventoryBtn, sellVehicleBtn, assistCustomerBtn, viewSalesBtn, logoutBtn;

    public SalespersonUI(DealershipNew d) {
        this.dealershipNew = d;
        setTitle("Car Dealership - Salesperson");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        viewInventoryBtn = new JButton("View Inventory");
        sellVehicleBtn = new JButton("Sell Vehicle");
        assistCustomerBtn = new JButton("Assist Customer");
        viewSalesBtn = new JButton("View Sales?");
        logoutBtn = new JButton("Logout");

        add(viewInventoryBtn);
        add(sellVehicleBtn);
        add(assistCustomerBtn);
        add(viewSalesBtn);
        add(logoutBtn);

        viewInventoryBtn.addActionListener(this);
        sellVehicleBtn.addActionListener(this);
        assistCustomerBtn.addActionListener(this);
        viewSalesBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewInventoryBtn) {
            JTextArea ta = new JTextArea(10,50);
            ta.setText(dealershipNew.displayInventory());
            JOptionPane.showMessageDialog(this, new JScrollPane(ta),
                "Salesperson - Inventory", JOptionPane.PLAIN_MESSAGE);
        } else if(e.getSource() == sellVehicleBtn) {
            JOptionPane.showMessageDialog(this, "Sell Vehicle - placeholder logic.");
        } else if(e.getSource() == assistCustomerBtn) {
            JOptionPane.showMessageDialog(this, "Assist Customer - placeholder logic.");
        } else if(e.getSource() == viewSalesBtn) {
            JOptionPane.showMessageDialog(this, "View Sales - placeholder logic.");
        } else if(e.getSource() == logoutBtn) {
            JOptionPane.showMessageDialog(this, "Logging out Salesperson...");
            dispose();
            new MainUI();
        }
    }
}

