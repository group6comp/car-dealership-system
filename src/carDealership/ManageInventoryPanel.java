package carDealership;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.io.IOException;

public class ManageInventoryPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private DefaultTableModel model;

    /**
     * Create the panel.
     */
    public ManageInventoryPanel(JPanel contentPane, CardLayout cardLayout) {
        this.contentPane = contentPane;
        this.cardLayout = cardLayout;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblTitle = new JLabel("Manage Inventory");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(250, 20, 150, 30);
        add(lblTitle);

        addButton("Add Vehicle", new int[]{50, 300, 150, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addVehicle();
            }
        });

        addButton("Edit Vehicle", new int[]{250, 300, 150, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    editVehicle(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a vehicle to edit.");
                }
            }
        });

        addButton("Delete Vehicle", new int[]{450, 300, 150, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    deleteVehicle(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a vehicle to delete.");
                }
            }
        });

        addButton("Back", new int[]{50, 350, 150, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "adminMainUI");
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 70, 550, 200);
        add(scrollPane);

        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        scrollPane.setViewportView(table);

        populateTable();
    }

    private void populateTable() {
        String[] columnNames = {"ID", "Make", "Model", "Color", "Year", "Price", "Type"};
        model = new DefaultTableModel(columnNames, 0);

        Vehicle[] vehicles = Main.m_dealership.getInventory();
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null) {
                Object[] rowData = {
                    vehicle.getId(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getColor(),
                    vehicle.getYear(),
                    vehicle.getPrice(),
                    vehicle.getType()
                };
                model.addRow(rowData);
            }
        }

        table.setModel(model);
        table.setRowSorter(new TableRowSorter<TableModel>(model));
    }

    private void editVehicle(int selectedRow) {
        // Get the current values
        int id = (int) table.getValueAt(selectedRow, 0);
        String make = (String) table.getValueAt(selectedRow, 1);
        String modelValue = (String) table.getValueAt(selectedRow, 2);
        String color = (String) table.getValueAt(selectedRow, 3);
        int year = (int) table.getValueAt(selectedRow, 4);
        double price = (double) table.getValueAt(selectedRow, 5);
        String type = (String) table.getValueAt(selectedRow, 6);

        // Create text fields for editing
        JTextField txtMake = new JTextField(make);
        JTextField txtModel = new JTextField(modelValue);
        JTextField txtColor = new JTextField(color);
        JTextField txtYear = new JTextField(String.valueOf(year));
        JTextField txtPrice = new JTextField(String.valueOf(price));
        JTextField txtType = new JTextField(type);

        // Create a panel to hold the text fields
        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(7, 2));
        panel.add(new JLabel("Make:"));
        panel.add(txtMake);
        panel.add(new JLabel("Model:"));
        panel.add(txtModel);
        panel.add(new JLabel("Color:"));
        panel.add(txtColor);
        panel.add(new JLabel("Year:"));
        panel.add(txtYear);
        panel.add(new JLabel("Price:"));
        panel.add(txtPrice);
        panel.add(new JLabel("Type:"));
        panel.add(txtType);

        // Show the dialog
        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Vehicle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Update the vehicle in the dealership
            Vehicle vehicle = new Car(id, txtMake.getText(), txtModel.getText(), txtColor.getText(), Integer.parseInt(txtYear.getText()), Double.parseDouble(txtPrice.getText()), txtType.getText());
            Main.m_dealership.removeVehicle(Main.m_dealership.getVehicleFromId(id));
            Main.m_dealership.addVehicle(vehicle);
            try {
                Main.save();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage());
            }
            populateTable();

            // Show confirmation dialog with new details
            JOptionPane.showMessageDialog(null, "Vehicle information successfully saved:\n" +
                    "Make: " + txtMake.getText() + "\n" +
                    "Model: " + txtModel.getText() + "\n" +
                    "Color: " + txtColor.getText() + "\n" +
                    "Year: " + txtYear.getText() + "\n" +
                    "Price: " + txtPrice.getText() + "\n" +
                    "Type: " + txtType.getText());
        }
    }

    private void addVehicle() {
        // Create text fields for adding a new vehicle
        JTextField txtID = new JTextField();
        JTextField txtMake = new JTextField();
        JTextField txtModel = new JTextField();
        JTextField txtColor = new JTextField();
        JTextField txtYear = new JTextField();
        JTextField txtPrice = new JTextField();
        JTextField txtType = new JTextField();

        // Create a panel to hold the text fields
        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(8, 2));
        panel.add(new JLabel("ID:"));
        panel.add(txtID);
        panel.add(new JLabel("Make:"));
        panel.add(txtMake);
        panel.add(new JLabel("Model:"));
        panel.add(txtModel);
        panel.add(new JLabel("Color:"));
        panel.add(txtColor);
        panel.add(new JLabel("Year:"));
        panel.add(txtYear);
        panel.add(new JLabel("Price:"));
        panel.add(txtPrice);
        panel.add(new JLabel("Type:"));
        panel.add(txtType);

        // Show the dialog
        int result = JOptionPane.showConfirmDialog(null, panel, "Add Vehicle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Add the new vehicle to the dealership
            Vehicle vehicle = new Car(Integer.parseInt(txtID.getText()), txtMake.getText(), txtModel.getText(), txtColor.getText(), Integer.parseInt(txtYear.getText()), Double.parseDouble(txtPrice.getText()), txtType.getText());
            Main.m_dealership.addVehicle(vehicle);
            try {
                Main.save();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage());
            }
            populateTable();

            // Show confirmation dialog with new details
            JOptionPane.showMessageDialog(null, "New vehicle successfully added:\n" +
                    "ID: " + txtID.getText() + "\n" +
                    "Make: " + txtMake.getText() + "\n" +
                    "Model: " + txtModel.getText() + "\n" +
                    "Color: " + txtColor.getText() + "\n" +
                    "Year: " + txtYear.getText() + "\n" +
                    "Price: " + txtPrice.getText() + "\n" +
                    "Type: " + txtType.getText());
        }
    }

    private void deleteVehicle(int selectedRow) {
        // Get the current values
        int id = (int) table.getValueAt(selectedRow, 0);
        String make = (String) table.getValueAt(selectedRow, 1);
        String modelValue = (String) table.getValueAt(selectedRow, 2);
        String color = (String) table.getValueAt(selectedRow, 3);
        int year = (int) table.getValueAt(selectedRow, 4);
        double price = (double) table.getValueAt(selectedRow, 5);
        String type = (String) table.getValueAt(selectedRow, 6);

        // Show confirmation dialog
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the following vehicle?\n" +
                "ID: " + id + "\n" +
                "Make: " + make + "\n" +
                "Model: " + modelValue + "\n" +
                "Color: " + color + "\n" +
                "Year: " + year + "\n" +
                "Price: " + price + "\n" +
                "Type: " + type, "Delete Vehicle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Remove the vehicle from the dealership
            Main.m_dealership.removeVehicle(Main.m_dealership.getVehicleFromId(id));
            try {
                Main.save();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage());
            }
            populateTable();

            // Show confirmation dialog
            JOptionPane.showMessageDialog(null, "Vehicle successfully deleted.");
        }
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