package carDealership;
import carDealership.Filter;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ManageInventoryPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private Dealership m_dealership = Main.m_dealership;
    private DefaultTableModel model;
    private Filter filter = new Filter();

    /**
     * Create the panel.
     */
    public ManageInventoryPanel(JPanel contentPane, CardLayout cardLayout) {
        this.contentPane = contentPane;
        this.cardLayout = cardLayout;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Manage Inventory");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        scrollPane.setViewportView(table);

        populateTable();

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addButton("Filter", buttonPanel, gbc, 0, 0, e -> filterInventory());
        addButton("Add", buttonPanel, gbc, 1, 0, e -> addVehicle());
        addButton("Edit", buttonPanel, gbc, 2, 0, e -> editSelectedVehicle());
        addButton("Delete", buttonPanel, gbc, 3, 0, e -> deleteSelectedVehicle());
        addButton("Back", buttonPanel, gbc, 4, 0, e -> Main.showRoleUI());
    }


    private void addButton(String text, JPanel panel, GridBagConstraints gbc, int x, int y, ActionListener actionListener) {
        gbc.gridx = x;
        gbc.gridy = y;
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        panel.add(btn, gbc);
        btn.addActionListener(actionListener);
    }

    private void populateTable() {
        String[] columnNames = {"ID", "Make", "Model", "Color", "Year", "Price", "Type", "Mileage", "Status"};
        model = new DefaultTableModel(columnNames, 0);
        List<Vehicle> vehicles = filter.filterInventory();
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null) {
                Object[] rowData = {
                    vehicle.getId(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getColor(),
                    vehicle.getYear(),
                    vehicle.getPrice(),
                    vehicle.getType(),
                    vehicle.getMileage(),
                    vehicle.getStatus()
                };
                model.addRow(rowData);
            }
        }
    
        table.setModel(model);
        table.setRowSorter(new TableRowSorter<>(model));
    }

    private void filterInventory() {
        JCheckBox carFilter = new JCheckBox("Car", true);
        JCheckBox motorcycleFilter = new JCheckBox("Motorcycle", true);
        JTextField makeFilter = new JTextField();
        JTextField modelFilter = new JTextField();
        JTextField colorFilter = new JTextField();
        JTextField typeFilter = new JTextField();
        JTextField yearMin = new JTextField();
        JTextField yearMax = new JTextField();
        JTextField priceMin = new JTextField();
        JTextField priceMax = new JTextField();
        JTextField mileageMin = new JTextField();
        JTextField mileageMax = new JTextField();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLabeledField("", carFilter, panel, gbc, 0, 0);
        addLabeledField("", motorcycleFilter, panel, gbc, 1, 0);
        addLabeledField("Make:", makeFilter, panel, gbc, 0, 1);
        addLabeledField("Model:", modelFilter, panel, gbc, 1, 1);
        addLabeledField("Color:", colorFilter, panel, gbc, 0, 2);
        addLabeledField("Type:", typeFilter, panel, gbc, 1, 2);
        addLabeledField("Year:", yearMin, panel, gbc, 0, 3);
        addLabeledField(" - ", yearMax, panel, gbc, 1, 3);
        addLabeledField("Price:", priceMin, priceMax, panel, gbc, 0, 4);
        addLabeledField(" - ", priceMax, panel, gbc, 1, 4);
        addLabeledField("Mileage:", mileageMin, mileageMax, panel, gbc, 0, 5);
        addLabeledField(" - ", mileageMax, panel, gbc, 1, 5);

        int result = JOptionPane.showConfirmDialog(null, panel, "Filter Inventory", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            applyFilters(carFilter.isSelected(), motorcycleFilter.isSelected(), makeFilter.getText(), modelFilter.getText(), colorFilter.getText(), typeFilter.getText(), yearMin.getText(), yearMax.getText(), priceMin.getText(), priceMax.getText(), mileageMin.getText(), mileageMax.getText());
        }
    }

    private void addLabeledField(String label, JCheckBox field, JPanel panel, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x * 2;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = x * 2 + 1;
        panel.add(field, gbc);
    }

    private void addLabeledField(String label, JTextField field, JPanel panel, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x * 2;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = x * 2 + 1;
        panel.add(field, gbc);
    }

    private void addLabeledField(String label, JTextField field1, JTextField field2, JPanel panel, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x * 2;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = x * 2 + 1;
        panel.add(field1, gbc);

        gbc.gridx = x * 2 + 2;
        panel.add(field2, gbc);
    }

    private void applyFilters(boolean carSelected, boolean motorcycleSelected, String make, String model, String color, String type, String yearMin, String yearMax, String priceMin, String priceMax, String mileageMin, String mileageMax) {
        filter.setCarSelected(carSelected);
        filter.setMotorcycleSelected(motorcycleSelected);
        filter.setMake(make);
        filter.setModel(model);
        filter.setColor(color);
        filter.setType(type);
        filter.setMinYear(yearMin.isEmpty() ? 0 : Integer.parseInt(yearMin));
        filter.setMaxYear(yearMax.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(yearMax));
        filter.setMinPrice(priceMin.isEmpty() ? 0 : Double.parseDouble(priceMin));
        filter.setMaxPrice(priceMax.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(priceMax));
        filter.setMinMileage(mileageMin.isEmpty() ? 0 : Integer.parseInt(mileageMin));
        filter.setMaxMileage(mileageMax.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(mileageMax));
        populateTable();
    }

    private void editSelectedVehicle() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            editVehicle(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a vehicle to edit.");
        }
    }

    private void deleteSelectedVehicle() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Vehicle vehicle = Main.m_dealership.getVehicleFromId((int) table.getValueAt(selectedRow, 0));
            m_dealership.removeVehicle(vehicle);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a vehicle to delete.");
        }
    }

    private void editVehicle(int selectedRow) {
        Vehicle vehicle = Main.m_dealership.getVehicleFromId((int) table.getValueAt(selectedRow, 0));
        JPanel panel = createVehicleEditPanel(vehicle, vehicle instanceof Car);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Vehicle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            updateVehicleFromPanel(vehicle, panel);
            populateTable();
            showConfirmationDialog("Vehicle information successfully saved", vehicle);
        }
    }

    private void addVehicle() {
        String[] options = {"Car", "Motorcycle"};
        int choice = JOptionPane.showOptionDialog(null, "Would you like to add a car or a motorcycle?", "Select Vehicle Type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            return; // User closed the dialog
        }

        boolean isCar = choice == 0;
        JPanel panel = createVehicleEditPanel(null, isCar);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Vehicle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Vehicle vehicle = createVehicleFromPanel(panel, isCar);
            populateTable();
            showConfirmationDialog("New vehicle successfully added", vehicle);
        }
    }

    private Vehicle createVehicleFromPanel(JPanel panel, boolean isCar) {
        String make = ((JTextField) panel.getComponent(1)).getText();
        String model = ((JTextField) panel.getComponent(3)).getText();
        String color = ((JTextField) panel.getComponent(5)).getText();
        int year = Integer.parseInt(((JTextField) panel.getComponent(7)).getText());
        double price = Double.parseDouble(((JTextField) panel.getComponent(9)).getText());
        int mileage = Integer.parseInt(((JTextField) panel.getComponent(13)).getText());
        Vehicle.Status status = Vehicle.Status.valueOf(((JTextField) panel.getComponent(15)).getText().toUpperCase());
        String typeOrHandlebar = ((JTextField) panel.getComponent(11)).getText();

        if (isCar) {
            return m_dealership.addCar(make, model, color, year, price, mileage, status, typeOrHandlebar);
        } else {
            return m_dealership.addMotorcycle(make, model, color, year, price, mileage, status, typeOrHandlebar);
        }
    }

    private void updateVehicleFromPanel(Vehicle vehicle, JPanel panel) {
        m_dealership.editVehicle(vehicle,
                ((JTextField) panel.getComponent(1)).getText(),
                ((JTextField) panel.getComponent(3)).getText(),
                ((JTextField) panel.getComponent(5)).getText(),
                Integer.parseInt(((JTextField) panel.getComponent(7)).getText()),
                Double.parseDouble(((JTextField) panel.getComponent(9)).getText()),
                Integer.parseInt(((JTextField) panel.getComponent(13)).getText()),
                ((JTextField) panel.getComponent(15)).getText(),
                ((JTextField) panel.getComponent(11)).getText());
    }

    private JPanel createVehicleEditPanel(Vehicle vehicle, boolean isCar) {
        JTextField txtMake = new JTextField(vehicle != null ? vehicle.getMake() : "");
        JTextField txtModel = new JTextField(vehicle != null ? vehicle.getModel() : "");
        JTextField txtColor = new JTextField(vehicle != null ? vehicle.getColor() : "");
        JTextField txtYear = new JTextField(vehicle != null ? Integer.toString(vehicle.getYear()) : "");
        JTextField txtPrice = new JTextField(vehicle != null ? Double.toString(vehicle.getPrice()) : "");
        JTextField txtTypeOrHandlebar = new JTextField(vehicle != null ? (isCar ? ((Car) vehicle).getType() : ((Motorcycle) vehicle).getType()) : "");
        JTextField txtMileage = new JTextField(vehicle != null ? Integer.toString(vehicle.getMileage()) : "");
        JTextField txtStatus = new JTextField(vehicle != null ? vehicle.getStatus().toString() : "");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLabeledField("Make:", txtMake, panel, gbc, 0, 0);
        addLabeledField("Model:", txtModel, panel, gbc, 0, 1);
        addLabeledField("Color:", txtColor, panel, gbc, 0, 2);
        addLabeledField("Year:", txtYear, panel, gbc, 0, 3);
        addLabeledField("Price:", txtPrice, panel, gbc, 0, 4);
        addLabeledField(isCar ? "Type:" : "Handlebar Type:", txtTypeOrHandlebar, panel, gbc, 0, 5);
        addLabeledField("Mileage:", txtMileage, panel, gbc, 0, 6);
        addLabeledField("Status:", txtStatus, panel, gbc, 0, 7);

        return panel;
    }

    private void showConfirmationDialog(String message, Vehicle vehicle) {
        JOptionPane.showMessageDialog(null, message + ":\n" +
                "Make: " + vehicle.getMake() + "\n" +
                "Model: " + vehicle.getModel() + "\n" +
                "Color: " + vehicle.getColor() + "\n" +
                "Year: " + vehicle.getYear() + "\n" +
                "Price: " + vehicle.getPrice() + "\n" +
                "Type: " + vehicle.getType() + "\n" +
                "Mileage: " + vehicle.getMileage() + "\n" +
                "Status: " + vehicle.getStatus());
    }
}