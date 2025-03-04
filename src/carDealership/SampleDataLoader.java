package carDealership;

/**
 * Updated to accept a DealershipNew object 
 * instead of the old Dealership.
 */
public class SampleDataLoader {

    private static int vehicleCounter = 100;

    public static void loadSampleVehicles(DealershipNew d) {
        Car c1 = new Car("Toyota", "Corolla", "Blue", 2022, 20000, "Sedan");
        c1.setId(vehicleCounter++);
        Car c2 = new Car("Honda", "Civic", "White", 2021, 19000, "Sedan");
        c2.setId(vehicleCounter++);
        Car c3 = new Car("Ford", "Mustang", "Red", 2020, 30000, "Coupe");
        c3.setId(vehicleCounter++);
        d.addVehicle(c1);
        d.addVehicle(c2);
        d.addVehicle(c3);

        Motorcycle m1 = new Motorcycle("Harley", "Nightster", "Black", 2023, 22000, "Drag");
        m1.setId(vehicleCounter++);
        Motorcycle m2 = new Motorcycle("Yamaha", "MT-07", "Gray", 2022, 8000, "Sport");
        m2.setId(vehicleCounter++);
        d.addVehicle(m1);
        d.addVehicle(m2);
    }
}

