package carDealership;

import carDealership.User.Role;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The ManageUsersPanel class is used to manage and edit user information.
 */
public class ManageUsersPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private UserTableModel model;
    private Dealership m_dealership = Main.m_dealership;

    /**
     * Constructor for creating the ManageUsersPanel.
     */
    public ManageUsersPanel() {

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        // Add title label
        JLabel lblTitle = new JLabel("Manage Users");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        // Add scroll pane for the table
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        // Create the table and make cells non-editable
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        scrollPane.setViewportView(table);

        // Populate the table with user data
        populateTable();

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add buttons to the button panel
        addButton("Add User", buttonPanel, gbc, 0, 0, e -> addUser());
        addButton("Edit User", buttonPanel, gbc, 1, 0, e -> editUser());
        addButton("Delete User", buttonPanel, gbc, 2, 0, e -> deleteUser());
        addButton("Back", buttonPanel, gbc, 3, 0, e -> Main.showMainUI());
    }

    /**
     * Populate the table with user data.
     */
    private void populateTable() {
        List<User> users = m_dealership.getUsers();
        model = new UserTableModel(users);
        table.setModel(model);
        table.setRowSorter(new TableRowSorter<>(model));
    }

    /**
     * Add a button to the specified panel.
     * 
     * @param text the text of the button
     * @param panel the panel to add the button to
     * @param gbc the GridBagConstraints for the button
     * @param x the x position of the button
     * @param y the y position of the button
     * @param actionListener the ActionListener for the button
     */
    private void addButton(String text, JPanel panel, GridBagConstraints gbc, int x, int y, ActionListener actionListener) {
        gbc.gridx = x;
        gbc.gridy = y;
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        panel.add(btn, gbc);
        btn.addActionListener(actionListener);
    }

    /**
     * Edit the selected user.
     */
    private void editUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            User user = model.getUserAt(selectedRow);

            // Get the current values
            String username = user.getUsername();
            String password = user.getPassword();
            Role role = user.getRole();

            // Create text fields for editing
            JTextField txtUsername = new JTextField(username);
            JTextField txtPassword = new JTextField(password);
            JTextField txtRole = new JTextField(role.toString());

            // Create a panel to hold the text fields
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 2));
            panel.add(new JLabel("Username:"));
            panel.add(txtUsername);
            panel.add(new JLabel("Password:"));
            panel.add(txtPassword);
            panel.add(new JLabel("Role:"));
            panel.add(txtRole);

            // Show the dialog
            int result = JOptionPane.showConfirmDialog(null, panel, "Edit User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                // Update the user and table
                user.setUsername(txtUsername.getText());
                user.setPassword(txtPassword.getText());
                user.setRole(Role.valueOf(txtRole.getText().toUpperCase()));
                populateTable();

                // Show confirmation dialog with new details
                JOptionPane.showMessageDialog(null, "User information successfully saved:\n" +
                        "Username: " + txtUsername.getText() + "\n" +
                        "Password: " + txtPassword.getText() + "\n" +
                        "Role: " + txtRole.getText());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a user to edit.");
        }
    }

    /**
     * Delete the selected user.
     */
    private void deleteUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Get the current user
            User user = model.getUserAt(selectedRow);

            // Show confirmation dialog
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the following user?\n" +
                    "Username: " + user.getUsername(), "Delete User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                // Remove the user from the user list
                m_dealership.getUsers().remove(user);
                populateTable();

                // Show confirmation dialog
                JOptionPane.showMessageDialog(null, "User successfully deleted.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a user to delete.");
        }
    }

    /**
     * Add a new user to the system.
     */
    private void addUser() {
        // Create text fields for adding a new user
        JTextField txtUsername = new JTextField();
        JTextField txtPassword = new JTextField();
        JTextField txtRole = new JTextField();

        // Create a panel to hold the text fields
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("Username:"));
        panel.add(txtUsername);
        panel.add(new JLabel("Password:"));
        panel.add(txtPassword);
        panel.add(new JLabel("Role:"));
        panel.add(txtRole);

        // Show the dialog
        int result = JOptionPane.showConfirmDialog(null, panel, "Add User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Add the new user to the user list
            User newUser = new User(txtUsername.getText(), txtPassword.getText(), Role.valueOf(txtRole.getText().toUpperCase()));
            m_dealership.getUsers().add(newUser);
            populateTable();

            // Show confirmation dialog with new details
            JOptionPane.showMessageDialog(null, "New user successfully added:\n" +
                    "Username: " + txtUsername.getText() + "\n" +
                    "Password: " + txtPassword.getText() + "\n" +
                    "Role: " + txtRole.getText());
        }
    }
}