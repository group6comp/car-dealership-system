package carDealership;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;

/**
 * The User class represents a user in the dealership system.
 * It includes details about the user's username, password, role, and wishlist.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private Role role; // Admin, Manager, Salesperson, Customer
    private List<Vehicle> wishlist;

    /**
     * The Role enum represents the different roles a user can have.
     */
    public enum Role {
        ADMIN, MANAGER, SALESPERSON, CUSTOMER, VISITOR;

        @Override
        public String toString() {
            return switch (this) {
                case ADMIN -> "Admin";
                case MANAGER -> "Manager";
                case SALESPERSON -> "Salesperson";
                case CUSTOMER -> "Customer";
                case VISITOR -> "Visitor";
            };
        }
    }

    /**
     * Constructor for creating a User object.
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @param role the role of the user
     */
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.wishlist = new ArrayList<>();
    }

    // Getters and setters for User attributes

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(String role) {
        this.role = roleFromString(role);
    }

    /**
     * Get the user's wishlist.
     * 
     * @return the filtered wishlist
     */
    public List<Vehicle> getWishlist() {
        return wishlist;
    }

    /**
     * Add a vehicle to the user's wishlist.
     * 
     * @param vehicle the vehicle to add to the wishlist
     */
    public void addToWishlist(Vehicle vehicle) {
        wishlist.add(vehicle);
    }

    /**
     * Convert a string to a Role enum.
     * 
     * @param role the role as a string
     * @return the corresponding Role enum, or null if the role is invalid
     */
    public static Role roleFromString(String role) {
        if (role == null) {
            return null;
        }
        switch (role.toUpperCase()) {
            case "ADMIN":
                return Role.ADMIN;
            case "MANAGER":
                return Role.MANAGER;
            case "SALESPERSON":
                return Role.SALESPERSON;
            case "CUSTOMER":
                return Role.CUSTOMER;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return username;
    }
}