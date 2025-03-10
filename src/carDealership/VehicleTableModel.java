package carDealership;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * The VehicleTableModel class represents the table model for displaying vehicle data.
 * It extends the DefaultTableModel class and provides custom functionality for handling vehicles.
 */
public class VehicleTableModel extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    private List<Vehicle> vehicles;

    /**
     * Constructor for creating a VehicleTableModel with a list of vehicles and column names.
     * 
     * @param vehicles the list of vehicles to be displayed in the table
     * @param columnNames the names of the columns
     */
    public VehicleTableModel(List<Vehicle> vehicles, String[] columnNames) {
        super(columnNames, 0);
        this.vehicles = vehicles;
        for (Vehicle vehicle : vehicles) {
            addRow(new Object[]{
                vehicle.getId(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getYear(),
                vehicle.getPrice(),
                vehicle.getType(),
                vehicle.getMileage(),
                vehicle.getStatus()
            });
        }
    }

    /**
     * Get the vehicle at the specified row.
     * 
     * @param row the row index
     * @return the Vehicle object at the specified row
     */
    public Vehicle getVehicleAt(int row) {
        return vehicles.get(row);
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