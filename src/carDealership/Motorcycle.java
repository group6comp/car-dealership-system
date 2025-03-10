package carDealership;

/**
 * The Motorcycle class represents a motorcycle in the dealership's inventory.
 * It extends the Vehicle class and includes additional attributes specific to motorcycles.
 */
public class Motorcycle extends Vehicle {
    private static final long serialVersionUID = -8537773978564014927L;
    private String handlebarType;

    /**
     * Constructor for creating a Motorcycle object with all attributes.
     * 
     * @param id the ID of the motorcycle
     * @param make the make of the motorcycle
     * @param model the model of the motorcycle
     * @param color the color of the motorcycle
     * @param year the year of the motorcycle
     * @param price the price of the motorcycle
     * @param mileage the mileage of the motorcycle
     * @param status the status of the motorcycle
     * @param handlebarType the type of handlebar of the motorcycle
     */
    public Motorcycle(int id, String make, String model, String color, int year, double price, int mileage, Status status, String handlebarType) {
        super(id, make, model, color, year, price, mileage, status);
        this.handlebarType = handlebarType;
    }

    /**
     * Constructor for creating a Motorcycle object without mileage and status.
     * 
     * @param id the ID of the motorcycle
     * @param make the make of the motorcycle
     * @param model the model of the motorcycle
     * @param color the color of the motorcycle
     * @param year the year of the motorcycle
     * @param price the price of the motorcycle
     * @param handlebarType the type of handlebar of the motorcycle
     */
    public Motorcycle(int id, String make, String model, String color, int year, double price, String handlebarType) {
        super(id, make, model, color, year, price);
        this.handlebarType = handlebarType;
    }

    /**
     * Update the motorcycle's attributes.
     * 
     * @param make the make of the motorcycle
     * @param model the model of the motorcycle
     * @param color the color of the motorcycle
     * @param type the type of handlebar of the motorcycle
     * @param year the year of the motorcycle
     * @param price the price of the motorcycle
     * @param mileage the mileage of the motorcycle
     * @param status the status of the motorcycle
     */
    @Override
    public void update(String make, String model, String color, String type, int year, double price, int mileage, Status status) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.handlebarType = type;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.status = status;
    }

    /**
     * Display the motorcycle's information.
     */
    @Override
    public void displayInfo() {
        System.out.println(this.toString());
    }

    /**
     * Get and set the type of handlebar of the motorcycle.
     * 
     * @return the type of handlebar
     */
    @Override
    public String getType() {
        return handlebarType;
    }

    @Override
    public void setType(String handlebarType) {
        this.handlebarType = handlebarType;
    }

    /**
     * Return a string representation of the motorcycle.
     * 
     * @return a string representation of the motorcycle
     */
    @Override
    public String toString() {
        return super.toString() + "\nHandlebar Type: " + handlebarType;
    }
}