package assignment04.hierarchies.vehicles;

public class Bicycle extends Bike {

    private final BicycleType type;

    public Bicycle(String brand, BicycleType type) {
        super(brand);
        this.type = type;
    }

    public BicycleType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "type=" + type +
                "} " + super.toString();
    }
}
