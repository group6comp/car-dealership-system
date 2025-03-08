package carDealership;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private Role role; // Admin, Manager, Salesperson, Customer

    public enum Role {
        ADMIN, MANAGER, SALESPERSON, CUSTOMER;

        public String toString() {
            return switch (this) {
                case ADMIN -> "Admin";
                case MANAGER -> "Manager";
                case SALESPERSON -> "Salesperson";
                case CUSTOMER -> "Customer";
            };
        }
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(Role role) { this.role = role; }
    public void setRole(String role) { this.role = roleFromString(role); }

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