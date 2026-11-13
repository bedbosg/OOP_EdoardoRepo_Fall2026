package example08.examples;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DownstreamExample {
    public static void main(String[] args) {
        List<Planet> planets = SolarSystem.getPlanets();

        // how many planets per atmosphere type
        Map<AtmosphereType, Long> counts =
                planets.stream().collect(
                        Collectors.groupingBy(
                                Planet::getAtmosphereType,
                                Collectors.counting()));
        System.out.println(counts);   // {ROCKY=4, GAS=2, ICE=2}
        // total mass per type
        Map<AtmosphereType, Double> mass =
                planets.stream().collect(
                        Collectors.groupingBy(
                                Planet::getAtmosphereType,
                                Collectors.summingDouble(Planet::getMass)));
        System.out.println(mass);
        // {ROCKY=11.812, GAS=2466.0, ICE=188.8}
        // just the names, not the whole objects
        Map<AtmosphereType, List<String>> names =
                planets.stream().collect(
                        Collectors.groupingBy(
                                Planet::getAtmosphereType,
                                Collectors.mapping(Planet::getName,
                                        Collectors.toList())));
        System.out.println(names);
        // {ROCKY=[Mercury, ...], GAS=[Jupiter, Saturn], ...}
    }

    private enum AtmosphereType {ROCKY, GAS, ICE}

    private static class Planet {
        private final String name;
        private final AtmosphereType atmosphereType;
        private final double mass;          // in 10^24 kg
        private final boolean rings;

        Planet(String name, AtmosphereType atmosphereType,
               double mass, boolean rings) {
            this.name = name;
            this.atmosphereType = atmosphereType;
            this.mass = mass;
            this.rings = rings;
        }

        String getName() {
            return name;
        }

        AtmosphereType getAtmosphereType() {
            return atmosphereType;
        }

        double getMass() {
            return mass;
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
                    new Planet("Mercury", AtmosphereType.ROCKY, 0.330, false),
                    new Planet("Venus", AtmosphereType.ROCKY, 4.87, false),
                    new Planet("Earth", AtmosphereType.ROCKY, 5.97, false),
                    new Planet("Mars", AtmosphereType.ROCKY, 0.642, false),
                    new Planet("Jupiter", AtmosphereType.GAS, 1898.0, true),
                    new Planet("Saturn", AtmosphereType.GAS, 568.0, true),
                    new Planet("Uranus", AtmosphereType.ICE, 86.8, true),
                    new Planet("Neptune", AtmosphereType.ICE, 102.0, true));
        }
    }
}