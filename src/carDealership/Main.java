package carDealership;

import java.util.Scanner;
import persistance.DealershipLayer;
import java.io.*;
import java.sql.SQLException;
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

    public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {
        mainFrame = new JFrame("Dealership System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(100, 100, 650, 400);

        contentPane = new JPanel();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);
        mainFrame.setContentPane(contentPane);

        // If there is not an existing dealership
        var dealership = new DealershipLayer();
        if (!dealership.existsAndSet()) {
            // Launch dealership creation page
            FirstLaunchPage newPage = new FirstLaunchPage();
        } else {
            // Load the existing dealership object
            m_dealership = new Dealership(dealership.getNname(), dealership.getLocation(), dealership.getCapacity());
            if (user == null) {
                new VisitorMainUI();
            } else {
                showRoleUI();
            }
        }
        mainFrame.setVisible(true);
    }


    public static void showRoleUI() {
        String role = user.getRole();
        JPanel panel = null;
        switch (role.toLowerCase()) {
			case "admin":
				if (contentPane.getComponentCount() == 0 || !isPanelAdded("adminMainUI")) {
					contentPane.add(new AdminMainUI(), "adminMainUI");
				}
				cardLayout.show(contentPane, "adminMainUI");
				break;
            case "manager":
                //panel = new ManagerMainUI();
                break;
            case "salesperson":
               // panel = new SalespersonMainUI();
                break;
            case "customer":
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
            throws IllegalCapacityException, SQLException {
        if (capacity < 1 || capacity > 100) {
            throw new IllegalCapacityException();
        }
        m_dealership = new Dealership(name, location, capacity);
    }

    public static void save() throws IOException {
        File saveFile = new File("save.data");
        FileOutputStream outFileStream = null;
        try {
            outFileStream = new FileOutputStream(saveFile);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ObjectOutputStream outObjStream = null;
        try {
            outObjStream = new ObjectOutputStream(outFileStream);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        outObjStream.writeObject(m_dealership);
        outObjStream.close();
    }
}