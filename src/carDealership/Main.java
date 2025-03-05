package carDealership;

import java.util.Scanner;
import persistance.DealershipLayer;
import java.io.*;
import java.sql.SQLException;
import javax.swing.JFrame;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static Dealership m_dealership;
    public static User user;

    public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {
        var dealership = new DealershipLayer();

        // TODO: Add a method in DBManager to tell if the database was just created and
        // use it here
        if (!dealership.existsAndSet()) {
            FirstLaunchPage newPage = new FirstLaunchPage();
        } else {
            m_dealership = new Dealership(dealership.getNname(), dealership.getLocation(), dealership.getCapacity());
            if (user == null) {
                new VisitorMainUI();
            } else {
                showRoleUI();
            }
        }
    }

    public static void showRoleUI() {
        String role = user.getRole();
        JFrame frame = null;
        switch (role.toLowerCase()) {
            case "admin":
                frame = new AdminMainUI();
                break;
            case "manager":
                frame = new ManagerMainUI();
                break;
            case "salesperson":
                frame = new SalespersonMainUI();
                break;
            case "customer":
                frame = new CustomerMainUI();
                break;
            default:
                frame = new VisitorMainUI();
                break;
        }
        if (frame != null) {
            frame.setVisible(true);
        }
    }

    public static void addVehicleMenu() {
        System.out.println("\n-------------------------------------------\n");
        System.out.println("Add a Vehicle");
        System.out.println("\nChoose an option:");
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Exit");

        String choice = input.nextLine();

        switch (choice) {
            case "1":
                addCarMenu();
                break;
            case "2":
                addMotorcycleMenu();
                break;
            case "3":
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void addCarMenu() {
        System.out.println("\n-------------------------------------------\n");
        System.out.println("Add a Car");

        System.out.print("\nEnter the make: ");
        String make = input.nextLine();

        System.out.print("Enter the model: ");
        String model = input.nextLine();

        System.out.print("Enter the color: ");
        String color = input.nextLine();

        System.out.print("Enter the year: ");
        int year = input.nextInt();

        System.out.print("Enter the price: ");
        double price = input.nextDouble();

        System.out.print("Enter the type: ");
        input.nextLine();
        String type = input.nextLine();

        if (m_dealership.addVehicle(new Car(make, model, color, year, price, type))) {
            System.out.println("Car added successfully.");
        } else {
            System.out.println("Couldn't add car.");
        }
    }

    public static void addMotorcycleMenu() {
        System.out.println("\n-------------------------------------------\n");
        System.out.println("Add a Motorcycle");

        System.out.print("\nEnter the make: ");
        String make = input.nextLine();

        System.out.print("Enter the model: ");
        String model = input.nextLine();

        System.out.print("Enter the color: ");
        String color = input.nextLine();

        System.out.print("Enter the year: ");
        int year = input.nextInt();

        System.out.print("Enter the price: ");
        double price = input.nextDouble();
        input.nextLine();

        System.out.print("Enter the handlebar type: ");
        String handlebarType = input.nextLine();

        if (m_dealership.addVehicle(new Motorcycle(make, model, color, year, price, handlebarType))) {
            System.out.println("Motorcycle added successfully.");
        } else {
            System.out.println("Couldn't add Motorcycle.");
        }
    }

    public static void sellVehicleMenu() {
        System.out.println("\n-------------------------------------------\n");
        System.out.println("Sell a Vehicle");

        System.out.print("\nEnter the id of the vehicle: ");
        int id = input.nextInt();
        input.nextLine();

        if (m_dealership.getIndexFromId(id) == -1) {
            System.out.println("\nVehicle not found!");
            return;
        }

        System.out.print("Enter the buyer's name: ");
        String buyerName = input.nextLine();

        System.out.print("Enter the buyer's contact: ");
        String buyerContact = input.nextLine();

        Vehicle vehicle = m_dealership.getVehicleFromId(id);

        if (m_dealership.sellVehicle(vehicle, buyerName, buyerContact)) {
            System.out.println("Vehicle sold successfully.");
        } else {
            System.out.println("Couldn't sell vehicle");
        }
    }

    public static void removeVehicleMenu() {
        System.out.println("\n-------------------------------------------\n");
        System.out.println("Remove a Vehicle");

        System.out.print("\nEnter the id of the vehicle: ");
        int id = input.nextInt();
        input.nextLine();

        if (m_dealership.getIndexFromId(id) == -1) {
            System.out.println("\nVehicle not found!");
            return;
        }

        Vehicle vehicle = m_dealership.getVehicleFromId(id);

        if (m_dealership.removeVehicle(vehicle)) {
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Couldn't remove vehicle");
        }
    }

    public static void editVehicleMenu() {
        System.out.println("\n-------------------------------------------\n");
        System.out.println("Edit a Vehicle");

        System.out.print("\nEnter the id of the vehicle: ");
        int id = input.nextInt();
        input.nextLine();

        if (m_dealership.getIndexFromId(id) == -1) {
            System.out.println("\nVehicle not found!");
            return;
        }
        System.out.println("\nEnter the new information");
        Vehicle vehicle = m_dealership.getVehicleFromId(id);
        vehicle.displayInfo();

        if (vehicle instanceof Car) {
            carEdit((Car) vehicle);
        }
        if (vehicle instanceof Motorcycle) {
            motorcycleEdit((Motorcycle) vehicle);
        }
    }

    public static void carEdit(Car c) {
        System.out.print("\nEnter the make: ");
        String make = input.nextLine();

        System.out.print("Enter the model: ");
        String model = input.nextLine();

        System.out.print("Enter the color: ");
        String color = input.nextLine();

        System.out.print("Enter the year: ");
        int year = input.nextInt();

        System.out.print("Enter the price: ");
        double price = input.nextDouble();

        System.out.print("Enter the type: ");
        input.nextLine();
        String type = input.nextLine();

        c.setMake(make);
        c.setModel(model);
        c.setColor(color);
        c.setYear(year);
        c.setPrice(price);
        c.setType(type);
    }

    public static void motorcycleEdit(Motorcycle m) {
        System.out.print("\nEnter the make: ");
        String make = input.nextLine();

        System.out.print("Enter the model: ");
        String model = input.nextLine();

        System.out.print("Enter the color: ");
        String color = input.nextLine();

        System.out.print("Enter the year: ");
        int year = input.nextInt();

        System.out.print("Enter the price: ");
        double price = input.nextDouble();

        System.out.print("Enter the handlebar type: ");
        input.nextLine();
        String handlebarType = input.nextLine();

        m.setMake(make);
        m.setModel(model);
        m.setColor(color);
        m.setYear(year);
        m.setPrice(price);
        m.setHandlebarType(handlebarType);
    }

    public static void searchCarMenu() {
        if (!(m_dealership.isEmpty())) {
            System.out.println("Enter type: ");
            String s = input.nextLine();

            Car[] v = m_dealership.searchCar(s);
            int total = 0;
            if (v != null) {
                for (int i = 0; i < v.length; i++) {
                    if (v[i] != null) {
                        v[i].displayInfo();
                        System.out.println();
                        total++;
                    }
                }
            }
            System.out.printf("Total found: [%d]\n", total);
        } else
            System.out.println("Sorry the inventory is empty.");
    }

    public static void changeColorMenu() {
        System.out.println("\n-------------------------------------------\n");
        System.out.println("Add a Vehicle");
        System.out.println("\nChoose an option:");
        System.out.println("1. Blue");
        System.out.println("2. Green");
        System.out.println("3. White");
        System.out.println("4. Exit");
        String choice = input.nextLine();

        switch (choice) {
            case "1":
                System.out.println("\u001B[36m"); // Changes color to Blue.
                break;
            case "2":
                System.out.println("\u001B[32m"); // Changes color to Green.
                break;
            case "3":
                System.out.println("\u001B[0m"); // Changes color back to White.
                break;
            case "4":
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void budgetCarMenu() {
        if (!(m_dealership.isEmpty())) {
            System.out.println("Enter budget: ");
            String budget = input.nextLine();

            for (int i = 0; i < budget.length(); i++) {
                if (budget.charAt(i) < 48 || budget.charAt(i) > 57) { // Ascii digits from 0 to 9.
                    System.out.println("Invalid Input, Please enter positive numbers only.");
                    return;
                }
            }
            {
                int total = m_dealership.carBudget(Double.parseDouble(budget)); // Calling carBudget Method.
                System.out.printf("Total [%d]\n", total);
            }
        } else
            System.out.println("Sorry the inventory is empty.");
    }

    public static void createDealership(String name, String location, int capacity)
            throws IllegalCapacityException, SQLException {
        if (capacity < 1 || capacity > 100) {
            throw new IllegalCapacityException();
        }
        m_dealership = new Dealership(name, location, capacity);
    }

    public static void save() throws IOException {
        File saveFile = new File("save.data");
        FileOutputStream outFileStream = null;
        try {
            outFileStream = new FileOutputStream(saveFile);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ObjectOutputStream outObjStream = null;
        try {
            outObjStream = new ObjectOutputStream(outFileStream);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        outObjStream.writeObject(m_dealership);
        outObjStream.close();
    }
}