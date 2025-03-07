package data;

import carDealership.Car;
import carDealership.Dealership;
import carDealership.Motorcycle;
import carDealership.Vehicle.Status;
import carDealership.User.Role;
import carDealership.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PopulateSaveData {
    public static void main(String[] args) {
        // Create a new Dealership object
        Dealership dealership = new Dealership("Centre-Ville", "Montreal", 90);

        // Add 35 cars to the dealership with unique and realistic data
        dealership.addVehicle(new Car(1, "Toyota", "Camry", "White", 2021, 24000, 0, Status.AVAILABLE, "Sedan"));
        dealership.addVehicle(new Car(2, "Honda", "Civic", "Black", 2020, 22000, 0, Status.AVAILABLE, "Sedan"));
        dealership.addVehicle(new Car(3, "Ford", "Mustang", "Red", 2022, 35000, 0, Status.AVAILABLE, "Coupe"));
        dealership.addVehicle(new Car(4, "Chevrolet", "Malibu", "Blue", 2019, 21000,5000,  Status.AVAILABLE, "Sedan"));
        dealership.addVehicle(new Car(5, "Nissan", "Altima", "Gray", 2021, 23000,5000,  Status.AVAILABLE, "Sedan"));
        dealership.addVehicle(new Car(6, "BMW", "3 Series", "White", 2022, 41000,5000,  Status.AVAILABLE, "Sedan"));
        dealership.addVehicle(new Car(7, "Audi", "A4", "Black", 2021, 39000,10000, Status.SOLD, "Sedan"));
        dealership.addVehicle(new Car(8, "Mercedes-Benz", "C-Class", "Silver", 2020, 42000,10000, Status.SOLD, "Sedan"));
        dealership.addVehicle(new Car(9, "Volkswagen", "Passat", "Blue", 2019, 25000,10000, Status.SOLD, "Sedan"));
        dealership.addVehicle(new Car(10, "Hyundai", "Elantra", "Red", 2021, 20000,10000, Status.MAINTENANCE, "Sedan"));
        dealership.addVehicle(new Car(11, "Kia", "Optima", "White", 2020, 21000,10000, Status.MAINTENANCE, "Sedan"));
        dealership.addVehicle(new Car(12, "Mazda", "Mazda3", "Black", 2022, 22000,0, Status.MAINTENANCE, "Sedan"));
        dealership.addVehicle(new Car(13, "Subaru", "Impreza", "Gray", 2021, 23000,0, Status.MAINTENANCE, "Sedan"));
        dealership.addVehicle(new Car(14, "Tesla", "Model 3", "White", 2022, 45000, 50000,  Status.AVAILABLE,"Sedan"));
        dealership.addVehicle(new Car(15, "Volvo", "S60", "Blue", 2020, 40000, 50000,  Status.AVAILABLE, "Sedan"));
        dealership.addVehicle(new Car(16, "Lexus", "ES", "Silver", 2021, 43000, 50000,  Status.AVAILABLE, "Sedan"));
        dealership.addVehicle(new Car(17, "Acura", "TLX", "Red", 2022, 38000, 500,  Status.AVAILABLE, "Sedan"));
        dealership.addVehicle(new Car(18, "Infiniti", "Q50", "Black", 2020, 37000, 500,  Status.AVAILABLE, "Sedan"));
        dealership.addVehicle(new Car(19, "Cadillac", "CT5", "White", 2021, 46000, 0,  Status.SOLD, "Sedan"));
        dealership.addVehicle(new Car(20, "Jaguar", "XE", "Blue", 2022, 47000, 0,  Status.SOLD,"Sedan"));
        dealership.addVehicle(new Car(21, "Genesis", "G70", "Gray", 2021, 39000, 50000,  Status.SOLD,"Sedan"));
        dealership.addVehicle(new Car(22, "Alfa Romeo", "Giulia", "Red", 2020, 45000, 50000,  Status.SOLD,"Sedan"));
        dealership.addVehicle(new Car(23, "Porsche", "Panamera", "Black", 2022, 87000, 50000,  Status.SOLD,"Sedan"));
        dealership.addVehicle(new Car(24, "Maserati", "Ghibli", "White", 2021, 76000, 50000,  Status.SOLD,"Sedan"));
        dealership.addVehicle(new Car(25, "Aston Martin", "Rapide", "Blue", 2020, 150000, 50000,  Status.AVAILABLE,"Sedan"));
        dealership.addVehicle(new Car(26, "Bentley", "Flying Spur", "Silver", 2022, 200000, 20000,  Status.AVAILABLE, "Sedan"));
        dealership.addVehicle(new Car(27, "Rolls-Royce", "Ghost", "Black", 2021, 300000, 20000,  Status.AVAILABLE,"Sedan"));
        dealership.addVehicle(new Car(28, "Ferrari", "Roma", "Red", 2022, 220000, 0,  Status.AVAILABLE,"Coupe"));
        dealership.addVehicle(new Car(29, "Lamborghini", "Huracan", "Yellow", 2021, 250000, 0,  Status.AVAILABLE, "Coupe"));
        dealership.addVehicle(new Car(30, "McLaren", "GT", "Orange", 2020, 210000, 12000,  Status.AVAILABLE,"Coupe"));
        dealership.addVehicle(new Car(31, "Bugatti", "Chiron", "Blue", 2022, 3000000, 0,  Status.SOLD,"Coupe"));
        dealership.addVehicle(new Car(32, "Pagani", "Huayra", "Silver", 2021, 3200000, 200,  Status.SOLD,"Coupe"));
        dealership.addVehicle(new Car(33, "Koenigsegg", "Jesko", "White", 2022, 2800000, 15000,  Status.SOLD,"Coupe"));
        dealership.addVehicle(new Car(34, "Rimac", "C_Two", "Black", 2021, 2000000, 0,  Status.MAINTENANCE,"Coupe"));
        dealership.addVehicle(new Car(35, "Lotus", "Evija", "Green", 2020, 2100000, 20000,  Status.SOLD,"Coupe"));

        // Add 35 motorcycles to the dealership with unique and realistic data
        dealership.addVehicle(new Motorcycle(1, "Harley-Davidson", "Street 750", "Black", 2021, 7500, 0, Status.AVAILABLE,"Cruiser"));
        dealership.addVehicle(new Motorcycle(2, "Yamaha", "YZF-R3", "Blue", 2020, 5000, 0, Status.AVAILABLE,"Sport"));
        dealership.addVehicle(new Motorcycle(3, "Kawasaki", "Ninja 400", "Green", 2022, 6000, 0, Status.AVAILABLE,"Sport"));
        dealership.addVehicle(new Motorcycle(4, "Honda", "CBR500R", "Red", 2019, 7000, 5000,  Status.AVAILABLE,"Sport"));
        dealership.addVehicle(new Motorcycle(5, "Suzuki", "GSX-R600", "White", 2021, 8000, 5000,  Status.AVAILABLE,"Sport"));
        dealership.addVehicle(new Motorcycle(6, "Ducati", "Panigale V2", "Red", 2022, 16000, 5000,  Status.AVAILABLE,"Sport"));
        dealership.addVehicle(new Motorcycle(7, "BMW", "S1000RR", "Black", 2021, 18000, 10000, Status.SOLD,"Sport"));
        dealership.addVehicle(new Motorcycle(8, "Triumph", "Street Triple", "Silver", 2020, 9000, 10000, Status.SOLD,"Naked"));
        dealership.addVehicle(new Motorcycle(9, "KTM", "Duke 390", "Orange", 2019, 5500, 10000, Status.SOLD,"Naked"));
        dealership.addVehicle(new Motorcycle(10, "Aprilia", "RS 660", "Blue", 2021, 11000, 10000, Status.MAINTENANCE,"Sport"));
        dealership.addVehicle(new Motorcycle(11, "Indian", "Scout Bobber", "Black", 2020, 12000, 10000, Status.MAINTENANCE,"Cruiser"));
        dealership.addVehicle(new Motorcycle(12, "Moto Guzzi", "V7 III", "Green", 2022, 10000, 10000, Status.MAINTENANCE,"Classic"));
        dealership.addVehicle(new Motorcycle(13, "Royal Enfield", "Interceptor 650", "Orange", 2021, 6000, 0, Status.MAINTENANCE, "Classic"));
        dealership.addVehicle(new Motorcycle(14, "Husqvarna", "Svartpilen 401", "Black", 2022, 6500, 0, Status.MAINTENANCE, "Scrambler"));
        dealership.addVehicle(new Motorcycle(15, "MV Agusta", "Brutale 800", "Red", 2020, 14000, 0, Status.MAINTENANCE, "Naked"));
        dealership.addVehicle(new Motorcycle(16, "Benelli", "TNT 600", "White", 2021, 7000, 50000,  Status.AVAILABLE, "Naked"));
        dealership.addVehicle(new Motorcycle(17, "Zero", "SR/F", "Blue", 2022, 19000, 50000,  Status.AVAILABLE, "Electric"));
        dealership.addVehicle(new Motorcycle(18, "Energica", "Ego", "Red", 2020, 23000, 50000,  Status.AVAILABLE, "Electric"));
        dealership.addVehicle(new Motorcycle(19, "Lightning", "LS-218", "Black", 2021, 38000, 500,  Status.AVAILABLE, "Electric"));
        dealership.addVehicle(new Motorcycle(20, "LiveWire", "One", "Yellow", 2022, 22000, 500,  Status.AVAILABLE, "Electric"));
        dealership.addVehicle(new Motorcycle(21, "Victory", "Octane", "Gray", 2021, 13000, 0,  Status.SOLD, "Cruiser"));
        dealership.addVehicle(new Motorcycle(22, "Bajaj", "Pulsar NS200", "Blue", 2020, 3000, 0,  Status.SOLD, "Naked"));
        dealership.addVehicle(new Motorcycle(23, "Hero", "Xtreme 200R", "Red", 2022, 2500, 50000,  Status.SOLD, "Naked"));
        dealership.addVehicle(new Motorcycle(24, "TVS", "Apache RTR 200", "White", 2021, 2800, 50000,  Status.SOLD, "Naked"));
        dealership.addVehicle(new Motorcycle(25, "Mahindra", "Mojo", "Black", 2020, 3200, 50000,  Status.SOLD, "Touring"));
        dealership.addVehicle(new Motorcycle(26, "UM", "Renegade Commando", "Green", 2021, 3500, 50000,  Status.AVAILABLE, "Cruiser"));
        dealership.addVehicle(new Motorcycle(27, "CFMoto", "300NK", "Orange", 2022, 4000, 20000,  Status.AVAILABLE, "Naked"));
        dealership.addVehicle(new Motorcycle(28, "SYM", "Wolf CR300i", "Red", 2020, 4500, 20000,  Status.AVAILABLE, "Classic"));
        dealership.addVehicle(new Motorcycle(29, "Kymco", "K-Pipe 125", "Blue", 2021, 2000, 0,  Status.AVAILABLE, "Naked"));
        dealership.addVehicle(new Motorcycle(30, "Keeway", "RKF 125", "Black", 2022, 2200, 0,  Status.AVAILABLE, "Naked"));
        dealership.addVehicle(new Motorcycle(31, "SWM", "Gran Milano 440", "Silver", 2021, 5000, 12000,  Status.AVAILABLE, "Classic"));
        dealership.addVehicle(new Motorcycle(32, "AJP", "PR7", "White", 2020, 9000, 0,  Status.SOLD, "Adventure"));
        dealership.addVehicle(new Motorcycle(33, "Beta", "RR 300", "Red", 2021, 8000, 200,  Status.SOLD, "Enduro"));
        dealership.addVehicle(new Motorcycle(34, "Sherco", "300 SEF-R", "Blue", 2022, 8500, 15000,  Status.SOLD, "Enduro"));
        dealership.addVehicle(new Motorcycle(35, "GasGas", "EC 300", "Red", 2020, 7500, 0,  Status.MAINTENANCE, "Enduro"));

        dealership.addUser(new User("admin1", "admin1", Role.ADMIN));
        dealership.addUser(new User("admin2", "admin2", Role.ADMIN));
        dealership.addUser(new User("manager1", "manager1", Role.MANAGER));
        dealership.addUser(new User("manager2", "manager2", Role.MANAGER));
        dealership.addUser(new User("manager3", "manager3", Role.MANAGER));
        dealership.addUser(new User("salesperson1", "salesperson1", Role.SALESPERSON));
        dealership.addUser(new User("salesperson2", "salesperson2", Role.SALESPERSON));
        dealership.addUser(new User("salesperson3", "salesperson3", Role.SALESPERSON));
        dealership.addUser(new User("salesperson4", "salesperson4", Role.SALESPERSON));
        dealership.addUser(new User("salesperson5", "salesperson5", Role.SALESPERSON));
        dealership.addUser(new User("customer1", "customer1", Role.CUSTOMER));
        dealership.addUser(new User("customer2", "customer2", Role.CUSTOMER));
        dealership.addUser(new User("customer3", "customer3", Role.CUSTOMER));
        dealership.addUser(new User("customer4", "customer4", Role.CUSTOMER));
        dealership.addUser(new User("customer5", "customer5", Role.CUSTOMER));
        

        // Save the dealership object to the save.data file
        try (FileOutputStream fileOut = new FileOutputStream("save.data");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(dealership);
            System.out.println("Data has been saved to save.data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}