package carDealership;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Dealership class represents a car dealership.
 * It manages the inventory of vehicles, sales, users, and enquiries.
 */
public class Dealership implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String location;
    private List<Vehicle> inventory;
    private int maxInventory;
    private List<Sale> sales;
    private List<User> userData;
    private List<Enquiry> enquiries;
    private int nextId = 1; // Attribute to generate unique IDs for vehicles

    /**
     * Constructor for creating a Dealership object.
     * 
     * @param name the name of the dealership
     * @param location the location of the dealership
     * @param maxInventory the maximum number of vehicles the dealership can hold
     */
    public Dealership(String name, String location, int maxInventory) {
        this.name = name;
        this.location = location;
        this.maxInventory = maxInventory;
        this.inventory = new ArrayList<>(maxInventory);
        this.sales = new ArrayList<>();
        this.userData = new ArrayList<>();
        this.enquiries = new ArrayList<>();
    }

    // DEALERSHIP INFORMATION METHODS

    /**
     * Print dealership information to the console.
     */
    public void getInfo() {
        System.out.printf("Name: [%s]\nLocation: [%s]\nInventory Size: [%d]\n", name, location, inventory.size());
    }

    /**
     * Get dealership information formatted for GUI display.
     * 
     * @return a string containing dealership information
     */
    public String getInfoGUI() {
        return String.format("Dealership name: [%s]\nLocation: [%s]\nInventory Size: [%d]\n\nAvailable space: %d\nTotal Cars: %d\nTotal Motorcycles: %d\n\nTotal sales profit: %d\nTotal vehicles sold: %d",
                name, location, inventory.size(), maxInventory - inventory.size(), getTotalCars(), getTotalMotorcycles(), salesProfit(), sales.size());
    }

    // USER METHODS

    /**
     * Get the list of users.
     * 
     * @return a list of users
     */
    public List<User> getUsers() {
        return userData;
    }

    /**
     * Get a user by username.
     * 
     * @param username the username of the user
     * @return the user with the specified username, or null if not found
     */
    public User getUser(String username) {
        for (User user : userData) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Add a user to the dealership.
     * 
     * @param user the user to add
     */
    public void addUser(User user) {
        userData.add(user);
        save();
    }

    /**
     * Remove a user from the dealership.
     * 
     * @param user the user to remove
     */
    public void removeUser(User user) {
        userData.remove(user);
        save();
    }

    /**
     * Edit a user's information.
     * 
     * @param user the user to edit
     * @param username the new username
     * @param password the new password
     * @param role the new role
     */
    public void editUser(User user, String username, String password, User.Role role) {
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        save();
    }

    // INVENTORY METHODS

    /**
     * Get the list of vehicles in the inventory.
     * 
     * @return a list of vehicles
     */
    public List<Vehicle> getVehicles() {
        return inventory;
    }

    /**
     * Get a vehicle by ID.
     * 
     * @param id the ID of the vehicle
     * @return the vehicle with the specified ID, or null if not found
     */
    public Vehicle getVehicle(int id) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getId() == id) {
                return vehicle;
            }
        }
        System.out.println("Vehicle with id: " + id + " not found.");
        return null;
    }

    /**
     * Add a car to the inventory.
     * 
     * @param make the make of the car
     * @param model the model of the car
     * @param color the color of the car
     * @param year the year the car was manufactured
     * @param price the price of the car
     * @param mileage the mileage of the car
     * @param status the status of the car (e.g., available, sold)
     * @param type the type of the car (e.g., sedan, SUV)
     * @return the added car, or null if the inventory is full
     */
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

    /**
     * Add a motorcycle to the inventory.
     * 
     * @param make the make of the motorcycle
     * @param model the model of the motorcycle
     * @param color the color of the motorcycle
     * @param year the year the motorcycle was manufactured
     * @param price the price of the motorcycle
     * @param mileage the mileage of the motorcycle
     * @param status the status of the motorcycle (e.g., available, sold)
     * @param handlebarType the type of handlebar of the motorcycle
     * @return the added motorcycle, or null if the inventory is full
     */
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

    /**
     * Remove a vehicle from the inventory.
     * 
     * @param vehicle the vehicle to remove
     */
    public void removeVehicle(Vehicle vehicle) {
        if (inventory.remove(vehicle)) {
            save();
        } else {
            System.out.println("Unable to remove vehicle.");
        }
    }

    /**
     * Edit a vehicle's information.
     * 
     * @param vehicle the vehicle to edit
     * @param make the new make of the vehicle
     * @param model the new model of the vehicle
     * @param color the new color of the vehicle
     * @param year the new year of the vehicle
     * @param price the new price of the vehicle
     * @param mileage the new mileage of the vehicle
     * @param status the new status of the vehicle
     * @param type the new type of the vehicle (for cars) or handlebar type (for motorcycles)
     */
    public void editVehicle(Vehicle vehicle, String make, String model, String color, int year, double price, int mileage, String status, String type) {
        if (vehicle instanceof Car) {
            ((Car) vehicle).update(make, model, color, type, year, price, mileage, Vehicle.Status.valueOf(status.toUpperCase()));
        } else if (vehicle instanceof Motorcycle) {
            ((Motorcycle) vehicle).update(make, model, color, type, year, price, mileage, Vehicle.Status.valueOf(status.toUpperCase()));
        }
        save();
    }

    /**
     * Mark a vehicle as sold and add a sale record.
     * 
     * @param vehicle the vehicle to sell
     * @param salesperson the salesperson who sold the vehicle
     * @param buyerName the name of the buyer
     * @param buyerContact the contact information of the buyer
     */
    public void sellVehicle(Vehicle vehicle, User salesperson, String buyerName, String buyerContact) {
        vehicle.setStatus(Vehicle.Status.SOLD);
        sales.add(new Sale(vehicle, salesperson, buyerName, buyerContact, LocalDate.now()));
        save();
    }

    /**
     * Mark a vehicle as sold and add a sale record with a specified sale date and pending status.
     * 
     * @param vehicle the vehicle to sell
     * @param salesperson the salesperson who sold the vehicle
     * @param buyerName the name of the buyer
     * @param buyerContact the contact information of the buyer
     * @param saleDate the date of the sale
     * @param pending whether the sale is pending approval
     */
    public void sellVehicle(Vehicle vehicle, User salesperson, String buyerName, String buyerContact, LocalDate saleDate, boolean pending) {
        vehicle.setStatus(Vehicle.Status.SOLD);
        sales.add(new Sale(vehicle, salesperson, buyerName, buyerContact, saleDate, pending));
        save();
    }

    /**
     * Approve a pending sale.
     * 
     * @param sale the sale to approve
     */
    public void approveSale(Sale sale) {
        sale.getVehicle().setStatus(Vehicle.Status.SOLD);
        sale.setPending(false);
        save();
    }

    /**
     * Reject a pending sale.
     * 
     * @param sale the sale to reject
     */
    public void rejectSale(Sale sale) {
        sale.getVehicle().setStatus(Vehicle.Status.AVAILABLE);
        sales.remove(sale);
        save();
    }

    /**
     * Mark a vehicle as under maintenance.
     * 
     * @param vehicle the vehicle to mark as under maintenance
     */
    public void vehicleMaintenance(Vehicle vehicle) {
        vehicle.setStatus(Vehicle.Status.MAINTENANCE);
        save();
    }

    // SALES INFORMATION

    /**
     * Get the list of sales.
     * 
     * @return a list of sales
     */
    public List<Sale> getSales() {
        return sales;
    }

    /**
     * Get the list of sales made by a specific salesperson.
     * 
     * @param salesperson the salesperson whose sales to retrieve
     * @return a list of sales made by the specified salesperson
     */
    public List<Sale> getSales(User salesperson) {
        List<Sale> salespersonSales = new ArrayList<>();
        for (Sale sale : sales) {
            if (sale.getSalesperson().equals(salesperson)) {
                salespersonSales.add(sale);
            }
        }
        return salespersonSales;
    }

    /**
     * Calculate the total sales profit.
     * 
     * @return the total sales profit
     */
    private int salesProfit() {
        int totalProfit = 0;
        for (Sale sale : sales) {
            totalProfit += sale.getVehicle().getPrice();
        }
        return totalProfit;
    }

    /**
     * Calculate the total gross value of the inventory.
     * 
     * @return the total gross value of the inventory
     */
    public double getInventoryGrossValue() {
        double totalValue = 0;
        for (Vehicle vehicle : inventory) {
            totalValue += vehicle.getPrice();
        }
        return totalValue;
    }

    /**
     * Get the total number of cars in the inventory.
     * 
     * @return the total number of cars
     */
    public int getTotalCars() {
        int totalCars = 0;
        for (Vehicle vehicle : inventory) {
            if (vehicle instanceof Car) {
                totalCars++;
            }
        }
        return totalCars;
    }

    /**
     * Get the total number of motorcycles in the inventory.
     * 
     * @return the total number of motorcycles
     */
    public int getTotalMotorcycles() {
        int totalMotorcycles = 0;
        for (Vehicle vehicle : inventory) {
            if (vehicle instanceof Motorcycle) {
                totalMotorcycles++;
            }
        }
        return totalMotorcycles;
    }

    /**
     * Check if the inventory is full.
     * 
     * @return true if the inventory is full, false otherwise
     */
    public boolean isFull() {
        return inventory.size() == maxInventory;
    }

    /**
     * Check if the inventory is empty.
     * 
     * @return true if the inventory is empty, false otherwise
     */
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    /**
     * Get a vehicle by ID.
     * 
     * @param id the ID of the vehicle
     * @return the vehicle with the specified ID, or null if not found
     */
    public Vehicle getVehicleFromId(int id) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getId() == id) {
                return vehicle;
            }
        }
        return null;
    }

    // ENQUIRY METHODS

    /**
     * Add an enquiry.
     * 
     * @param vehicle the vehicle the enquiry is about
     * @param user the user making the enquiry
     * @param message the enquiry message
     * @param contact the contact information of the user
     */
    public void addEnquiry(Vehicle vehicle, User user, String message, String contact) {
        Enquiry enquiry = new Enquiry(vehicle, user, message, contact);
        enquiries.add(enquiry);
        save();
    }

    /**
     * Remove an enquiry.
     * 
     * @param enquiry the enquiry to remove
     */
    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
        save();
    }

    /**
     * Get the list of enquiries.
     * 
     * @return a list of enquiries
     */
    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    // LOAD AND SAVE METHODS

    /**
     * Save the dealership data to a file.
     */
    public void save() {
        File saveFile = new File("save.data");
        try (FileOutputStream outFileStream = new FileOutputStream(saveFile);
             ObjectOutputStream outObjStream = new ObjectOutputStream(outFileStream)) {
            outObjStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the dealership data from a file.
     * 
     * @return the loaded dealership object, or null if the file does not exist or an error occurs
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
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