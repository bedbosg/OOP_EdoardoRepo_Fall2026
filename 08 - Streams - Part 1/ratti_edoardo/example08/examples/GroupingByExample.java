package example08.examples;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByExample {
    public static void main(String[] args) {
        List<Planet> planets = SolarSystem.getPlanets();

        // classifier -> Map<K, List<T>>
        Map<AtmosphereType, List<Planet>> byAtmosphere =
                planets.stream()
                        .collect(Collectors.groupingBy(
                                Planet::getAtmosphereType));
        System.out.println(byAtmosphere);
        // {ICE=[Uranus, Neptune],
        //  ROCKY=[Mercury, Venus, Earth, Mars],
        //  GAS=[Jupiter, Saturn]}

        // the classifier can be any function
        Map<Boolean, List<Planet>> byRings =
                planets.stream()
                        .collect(Collectors.groupingBy(
                                Planet::hasRings));
        System.out.println(byRings);
        // {false=[Mercury, Venus, Earth, Mars],
        //  true=[Jupiter, Saturn, Uranus, Neptune]}
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