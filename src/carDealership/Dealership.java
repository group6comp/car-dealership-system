package carDealership;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dealership implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String location;
    private int nv;
    private int ns;
    private Vehicle[] inventory;
    private Sale[] sales;
    private int nextId;
    private List<User> userData;

    public Dealership(String name, String location, int maxInventory) {
        this.name = name;
        this.location = location;
        inventory = new Vehicle[maxInventory];
        sales = new Sale[maxInventory * 2];
        nv = 0;
        ns = 0;
        nextId = 0;
        userData = new ArrayList<>();
    }

    public Vehicle[] getInventory() {
        return inventory;
    }

    public void getInfo() {
        System.out.printf("Name: [%s]\nLocation: [%s]\nInventory Size: [%d]\n", name, location, inventory.length);
    }

    public String getInfoGUI() {
        return String.format("Dealership name: [%s]\nLocation: [%s]\nInventory Size: [%d]\n\nAvailable space: %d\nTotal Cars: %d\nTotal Motorcycles: %d\n\nTotal sales profit: %d\nTotal vehicles sold: %d",
                name, location, inventory.length, inventory.length - nv, getTotalCars(), getTotalMotorcycles(), salesProfit(), ns);
    }

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
    }

    public void removeUser(User user) {
        userData.remove(user);
    }

    private int salesProfit() {
        int total = 0;
        for (int i = 0; i < ns; i++) {
            total += sales[i].getVehicle().getPrice();
        }
        return total;
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (nv == inventory.length) {
            return false;
        }

        if (vehicle instanceof Car) {
            Car c = new Car((Car) vehicle);
            c.setId(nextId++);
            inventory[nv++] = c;
        } else if (vehicle instanceof Motorcycle) {
            Motorcycle m = new Motorcycle((Motorcycle) vehicle);
            m.setId(nextId++);
            inventory[nv++] = m;
        }

        return true;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return false;
        }
        int index = getIndexFromId(vehicle.id);

        if (index == -1) {
            return false;
        }

        System.arraycopy(inventory, index + 1, inventory, index, nv - index - 1);
        nv--;
        return true;
    }

    public int getIndexFromId(int id) {
        for (int i = 0; i < nv; i++) {
            if (inventory[i].id == id) {
                return i;
            }
        }
        return -1;
    }

    public boolean sellVehicle(Vehicle vehicle, String buyerName, String buyerContact) {
        if (!removeVehicle(vehicle)) {
            return false;
        }
        sales[ns++] = new Sale(vehicle, buyerName, buyerContact, LocalDate.now());
        return true;
    }

    public double getInventoryGrossValue() {
        double totalValue = 0.0;
        for (int i = 0; i < nv; i++) {
            totalValue += inventory[i].price;
        }
        return totalValue;
    }

    public int getTotalCars() {
        int totalCars = 0;
        for (int i = 0; i < nv; i++) {
            if (inventory[i] instanceof Car) {
                totalCars++;
            }
        }
        return totalCars;
    }

    public int getTotalMotorcycles() {
        int totalMotorcycles = 0;
        for (int i = 0; i < nv; i++) {
            if (inventory[i] instanceof Motorcycle) {
                totalMotorcycles++;
            }
        }
        return totalMotorcycles;
    }

    public void displayAll() {
        System.out.println("Inventory Details:");
        System.out.println("Total inventory value: " + getInventoryGrossValue());
        for (int i = 0; i < nv; i++) {
            System.out.println("-------------------");
            inventory[i].displayInfo();
            System.out.println("-------------------");
        }
        System.out.printf("Size: [%d/%d]\n", nv, inventory.length);
    }

    public String displayAlls() {
        StringBuilder sb = new StringBuilder("Inventory Details:\n");
        sb.append("Total inventory value: ").append(getInventoryGrossValue()).append("\n");

        for (int i = 0; i < nv; i++) {
            sb.append("-------------------\n");
            sb.append(inventory[i].toString()).append("\n");
        }
        sb.append("-------------------\nSize: [").append(nv).append("/").append(inventory.length).append("]\n");
        return sb.toString();
    }

    public boolean isFull() {
        return nv == inventory.length;
    }

    public boolean isEmpty() {
        return nv == 0;
    }

    public Vehicle getVehicleFromId(int id) {
        for (int i = 0; i < nv; i++) {
            if (inventory[i].id == id) {
                return inventory[i];
            }
        }
        return null;
    }

    public void displaySalesHistory() {
        System.out.println("Sales History:");
        if (ns == 0) {
            System.out.println("No sales recorded.");
            return;
        }
        for (int i = 0; i < ns; i++) {
            System.out.println("-------------------");
            sales[i].getVehicle().displayInfo();
            System.out.println("Buyer Name: " + sales[i].getBuyerName());
            System.out.println("Buyer Contact: " + sales[i].getBuyerContact());
            System.out.println("Sale Date: " + sales[i].getSaleDate());
            System.out.println("-------------------");
        }
    }

    public String showSalesHistory() {
        if (ns == 0) {
            return "No sales recorded.";
        }

        StringBuilder sb = new StringBuilder("Sales History:\n");
        for (int i = 0; i < ns; i++) {
            sb.append("-------------------\n");
            sb.append(sales[i].getVehicle().toString()).append("\n");
            sb.append("Buyer Name: ").append(sales[i].getBuyerName()).append("\n");
            sb.append("Buyer Contact: ").append(sales[i].getBuyerContact()).append("\n");
            sb.append("Sale Date: ").append(sales[i].getSaleDate()).append("\n");
        }
        sb.append("-------------------\n");
        return sb.toString();
    }

    public Car[] searchCar(String type) {
        Car[] types = new Car[getTotalCars()];
        int counter = 0;

        for (int i = 0; i < nv; i++) {
            if (inventory[i] instanceof Car && ((Car) inventory[i]).getType().equalsIgnoreCase(type)) {
                types[counter++] = (Car) inventory[i];
            }
        }
        if (counter == 0) {
            System.out.println("Sorry, didn't find car with type: " + type);
        }
        return types;
    }

    public int carBudget(double budget) {
        int total = 0;
        boolean notChecked = true;

        for (int i = 0; i < nv; i++) {
            if (inventory[i] instanceof Car) {
                notChecked = false;
                if (inventory[i].getPrice() <= budget) {
                    inventory[i].displayInfo();
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

    public Car[] carsWithinBudget(double budget) {
        Car[] cars = new Car[getTotalCars()];
        int counter = 0;
        for (int i = 0; i < nv; i++) {
            if (inventory[i] instanceof Car && inventory[i].getPrice() <= budget) {
                cars[counter++] = (Car) inventory[i];
            }
        }
        return cars;
    }

    public List<User> getUserData() {
        return userData;
    }

    public void setUserData(List<User> userData) {
        this.userData = userData;
    }
}