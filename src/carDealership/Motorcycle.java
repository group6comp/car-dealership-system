package carDealership;

public class Motorcycle extends Vehicle {
    private static final long serialVersionUID = -8537773978564014927L;
    private String handlebarType;

    public Motorcycle(int id, String make, String model, String color, int year, double price, int mileage, Status status, String handlebarType) {
        super(id, make, model, color, year, price, mileage, status);
        this.handlebarType = handlebarType;
    }

    public Motorcycle(int id, String make, String model, String color, int year, double price, String handlebarType) {
        super(id, make, model, color, year, price);
        this.handlebarType = handlebarType;
    }

    @Override
    public void update(String make, String model, String color, String type, int year, double price, int mileage, Status status){
        this.make = make;
        this.model = model;
        this.color = color;
        this.handlebarType = type;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.status = status;
    }

    @Override
    public void displayInfo() {
        System.out.println(this.toString());
    }

    @Override
    public String getType() {
        return handlebarType;
    }

    @Override
    public void setType(String handlebarType) {
        this.handlebarType = handlebarType;
    }

    @Override
    public String toString() {
        return super.toString() + "\nHandlebar Type: " + handlebarType;
    }
}