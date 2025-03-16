package data;

import carDealership.Dealership;
import carDealership.User.Role;
import carDealership.User;
import java.time.LocalDate;

public class PopulateSaveData {
    public static void main(String[] args) {
        
        // Create a new Dealership object
        Dealership dealership = new Dealership("Centre-Ville", "Montreal", 90);

        // Add 35 cars to the dealership with unique and realistic data
        dealership.addCar("Toyota", "Camry", "White", 2021, 24000, 1, "Sedan");
        dealership.addCar("Honda", "Civic", "Black", 2020, 22000, 3, "Sedan");
        dealership.addCar("Ford", "Mustang", "Red", 2022, 35000, 1, "Coupe");
        dealership.addCar("Chevrolet", "Malibu", "Blue", 2019, 21000,0, "Sedan");
        dealership.addCar("Nissan", "Altima", "Gray", 2021, 23000,2, "Sedan");
        dealership.addCar("BMW", "3 Series", "White", 2022, 41000,5, "Sedan");
        dealership.addCar( "Audi", "A4", "Black", 2021, 39000,3, "Sedan");
        dealership.addCar( "Mercedes-Benz", "C-Class", "Silver", 2020, 42000,2, "Sedan");
        dealership.addCar( "Volkswagen", "Passat", "Blue", 2019, 25000,4, "Sedan");
        dealership.addCar( "Hyundai", "Elantra", "Red", 2021, 20000,8, "Sedan");
        dealership.addCar( "Kia", "Optima", "White", 2020, 21000,2, "Sedan");
        dealership.addCar( "Mazda", "Mazda3", "Black", 2022, 22000,1, "Sedan");
        dealership.addCar( "Subaru", "Impreza", "Gray", 2021, 23000,1, "Sedan");
        dealership.addCar( "Tesla", "Model 3", "White", 2022, 45000, 0,"Sedan");
        dealership.addCar( "Volvo", "S60", "Blue", 2020, 40000, 9, "Sedan");
        dealership.addCar( "Lexus", "ES", "Silver", 2021, 43000, 4, "Sedan");
        dealership.addCar( "Acura", "TLX", "Red", 2022, 38000, 3, "Sedan");
        dealership.addCar( "Infiniti", "Q50", "Black", 2020, 37000, 3, "Sedan");
        dealership.addCar( "Cadillac", "CT5", "White", 2021, 46000, 8, "Sedan");
        dealership.addCar( "Jaguar", "XE", "Blue", 2022, 47000, 5,"Sedan");
        dealership.addCar( "Genesis", "G70", "Gray", 2021, 39000, 6,"Sedan");
        dealership.addCar( "Alfa Romeo", "Giulia", "Red", 2020, 45000, 6,"Sedan");
        dealership.addCar( "Porsche", "Panamera", "Black", 2022, 87000, 6,"Sedan");
        dealership.addCar( "Maserati", "Ghibli", "White", 2021, 76000, 6,"Sedan");
        dealership.addCar( "Aston Martin", "Rapide", "Blue", 2020, 150000, 2,"Sedan");
        dealership.addCar( "Bentley", "Flying Spur", "Silver", 2022, 200000, 1, "Sedan");
        dealership.addCar( "Rolls-Royce", "Ghost", "Black", 2021, 300000, 7,"Sedan");
        dealership.addCar( "Ferrari", "Roma", "Red", 2022, 220000, 0,"Coupe");
        dealership.addCar( "Lamborghini", "Huracan", "Yellow", 2021, 250000, 2, "Coupe");
        dealership.addCar( "McLaren", "GT", "Orange", 2020, 210000, 1,"Coupe");
        dealership.addCar( "Bugatti", "Chiron", "Blue", 2022, 3000000, 8,"Coupe");
        dealership.addCar( "Pagani", "Huayra", "Silver", 2021, 3200000, 11,"Coupe");
        dealership.addCar( "Koenigsegg", "Jesko", "White", 2022, 2800000, 7,"Coupe");
        dealership.addCar( "Rimac", "C_Two", "Black", 2021, 2000000, 12,"Coupe");
        dealership.addCar( "Lotus", "Evija", "Green", 2020, 2100000, 3,"Coupe");

        // Add 35 motorcycles to the dealership with unique and realistic data
        dealership.addMotorcycle( "Harley-Davidson", "Street 750", "Black", 2021, 7500, 0,"Cruiser");
        dealership.addMotorcycle( "Yamaha", "YZF-R3", "Blue", 2020, 5000, 0,"Sport");
        dealership.addMotorcycle( "Kawasaki", "Ninja 400", "Green", 2022, 6000, 3,"Sport");
        dealership.addMotorcycle( "Honda", "CBR500R", "Red", 2019, 7000, 5,"Sport");
        dealership.addMotorcycle( "Suzuki", "GSX-R600", "White", 2021, 8000, 9,"Sport");
        dealership.addMotorcycle( "Ducati", "Panigale V2", "Red", 2022, 16000, 12,"Sport");
        dealership.addMotorcycle( "BMW", "S1000RR", "Black", 2021, 18000, 14,"Sport");
        dealership.addMotorcycle( "Triumph", "Street Triple", "Silver", 2020, 1, 2,"Naked");
        dealership.addMotorcycle( "KTM", "Duke 390", "Orange", 2019, 5500, 1,"Naked");
        dealership.addMotorcycle( "Aprilia", "RS 660", "Blue", 2021, 11000, 8,"Sport");
        dealership.addMotorcycle( "Indian", "Scout Bobber", "Black", 2020, 12000, 6,"Cruiser");
        dealership.addMotorcycle( "Moto Guzzi", "V7 III", "Green", 2022, 10000, 2,"Classic");
        dealership.addMotorcycle( "Royal Enfield", "Interceptor 650", "Orange", 2021, 6000, 5, "Classic");
        dealership.addMotorcycle( "Husqvarna", "Svartpilen 401", "Black", 2022, 6500, 5, "Scrambler");
        dealership.addMotorcycle( "MV Agusta", "Brutale 800", "Red", 2020, 14000, 9, "Naked");
        dealership.addMotorcycle( "Benelli", "TNT 600", "White", 2021, 7000, 11, "Naked");
        dealership.addMotorcycle( "Zero", "SR/F", "Blue", 2022, 19000, 0, "Electric");
        dealership.addMotorcycle( "Energica", "Ego", "Red", 2020, 23000, 4, "Electric");
        dealership.addMotorcycle( "Lightning", "LS-218", "Black", 2021, 38000, 2, "Electric");
        dealership.addMotorcycle( "LiveWire", "One", "Yellow", 2022, 22000, 15, "Electric");
        dealership.addMotorcycle( "Victory", "Octane", "Gray", 2021, 13000, 1, "Cruiser");
        dealership.addMotorcycle( "Bajaj", "Pulsar NS200", "Blue", 2020, 3000, 3, "Naked");
        dealership.addMotorcycle( "Hero", "Xtreme 200R", "Red", 2022, 2500, 4, "Naked");
        dealership.addMotorcycle( "TVS", "Apache RTR 200", "White", 2021, 2800, 4, "Naked");
        dealership.addMotorcycle( "Mahindra", "Mojo", "Black", 2020, 3200, 0, "Touring");
        dealership.addMotorcycle( "UM", "Renegade Commando", "Green", 2021, 3500, 7, "Cruiser");
        dealership.addMotorcycle( "CFMoto", "300NK", "Orange", 2022, 4000, 6, "Naked");
        dealership.addMotorcycle( "SYM", "Wolf CR300i", "Red", 2020, 4500, 9, "Classic");
        dealership.addMotorcycle( "Kymco", "K-Pipe 125", "Blue", 2021, 2000, 1, "Naked");
        dealership.addMotorcycle( "Keeway", "RKF 125", "Black", 2022, 2200, 13, "Naked");
        dealership.addMotorcycle( "SWM", "Gran Milano 440", "Silver", 2021, 5000, 11, "Classic");
        dealership.addMotorcycle( "AJP", "PR7", "White", 2020, 9000, 7, "Adventure");
        dealership.addMotorcycle( "Beta", "RR 300", "Red", 2021, 8000, 5, "Enduro");
        dealership.addMotorcycle( "Sherco", "300 SEF-R", "Blue", 2022, 8500, 3, "Enduro");
        dealership.addMotorcycle( "GasGas", "EC 300", "Red", 2020, 7500, 2, "Enduro");

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

        // Add 15 sales to the dealership
        dealership.sellVehicle(dealership.getVehicleFromId(7), dealership.getUser("salesperson1"), "John Doe", "555-1234", LocalDate.of(2024, 1, 15), false);
        dealership.sellVehicle(dealership.getVehicleFromId(10), dealership.getUser("salesperson4"), "Jane Smith", "555-5678", LocalDate.of(2024, 2, 10), false);
        dealership.sellVehicle(dealership.getVehicleFromId(14), dealership.getUser("salesperson1"), "Alice Johnson", "555-8765", LocalDate.of(2024, 3, 5), false);
        dealership.sellVehicle(dealership.getVehicleFromId(19), dealership.getUser("salesperson5"), "Bob Brown", "555-4321", LocalDate.of(2024, 4, 20), false);
        dealership.sellVehicle(dealership.getVehicleFromId(22), dealership.getUser("salesperson1"), "Charlie Davis", "555-6789", LocalDate.of(2024, 5, 15), true);
        dealership.sellVehicle(dealership.getVehicleFromId(28), dealership.getUser("salesperson4"), "Diana Evans", "555-9876", LocalDate.of(2024, 6, 10), false);
        dealership.sellVehicle(dealership.getVehicleFromId(31), dealership.getUser("salesperson1"), "Ethan Foster", "555-3456", LocalDate.of(2024, 7, 25), false);
        dealership.sellVehicle(dealership.getVehicleFromId(36), dealership.getUser("salesperson4"), "Fiona Green", "555-6543", LocalDate.of(2024, 8, 30), false);
        dealership.sellVehicle(dealership.getVehicleFromId(40), dealership.getUser("salesperson1"), "George Harris", "555-7890", LocalDate.of(2024, 9, 15), false);
        dealership.sellVehicle(dealership.getVehicleFromId(44), dealership.getUser("salesperson2"), "Hannah Irving", "555-0987", LocalDate.of(2024, 10, 10), true);
        dealership.sellVehicle(dealership.getVehicleFromId(47), dealership.getUser("salesperson3"), "Ian Jackson", "555-5670", LocalDate.of(2024, 11, 5), false);
        dealership.sellVehicle(dealership.getVehicleFromId(52), dealership.getUser("salesperson2"), "Jackie King", "555-8760", LocalDate.of(2024, 12, 20), false);
        dealership.sellVehicle(dealership.getVehicleFromId(56), dealership.getUser("salesperson1"), "Karen Lewis", "555-4320", LocalDate.of(2024, 1, 30), false);
        dealership.sellVehicle(dealership.getVehicleFromId(65), dealership.getUser("salesperson2"), "Liam Martin", "555-6780", LocalDate.of(2024, 2, 25), false);
        dealership.sellVehicle(dealership.getVehicleFromId(59), dealership.getUser("salesperson2"), "Mia Nelson", "555-9870", LocalDate.of(2024, 3, 20), false);

        // Save the dealership object to the save.data file
        dealership.save();
    }
}