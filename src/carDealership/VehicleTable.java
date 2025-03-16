package carDealership;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.util.List;

/**
 * The VehicleTable class represents the table model for displaying vehicle data.
 * It extends the JTable class and provides custom functionality for handling vehicles.
 */
public class VehicleTable extends JTable {
    private static final long serialVersionUID = 1L;
    private List<Vehicle> vehicles = Main.m_dealership.getVehicles();
    private String[] columnNames = {"ID", "Make", "Model", "Color", "Year", "Price", "Type", "Stock"};
    private DefaultTableModel tableModel;

    /**
     * Constructor for creating a VehicleTable with a list of vehicles and column names.
     * 
     * @param vehicles the list of vehicles to be displayed in the table
     * @param columnNames the names of the columns
     */
    public VehicleTable(Filter filter) {
        populate(filter);
    }

    /**
     * Populate the table with vehicle data.
     */
    public void populate(Filter filter) {
        this.tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        List<Vehicle> vehicles = filter.filterInventory();
        for (Vehicle vehicle : vehicles) {
            tableModel.addRow(new Object[]{
                vehicle.getId(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getYear(),
                vehicle.getPrice(),
                vehicle.getType(),
                vehicle.getStock(),
            });
        };
        setModel(tableModel);
        this.setRowSorter(new TableRowSorter<>(tableModel));
    }

    public Vehicle get() {
        int selectedRow = getSelectedRow();
        if (selectedRow != -1) {
            return vehicles.get(convertRowIndexToModel(selectedRow));
        }
        return null;
    }

}