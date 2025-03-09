package carDealership;

import java.util.List;

public class Filter {
    Boolean carSelected;
    Boolean motorcycleSelected;
    String make;
    String model;
    String color;
    String type;
    int minYear;
    int maxYear;
    double minPrice;
    double maxPrice;
    int minMileage;
    int maxMileage;
    Vehicle.Status status;

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

    public Filter(){
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

    public List<Vehicle> filterInventory(){
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

    private boolean isEmpty(int num, String str){
        if (str=="min"){
            return num == 0;
        }
        else if (str=="max"){
            return num == Integer.MAX_VALUE;
        }
        else{
            return false;
        }

    }

    private boolean isEmpty(double num, String str){
        if (str=="min"){
            return num == 0;
        }
        else if (str=="max"){
            return num == Double.MAX_VALUE;
        }
        else{
            return false;
        }

    }

    public void reset(){
        
    }

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
