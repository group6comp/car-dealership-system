package carDealership;

public class Motorcycle extends Vehicle {
	private static final long serialVersionUID = -3762021630826741458L;
	private String handlebarType;

	public Motorcycle(int id, String make, String model, String color, int year, double price, int mileage, Status status, String handlebarType) {
		super(id, make, model, color, year, price, mileage, status);
		this.handlebarType = handlebarType;
	}

	public Motorcycle(int id, String make, String model, String color, int year, double price, String handlebarType) {
		this(id, make, model, color, year, price, 0, Status.AVAILABLE, handlebarType);
	}

	public Motorcycle(Motorcycle m) {
		this(m.id, m.make, m.model, m.color, m.year, m.price, m.mileage, m.status, m.handlebarType);
	}

	public void displayInfo() {
		String motorcycleName = make + " " + model + " " + year;
		System.out.println("ID: " + id);
		System.out.println("Motorcycle: " + motorcycleName);
		System.out.println("Color: " + color);
		System.out.println("Handlebar type: " + handlebarType);
		System.out.println("Price: " + price + " SAR");
	}

	public String getHandlebarType() {
		return handlebarType;
	}

	public void setHandlebarType(String handlebarType) {
		this.handlebarType = handlebarType;
	}

	public String toString() {

		return super.toString() + "\nHandelbarType: " + handlebarType;
	}

	public String getCsvEntry() {
		return id + "," + make + "," + model + "," + color + "," + year + "," + price + "," + mileage + "," + status + "," + handlebarType;
	}

	public String getType() {
		return handlebarType;
	}
}
