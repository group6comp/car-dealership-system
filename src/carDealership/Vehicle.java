package carDealership;

import java.io.Serializable;

public abstract class Vehicle implements Serializable {
	private static final long serialVersionUID = -8537773978564014927L;
	protected String make, model, color;
	protected int year, mileage, id;
	protected double price;
	protected Status status;
	private static int nextId;

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
		this.status = Status.AVAILABLE;
	}

	public Vehicle(int id, String make, String model, String color, int year, double price) {
		this(id, make, model, color, year, price, 0, Status.AVAILABLE);
	}

	public abstract void displayInfo();
	
	public String toString() {
		return "ID: " + id + "\nMake: " + make + "\nModel: " + model + "\nColor: " + color + "\nYear: " + year
				+ "\nPrice: " + price + "\nMileage: " + mileage + "\nStatus: " + status;
	}
	
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
}
