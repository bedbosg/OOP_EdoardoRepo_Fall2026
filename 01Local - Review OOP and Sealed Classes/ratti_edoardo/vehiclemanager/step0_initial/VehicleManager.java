package vehiclemanager.step0_initial;

import java.time.LocalDateTime;
import java.util.Scanner;

class Vehicle {
    public int number;
    public int type; // 0: bike, 1: car, 2: truck, 3: bus
    public int price;
    public int numDoors; // for cars
    public int trailerVolume; // for trucks
    public int numPassengers; // for buses
    public int professionalLicense; // for trucks and buses
    public LocalDateTime enrolmentDate;
}

public class VehicleManager {
    public static Scanner scanner = new Scanner(System.in);
    public static Vehicle[] vehicles = new Vehicle[100];
    public static int numVehicles = 0;

    public static void main(String[] args) {
        addInitialVehicles();

        System.out.println("Available commands:");
        System.out.println("list - to show the available vehicles");
        System.out.println("add - to add a new vehicle");
        System.out.println("sell - to sell a vehicle");
        System.out.println("license - to update a vehicle's professional license");
        System.out.println("end - to close the application");

        while (true) {
            System.out.print("next command? ");
            String operation = scanner.nextLine();
            if (operation.equals("list")) {
                for (Vehicle vehicle : vehicles) {
                    if (vehicle != null) {
                        System.out.print("number: " + vehicle.number + " - ");
                        System.out.print("type: " + vehicle.type + " - ");
                        System.out.print("price: " + vehicle.price + " - ");
                        System.out.print("doors: " + vehicle.numDoors + " - ");
                        System.out.print("trailer volume: " + vehicle.trailerVolume + " - ");
                        System.out.print("passengers: " + vehicle.numPassengers + " - ");
                        System.out.print("prof. license: " + vehicle.professionalLicense + " - ");
                        System.out.println("enrolment date: " + vehicle.enrolmentDate);
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
                int type = vehicles[number].type;
                if (type == 0) {
                    sellBike(vehicles[number]);
                } else if (type == 1) {
                    sellCar(vehicles[number]);
                } else if (type == 2) {
                    sellTruck(vehicles[number]);
                } else if (type == 3) {
                    sellBus(vehicles[number]);
                }
            } else if (operation.equals("license")) {
                System.out.print("Vehicle number? ");
                int number = scanner.nextInt();
                System.out.print("professional license number? ");
                int professionalLicense = scanner.nextInt();
                vehicles[number].professionalLicense = professionalLicense;
            } else if (operation.equals("end")) {
                break;
            }
        }
        scanner.close();
    }

    public static void addInitialVehicles() {
        // Add Bike
        vehicles[numVehicles] = new Vehicle();
        vehicles[numVehicles].number = numVehicles;
        vehicles[numVehicles].type = 0;
        vehicles[numVehicles].price = 8_000;
        numVehicles++;

        // Add Car
        vehicles[numVehicles] = new Vehicle();
        vehicles[numVehicles].number = numVehicles;
        vehicles[numVehicles].type = 1;
        vehicles[numVehicles].price = 75_000;
        vehicles[numVehicles].numDoors = 2;
        numVehicles++;

        // Add Truck
        vehicles[numVehicles] = new Vehicle();
        vehicles[numVehicles].number = numVehicles;
        vehicles[numVehicles].type = 2;
        vehicles[numVehicles].price = 183_700;
        vehicles[numVehicles].trailerVolume = 86;
        numVehicles++;

        // Add Bus
        vehicles[numVehicles] = new Vehicle();
        vehicles[numVehicles].number = numVehicles;
        vehicles[numVehicles].type = 3;
        vehicles[numVehicles].price = 245_000;
        vehicles[numVehicles].numPassengers = 50;
        numVehicles++;
    }

    public static void addBike() {
        vehicles[numVehicles] = new Vehicle();
        vehicles[numVehicles].number = numVehicles;
        vehicles[numVehicles].type = 0;
        System.out.print("price? ");
        int price = scanner.nextInt();
        vehicles[numVehicles].price = price;
        numVehicles++;
    }

    public static void addCar() {
        vehicles[numVehicles] = new Vehicle();
        vehicles[numVehicles].number = numVehicles;
        vehicles[numVehicles].type = 1;
        System.out.print("price? ");
        int price = scanner.nextInt();
        vehicles[numVehicles].price = price;
        System.out.print("Number of doors? ");
        int numDoors = scanner.nextInt();
        vehicles[numVehicles].numDoors = numDoors;
        numVehicles++;
    }

    public static void addTruck() {
        vehicles[numVehicles] = new Vehicle();
        vehicles[numVehicles].number = numVehicles;
        vehicles[numVehicles].type = 2;
        System.out.print("price? ");
        int price = scanner.nextInt();
        vehicles[numVehicles].price = price;
        System.out.print("Volume of trailer? ");
        int trailerVolume = scanner.nextInt();
        vehicles[numVehicles].trailerVolume = trailerVolume;
        numVehicles++;
    }

    public static void addBus() {
        vehicles[numVehicles] = new Vehicle();
        vehicles[numVehicles].number = numVehicles;
        vehicles[numVehicles].type = 3;
        System.out.print("price? ");
        int price = scanner.nextInt();
        vehicles[numVehicles].price = price;
        System.out.print("Number of passengers? ");
        int numPassengers = scanner.nextInt();
        vehicles[numVehicles].numPassengers = numPassengers;
        numVehicles++;
    }

    public static void sellBike(Vehicle vehicle) {
        vehicle.enrolmentDate = LocalDateTime.now();
    }

    public static void sellCar(Vehicle vehicle) {
        vehicle.enrolmentDate = LocalDateTime.now();
    }

    public static void sellTruck(Vehicle vehicle) {
        vehicle.enrolmentDate = LocalDateTime.now();
        System.out.print("professional license number? ");
        int professionalLicense = scanner.nextInt();
        vehicle.professionalLicense = professionalLicense;
    }

    public static void sellBus(Vehicle vehicle) {
        vehicle.enrolmentDate = LocalDateTime.now();
        System.out.print("professional license number? ");
        int professionalLicense = scanner.nextInt();
        vehicle.professionalLicense = professionalLicense;
    }
}
