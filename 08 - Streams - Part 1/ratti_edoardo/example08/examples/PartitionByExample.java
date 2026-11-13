package example08.examples;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionByExample {
    public static void main(String[] args) {
        List<Planet> planets = SolarSystem.getPlanets();

        // a predicate, not a classifier
        Map<Boolean, List<Planet>> split =
                planets.stream().collect(
                        Collectors.partitioningBy(Planet::hasRings));
        System.out.println(split);

        List<Planet> withRings = split.get(true);
        List<Planet> withoutRings = split.get(false);
        System.out.println("With rings   : " + withRings);
        System.out.println("Without rings: " + withoutRings);

        // downstream collectors work here too
        Map<Boolean, Long> howMany =
                planets.stream().collect(
                        Collectors.partitioningBy(
                                Planet::hasRings,
                                Collectors.counting()));
        System.out.println(howMany);
        Long withRingsCount = howMany.get(true);
        Long withoutRingsCount = howMany.get(false);
        System.out.println("With rings   : " + withRingsCount);
        System.out.println("Without rings: " + withoutRingsCount);
    }

    private enum AtmosphereType {ROCKY, GAS, ICE}

    private static class Planet {
        private final String name;
        private final AtmosphereType atmosphereType;
        private final boolean rings;

        Planet(String name, AtmosphereType atmosphereType, boolean rings) {
            this.name = name;
            this.atmosphereType = atmosphereType;
            this.rings = rings;
        }

        AtmosphereType getAtmosphereType() {
            return atmosphereType;
        }

        boolean hasRings() {
            return rings;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class SolarSystem {
        static List<Planet> getPlanets() {
            return List.of(
                    new Planet("Mercury", AtmosphereType.ROCKY, false),
                    new Planet("Venus", AtmosphereType.ROCKY, false),
                    new Planet("Earth", AtmosphereType.ROCKY, false),
                    new Planet("Mars", AtmosphereType.ROCKY, false),
                    new Planet("Jupiter", AtmosphereType.GAS, true),
                    new Planet("Saturn", AtmosphereType.GAS, true),
                    new Planet("Uranus", AtmosphereType.ICE, true),
                    new Planet("Neptune", AtmosphereType.ICE, true));
        }
    }
}