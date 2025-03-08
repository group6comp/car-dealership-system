package carDealership;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class SalesTableModel extends DefaultTableModel {
    private List<Sale> sales;
    private static final String[] COLUMN_NAMES = {"Vehicle ID", "Make", "Model", "Buyer Name", "Buyer Contact", "Sale Date"};

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

    public Sale getSaleAt(int row) {
        return sales.get(row);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Make table cells non-editable
    }
}