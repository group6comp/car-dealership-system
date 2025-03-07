package carDealership;

public class Car extends Vehicle {
	private static final long serialVersionUID = -8046573803286029881L;
	private String type;

	public Car(int id, String make, String model, String color, int year, double price, int mileage, Status status, String type) {
		super(id, make, model, color, year, price, mileage, status);
		this.type = type;
	}

	public Car (int id, String make, String model, String color, int year, double price, String type) {
		this(id, make, model, color, year, price, 0, Status.AVAILABLE, type);
	}

	public Car(Car c) {
		this(c.id, c.make, c.model, c.color, c.year, c.price, c.mileage, c.status, c.type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void displayInfo() {
		String carName = make + " " + model + " " + year;
		System.out.println("ID: " + id);
		System.out.println("Car: " + carName);
		System.out.println("Color: " + color);
		System.out.println("Type: " + type);
		System.out.println("Price: " + price + " SAR");
	}

	public String toString() {

		return super.toString() + "\nType: " + type;
	}

    public String[] toArray() {
        return new String[] {
            String.valueOf(id),
            make,
            model,
            color,
            String.valueOf(year),
            String.valueOf(price),
            String.valueOf(mileage),
            status.toString(),
            type
        };
    }
}
