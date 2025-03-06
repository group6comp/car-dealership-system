package carDealership;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static List<User> userList = new ArrayList<>();
    private static final String CSV_FILE = "src/data/users.csv";

    // Static block to initialize the user list from the CSV file
    static {
        loadUsersFromCSV();
    }

    // Load users from the CSV file
    private static void loadUsersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            String csvSplitBy = ",";
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(csvSplitBy);
                User user = new User(userData[0], userData[1], userData[2]);
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update the CSV file with the current user list
    private static void updateCSV() {
        try (FileWriter writer = new FileWriter(CSV_FILE)) {
            // Write the header
            writer.append("Username,Password,Role\n");
            // Write the data
            for (User user : userList) {
                writer.append(user.getUsername()).append(",")
                      .append(user.getPassword()).append(",")
                      .append(user.getRole()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if user exists with matching pass
    public static User validateLogin(String username, String password) {
        for (User u : userList) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null; // not found or password mismatch
    }

    public static User getUser(String username) {
        for (User u : userList) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null; // not found
    }

    public static User[] getUsers() {
        return userList.toArray(new User[0]);
    }

    public static void addUser(String username, String password, String role) {
        User user = new User(username, password, role);
        userList.add(user);
        updateCSV();
    }

    public static void removeUser(User user) {
        userList.remove(user);
        updateCSV();
    }

    public static void updateUser(User user, String username, String password, String role) {
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        updateCSV();
    }
}