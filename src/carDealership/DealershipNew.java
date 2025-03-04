package carDealership;

import java.util.ArrayList;
import java.util.List;

/**
 * Renamed from "Dealership" to "DealershipNew"
 */
public class DealershipNew {
    private String name;
    private String location;
    private List<Vehicle> inventory;

    public DealershipNew(String name, String location) {
        this.name = name;
        this.location = location;
        inventory = new ArrayList<>();
    }

    // Add vehicle
    public void addVehicle(Vehicle v) {
        inventory.add(v);
    }

    public List<Vehicle> getInventory() {
        return inventory;
    }

    public String displayInventory() {
        if (inventory.isEmpty()) return "No vehicles in inventory.";
        StringBuilder sb = new StringBuilder("Current Inventory:\n");
        for (Vehicle v : inventory) {
            sb.append("ID: ").append(v.getId()).append(" | ")
              .append(v.getMake()).append(" ").append(v.getModel())
              .append(" (").append(v.getYear()).append(") $").append(v.getPrice());
            if (v instanceof Car) {
                sb.append(" [").append(((Car)v).getType()).append("]");
            } else if (v instanceof Motorcycle) {
                sb.append(" [").append(((Motorcycle)v).getHandlebarType()).append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

