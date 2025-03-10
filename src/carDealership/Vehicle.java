package carDealership;

import java.io.Serializable;

import javax.swing.JOptionPane;

public abstract class Vehicle implements Serializable {
    private static final long serialVersionUID = -8537773978564014927L;
    protected String make, model, color;
    protected int year, mileage, id;
    protected double price;
    protected Status status;

    public enum Status {
        AVAILABLE,
        SOLD,
        MAINTENANCE;

        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    public Vehicle(int id, String make, String model, String color, int year, double price, int mileage, Status status) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.status = status;
    }

    public Vehicle(int id, String make, String model, String color, int year, double price) {
        this(id, make, model, color, year, price, 0, Status.AVAILABLE);
    }

    public abstract void update(String make, String model, String color, String type, int year, double price, int mileage, Status status);
    
    public abstract void displayInfo();

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
                "Mileage: " + mileage);
    }

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
                "Mileage: " + mileage + "\n" +
                "Status: " + status);
    }

    // Getters and setters
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getPrice() { return price; }
    public void setPrice(double price) { if (price < 0) { price = 0; } this.price = price; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMileage() { return mileage; }
    public void setMileage(int mileage) { if (mileage < 0) { mileage = 0; } this.mileage = mileage; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public abstract String getType();
    public abstract void setType(String type);
}