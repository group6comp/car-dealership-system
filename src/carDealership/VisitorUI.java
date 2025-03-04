package carDealership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VisitorUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private DealershipNew dealershipNew;
    private JButton browseInventoryBtn, searchBtn, loginBtn, signUpBtn, exitBtn;

    public VisitorUI(DealershipNew d) {
        this.dealershipNew = d;
        setTitle("Car Dealership - Visitor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        browseInventoryBtn = new JButton("Browse Inventory");
        searchBtn = new JButton("Search Vehicles");
        loginBtn = new JButton("Login");
        signUpBtn = new JButton("Sign Up");
        exitBtn = new JButton("Exit");

        add(browseInventoryBtn);
        add(searchBtn);
        add(loginBtn);
        add(signUpBtn);
        add(exitBtn);

        browseInventoryBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        loginBtn.addActionListener(this);
        signUpBtn.addActionListener(this);
        exitBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == browseInventoryBtn) {
            JTextArea ta = new JTextArea(10,50);
            ta.setText(dealershipNew.displayInventory());
            JOptionPane.showMessageDialog(this, new JScrollPane(ta),
                "Visitor - Inventory (Read-Only)", JOptionPane.PLAIN_MESSAGE);
        } else if(e.getSource() == searchBtn) {
            JOptionPane.showMessageDialog(this, 
                "Visitor search - placeholder. Possibly limited searching.",
                "Visitor Search", JOptionPane.INFORMATION_MESSAGE);
        } else if(e.getSource() == loginBtn) {
            JOptionPane.showMessageDialog(this, "Redirect to main login (MainUI).");
            dispose();
            new MainUI();
        } else if(e.getSource() == signUpBtn) {
            JOptionPane.showMessageDialog(this, 
                "Sign Up not implemented. Could create new user with role=Customer.",
                "Sign Up", JOptionPane.INFORMATION_MESSAGE);
        } else if(e.getSource() == exitBtn) {
            JOptionPane.showMessageDialog(this, "Exiting as Visitor...");
            dispose();
        }
    }
}

