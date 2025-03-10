package carDealership;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dealership implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String location;
    private List<Vehicle> inventory;
    private int maxInventory;
    private List<Sale> sales;
    private List<User> userData;
    private List<Enquiry> enquiries;
    private int nextId = 1; // Add nextId attribute

    public Dealership(String name, String location, int maxInventory) {
        this.name = name;
        this.maxInventory = maxInventory;
        inventory = new ArrayList<>(maxInventory);
        sales = new ArrayList<>();
        userData = new ArrayList<>();
        enquiries = new ArrayList<>();
    }

    // DEALERSHIP INFORMATION METHODS
    public void getInfo() {
        System.out.printf("Name: [%s]\nLocation: [%s]\nInventory Size: [%d]\n", name, location, inventory.size());
    }

    public String getInfoGUI() {
        return String.format("Dealership name: [%s]\nLocation: [%s]\nInventory Size: [%d]\n\nAvailable space: %d\nTotal Cars: %d\nTotal Motorcycles: %d\n\nTotal sales profit: %d\nTotal vehicles sold: %d",
                name, location, inventory.size(), inventory.size(), getTotalCars(), getTotalMotorcycles(), salesProfit(), sales.size());
    }

    // USER METHODS
    public List<User> getUsers() {
        return userData;
    }

    public User getUser(String username) {
        for (User user : userData) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        userData.add(user);
        save();
    }

    public void removeUser(User user) {
        userData.remove(user);
        save();
    }

    public void editUser(User user, String username, String password, User.Role role) {
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        save();
    }

    // INVENTORY METHODS
    public List<Vehicle> getVehicles() {
        return inventory;
    }

    public Vehicle getVehicle(int id) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getId() == id) {
                return vehicle;
            }
        }
        System.out.println("Vehicle with id: " + id + " not found.");
        return null;
    }

    public Vehicle addCar(String make, String model, String color, int year, double price, int mileage, Vehicle.Status status, String type) {
        if (isFull()) {
            System.out.println("Failed to add vehicle: inventory is full.");
            return null;
        }
        Car car = new Car(nextId++, make, model, color, year, price, mileage, status, type);
        inventory.add(car);
        save();
        return car;
    }

    public Vehicle addMotorcycle(String make, String model, String color, int year, double price, int mileage, Vehicle.Status status, String handlebarType) {
        if (isFull()) {
            System.out.println("Failed to add vehicle: inventory is full.");
            return null;
        }
        Motorcycle motorcycle = new Motorcycle(nextId++, make, model, color, year, price, mileage, status, handlebarType);
        inventory.add(motorcycle);
        save();
        return motorcycle;
    }

    public void removeVehicle(Vehicle vehicle) {
        if (inventory.remove(vehicle)) {
            save();
        } else {
            System.out.println("Unable to remove vehicle.");
        }
    }

    public void editVehicle(Vehicle vehicle, String make, String model, String color, int year, double price, 
                        int mileage, String status, String type) {
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setColor(color);
        vehicle.setYear(year);
        vehicle.setPrice(price);
        vehicle.setMileage(mileage);
        vehicle.setStatus(Vehicle.Status.valueOf(status.toUpperCase()));
        if (vehicle instanceof Car) {
            ((Car) vehicle).setType(type);
        } else if (vehicle instanceof Motorcycle) {
            ((Motorcycle) vehicle).setType(type);
        }
        save();
    }

    public void sellVehicle(Vehicle vehicle, User salesperson, String buyerName, String buyerContact) {
        vehicle.setStatus(Vehicle.Status.SOLD);
        sales.add(new Sale(vehicle, salesperson, buyerName, buyerContact, LocalDate.now()));
        save();
    }

    public void sellVehicle(Vehicle vehicle, User salesperson, String buyerName, String buyerContact, LocalDate saleDate) {
        vehicle.setStatus(Vehicle.Status.SOLD);
        sales.add(new Sale(vehicle, salesperson, buyerName, buyerContact, saleDate));
        save();
    }

    public void sellVehicle(Vehicle vehicle, User salesperson, String buyerName, String buyerContact, LocalDate saleDate, boolean pending) {
        vehicle.setStatus(Vehicle.Status.SOLD);
        sales.add(new Sale(vehicle, salesperson, buyerName, buyerContact, saleDate, pending));
        save();
    }

    public void approveSale(Sale sale) {
        sale.getVehicle().setStatus(Vehicle.Status.SOLD);
        sale.setPending(false);
        save();
    }

    public void rejectSale(Sale sale) {
        sale.getVehicle().setStatus(Vehicle.Status.AVAILABLE);
        sales.remove(sale);
        save();
    }

    public void vehicleMaintenance(Vehicle vehicle) {
        vehicle.setStatus(Vehicle.Status.MAINTENANCE);
        save();
    }

    // SALES INFORMATION
    public List<Sale> getSales() {
        return sales;
    }

    public List<Sale> getSales(User salesperson) {
        List<Sale> salespersonSales = new ArrayList<>();
        for (Sale sale : sales) {
            if (sale.getSalesperson().equals(salesperson)) {
                salespersonSales.add(sale);
            }
        }
        return salespersonSales;
    }

    private int salesProfit() {
        int totalProfit = 0;
        for (Sale sale : sales) {
            totalProfit += sale.getVehicle().getPrice();
        }
        return totalProfit;
    }

    public double getInventoryGrossValue() {
        double totalValue = 0;
        for (Vehicle vehicle : inventory) {
            totalValue += vehicle.getPrice();
        }
        return totalValue;
    }

    public int getTotalCars() {
        int totalCars = 0;
        for (Vehicle vehicle : inventory) {
            if (vehicle instanceof Car) {
                totalCars++;
            }
        }
        return totalCars;
    }

    public int getTotalMotorcycles() {
        int totalMotorcycles = 0;
        for (Vehicle vehicle : inventory) {
            if (vehicle instanceof Motorcycle) {
                totalMotorcycles++;
            }
        }
        return totalMotorcycles;
    }

    public void displayAll() {
        System.out.println("Inventory Details:");
        System.out.println("Total inventory value: " + getInventoryGrossValue());
        for (Vehicle vehicle : inventory) {
            System.out.println("-------------------");
            vehicle.displayInfo();
            System.out.println("-------------------");
        }
        System.out.printf("Size: [%d/%d]\n", inventory.size(), inventory.size());
    }

    public String displayAlls() {
        StringBuilder sb = new StringBuilder("Inventory Details:\n");
        sb.append("Total inventory value: ").append(getInventoryGrossValue()).append("\n");

        for (Vehicle vehicle : inventory) {
            sb.append("-------------------\n");
            sb.append(vehicle.toString()).append("\n");
        }
        sb.append("-------------------\nSize: [").append(inventory.size()).append("/").append(inventory.size()).append("]\n");
        return sb.toString();
    }

    public boolean isFull() {
        return inventory.size() == maxInventory;
    }

    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    public Vehicle getVehicleFromId(int id) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getId() == id) {
                return vehicle;
            }
        }
        return null;
    }

    public void displaySalesHistory() {
        System.out.println("Sales History:");
        if (sales.isEmpty()) {
            System.out.println("No sales recorded.");
            return;
        }
        for (Sale sale : sales) {
            System.out.println("-------------------");
            sale.getVehicle().displayInfo();
            System.out.println("Buyer Name: " + sale.getBuyerName());
            System.out.println("Buyer Contact: " + sale.getBuyerContact());
            System.out.println("Sale Date: " + sale.getSaleDate());
            System.out.println("-------------------");
        }
    }

    public String showSalesHistory() {
        if (sales.isEmpty()) {
            return "No sales recorded.";
        }

        StringBuilder sb = new StringBuilder("Sales History:\n");
        for (Sale sale : sales) {
            sb.append("-------------------\n");
            sb.append(sale.getVehicle().toString()).append("\n");
            sb.append("Buyer Name: ").append(sale.getBuyerName()).append("\n");
            sb.append("Buyer Contact: ").append(sale.getBuyerContact()).append("\n");
            sb.append("Sale Date: ").append(sale.getSaleDate()).append("\n");
        }
        sb.append("-------------------\n");
        return sb.toString();
    }

    public List<Car> searchCar(String type) {
        List<Car> types = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle instanceof Car && ((Car) vehicle).getType().equalsIgnoreCase(type)) {
                types.add((Car) vehicle);
            }
        }
        if (types.isEmpty()) {
            System.out.println("Sorry, didn't find car with type: " + type);
        }
        return types;
    }

    public int carBudget(double budget) {
        int total = 0;
        boolean notChecked = true;

        for (Vehicle vehicle : inventory) {
            if (vehicle instanceof Car) {
                notChecked = false;
                if (vehicle.getPrice() <= budget) {
                    vehicle.displayInfo();
                    System.out.println("---------------------");
                    total++;
                }
            }
        }
        if (total == 0) {
            if (notChecked) {
                System.out.println("Sorry, there are no cars.");
            } else {
                System.out.println("No cars found within the budget of [" + budget + " SAR].");
            }
        }
        return total;
    }

    public List<Car> carsWithinBudget(double budget) {
        List<Car> cars = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle instanceof Car && vehicle.getPrice() <= budget) {
                cars.add((Car) vehicle);
            }
        }
        return cars;
    }

    public void addEnquiry(Vehicle vehicle, User user, String message, String contact) {
        Enquiry enquiry = new Enquiry(vehicle, user, message, contact);
        enquiries.add(enquiry);
        save();
    }

    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
        save();
    }

    public List<Enquiry> getEnquiries() {
        return enquiries;
    }
    
    // LOAD AND SAVE METHODS
    public void save() {
        File saveFile = new File("save.data");
        try (FileOutputStream outFileStream = new FileOutputStream(saveFile);
             ObjectOutputStream outObjStream = new ObjectOutputStream(outFileStream)) {
            outObjStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Dealership load() throws IOException, ClassNotFoundException {
        File saveFile = new File("save.data");
        if (!saveFile.exists()) {
            return null;
        }

        try (FileInputStream inFileStream = new FileInputStream(saveFile);
             ObjectInputStream inObjStream = new ObjectInputStream(inFileStream)) {
            return (Dealership) inObjStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}