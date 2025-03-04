package carDealership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private DealershipNew dealershipNew;

    private JButton browseInventoryBtn, searchVehiclesBtn, wishlistBtn, logoutBtn;

    public CustomerUI(DealershipNew d) {
        this.dealershipNew = d;
        setTitle("Car Dealership - Customer");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        browseInventoryBtn = new JButton("Browse Inventory");
        searchVehiclesBtn = new JButton("Search Vehicles");
        wishlistBtn = new JButton("Wishlist");
        logoutBtn = new JButton("Logout");

        add(browseInventoryBtn);
        add(searchVehiclesBtn);
        add(wishlistBtn);
        add(logoutBtn);

        browseInventoryBtn.addActionListener(this);
        searchVehiclesBtn.addActionListener(this);
        wishlistBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == browseInventoryBtn) {
            JTextArea ta = new JTextArea(10,50);
            ta.setText(dealershipNew.displayInventory());
            JOptionPane.showMessageDialog(this, new JScrollPane(ta),
                "Customer - Inventory", JOptionPane.PLAIN_MESSAGE);
        } else if(e.getSource() == searchVehiclesBtn) {
            JOptionPane.showMessageDialog(this, "SearchVehicles - placeholder for Customer.");
        } else if(e.getSource() == wishlistBtn) {
            JOptionPane.showMessageDialog(this, "Wishlist - placeholder. Could store user picks.");
        } else if(e.getSource() == logoutBtn) {
            JOptionPane.showMessageDialog(this, "Logging out Customer...");
            dispose();
            new MainUI();
        }
    }
}

