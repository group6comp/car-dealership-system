package carDealership;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

public class ViewWishlistPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private DefaultTableModel model;
    private User currentUser = Main.getCurrentUser();

    /**
     * Create the panel.
     */
    public ViewWishlistPanel(JPanel parentPanel, CardLayout cardLayout) {
        this.contentPane = parentPanel;
        this.cardLayout = cardLayout;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("My Wishlist");
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

        addButton("Enquire", buttonPanel, gbc, 0, 0, e -> enquire());
        addButton("Back", buttonPanel, gbc, 1, 0, e -> Main.showMainUI());
    }

    private void populateTable() {
        List<Vehicle> wishlist = currentUser.getWishlist();
        String[] columnNames = {"ID", "Make", "Model", "Color", "Year", "Price", "Type", "Mileage", "Status"};
        model = new DefaultTableModel(columnNames, 0);

        for (Vehicle vehicle : wishlist) {
            Object[] row = {
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
            model.addRow(row);
        }

        table.setModel(model);
        table.setRowSorter(new TableRowSorter<>(model));
    }

    private void enquire() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Vehicle vehicle = currentUser.getWishlist().get(table.convertRowIndexToModel(selectedRow));
            JTextArea messageField = new JTextArea(5, 20); // 5 rows tall
            JTextField contactField = new JTextField();

            // Set preferred size for each JTextField
            int fieldWidth = 20; // Increase the width to make the input boxes larger
            messageField.setLineWrap(true);
            messageField.setWrapStyleWord(true);
            JScrollPane messageScrollPane = new JScrollPane(messageField);
            contactField.setPreferredSize(new java.awt.Dimension(fieldWidth * 10, contactField.getPreferredSize().height));

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            addLabeledField("Message:", messageScrollPane, panel, gbc, 0, 0);
            addLabeledField("Contact Info:", contactField, panel, gbc, 0, 1);

            int result = JOptionPane.showConfirmDialog(null, panel, "Enquire about Vehicle", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String message = messageField.getText();
                String contactInfo = contactField.getText();
                User currentUser = Main.getCurrentUser();

                Main.m_dealership.addEnquiry(vehicle, currentUser, message, contactInfo);
                JOptionPane.showMessageDialog(null, "Enquiry successfully sent. A salesperson will contact you shortly.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a vehicle to enquire about.");
        }
    }

    private void addLabeledField(String label, JComponent field, JPanel panel, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x * 2;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = x * 2 + 1;
        panel.add(field, gbc);
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
}