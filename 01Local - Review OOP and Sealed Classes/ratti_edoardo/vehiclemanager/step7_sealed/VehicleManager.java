package vehiclemanager.step7_sealed;

import java.time.LocalDateTime;
import java.util.Scanner;

interface ProfessionalVehicle {
    void updateLicense(int licenseNumber);
}

abstract sealed class Vehicle permits Bike, Car, Truck, Bus {
    private int number;
    private int price;
    protected LocalDateTime enrolmentDate;

    public Vehicle(int number, int price) {
        this.number = number;
        this.price = (price > 0) ? price : 100;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }

    public void sell() {
        enrolmentDate = LocalDateTime.now();
    }

    public abstract String getTextualRepresentation();
}

final class Bike extends Vehicle {
    public Bike(int number, int price) {
        super(number, price);
    }

    @Override
    public String getTextualRepresentation() {
        return "number: " + getNumber() + " - price: " + getPrice() + " - enrolment date: " + enrolmentDate;
    }
}

final class Car extends Vehicle {
    private int numDoors;

    public Car(int number, int price, int numDoors) {
        super(number, price);
        this.numDoors = (numDoors > 0) ? numDoors : 5;
    }

    public int getNumDoors() {
        return numDoors;
    }

    @Override
    public String getTextualRepresentation() {
        return "number: " + getNumber() + " - price: " + getPrice() + " - doors: " + numDoors
                + " - enrolment date: " + enrolmentDate;
    }
}

final class Truck extends Vehicle implements ProfessionalVehicle {
    private int trailerVolume;
    private int professionalLicense;

    public Truck(int number, int price, int trailerVolume) {
        super(number, price);
        this.trailerVolume = (trailerVolume > 0) ? trailerVolume : 150;
    }

    public int getTrailerVolume() {
        return trailerVolume;
    }

    @Override
    public void updateLicense(int licenseNumber) {
        this.professionalLicense = licenseNumber;
    }

    @Override
    public void sell() {
        super.sell();
        System.out.print("professional license number? ");
        updateLicense(VehicleManager.scanner.nextInt());
    }

    @Override
    public String getTextualRepresentation() {
        return "number: " + getNumber() + " - price: " + getPrice() + " - trailer volume: " + trailerVolume
                + " - prof. license: " + professionalLicense + " - enrolment date: " + enrolmentDate;
    }
}

// CHANGE: Bus is non-sealed (reopened for future extension), unlike the other three types
non-sealed class Bus extends Vehicle implements ProfessionalVehicle {
    private int numPassengers;
    private int professionalLicense;

    public Bus(int number, int price, int numPassengers) {
        super(number, price);
        this.numPassengers = numPassengers;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    @Override
    public void updateLicense(int licenseNumber) {
        this.professionalLicense = licenseNumber;
    }

    @Override
    public void sell() {
        super.sell();
        System.out.print("professional license number? ");
        updateLicense(VehicleManager.scanner.nextInt());
    }

    @Override
    public String getTextualRepresentation() {
        return "number: " + getNumber() + " - price: " + getPrice() + " - passengers: " + numPassengers
                + " - prof. license: " + professionalLicense + " - enrolment date: " + enrolmentDate;
    }
}

public class VehicleManager {
    static Scanner scanner = new Scanner(System.in);
    private static Vehicle[] vehicles = new Vehicle[100];
    private static int numVehicles = 0;

    public static void main(String[] args) {
        addInitialVehicles();

        System.out.println("Available commands:");
        System.out.println("list - to show the available vehicles");
        System.out.println("add - to add a new vehicle");
        System.out.println("sell - to sell a vehicle");
        System.out.println("license - to update a vehicle's professional license");
        System.out.println("category - to show a vehicle's registration category");
        System.out.println("end - to close the application");

        while (true) {
            System.out.print("next command? ");
            String operation = scanner.nextLine();
            if (operation.equals("list")) {
                for (Vehicle vehicle : vehicles) {
                    if (vehicle != null) {
                        System.out.println(vehicle.getTextualRepresentation());
                    }
                }
            } else if (operation.equals("add")) {
                System.out.print("type (bike, car, truck, bus)? ");
                String type = scanner.nextLine();
                if (type.equals("bike")) {
                    addBike();
                } else if (type.equals("car")) {
                    addCar();
                } else if (type.equals("truck")) {
                    addTruck();
                } else if (type.equals("bus")) {
                    addBus();
                }
            } else if (operation.equals("sell")) {
                System.out.print("Vehicle number? ");
                int number = scanner.nextInt();
                vehicles[number].sell();
            } else if (operation.equals("license")) {
                System.out.print("Vehicle number? ");
                int number = scanner.nextInt();
                Vehicle vehicle = vehicles[number];
                if (vehicle instanceof ProfessionalVehicle pv) {
                    System.out.print("professional license number? ");
                    pv.updateLicense(scanner.nextInt());
                }
            } else if (operation.equals("category")) {
                System.out.print("Vehicle number? ");
                int number = scanner.nextInt();
                System.out.println(registrationCategory(vehicles[number]));
            } else if (operation.equals("end")) {
                break;
            }
        }
        scanner.close();
    }

    // Exhaustive: the compiler rejects this switch if a permitted subtype is left uncovered,
    // and rejects adding a Vehicle subtype outside the permits list in the first place.
    private static String registrationCategory(Vehicle vehicle) {
        return switch (vehicle) {
            case Bike b -> "no plate required";
            case Car c -> "standard plate";
            case Truck t -> "commercial plate";
            case Bus b -> "public transport plate";
        };
    }

    private static void addInitialVehicles() {
        vehicles[numVehicles++] = new Bike(0, 8_000);
        vehicles[numVehicles++] = new Car(1, 75_000, 2);
        vehicles[numVehicles++] = new Truck(2, 183_700, 86);
        vehicles[numVehicles++] = new Bus(3, 245_000, 50);
    }

    private static void addBike() {
        System.out.print("price? ");
        int price = scanner.nextInt();
        vehicles[numVehicles] = new Bike(numVehicles, price);
        numVehicles++;
    }

    private static void addCar() {
        System.out.print("price? ");
        int price = scanner.nextInt();
        System.out.print("Number of doors? ");
        int numDoors = scanner.nextInt();
        vehicles[numVehicles] = new Car(numVehicles, price, numDoors);
        numVehicles++;
    }

    private static void addTruck() {
        System.out.print("price? ");
        int price = scanner.nextInt();
        System.out.print("Volume of trailer? ");
        int trailerVolume = scanner.nextInt();
        vehicles[numVehicles] = new Truck(numVehicles, price, trailerVolume);
        numVehicles++;
    }

    private static void addBus() {
        System.out.print("price? ");
        int price = scanner.nextInt();
        System.out.print("Number of passengers? ");
        int numPassengers = scanner.nextInt();
        vehicles[numVehicles] = new Bus(numVehicles, price, numPassengers);
        numVehicles++;
    }
}
