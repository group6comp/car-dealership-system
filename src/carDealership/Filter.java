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
    private int minMileage;
    private int maxMileage;
    private Vehicle.Status status;

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
     * @param minMileage the minimum mileage of the vehicle
     * @param maxMileage the maximum mileage of the vehicle
     * @param status the status of the vehicle (e.g., available, sold)
     */
    public Filter(Boolean carSelected, Boolean motorcycleSelected, String make, String model, String color, String type, int minYear, int maxYear, double minPrice, double maxPrice, int minMileage, int maxMileage, Vehicle.Status status) {
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
        this.minMileage = minMileage;
        this.maxMileage = maxMileage;
        this.status = status;
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
        this.minMileage = 0;
        this.maxMileage = Integer.MAX_VALUE;
        this.status = null;
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
                .filter(vehicle -> isEmpty(minMileage, "min") || vehicle.getMileage() >= minMileage)
                .filter(vehicle -> isEmpty(maxMileage, "max") || vehicle.getMileage() <= maxMileage)
                .filter(vehicle -> status == null || vehicle.getStatus() == status)
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

    public int getMinMileage() {
        return minMileage;
    }

    public void setMinMileage(int minMileage) {
        this.minMileage = minMileage;
    }

    public int getMaxMileage() {
        return maxMileage;
    }

    public void setMaxMileage(int maxMileage) {
        this.maxMileage = maxMileage;
    }

    public Vehicle.Status getStatus() {
        return status;
    }

    public void setStatus(Vehicle.Status status) {
        this.status = status;
    }
}