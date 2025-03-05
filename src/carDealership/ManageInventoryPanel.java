package carDealership;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

public class ManageInventoryPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JPanel parentPanel;
    private CardLayout cardLayout;
    private Dealership dealership;

    /**
     * Create the panel.
     */
    public ManageInventoryPanel(JPanel parentPanel, CardLayout cardLayout) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        this.dealership = Main.m_dealership;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblTitle = new JLabel("Manage Inventory");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(250, 20, 150, 30);
        add(lblTitle);

        JButton btnAddVehicle = new JButton("Add Vehicle");
        btnAddVehicle.setBounds(50, 300, 150, 30);
        add(btnAddVehicle);

        JButton btnEditVehicle = new JButton("Edit Vehicle");
        btnEditVehicle.setBounds(250, 300, 150, 30);
        add(btnEditVehicle);

        JButton btnDeleteVehicle = new JButton("Delete Vehicle");
        btnDeleteVehicle.setBounds(450, 300, 150, 30);
        add(btnDeleteVehicle);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(50, 350, 150, 30);
        add(btnBack);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 70, 550, 200);
        add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        populateTable();

        btnAddVehicle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Add Vehicle functionality
                System.out.println("Add Vehicle clicked");
            }
        });

        btnEditVehicle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Edit Vehicle functionality
                System.out.println("Edit Vehicle clicked");
            }
        });

        btnDeleteVehicle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement Delete Vehicle functionality
                System.out.println("Delete Vehicle clicked");
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(parentPanel, "mainPanel");
            }
        });
    }

    private void populateTable() {
        String[] columnNames = {"ID", "Make", "Model", "Color", "Year", "Price", "Type"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Vehicle vehicle : dealership.getInventory()) {
            if (vehicle != null) {
                Object[] rowData = {
                    vehicle.getId(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getColor(),
                    vehicle.getYear(),
                    vehicle.getPrice(),
                    vehicle instanceof Car ? "Car" : "Motorcycle"
                };
                model.addRow(rowData);
            }
        }

        table.setModel(model);
    }
}