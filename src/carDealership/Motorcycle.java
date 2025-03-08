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
    public void displayInfo() {
        System.out.println(this.toString());
    }

    @Override
    public String getType() {
        return handlebarType;
    }

    public void setHandlebarType(String handlebarType) {
        this.handlebarType = handlebarType;
    }

    @Override
    public String toString() {
        return super.toString() + "\nHandlebar Type: " + handlebarType;
    }
}