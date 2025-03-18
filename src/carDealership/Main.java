package carDealership;

import java.util.Scanner;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import carDealership.User.Role;
import java.awt.CardLayout;

/**
 * The Main class serves as the entry point for the dealership system
 * application.
 * It initializes the main frame, loads the dealership data, and manages the UI
 * panels.
 */
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static Dealership m_dealership;
    public static User user;
    public static Role role = Role.VISITOR;
    public static JFrame mainFrame;
    public static JPanel contentPane;
    public static CardLayout cardLayout;

    /**
     * The main method initializes the application.
     * 
     * @param args command-line arguments
     * @throws IOException            if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object cannot be
     *                                found
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        // Initialize the main frame
        mainFrame = new JFrame("Dealership System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(100, 100, 650, 400);

        // Initialize the content pane with CardLayout
        contentPane = new JPanel();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);
        mainFrame.setContentPane(contentPane);

        // Load the existing dealership object
        m_dealership = load();
        if (m_dealership != null) {
            System.out.println("Dealership loaded successfully.");
            showMainUI();
        } else {
            System.out.println("No dealership found. Creating a new one.");
            contentPane.add(new FirstLaunchPage(), "firstLaunchPage");
            cardLayout.show(contentPane, "firstLaunchPage");
        }

        mainFrame.setVisible(true);
    }

    /**
     * Show the main UI panel.
     */
    public static void showMainUI() {
        if (contentPane.getComponentCount() == 0 || !isPanelAdded("mainUI")) {
            contentPane.add(new MainUI(), "mainUI");
        }
        cardLayout.show(contentPane, "mainUI");
    }

    /**
     * Check if a panel with the specified name has been added to the content pane.
     * 
     * @param panelName the name of the panel
     * @return true if the panel has been added, false otherwise
     */
    private static boolean isPanelAdded(String panelName) {
        for (java.awt.Component comp : contentPane.getComponents()) {
            if (comp.getName() != null && comp.getName().equals(panelName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create a new dealership with the specified name, location, and capacity.
     * 
     * @param name     the name of the dealership
     * @param location the location of the dealership
     * @param capacity the inventory capacity of the dealership
     */
    public static void createDealership(String name, String location, int capacity) {
        m_dealership = new Dealership(name, location, capacity);
    }

    /**
     * Log out the current user and reset the role to VISITOR.
     */
    public static void logout() {
        user = null;
        role = Role.VISITOR;
        showMainUI();
    }

    /**
     * Get the current logged-in user.
     * 
     * @return the current user, or null if no user is logged in
     */
    public static User getCurrentUser() {
        return user;
    }

    /**
     * Load the dealership data from a file.
     * 
     * @return the loaded Dealership object, or null if no data is found
     * @throws IOException            if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object cannot be
     *                                found
     */
    public static Dealership load() throws IOException, ClassNotFoundException {
        File saveFile = new File("save.data");
        if (!saveFile.exists()) {
            return null;
        }

        try (FileInputStream inFileStream = new FileInputStream(saveFile);
                ObjectInputStream inObjStream = new ObjectInputStream(inFileStream)) {
            return (Dealership) inObjStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}