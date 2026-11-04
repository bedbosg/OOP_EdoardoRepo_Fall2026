package vehiclemanager.step3_inheritance;

import java.time.LocalDateTime;
import java.util.Scanner;

abstract class Vehicle {
    private int number;
    private int price;
    private LocalDateTime enrolmentDate;

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

    public LocalDateTime getEnrolmentDate() {
        return enrolmentDate;
    }

    public void setEnrolmentDate(LocalDateTime enrolmentDate) {
        this.enrolmentDate = enrolmentDate;
    }
}

class Bike extends Vehicle {
    public Bike(int number, int price) {
        super(number, price);
    }

    // CHANGE: sellBike() is now an instance method (was a static helper before Bus was added)
    public void sellBike() {
        setEnrolmentDate(LocalDateTime.now());
    }
}

class Car extends Vehicle {
    private int numDoors;

    public Car(int number, int price, int numDoors) {
        super(number, price);
        this.numDoors = (numDoors > 0) ? numDoors : 5;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public void sellCar() {
        setEnrolmentDate(LocalDateTime.now());
    }
}

class Truck extends Vehicle {
    private int trailerVolume;
    private int professionalLicense;

    public Truck(int number, int price, int trailerVolume) {
        super(number, price);
        this.trailerVolume = (trailerVolume > 0) ? trailerVolume : 150;
    }

    public int getTrailerVolume() {
        return trailerVolume;
    }

    public int getProfessionalLicense() {
        return professionalLicense;
    }

    public void setProfessionalLicense(int professionalLicense) {
        this.professionalLicense = professionalLicense;
    }

    public void sellTruck() {
        setEnrolmentDate(LocalDateTime.now());
        System.out.print("professional license number? ");
        professionalLicense = VehicleManager.scanner.nextInt();
    }
}

class Bus extends Vehicle {
    private int numPassengers;
    private int professionalLicense;

    public Bus(int number, int price, int numPassengers) {
        super(number, price);
        if (numPassengers > 0) {
            this.numPassengers = numPassengers;
        }
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public int getProfessionalLicense() {
        return professionalLicense;
    }

    public void setProfessionalLicense(int professionalLicense) {
        this.professionalLicense = professionalLicense;
    }

    public void sellBus() {
        setEnrolmentDate(LocalDateTime.now());
        System.out.print("professional license number? ");
        professionalLicense = VehicleManager.scanner.nextInt();
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
        System.out.println("end - to close the application");

        while (true) {
            System.out.print("next command? ");
            String operation = scanner.nextLine();
            if (operation.equals("list")) {
                for (Vehicle vehicle : vehicles) {
                    if (vehicle == null) {
                        continue;
                    }
                    System.out.print("number: " + vehicle.getNumber() + " - ");
                    System.out.print("price: " + vehicle.getPrice() + " - ");
                    // instanceof chain: printing type-specific fields needs to know the exact class
                    if (vehicle instanceof Car c) {
                        System.out.print("doors: " + c.getNumDoors() + " - ");
                    } else if (vehicle instanceof Truck t) {
                        System.out.print("trailer volume: " + t.getTrailerVolume() + " - ");
                        System.out.print("prof. license: " + t.getProfessionalLicense() + " - ");
                    } else if (vehicle instanceof Bus b) {
                        System.out.print("passengers: " + b.getNumPassengers() + " - ");
                        System.out.print("prof. license: " + b.getProfessionalLicense() + " - ");
                    }
                    System.out.println("enrolment date: " + vehicle.getEnrolmentDate());
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
                Vehicle current = vehicles[number];
                // instanceof chain: selling still needs to know the exact class
                if (current instanceof Bike b) {
                    b.sellBike();
                } else if (current instanceof Car c) {
                    c.sellCar();
                } else if (current instanceof Truck t) {
                    t.sellTruck();
                } else if (current instanceof Bus b) {
                    b.sellBus();
                }
            } else if (operation.equals("license")) {
                // CHANGE: new command, added alongside Bus so a license can be set/updated
                // without going through a full sale
                System.out.print("Vehicle number? ");
                int number = scanner.nextInt();
                Vehicle current = vehicles[number];
                System.out.print("professional license number? ");
                int professionalLicense = scanner.nextInt();
                if (current instanceof Truck t) {
                    t.setProfessionalLicense(professionalLicense);
                } else if (current instanceof Bus b) {
                    b.setProfessionalLicense(professionalLicense);
                }
            } else if (operation.equals("end")) {
                break;
            }
        }
        scanner.close();
    }

    private static void addInitialVehicles() {
        vehicles[numVehicles] = new Bike(numVehicles, 8_000);
        numVehicles++;

        vehicles[numVehicles] = new Car(numVehicles, 75_000, 2);
        numVehicles++;

        vehicles[numVehicles] = new Truck(numVehicles, 183_700, 86);
        numVehicles++;

        vehicles[numVehicles] = new Bus(numVehicles, 245_000, 50);
        numVehicles++;
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
