package assignment04.hierarchies.vehicles;


public abstract class RacingCar extends Car {

    private final int carNumber;

    private final String pilotName;

    public RacingCar(String brand, int carNumber, String pilotName) {
        super(brand);
        this.carNumber = carNumber;
        this.pilotName = pilotName;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public String getPilotName() {
        return pilotName;
    }

    @Override
    public String toString() {
        return "RacingCar{" +
                "carNumber=" + carNumber +
                ", pilotName='" + pilotName + '\'' +
                "} " + super.toString();
    }
}
