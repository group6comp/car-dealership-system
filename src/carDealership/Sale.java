package carDealership;


import java.io.Serializable;
import java.time.LocalDate;

public class Sale implements Serializable {
	private static final long serialVersionUID = -7473395562909124471L;
	private Vehicle vehicle;
	private User salesperson;
	private String buyerName;
	private String buyerContact;
	private LocalDate saleDate;
	private boolean pending;

	public Sale(Vehicle vehicle, User salesperson, String buyerName, String buyerContact, LocalDate saleDate) {
		this.vehicle = vehicle;
		this.salesperson = salesperson;
		this.buyerName = buyerName;
		this.buyerContact = buyerContact;
		this.saleDate = saleDate;
		this.pending = true;
	}

	public Sale(Vehicle vehicle, User salesperson, String buyerName, String buyerContact, LocalDate saleDate, boolean pending) {
		this.vehicle = vehicle;
		this.salesperson = salesperson;
		this.buyerName = buyerName;
		this.buyerContact = buyerContact;
		this.saleDate = saleDate;
		this.pending = pending;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public User getSalesperson() {
		return salesperson;
	}

	public void setSalesperson(User salesperson) {
		this.salesperson = salesperson;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerContact() {
		return buyerContact;
	}

	public void setBuyerContact(String buyerContact) {
		this.buyerContact = buyerContact;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

	public boolean isPending() {
		return pending;
	}

	public void approve(){
		this.pending = false;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}
}
