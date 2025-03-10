package carDealership;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The Sale class represents a sale transaction in the dealership system.
 * It includes details about the vehicle sold, the salesperson, the buyer, and the sale date.
 */
public class Sale implements Serializable {
    private static final long serialVersionUID = -7473395562909124471L;
    private Vehicle vehicle;
    private User salesperson;
    private String buyerName;
    private String buyerContact;
    private LocalDate saleDate;
    private boolean pending;

    /**
     * Constructor for creating a Sale object with all attributes.
     * 
     * @param vehicle the vehicle sold
     * @param salesperson the salesperson who made the sale
     * @param buyerName the name of the buyer
     * @param buyerContact the contact information of the buyer
     * @param saleDate the date of the sale
     */
    public Sale(Vehicle vehicle, User salesperson, String buyerName, String buyerContact, LocalDate saleDate) {
        this.vehicle = vehicle;
        this.salesperson = salesperson;
        this.buyerName = buyerName;
        this.buyerContact = buyerContact;
        this.saleDate = saleDate;
        this.pending = true;
    }

    /**
     * Constructor for creating a Sale object with all attributes including pending status.
     * 
     * @param vehicle the vehicle sold
     * @param salesperson the salesperson who made the sale
     * @param buyerName the name of the buyer
     * @param buyerContact the contact information of the buyer
     * @param saleDate the date of the sale
     * @param pending the pending status of the sale
     */
    public Sale(Vehicle vehicle, User salesperson, String buyerName, String buyerContact, LocalDate saleDate, boolean pending) {
        this.vehicle = vehicle;
        this.salesperson = salesperson;
        this.buyerName = buyerName;
        this.buyerContact = buyerContact;
        this.saleDate = saleDate;
        this.pending = pending;
    }

    // Getters and setters for Sale attributes

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(User salesperson) {
        this.salesperson = salesperson;
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

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    /**
     * Approve the sale by setting the pending status to false.
     */
    public void approve() {
        this.pending = false;
    }
}