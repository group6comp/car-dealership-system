package carDealership;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
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

public class ApproveTransactionsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private SalesTableModel model;
    private Dealership m_dealership = Main.m_dealership;

    /**
     * Create the panel.
     */
    public ApproveTransactionsPanel() {

        // Set the border and layout for the panel
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        // Create and add the title label
        JLabel lblTitle = new JLabel("Approve Transactions");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        // Create and add the scroll pane for the table
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        // Create the table with non-editable cells
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        scrollPane.setViewportView(table);

        // Populate the table with pending sales
        populateTable();

        // Create and add the button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        // Set up the grid bag constraints for the buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the buttons to the button panel
        addButton("Approve", buttonPanel, gbc, 0, 0, e -> approveSelectedTransaction());
        addButton("Reject", buttonPanel, gbc, 1, 0, e -> rejectSelectedTransaction());
    }

    /**
     * Populate the table with pending sales.
     */
    private void populateTable() {
        // Get the list of pending sales
        List<Sale> sales = m_dealership.getSales().stream()
                .filter(Sale::isPending)
                .collect(Collectors.toList());
        // Create the table model with the pending sales
        model = new SalesTableModel(sales);
        table.setModel(model);
        table.setRowSorter(new TableRowSorter<>(model));
    }

    /**
     * Approve the selected transaction.
     */
    private void approveSelectedTransaction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Get the selected sale and approve it
            Sale sale = model.getSaleAt(table.convertRowIndexToModel(selectedRow));
            m_dealership.approveSale(sale);
            // Refresh the table
            populateTable();
        } else {
            // Show a message if no transaction is selected
            JOptionPane.showMessageDialog(this, "Please select a transaction to approve.");
        }
    }

    /**
     * Reject the selected transaction.
     */
    private void rejectSelectedTransaction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Get the selected sale and reject it
            Sale sale = model.getSaleAt(table.convertRowIndexToModel(selectedRow));
            m_dealership.rejectSale(sale);
            // Refresh the table
            populateTable();
        } else {
            // Show a message if no transaction is selected
            JOptionPane.showMessageDialog(this, "Please select a transaction to reject.");
        }
    }

    /**
     * Add a button to the panel.
     * 
     * @param text           the text of the button
     * @param panel          the panel to add the button to
     * @param gbc            the grid bag constraints
     * @param x              the x position in the grid
     * @param y              the y position in the grid
     * @param actionListener the action listener for the button
     */
    private void addButton(String text, JPanel panel, GridBagConstraints gbc, int x, int y,
            ActionListener actionListener) {
        gbc.gridx = x;
        gbc.gridy = y;
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(241, 57, 83));
        panel.add(btn, gbc);
        btn.addActionListener(actionListener);
    }
}