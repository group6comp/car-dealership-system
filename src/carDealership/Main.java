package carDealership;

import static carDealership.User.Role;

import java.util.Scanner;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import carDealership.User.Role;

import java.awt.CardLayout;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static Dealership m_dealership;
    public static User user = null;
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
        m_dealership = load();
        if(m_dealership != null) {
            System.out.println("Dealership loaded successfully.");
        } else {
            System.out.println("No dealership found. Creating a new one.");
            contentPane.add(new FirstLaunchPage(), "firstLaunchPage");
            cardLayout.show(contentPane, "firstLaunchPage");
        }
        showRoleUI();
        mainFrame.setVisible(true);
}

    public static void showRoleUI() {
        if (user == null) {
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

    public static void createDealership(String name, String location, int capacity){
        m_dealership = new Dealership(name, location, capacity);
    }

    public static void showMainFrame() {
        mainFrame.setVisible(true);
    }

    public static void logout() {
        user = null;
        showRoleUI();
    }

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