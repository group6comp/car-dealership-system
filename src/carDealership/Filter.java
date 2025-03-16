package carDealership;

import java.util.List;

/**
 * The Filter class is used to filter the vehicle inventory based on various criteria.
 */
public class Filter {
    private Boolean carSelected;
    private Boolean motorcycleSelected;
    private String make;
    private String model;
    private String color;
    private String type;
    private int minYear;
    private int maxYear;
    private double minPrice;
    private double maxPrice;
    private boolean inStock;

    /**
     * Constructor for creating a Filter object with specific criteria.
     * 
     * @param carSelected whether cars are selected
     * @param motorcycleSelected whether motorcycles are selected
     * @param make the make of the vehicle
     * @param model the model of the vehicle
     * @param color the color of the vehicle
     * @param type the type of the vehicle
     * @param minYear the minimum year of the vehicle
     * @param maxYear the maximum year of the vehicle
     * @param minPrice the minimum price of the vehicle
     * @param maxPrice the maximum price of the vehicle
     * @param inStock show only vehicles in stock
     */
    public Filter(Boolean carSelected, Boolean motorcycleSelected, String make, String model, String color, String type, int minYear, int maxYear, double minPrice, double maxPrice, boolean inStock) {
        this.carSelected = carSelected;
        this.motorcycleSelected = motorcycleSelected;
        this.make = make;
        this.model = model;
        this.color = color;
        this.type = type;
        this.minYear = minYear;
        this.maxYear = maxYear;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.inStock = inStock;
    }

    /**
     * Default constructor for creating a Filter object with default criteria.
     */
    public Filter() {
        this.carSelected = true;
        this.motorcycleSelected = true;
        this.make = "";
        this.model = "";
        this.color = "";
        this.type = "";
        this.minYear = 0;
        this.maxYear = Integer.MAX_VALUE;
        this.minPrice = 0;
        this.maxPrice = Double.MAX_VALUE;
        this.inStock = false;
    }

    /**
     * Filter the inventory based on the criteria set in the Filter object.
     * 
     * @return a list of vehicles that match the filter criteria
     */
    public List<Vehicle> filterInventory() {
        List<Vehicle> inventory = Main.m_dealership.getVehicles();
        return inventory
                .stream()
                .filter(vehicle -> (carSelected && vehicle instanceof Car) || (motorcycleSelected && vehicle instanceof Motorcycle))
                .filter(vehicle -> make.isEmpty() || vehicle.getMake().equalsIgnoreCase(make))
                .filter(vehicle -> model.isEmpty() || vehicle.getModel().equalsIgnoreCase(model))
                .filter(vehicle -> color.isEmpty() || vehicle.getColor().equalsIgnoreCase(color))
                .filter(vehicle -> type.isEmpty() || vehicle.getType().equalsIgnoreCase(type))
                .filter(vehicle -> isEmpty(minYear, "min") || vehicle.getYear() >= minYear)
                .filter(vehicle -> isEmpty(maxYear, "max") || vehicle.getYear() <= maxYear)
                .filter(vehicle -> isEmpty(minPrice, "min") || vehicle.getPrice() >= minPrice)
                .filter(vehicle -> isEmpty(maxPrice, "max") || vehicle.getPrice() <= maxPrice)
                .filter(vehicle -> !inStock || vehicle.isInStock())
                .toList();
    }

    /**
     * Check if a number is empty based on the specified type (min or max).
     * 
     * @param num the number to check
     * @param str the type (min or max)
     * @return true if the number is empty, false otherwise
     */
    private boolean isEmpty(int num, String str) {
        if (str.equals("min")) {
            return num == 0;
        } else if (str.equals("max")) {
            return num == Integer.MAX_VALUE;
        } else {
            return false;
        }
    }

    /**
     * Check if a number is empty based on the specified type (min or max).
     * 
     * @param num the number to check
     * @param str the type (min or max)
     * @return true if the number is empty, false otherwise
     */
    private boolean isEmpty(double num, String str) {
        if (str.equals("min")) {
            return num == 0;
        } else if (str.equals("max")) {
            return num == Double.MAX_VALUE;
        } else {
            return false;
        }
    }

    // Getters and setters for the filter criteria

    public Boolean getCarSelected() {
        return carSelected;
    }

    public void setCarSelected(Boolean carSelected) {
        this.carSelected = carSelected;
    }

    public Boolean getMotorcycleSelected() {
        return motorcycleSelected;
    }

    public void setMotorcycleSelected(Boolean motorcycleSelected) {
        this.motorcycleSelected = motorcycleSelected;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(int minYear) {
        this.minYear = minYear;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(int maxYear) {
        this.maxYear = maxYear;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public boolean getInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}