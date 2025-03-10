package carDealership;

import java.io.Serializable;
import java.time.LocalDate;

public class Enquiry implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Vehicle vehicle;
    private User user;
    private String message;
    private String contact;
    private LocalDate date;
    private Status status;

    public enum Status {
        pending,
        resolved;
    }

    public Enquiry(Vehicle vehicle, User user, String message, String contact) {
        this.vehicle = vehicle;
        this.user = user;
        this.message = message;
        this.contact = contact;
        this.date = LocalDate.now();
        this.status = Status.pending;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public String getContact() {
        return contact;
    }

    public LocalDate getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}