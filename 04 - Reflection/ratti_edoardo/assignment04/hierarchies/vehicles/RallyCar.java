package assignment04.hierarchies.vehicles;

public class RallyCar extends RacingCar {

    private final String copilotName;

    public RallyCar(String brand, int carNumber, String pilotName, String copilotName) {
        super(brand, carNumber, pilotName);
        this.copilotName = copilotName;
    }

    public String getCopilotName() {
        return copilotName;
    }

    @Override
    public String toString() {
        return "RallyCar{" +
                "copilotName='" + copilotName + '\'' +
                "} " + super.toString();
    }
}
