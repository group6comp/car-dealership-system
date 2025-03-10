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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import java.util.stream.Collectors;

public class ApproveTransactionsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private SalesTableModel model;
    private Dealership m_dealership = Main.m_dealership;

    /**
     * Create the panel.
     */
    public ApproveTransactionsPanel(JPanel parentPanel, CardLayout cardLayout) {
        this.contentPane = parentPanel;
        this.cardLayout = cardLayout;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Approve Transactions");
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

        addButton("Approve", buttonPanel, gbc, 0, 0, e -> approveSelectedTransaction());
        addButton("Reject", buttonPanel, gbc, 1, 0, e -> rejectSelectedTransaction());
        addButton("Back", buttonPanel, gbc, 2, 0, e -> Main.showMainUI());
    }

    private void populateTable() {
        List<Sale> sales = m_dealership.getSales().stream()
                .filter(Sale::isPending)
                .collect(Collectors.toList());
        model = new SalesTableModel(sales);
        table.setModel(model);
        table.setRowSorter(new TableRowSorter<>(model));
    }

    private void approveSelectedTransaction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Sale sale = model.getSaleAt(table.convertRowIndexToModel(selectedRow));
            m_dealership.approveSale(sale);
            populateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a transaction to approve.");
        }
    }

    private void rejectSelectedTransaction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Sale sale = model.getSaleAt(table.convertRowIndexToModel(selectedRow));
            m_dealership.rejectSale(sale);
            populateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a transaction to reject.");
        }
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