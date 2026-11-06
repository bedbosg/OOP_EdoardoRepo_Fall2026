package classroom07.smarthome;

import java.time.LocalTime;
import java.util.*;

enum SensorType {
    TEMPERATURE, // degrees Celsius
    CO2,         // parts per million (ppm)
    WATER        // 1 = water detected on the floor, 0 = dry
}

record SensorReading(String room, SensorType type, double value, LocalTime time) {
}

// Decides whether a sensor reading triggers an alert.
interface SensorRule {
    boolean isTriggered(SensorReading reading);
}

// Informs someone (or something) that a rule has been triggered.
interface AlertNotifier {
    void alert(String ruleInstanceLabel, SensorReading reading);
}

// Rules


// Notifiers

record Phone(String owner) {
    public void pushAlert(String sms) {
        System.out.printf("  [PHONE -> %s] %s %n", owner(), sms);
    }
}

// Forwards every alert to a phone.

// The hub of the smart home: keeps the registered rules and notifiers, and checks every incoming reading.
public class SmartHomeMonitor {
    private final Map<String, SensorRule> rules = new LinkedHashMap<>();
    private final List<AlertNotifier> notifiers = new ArrayList<>();
    private final Map<String, Integer> wetReadings = new HashMap<>();

    // Registers the notifiers and rules, then replays the readings of one night.
    private void simulate() {
        Phone annasPhone = new Phone("Anna");

        addNotifier((ruleInstanceLabel, reading) -> System.out.printf(
                "[ALERT] %s  %-12s %-12s %7.1f  -> %s%n", reading.time(), reading.room(), reading.type(), reading.value(), ruleInstanceLabel));
        addNotifier((ruleInstanceLabel, reading) -> annasPhone.pushAlert(
                "%s: %s in the %s".formatted(reading.time(), ruleInstanceLabel, reading.room())));

        addRule("Frost protection (garage)", new SensorRule() {
            @Override
            public boolean isTriggered(SensorReading reading) {
                return reading.type() == SensorType.TEMPERATURE
                        && reading.room().equals("garage")
                        && reading.value() < 3.0;
            }
        });
        addRule("Frost protection (cellar)", (reading)->{return reading.type() == SensorType.TEMPERATURE
                && reading.room().equals("cellar")
                && reading.value() < 5.0;});
        addRule("Water leak", (reading) -> {
            if (reading.type() != SensorType.WATER) {
                return false;
            }
            if (reading.value() == 0) {
                wetReadings.put(reading.room(), 0);   // dry again: start counting from scratch
                return false;
            }
            int count = wetReadings.getOrDefault(reading.room(), 0) + 1;
            wetReadings.put(reading.room(), count);
            return count == 2;            // alert once, when the leak is confirmed
        });

        Map<String, Double> roomLimits = Map.of("kids room", 1000.0, "bedroom", 1000.0, "office", 1500.0);
        double co2MaxLimit = 1200.;
        addRule("Poor air quality", (reading -> {
            if (reading.type() != SensorType.CO2) {
                return false;
            }
            double limit = roomLimits.getOrDefault(reading.room(), co2MaxLimit);
            return reading.value() > limit;
        }));

        List<SensorReading> lastNight = lastNightReadings();

        process(lastNight);
    }

    public static void main(String[] args) {
        SmartHomeMonitor monitor = new SmartHomeMonitor();
        monitor.simulate();
    }

    // Helper / Support methods
    private static List<SensorReading> lastNightReadings() {
        return List.of(
                new SensorReading("kids room", SensorType.CO2, 1250, LocalTime.of(21, 50)),
                new SensorReading("office", SensorType.CO2, 1300, LocalTime.of(22, 10)),
                new SensorReading("cellar", SensorType.TEMPERATURE, 6.1, LocalTime.of(22, 40)),
                new SensorReading("garage", SensorType.TEMPERATURE, 4.5, LocalTime.of(23, 10)),
                new SensorReading("living room", SensorType.CO2, 1150, LocalTime.of(0, 30)),
                new SensorReading("laundry", SensorType.WATER, 1, LocalTime.of(1, 20)),
                new SensorReading("laundry", SensorType.WATER, 0, LocalTime.of(1, 25)),
                new SensorReading("garage", SensorType.TEMPERATURE, 1.8, LocalTime.of(2, 40)),
                new SensorReading("cellar", SensorType.TEMPERATURE, 4.2, LocalTime.of(3, 30)),
                new SensorReading("laundry", SensorType.WATER, 1, LocalTime.of(4, 5)),
                new SensorReading("laundry", SensorType.WATER, 1, LocalTime.of(4, 10)),
                new SensorReading("laundry", SensorType.WATER, 1, LocalTime.of(4, 15)),
                new SensorReading("bedroom", SensorType.CO2, 1100, LocalTime.of(5, 30)),
                new SensorReading("kids room", SensorType.CO2, 820, LocalTime.of(6, 30)));
    }

    private void addRule(String name, SensorRule rule) {
        rules.put(name, rule);
    }

    private void addNotifier(AlertNotifier notifier) {
        notifiers.add(notifier);
    }

    private void process(List<SensorReading> readings) {
        for (SensorReading reading : readings) {
            for (Map.Entry<String, SensorRule> entry : rules.entrySet()) {
                if (entry.getValue().isTriggered(reading)) {
                    for (AlertNotifier notifier : notifiers) {
                        notifier.alert(entry.getKey(), reading);
                    }
                }
            }
        }
    }
}