package carDealership;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.TableRowSorter;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.List;

public class ViewSalesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JPanel parentPanel;
    private CardLayout cardLayout;
    private SalesTableModel model;
    private Dealership m_dealership = Main.m_dealership;

    /**
     * Create the panel.
     */
    public ViewSalesPanel(JPanel parentPanel, CardLayout cardLayout) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblTitle = new JLabel("View Sales");
        lblTitle.setFont(new Font("Dubai Medium", Font.PLAIN, 20));
        lblTitle.setBounds(250, 20, 150, 30);
        add(lblTitle);

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

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(50, 300, 150, 30);
        add(btnBack);

        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(241, 57, 83));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.showRoleUI();
            }
        });

        populateTable();
    }

    private void populateTable() {
        List<Sale> sales = m_dealership.getSales();
        model = new SalesTableModel(sales);
        table.setModel(model);
        table.setRowSorter(new TableRowSorter<>(model));
    }
}