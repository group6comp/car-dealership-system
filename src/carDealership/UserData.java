package carDealership;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static List<User> userList = new ArrayList<>();

    // Add some accounts
    static {
        userList.add(new User("admin01","adminpass","Admin"));
        userList.add(new User("manager01","managerpass","Manager"));
        userList.add(new User("sales01","salespass","Salesperson"));
        userList.add(new User("john","customerpass","Customer"));
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

