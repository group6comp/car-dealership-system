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

public class SalespersonMainUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;

    /**
     * Create the panel.
     */
    public SalespersonMainUI() {
        contentPane = Main.contentPane;
        cardLayout = Main.cardLayout;

        setBackground(new Color(230, 230, 230));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblTitle = new JLabel("Salesperson Main Interface");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(200, 20, 250, 30);
        add(lblTitle);

        addButton("View Inventory", new int[]{225, 80, 200, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (contentPane.getComponentCount() > 0 && contentPane.getComponent(0) instanceof InventoryPanel) {
                    cardLayout.show(contentPane, "salesInventoryPanel");
                } else {
                    contentPane.add(new InventoryPanel(contentPane, cardLayout), "salesInventoryPanel");
                    cardLayout.show(contentPane, "salesInventoryPanel");
                }
                System.out.println("View Inventory clicked");
            }
        });

        addButton("Logout", new int[]{225, 230, 200, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Logout functionality
                System.out.println("Logout clicked");
                Main.logout();
            }
        });
    }

    private void addButton(String text, int[] bounds, ActionListener a) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        btn.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        add(btn);
        btn.addActionListener(a);
    }

}