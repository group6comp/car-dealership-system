package carDealership;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The ManageEnquiriesPanel class is used to manage and resolve customer enquiries.
 */
public class ManageEnquiriesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;

    /**
     * Create the panel.
     */
    public ManageEnquiriesPanel() {

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        // Add title label
        JLabel lblTitle = new JLabel("Manage Enquiries");
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

        // Populate the table with enquiries data
        populateTable();

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add buttons to the button panel
        addButton("Resolve", buttonPanel, gbc, 0, 0, e -> resolveSelectedEnquiry());
        addButton("Back", buttonPanel, gbc, 1, 0, e -> Main.showMainUI());
    }

    /**
     * Populate the table with enquiries data.
     */
    private void populateTable() {
        List<Enquiry> enquiries = Main.m_dealership.getEnquiries().stream()
                .filter(enquiry -> enquiry.getStatus() == Enquiry.Status.pending)
                .sorted((e1, e2) -> e1.getDate().compareTo(e2.getDate()))
                .collect(Collectors.toList());

        String[] columnNames = {"Vehicle ID", "User", "Message", "Contact", "Date"};
        model = new DefaultTableModel(columnNames, 0);

        for (Enquiry enquiry : enquiries) {
            Object[] row = {
                enquiry.getVehicle().getId(),
                enquiry.getUser().getUsername(),
                enquiry.getMessage(),
                enquiry.getContact(),
                enquiry.getDate().toString()
            };
            model.addRow(row);
        }

        table.setModel(model);
        table.setRowSorter(new TableRowSorter<>(model));
    }

    /**
     * Resolve the selected enquiry.
     */
    private void resolveSelectedEnquiry() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Enquiry enquiry = Main.m_dealership.getEnquiries().get(table.convertRowIndexToModel(selectedRow));
            enquiry.setStatus(Enquiry.Status.resolved);
            populateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an enquiry to resolve.");
        }
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
}