package assignment04.hierarchies.vehicles;

public class F1Car extends RacingCar {

    private final int kersCapacity;

    public F1Car(String brand, int carNumber, String pilotName, int kersCapacity) {
        super(brand, carNumber, pilotName);
        this.kersCapacity = kersCapacity;
    }

    public int getKersCapacity() {
        return kersCapacity;
    }

    @Override
    public String toString() {
        return "F1Car{" +
                "kersCapacity=" + kersCapacity +
                "} " + super.toString();
    }
}
