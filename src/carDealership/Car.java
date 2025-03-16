package carDealership;

/**
 * The Car class represents a car in the dealership's inventory.
 * It extends the Vehicle class and adds a type attribute specific to cars.
 */
public class Car extends Vehicle {
    private static final long serialVersionUID = -8537773978564014927L;
    private String type;

    /**
     * Constructor for creating a Car object with all attributes.
     * 
     * @param id the unique identifier for the car
     * @param make the make of the car
     * @param model the model of the car
     * @param color the color of the car
     * @param year the year the car was manufactured
     * @param price the price of the car
     * @param stock
     * @param type the type of the car (e.g., sedan, SUV)
     */
    public Car(int id, String make, String model, String color, int year, double price, int stock, String type) {
        super(id, make, model, color, year, price, stock);
        this.type = type;
    }

    /**
     * Constructor for creating a Car object without stock.
     * 
     * @param id the unique identifier for the car
     * @param make the make of the car
     * @param model the model of the car
     * @param color the color of the car
     * @param year the year the car was manufactured
     * @param price the price of the car
     * @param type the type of the car (e.g., sedan, SUV)
     */
    public Car(int id, String make, String model, String color, int year, double price, String type) {
        super(id, make, model, color, year, price);
        this.type = type;
    }

    /**
     * Update the car's attributes.
     * 
     * @param make the new make of the car
     * @param model the new model of the car
     * @param color the new color of the car
     * @param type the new type of the car
     * @param year the new year of the car
     * @param price the new price of the car
     * @param stock the new stock of the car
     */
    @Override
    public void update(String make, String model, String color, String type, int year, double price, int stock) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.type = type;
        this.year = year;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Display the car's information.
     */
    @Override
    public void displayInfo() {
        System.out.println(this.toString());
    }

    /**
     * Getters and setters for the Car class.
     */
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Return a string representation of the car.
     * 
     * @return a string representation of the car
     */
    @Override
    public String toString() {
        return super.toString() + "\nType: " + type;
    }
}