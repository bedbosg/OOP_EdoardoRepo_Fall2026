package assignment04.hierarchies.vehicles;

public abstract class Bike extends Vehicle {

    public Bike(String brand) {
        super(brand, 2);
    }

    @Override
    public String toString() {
        return "Bike{} " + super.toString();
    }
}
