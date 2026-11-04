package assignment04.hierarchies.vehicles;

public class Motorbike extends Bike {

    private final MotorbikeType type;

    public Motorbike(String brand, MotorbikeType type) {
        super(brand);
        this.type = type;
    }

    public MotorbikeType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Motorbike{" +
                "type=" + type +
                "} " + super.toString();
    }
}
