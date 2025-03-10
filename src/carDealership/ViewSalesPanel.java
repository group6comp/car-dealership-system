package carDealership;

import javax.swing.JPanel;
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

/**
 * The ViewSalesPanel class represents the panel for viewing sales in the dealership system.
 * It displays a table of sales and includes a back button to return to the main UI.
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
            lblTitle = new JLabel("View Sales");
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

        // Add back button to the button panel
        addButton("Back", buttonPanel, gbc, 1, 0, e -> Main.showMainUI());
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