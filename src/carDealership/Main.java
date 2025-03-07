package carDealership;

import static carDealership.User.Role;

import java.util.Scanner;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static Dealership m_dealership;
    public static User user;
    public static JFrame mainFrame;
    public static JPanel contentPane;
    public static CardLayout cardLayout;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        mainFrame = new JFrame("Dealership System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(100, 100, 650, 400);

        contentPane = new JPanel();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);
        mainFrame.setContentPane(contentPane);

        // Load the existing dealership object
        load();
        if (m_dealership == null) {
            contentPane.add(new FirstLaunchPage(), "firstLaunchPage");
            cardLayout.show(contentPane, "firstLaunchPage");
        } else {
            if (user == null) {
                new VisitorMainUI();
            } else {
                showRoleUI();
            }
        }
        mainFrame.setVisible(true);

        // Add shutdown hook to save data on close
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    public static void showRoleUI() {
        if (user == null || user.getRole() == null) {
            // Handle the case where the user or role is null
            new VisitorMainUI();
            return;
        }

        Role role = user.getRole();
        JPanel panel = null;
        switch (role) {
            case ADMIN:
                if (contentPane.getComponentCount() == 0 || !isPanelAdded("adminMainUI")) {
                    contentPane.add(new AdminMainUI(), "adminMainUI");
                }
                cardLayout.show(contentPane, "adminMainUI");
                break;
            case MANAGER:
                //panel = new ManagerMainUI();
                break;
            case SALESPERSON:
               // panel = new SalespersonMainUI();
                break;
            case CUSTOMER:
                //panel = new CustomerMainUI();
                break;
            default:
                panel = new VisitorMainUI();
                break;
        }
        if (panel != null) {
            contentPane.removeAll();
            contentPane.add(panel, role + "Panel");
            cardLayout.show(contentPane, role + "Panel");
            mainFrame.revalidate();
            mainFrame.repaint();
        }
    }

    private static boolean isPanelAdded(String panelName) {
        for (java.awt.Component comp : contentPane.getComponents()) {
            if (comp.getName() != null && comp.getName().equals(panelName)) {
                return true;
            }
        }
        return false;
    }

    public static void createDealership(String name, String location, int capacity)
            throws IllegalCapacityException {
        if (capacity < 1 || capacity > 100) {
            throw new IllegalCapacityException();
        }
        m_dealership = new Dealership(name, location, capacity);
    }

    public static void save() throws IOException {
        File saveFile = new File("save.data");
        try (FileOutputStream outFileStream = new FileOutputStream(saveFile);
             ObjectOutputStream outObjStream = new ObjectOutputStream(outFileStream)) {
            outObjStream.writeObject(m_dealership);
        }
    }

    public static void load() throws IOException, ClassNotFoundException {
        File saveFile = new File("save.data");
        if (!saveFile.exists()) {
            m_dealership = null;
            return;
        }

        try (FileInputStream inFileStream = new FileInputStream(saveFile);
             ObjectInputStream inObjStream = new ObjectInputStream(inFileStream)) {
            m_dealership = (Dealership) inObjStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            m_dealership = null;
        }
    }

    public static void showMainFrame() {
        mainFrame.setVisible(true);
    }

    public static void logout() {
        user = null;
        showRoleUI();
    }
}