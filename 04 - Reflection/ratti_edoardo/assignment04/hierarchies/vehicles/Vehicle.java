package assignment04.hierarchies.vehicles;

public abstract class Vehicle {

    private final String brand;

    private final int numWheels;

    public Vehicle(String brand, int numWheels) {
        this.brand = brand;
        this.numWheels = numWheels;
    }

    public int getNumWheels() {
        return numWheels;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", numWheels=" + numWheels +
                '}';
    }
}
