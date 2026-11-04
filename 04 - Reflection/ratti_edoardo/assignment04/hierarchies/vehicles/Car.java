package assignment04.hierarchies.vehicles;

public abstract class Car extends Vehicle {

    public Car(String brand) {
        super(brand, 4);
    }

    @Override
    public String toString() {
        return "Car{} " + super.toString();
    }
}
