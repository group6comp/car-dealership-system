package carDealership;

public class Car extends Vehicle {
    private static final long serialVersionUID = -8537773978564014927L;
    private String type;

    public Car(int id, String make, String model, String color, int year, double price, int mileage, Status status, String type) {
        super(id, make, model, color, year, price, mileage, status);
        this.type = type;
    }

    public Car(int id,String make, String model, String color, int year, double price, String type) {
        super(id, make, model, color, year, price);
        this.type = type;
    }

    @Override
    public void update(String make, String model, String color, String type, int year, double price, int mileage, Status status){
        this.make = make;
        this.model = model;
        this.color = color;
        this.type = type;
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
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + "\nType: " + type;
    }
}