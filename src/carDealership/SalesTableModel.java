package carDealership;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * The SalesTableModel class represents the table model for displaying sales data.
 * It extends the DefaultTableModel class and provides custom functionality for handling sales.
 */
public class SalesTableModel extends DefaultTableModel {
    private List<Sale> sales;
    private static final String[] COLUMN_NAMES = {"Vehicle ID", "Make", "Model", "Buyer Name", "Buyer Contact", "Sale Date"};

    /**
     * Constructor for creating a SalesTableModel with a list of sales.
     * 
     * @param sales the list of sales to be displayed in the table
     */
    public SalesTableModel(List<Sale> sales) {
        super(COLUMN_NAMES, 0);
        this.sales = sales;
        for (Sale sale : sales) {
            addRow(new Object[]{
                sale.getVehicle().getId(),
                sale.getVehicle().getMake(),
                sale.getVehicle().getModel(),
                sale.getBuyerName(),
                sale.getBuyerContact(),
                sale.getSaleDate().toString()
            });
        }
    }

    /**
     * Get the sale at the specified row.
     * 
     * @param row the row index
     * @return the Sale object at the specified row
     */
    public Sale getSaleAt(int row) {
        return sales.get(row);
    }

    /**
     * Override the isCellEditable method to make table cells non-editable.
     * 
     * @param row the row index
     * @param column the column index
     * @return false to make table cells non-editable
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Make table cells non-editable
    }
}