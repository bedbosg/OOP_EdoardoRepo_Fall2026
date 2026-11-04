package vehiclemanager.step2_enum;

import java.time.LocalDateTime;
import java.util.Scanner;

enum VehicleType {BIKE, CAR, TRUCK, BUS}

class Vehicle {
    private int number;
    private VehicleType type;
    private int price;
    private int numDoors; // for cars
    private int trailerVolume; // for trucks
    private int numPassengers; // for buses
    private int professionalLicense; // for trucks and buses
    private LocalDateTime enrolmentDate;

    public Vehicle(int number, VehicleType type, int price) {
        this.number = number;
        this.type = type;
        this.price = (price > 0) ? price : 100;
    }

    public int getNumber() {
        return number;
    }

    public VehicleType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public int getTrailerVolume() {
        return trailerVolume;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public int getProfessionalLicense() {
        return professionalLicense;
    }

    public LocalDateTime getEnrolmentDate() {
        return enrolmentDate;
    }

    public void setNumDoors(int numDoors) {
        if (numDoors > 0) {
            this.numDoors = numDoors;
        } else {
            this.numDoors = 5;
        }
    }

    public void setTrailerVolume(int trailerVolume) {
        if (trailerVolume > 0) {
            this.trailerVolume = trailerVolume;
        } else {
            this.trailerVolume = 150;
        }
    }

    public void setNumPassengers(int numPassengers) {
        if (numPassengers > 0) {
            this.numPassengers = numPassengers;
        }
    }

    public void setProfessionalLicense(int professionalLicense) {
        if (professionalLicense > 0) {
            this.professionalLicense = professionalLicense;
        }
    }

    public void setEnrolmentDate(LocalDateTime enrolmentDate) {
        this.enrolmentDate = enrolmentDate;
    }
}

public class VehicleManager {
    static Scanner scanner = new Scanner(System.in);
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
                        System.out.print("number: " + vehicle.getNumber() + " - ");
                        System.out.print("type: " + vehicle.getType() + " - ");
                        System.out.print("price: " + vehicle.getPrice() + " - ");
                        System.out.print("doors: " + vehicle.getNumDoors() + " - ");
                        System.out.print("trailer volume: " + vehicle.getTrailerVolume() + " - ");
                        System.out.print("passengers: " + vehicle.getNumPassengers() + " - ");
                        System.out.print("prof. license: " + vehicle.getProfessionalLicense() + " - ");
                        System.out.println("enrolment date: " + vehicle.getEnrolmentDate());
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
                final Vehicle current = vehicles[scanner.nextInt()];
                switch (current.getType()) {
                    case BIKE -> sellBike(current);
                    case CAR -> sellCar(current);
                    case TRUCK -> sellTruck(current);
                    case BUS -> sellBus(current);
                }
            } else if (operation.equals("license")) {
                System.out.print("Vehicle number? ");
                int number = scanner.nextInt();
                System.out.print("professional license number? ");
                int professionalLicense = scanner.nextInt();
                vehicles[number].setProfessionalLicense(professionalLicense);
            } else if (operation.equals("end")) {
                break;
            }
        }
        scanner.close();
    }

    private static void addInitialVehicles() {
        vehicles[numVehicles] = new Vehicle(numVehicles, VehicleType.BIKE, 8_000);
        numVehicles++;

        vehicles[numVehicles] = new Vehicle(numVehicles, VehicleType.CAR, 75_000);
        vehicles[numVehicles].setNumDoors(2);
        numVehicles++;

        vehicles[numVehicles] = new Vehicle(numVehicles, VehicleType.TRUCK, 183_700);
        vehicles[numVehicles].setTrailerVolume(86);
        numVehicles++;

        vehicles[numVehicles] = new Vehicle(numVehicles, VehicleType.BUS, 245_000);
        vehicles[numVehicles].setNumPassengers(50);
        numVehicles++;
    }

    private static void addBike() {
        System.out.print("price? ");
        int price = scanner.nextInt();
        vehicles[numVehicles] = new Vehicle(numVehicles, VehicleType.BIKE, price);
        numVehicles++;
    }

    private static void addCar() {
        System.out.print("price? ");
        int price = scanner.nextInt();
        vehicles[numVehicles] = new Vehicle(numVehicles, VehicleType.CAR, price);
        System.out.print("Number of doors? ");
        int numDoors = scanner.nextInt();
        vehicles[numVehicles].setNumDoors(numDoors);
        numVehicles++;
    }

    private static void addTruck() {
        System.out.print("price? ");
        int price = scanner.nextInt();
        vehicles[numVehicles] = new Vehicle(numVehicles, VehicleType.TRUCK, price);
        System.out.print("Volume of trailer? ");
        int trailerVolume = scanner.nextInt();
        vehicles[numVehicles].setTrailerVolume(trailerVolume);
        numVehicles++;
    }

    private static void addBus() {
        System.out.print("price? ");
        int price = scanner.nextInt();
        vehicles[numVehicles] = new Vehicle(numVehicles, VehicleType.BUS, price);
        System.out.print("Number of passengers? ");
        int numPassengers = scanner.nextInt();
        vehicles[numVehicles].setNumPassengers(numPassengers);
        numVehicles++;
    }

    private static void sellBike(Vehicle vehicle) {
        vehicle.setEnrolmentDate(LocalDateTime.now());
    }

    private static void sellCar(Vehicle vehicle) {
        vehicle.setEnrolmentDate(LocalDateTime.now());
    }

    private static void sellTruck(Vehicle vehicle) {
        vehicle.setEnrolmentDate(LocalDateTime.now());
        System.out.print("professional license number? ");
        int professionalLicense = scanner.nextInt();
        vehicle.setProfessionalLicense(professionalLicense);
    }

    private static void sellBus(Vehicle vehicle) {
        vehicle.setEnrolmentDate(LocalDateTime.now());
        System.out.print("professional license number? ");
        int professionalLicense = scanner.nextInt();
        vehicle.setProfessionalLicense(professionalLicense);
    }
}
