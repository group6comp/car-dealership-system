package carDealership;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VehicleTableModel extends DefaultTableModel {
    private List<Vehicle> vehicles;

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

    public Vehicle getVehicleAt(int row) {
        return vehicles.get(row);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Make table cells non-editable
    }
}