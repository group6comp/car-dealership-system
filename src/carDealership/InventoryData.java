package carDealership;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryData {
    private static List<Vehicle> inventoryList = new ArrayList<>();
    private static final String CSV_FILE = "src/data/inventory.csv";

    // Static block to initialize the inventory list from the CSV file
    static {
        loadInventoryFromCSV();
    }

    // Load inventory from the CSV file
    private static void loadInventoryFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            String csvSplitBy = ",";
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] vehicleData = line.split(csvSplitBy);
                String type = vehicleData[6]; // Assuming the 6th column is the type
                Vehicle vehicle;
                if (type.equalsIgnoreCase("car")) {
                    vehicle = new Car(Integer.parseInt(vehicleData[0]), vehicleData[1], vehicleData[2], vehicleData[3], Integer.parseInt(vehicleData[4]), Double.parseDouble(vehicleData[5]), vehicleData[6]);
                } else if (type.equalsIgnoreCase("motorcycle")) {
                    vehicle = new Motorcycle(Integer.parseInt(vehicleData[0]), vehicleData[1], vehicleData[2], vehicleData[3], Integer.parseInt(vehicleData[4]), Double.parseDouble(vehicleData[5]), vehicleData[6]);
                } else {
                    continue; // Skip unknown types
                }
                inventoryList.add(vehicle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateCSV() {
        try (FileWriter writer = new FileWriter(CSV_FILE)) {
            // Write the header
            writer.append("ID,Make,Model,Color,Year,Price,Type\n");
            // Write the data
            for (Vehicle vehicle : inventoryList) {
                writer.append(String.valueOf(vehicle.getId())).append(",")
                      .append(vehicle.getMake()).append(",")
                      .append(vehicle.getModel()).append(",")
                      .append(vehicle.getColor()).append(",")
                      .append(String.valueOf(vehicle.getYear())).append(",")
                      .append(String.valueOf(vehicle.getPrice())).append(",")
                      .append(vehicle instanceof Car ? "car" : "motorcycle").append(",")
                      .append(vehicle instanceof Car ? ((Car) vehicle).getType() : ((Motorcycle) vehicle).getHandlebarType()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addVehicle(Vehicle vehicle) {
        inventoryList.add(vehicle);
        updateCSV();
    }

    public static void removeVehicle(Vehicle vehicle) {
        inventoryList.remove(vehicle);
        updateCSV();
    }

    public static Vehicle getVehicleById(int id) {
        for (Vehicle vehicle : inventoryList) {
            if (vehicle.getId() == id) {
                return vehicle;
            }
        }
        return null;
    }

    public static Vehicle[] getVehicles() {
        return inventoryList.toArray(new Vehicle[0]);
    }

    public static void updateVehicle(int id, String make, String model, String color, int year, double price, String type) {
        Vehicle vehicle = getVehicleById(id);
        if (vehicle != null) {
            vehicle.setMake(make);
            vehicle.setModel(model);
            vehicle.setColor(color);
            vehicle.setYear(year);
            vehicle.setPrice(price);
            if (vehicle instanceof Car) {
                ((Car) vehicle).setType(type);
            } else if (vehicle instanceof Motorcycle) {
                ((Motorcycle) vehicle).setHandlebarType(type);
            }
            updateCSV();
        }
    }


}