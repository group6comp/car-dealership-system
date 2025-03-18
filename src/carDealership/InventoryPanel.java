package carDealership;

import carDealership.User.Role;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * The InventoryPanel class represents the panel for managing the dealership's
 * inventory.
 * It allows users to view, filter, add, edit, delete, sell, and enquire about
 * vehicles.
 */
public class InventoryPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Dealership m_dealership = Main.m_dealership;
    private Filter filter = new Filter();
    private VehicleTable table = new VehicleTable(filter);

    /**
     * Create the panel.
     */
    public InventoryPanel() {

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        // Add scroll pane for the table
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(table);

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add buttons to the button panel based on user role
        addButton("Filter", buttonPanel, new Color(51, 153, 255), gbc, 0, 0, e -> filterInventory());
        if (Main.role == Role.MANAGER) {
            addButton("Add", buttonPanel, new Color(0, 180, 0), gbc, 1, 0, e -> addVehicle());
            addButton("Edit", buttonPanel, new Color(0, 180, 0), gbc, 2, 0, e -> editSelectedVehicle());
            addButton("Delete", buttonPanel, new Color(255, 80, 80), gbc, 3, 0, e -> deleteSelectedVehicle());
        } else if (Main.role == Role.SALESPERSON) {
            addButton("Sell", buttonPanel, new Color(0, 204, 0), gbc, 1, 0, e -> sellSelectedVehicle());
        } else if (Main.role == Role.CUSTOMER) {
            addButton("Wishlist", buttonPanel, new Color(0, 204, 0), gbc, 1, 0, e -> wishlistSelectedVehicle());
            addButton("Enquire", buttonPanel, new Color(51, 153, 255), gbc, 2, 0, e -> enquire());
        }
    }

    /**
     * Add a button to the specified panel.
     * 
     * @param text           the text of the button
     * @param panel          the panel to add the button to
     * @param gbc            the GridBagConstraints for the button
     * @param x              the x position of the button
     * @param y              the y position of the button
     * @param actionListener the ActionListener for the button
     */
    private void addButton(String text, JPanel panel, Color color, GridBagConstraints gbc, int x, int y,
            ActionListener actionListener) {
        gbc.gridx = x;
        gbc.gridy = y;
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        panel.add(btn, gbc);
        btn.addActionListener(actionListener);
    }

    /**
     * Filter the inventory based on user input.
     */
    private void filterInventory() {
        JCheckBox carFilter = new JCheckBox("Car", filter.getCarSelected());
        JCheckBox motorcycleFilter = new JCheckBox("Motorcycle", filter.getMotorcycleSelected());
        JTextField makeFilter = new JTextField(filter.getMake());
        JTextField modelFilter = new JTextField(filter.getModel());
        JTextField colorFilter = new JTextField(filter.getColor());
        JTextField typeFilter = new JTextField(filter.getType());
        JTextField yearMin = new JTextField(filter.getMinYear() == 0 ? "" : String.valueOf(filter.getMinYear()));
        JTextField yearMax = new JTextField(
                filter.getMaxYear() == Integer.MAX_VALUE ? "" : String.valueOf(filter.getMaxYear()));
        JTextField priceMin = new JTextField(filter.getMinPrice() == 0 ? "" : String.valueOf(filter.getMinPrice()));
        JTextField priceMax = new JTextField(
                filter.getMaxPrice() == Double.MAX_VALUE ? "" : String.valueOf(filter.getMaxPrice()));
        JCheckBox inStock = new JCheckBox("Only show vehicles in stock", filter.getInStock());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc(0, 0, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5));

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
        addLabeledField("", inStock, panel, gbc, 0, 5);

        int result = JOptionPane.showConfirmDialog(null, panel, "Filter Inventory", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            applyFilters(carFilter.isSelected(), motorcycleFilter.isSelected(), makeFilter.getText(),
                    modelFilter.getText(), colorFilter.getText(), typeFilter.getText(), yearMin.getText(),
                    yearMax.getText(), priceMin.getText(), priceMax.getText(), inStock.isSelected());
        }
    }

    /**
     * Apply the filters to the inventory.
     * 
     * @param carSelected        whether cars are selected
     * @param motorcycleSelected whether motorcycles are selected
     * @param make               the make of the vehicle
     * @param model              the model of the vehicle
     * @param color              the color of the vehicle
     * @param type               the type of the vehicle
     * @param yearMin            the minimum year of the vehicle
     * @param yearMax            the maximum year of the vehicle
     * @param priceMin           the minimum price of the vehicle
     * @param priceMax           the maximum price of the vehicle
     * @param inStock            show only vehicles in stock
     */
    private void applyFilters(boolean carSelected, boolean motorcycleSelected, String make, String model, String color,
            String type, String yearMin, String yearMax, String priceMin, String priceMax, boolean inStock) {
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
        filter.setInStock(inStock);
        table.populate(filter);
    }

    /**
     * Enquire about the selected vehicle.
     */
    private void enquire() {
        if (table.get() == null) {
            JOptionPane.showMessageDialog(null, "Please select a vehicle to enquire about.");
            return;
        }
        Vehicle vehicle = table.get();

        JTextArea messageField = new JTextArea(5, 20);
        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        JScrollPane messageScrollPane = new JScrollPane(messageField);

        JTextField contactField = new JTextField();
        contactField.setPreferredSize(new java.awt.Dimension(200, contactField.getPreferredSize().height));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc(0, 0, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5));
        addLabeledField("Message:", messageScrollPane, panel, gbc, 0, 0);
        addLabeledField("Contact Info:", contactField, panel, gbc, 0, 1);

        int result = JOptionPane.showConfirmDialog(null, panel, "Enquire about Vehicle", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String message = messageField.getText();
            String contactInfo = contactField.getText();
            User currentUser = Main.getCurrentUser();

            m_dealership.addEnquiry(vehicle, currentUser, message, contactInfo);
            JOptionPane.showMessageDialog(null, "Enquiry successfully sent. A salesperson will contact you shortly.");
        }
    }

    /**
     * Edit the selected vehicle.
     */
    private void editSelectedVehicle() {
        if (table.get() == null) {
            JOptionPane.showMessageDialog(null, "Please select a vehicle to edit.");
            return;
        }
        Vehicle vehicle = table.get();
        JPanel panel = createVehicleEditPanel(vehicle, vehicle instanceof Car);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Vehicle", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            updateVehicleFromPanel(vehicle, panel);
            table.populate(filter);
            JOptionPane.showMessageDialog(null, "Vehicle information successfully updated:\n" + vehicle.toString());
        }
    }

    /**
     * Delete the selected vehicle.
     */
    private void deleteSelectedVehicle() {
        if (table.get() == null) {
            JOptionPane.showMessageDialog(null, "Please select a vehicle to delete.");
            return;
        }
        Vehicle vehicle = table.get();
        String message = "Are you sure you want to delete this vehicle?\n\n" + vehicle.toString();

        int result = JOptionPane.showConfirmDialog(null, message, "Confirm Delete", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            m_dealership.removeVehicle(vehicle);
            table.populate(filter);
            JOptionPane.showMessageDialog(null, "Vehicle successfully deleted:\n" + vehicle.toString());
        }
    }

    /**
     * Sell the selected vehicle.
     */
    private void sellSelectedVehicle() {
        if (table.get() == null) {
            JOptionPane.showMessageDialog(null, "Please select a vehicle to sell.");
            return;
        }
        Vehicle vehicle = table.get();
        JTextField buyerNameField = new JTextField();
        JTextField buyerContactField = new JTextField();

        buyerNameField.setPreferredSize(new java.awt.Dimension(100, buyerNameField.getPreferredSize().height));
        buyerContactField.setPreferredSize(new java.awt.Dimension(100, buyerContactField.getPreferredSize().height));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc(0, 0, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5));
        addLabeledField("Buyer Name:", buyerNameField, panel, gbc, 0, 0);
        addLabeledField("Buyer Contact:", buyerContactField, panel, gbc, 0, 1);

        int result = JOptionPane.showConfirmDialog(null, panel, "Sell Vehicle", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String buyerName = buyerNameField.getText();
            String buyerContact = buyerContactField.getText();
            User currentUser = Main.getCurrentUser();

            m_dealership.sellVehicle(vehicle, currentUser, buyerName, buyerContact);
            table.populate(filter);
            JOptionPane.showMessageDialog(null, "Vehicle successfully sold:\n" + vehicle.toString());
        }
    }

    /**
     * Add a new vehicle to the inventory.
     */
    private void addVehicle() {
        String[] options = { "Car", "Motorcycle" };
        int choice = JOptionPane.showOptionDialog(null, "Would you like to add a car or a motorcycle?",
                "Select Vehicle Type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            return; // User closed the dialog
        }

        boolean isCar = choice == 0;
        JPanel panel = createVehicleEditPanel(null, isCar);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Vehicle", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Vehicle vehicle = createVehicleFromPanel(panel, isCar);
            table.populate(filter);
            JOptionPane.showMessageDialog(null, "Vehicle successfully added:\n" + vehicle.toString());
        }
    }

    /**
     * Add the selected vehicle to the user's wishlist.
     */
    private void wishlistSelectedVehicle() {
        if (table.get() == null) {
            JOptionPane.showMessageDialog(null, "Please select a vehicle to add to your wishlist.");
            return;
        }
        Main.user.addToWishlist(table.get());
    }

    /**
     * Create a new vehicle from the specified panel.
     * 
     * @param panel the panel containing the vehicle information
     * @param isCar whether the vehicle is a car
     * @return the new vehicle
     */
    private Vehicle createVehicleFromPanel(JPanel panel, boolean isCar) {
        String make = ((JTextField) panel.getComponent(1)).getText();
        String model = ((JTextField) panel.getComponent(3)).getText();
        String color = ((JTextField) panel.getComponent(5)).getText();
        int year = Integer.parseInt(((JTextField) panel.getComponent(7)).getText());
        double price = Double.parseDouble(((JTextField) panel.getComponent(9)).getText());
        int stock = Integer.parseInt(((JTextField) panel.getComponent(13)).getText());
        String typeOrHandlebar = ((JTextField) panel.getComponent(11)).getText();

        if (isCar) {
            return m_dealership.addCar(make, model, color, year, price, stock, typeOrHandlebar);
        } else {
            return m_dealership.addMotorcycle(make, model, color, year, price, stock, typeOrHandlebar);
        }
    }

    /**
     * Update the specified vehicle with the information from the panel.
     * 
     * @param vehicle the vehicle to update
     * @param panel   the panel containing the vehicle information
     */
    private void updateVehicleFromPanel(Vehicle vehicle, JPanel panel) {
        m_dealership.editVehicle(vehicle,
                ((JTextField) panel.getComponent(1)).getText(),
                ((JTextField) panel.getComponent(3)).getText(),
                ((JTextField) panel.getComponent(5)).getText(),
                Integer.parseInt(((JTextField) panel.getComponent(7)).getText()),
                Double.parseDouble(((JTextField) panel.getComponent(9)).getText()),
                Integer.parseInt(((JTextField) panel.getComponent(13)).getText()),
                ((JTextField) panel.getComponent(11)).getText());
    }

    /**
     * Create a panel for editing a vehicle.
     * 
     * @param vehicle the vehicle to edit
     * @param isCar   whether the vehicle is a car
     * @return the panel for editing the vehicle
     */
    private JPanel createVehicleEditPanel(Vehicle vehicle, boolean isCar) {
        JTextField txtMake = new JTextField(vehicle != null ? vehicle.getMake() : "");
        JTextField txtModel = new JTextField(vehicle != null ? vehicle.getModel() : "");
        JTextField txtColor = new JTextField(vehicle != null ? vehicle.getColor() : "");
        JTextField txtYear = new JTextField(vehicle != null ? Integer.toString(vehicle.getYear()) : "");
        JTextField txtPrice = new JTextField(vehicle != null ? Double.toString(vehicle.getPrice()) : "");
        JTextField txtTypeOrHandlebar = new JTextField(
                vehicle != null ? (isCar ? ((Car) vehicle).getType() : ((Motorcycle) vehicle).getType()) : "");
        JTextField txtStock = new JTextField(vehicle != null ? Integer.toString(vehicle.getStock()) : "");

        // Set preferred size for each JTextField
        txtMake.setPreferredSize(new java.awt.Dimension(100, txtMake.getPreferredSize().height));
        txtModel.setPreferredSize(new java.awt.Dimension(100, txtModel.getPreferredSize().height));
        txtColor.setPreferredSize(new java.awt.Dimension(100, txtColor.getPreferredSize().height));
        txtYear.setPreferredSize(new java.awt.Dimension(100, txtYear.getPreferredSize().height));
        txtPrice.setPreferredSize(new java.awt.Dimension(100, txtPrice.getPreferredSize().height));
        txtTypeOrHandlebar.setPreferredSize(new java.awt.Dimension(100, txtTypeOrHandlebar.getPreferredSize().height));
        txtPrice.setPreferredSize(new java.awt.Dimension(100, txtStock.getPreferredSize().height));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc(0, 0, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5));

        addLabeledField("Make:", txtMake, panel, gbc, 0, 0);
        addLabeledField("Model:", txtModel, panel, gbc, 0, 1);
        addLabeledField("Color:", txtColor, panel, gbc, 0, 2);
        addLabeledField("Year:", txtYear, panel, gbc, 0, 3);
        addLabeledField("Price:", txtPrice, panel, gbc, 0, 4);
        addLabeledField(isCar ? "Type:" : "Handlebar Type:", txtTypeOrHandlebar, panel, gbc, 0, 5);
        addLabeledField("Stock:", txtStock, panel, gbc, 0, 6);

        return panel;
    }

    /**
     * Add a labeled field to the specified panel.
     * 
     * @param label the label text
     * @param field the field component
     * @param panel the panel to add the field to
     * @param gbc   the GridBagConstraints for the field
     * @param x     the x position of the field
     * @param y     the y position of the field
     */
    private void addLabeledField(String label, JComponent field, JPanel panel, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x * 2;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = x * 2 + 1;
        panel.add(field, gbc);
    }

    /**
     * Add a labeled field to the specified panel.
     * 
     * @param label the label text
     * @param field the field component
     * @param panel the panel to add the field to
     * @param gbc   the GridBagConstraints for the field
     * @param x     the x position of the field
     * @param y     the y position of the field
     */
    private void addLabeledField(String label, JCheckBox field, JPanel panel, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x * 2;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = x * 2 + 1;
        panel.add(field, gbc);
    }

    /**
     * Add a labeled field to the specified panel.
     * 
     * @param label the label text
     * @param field the field component
     * @param panel the panel to add the field to
     * @param gbc   the GridBagConstraints for the field
     * @param x     the x position of the field
     * @param y     the y position of the field
     */
    private void addLabeledField(String label, JTextField field, JPanel panel, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x * 2;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = x * 2 + 1;
        panel.add(field, gbc);
    }

    /**
     * Add a labeled field to the specified panel.
     * 
     * @param label  the label text
     * @param field1 the first field component
     * @param field2 the second field component
     * @param panel  the panel to add the fields to
     * @param gbc    the GridBagConstraints for the fields
     * @param x      the x position of the fields
     * @param y      the y position of the fields
     */
    private void addLabeledField(String label, JTextField field1, JTextField field2, JPanel panel,
            GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x * 2;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = x * 2 + 1;
        panel.add(field1, gbc);

        gbc.gridx = x * 2 + 2;
        panel.add(field2, gbc);
    }

    private GridBagConstraints createGbc(int gridx, int gridy, int fill, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.fill = fill;
        gbc.insets = insets;
        return gbc;
    }
}