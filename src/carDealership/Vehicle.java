package carDealership;

import java.io.Serializable;

/**
 * The Vehicle class represents a generic vehicle in the dealership's inventory.
 * It includes common attributes and methods for all types of vehicles.
 */
public abstract class Vehicle implements Serializable {
    private static final long serialVersionUID = -8537773978564014927L;
    protected String make, model, color;
    protected int year, stock, id;
    protected double price;

    /**
     * Constructor for creating a Vehicle object with all attributes.
     * 
     * @param id the ID of the vehicle
     * @param make the make of the vehicle
     * @param model the model of the vehicle
     * @param color the color of the vehicle
     * @param year the year of the vehicle
     * @param price the price of the vehicle
     */
    public Vehicle(int id, String make, String model, String color, int year, double price, int stock) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructor for creating a Vehicle object without stock.
     * 
     * @param id the ID of the vehicle
     * @param make the make of the vehicle
     * @param model the model of the vehicle
     * @param color the color of the vehicle
     * @param year the year of the vehicle
     * @param price the price of the vehicle
     */
    public Vehicle(int id, String make, String model, String color, int year, double price) {
        this(id, make, model, color, year, price, 0);
    }

    /**
     * Abstract method to update the vehicle's attributes.
     * 
     * @param make the make of the vehicle
     * @param model the model of the vehicle
     * @param color the color of the vehicle
     * @param type the type of the vehicle
     * @param year the year of the vehicle
     * @param price the price of the vehicle
     */
    public abstract void update(String make, String model, String color, String type, int year, double price, int stock);

    /**
     * Abstract method to display the vehicle's information.
     */
    public abstract void displayInfo();

    /**
     * Return a restricted string representation of the vehicle.
     * 
     * @return a restricted string representation of the vehicle
     */
    public String toStringRestricted() {
        String classText;
        String typeText;
        if (this instanceof Car) {
            classText = "Car";
            typeText = "Type: " + ((Car) this).getType();
        } else {
            classText = "Motorcycle";
            typeText = "Handlebar Type: " + ((Motorcycle) this).getType();
        }
        return (classText + "\n" +
                "Make: " + make + "\n" +
                "Model: " + model + "\n" +
                "Color: " + color + "\n" +
                "Year: " + year + "\n" +
                "Price: " + price + "\n" +
                typeText + "\n" +
                "Stock: " + stock);
    }

    /**
     * Return a string representation of the vehicle.
     * 
     * @return a string representation of the vehicle
     */
    @Override
    public String toString() {
        String classText;
        String typeText;
        if (this instanceof Car) {
            classText = "Car";
            typeText = "Type: " + ((Car) this).getType();
        } else {
            classText = "Motorcycle";
            typeText = "Handlebar Type: " + ((Motorcycle) this).getType();
        }
        return (classText + "\n" +
                "ID: " + id + "\n" +
                "Make: " + make + "\n" +
                "Model: " + model + "\n" +
                "Color: " + color + "\n" +
                "Year: " + year + "\n" +
                "Price: " + price + "\n" +
                typeText + "\n" +
                "Stock: " + stock);
    }

    // Getters and setters for Vehicle attributes

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            price = 0;
        }
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            stock = 0;
        }
        this.stock = stock;
    }

    public boolean isInStock() {
        return stock > 0;
    }

    // Abstract methods for type-specific attributes
    public abstract String getType();
    public abstract void setType(String type);
}