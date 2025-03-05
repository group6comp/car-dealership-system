package carDealership;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static List<User> userList = new ArrayList<>();

    // Add some accounts
    static {
        userList.add(new User("admin01", "adminpass", "Admin"));
        userList.add(new User("manager01", "managerpass", "Manager"));
        userList.add(new User("sales01", "salespass", "Salesperson"));
        userList.add(new User("john", "customerpass", "Customer"));
        userList.add(new User("admin02", "adminpass2", "Admin"));
        userList.add(new User("manager02", "managerpass2", "Manager"));
        userList.add(new User("sales02", "salespass2", "Salesperson"));
        userList.add(new User("jane", "customerpass2", "Customer"));
        userList.add(new User("admin03", "adminpass3", "Admin"));
        userList.add(new User("manager03", "managerpass3", "Manager"));
        userList.add(new User("sales03", "salespass3", "Salesperson"));
        userList.add(new User("mike", "customerpass3", "Customer"));
        userList.add(new User("admin04", "adminpass4", "Admin"));
        userList.add(new User("manager04", "managerpass4", "Manager"));
        userList.add(new User("sales04", "salespass4", "Salesperson"));
        userList.add(new User("susan", "customerpass4", "Customer"));
    }

    // Check if user exists with matching pass
    public static User validateLogin(String username, String password) {
        for(User u : userList) {
            if(u.getUsername().equalsIgnoreCase(username)
               && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null; // not found or password mismatch
    }

    public static User getUser(String username) {
        for(User u : userList) {
            if(u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null; // not found
    }
}

