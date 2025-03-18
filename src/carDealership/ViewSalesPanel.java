package carDealership;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableRowSorter;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The ViewSalesPanel class represents the panel for viewing sales in the
 * dealership system.
 * It displays a table of sales and includes a back button to return to the main
 * UI.
 */
public class ViewSalesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private SalesTableModel model;
    private Dealership m_dealership = Main.m_dealership;

    /**
     * Constructor for creating the ViewSalesPanel.
     */
    public ViewSalesPanel() {

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        // Add title label
        JLabel lblTitle;
        if (Main.role == User.Role.SALESPERSON) {
            lblTitle = new JLabel("My Sales");
        } else {
            lblTitle = new JLabel("Sales");
        }
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

        // Populate the table with sales data
        populateTable();

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    /**
     * Populate the table with sales data.
     */
    private void populateTable() {
        List<Sale> sales;
        if (Main.role == User.Role.SALESPERSON) {
            sales = m_dealership.getSales(Main.user);
        } else {
            sales = m_dealership.getSales();
        }
        // Filter sales to only include those with pending set to false
        sales = sales.stream()
                .filter(sale -> !sale.isPending())
                .collect(Collectors.toList());
        model = new SalesTableModel(sales);
        table.setModel(model);
        table.setRowSorter(new TableRowSorter<>(model));
    }
}