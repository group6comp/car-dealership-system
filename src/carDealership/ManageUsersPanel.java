package carDealership;
import carDealership.User.Role;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageUsersPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private UserTableModel model;
    private Dealership m_dealership = Main.m_dealership;

    public ManageUsersPanel(JPanel contentPane, CardLayout cardLayout) {
        this.contentPane = contentPane;
        this.cardLayout = cardLayout;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblTitle = new JLabel("Manage Users");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(250, 20, 150, 30);
        add(lblTitle);

        addButton("Add User", new int[]{50, 300, 150, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        addButton("Edit User", new int[]{250, 300, 150, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    editUser(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a user to edit.");
                }
            }
        });

        addButton("Delete User", new int[]{450, 300, 150, 30}, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    deleteUser(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a user to delete.");
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
        List<User> users = m_dealership.getUsers();
        model = new UserTableModel(users);
        table.setModel(model);
        table.setRowSorter(new TableRowSorter<>(model));
    }

    private void editUser(int selectedRow) {
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
            user.setRole(txtRole.getText());
            populateTable();

            // Show confirmation dialog with new details
            JOptionPane.showMessageDialog(null, "User information successfully saved:\n" +
                    "Username: " + txtUsername.getText() + "\n" +
                    "Password: " + txtPassword.getText() + "\n" +
                    "Role: " + txtRole.getText());
        }
    }

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
            User newUser = new User(txtUsername.getText(), txtPassword.getText(), User.roleFromString(txtRole.getText()));
            m_dealership.getUsers().add(newUser);
            populateTable();

            // Show confirmation dialog with new details
            JOptionPane.showMessageDialog(null, "New user successfully added:\n" +
                    "Username: " + txtUsername.getText() + "\n" +
                    "Password: " + txtPassword.getText() + "\n" +
                    "Role: " + txtRole.getText());
        }
    }

    private void deleteUser(int selectedRow) {
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