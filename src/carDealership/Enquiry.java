package carDealership;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDate;

public class Enquiry {
    Vehicle vehicle;
    User user;
    String message;
    String contact;
    LocalDate date;
    Status status;

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
