package example08.examples;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SolarSystemExample {
    public static void main(String[] args) {
        SolarSystem solarSystem = new SolarSystem();

        {
            Set<AtmosphereType> types = solarSystem.getPlanets()
                    .stream()                                      // open
                    .filter(Planet::hasRings)                      // intermediate
                    .map(Planet::getAtmosphereType)                // intermediate
                    .collect(Collectors.toSet());                  // terminal
            System.out.println("Stream: " + types);
        }

        // The same query written as a loop:
        {
            Set<AtmosphereType> types = new HashSet<>();
            for (Planet p : solarSystem.getPlanets()) {
                if (p.hasRings()) {
                    types.add(p.getAtmosphereType());
                }
            }
            System.out.println("Loop:   " + types);
        }
    }

    private enum AtmosphereType {NONE, CARBON_DIOXIDE, NITROGEN_OXYGEN, HYDROGEN_HELIUM, HYDROGEN_HELIUM_METHANE}

    private static class Planet {
        private final String name;
        private final boolean gasGiant;
        private final boolean rings;
        private final AtmosphereType atmosphereType;

        Planet(String name, boolean gasGiant, boolean rings, AtmosphereType atmosphereType) {
            this.name = name;
            this.gasGiant = gasGiant;
            this.rings = rings;
            this.atmosphereType = atmosphereType;
        }

        String getName() {
            return name;
        }

        boolean isGasGiant() {
            return gasGiant;
        }

        boolean hasRings() {
            return rings;
        }

        AtmosphereType getAtmosphereType() {
            return atmosphereType;
        }
    }

    private static class SolarSystem {
        private final List<Planet> planets = List.of(
                new Planet("Mercury", false, false, AtmosphereType.NONE),
                new Planet("Venus", false, false, AtmosphereType.CARBON_DIOXIDE),
                new Planet("Earth", false, false, AtmosphereType.NITROGEN_OXYGEN),
                new Planet("Mars", false, false, AtmosphereType.CARBON_DIOXIDE),
                new Planet("Jupiter", true, true, AtmosphereType.HYDROGEN_HELIUM),
                new Planet("Saturn", true, true, AtmosphereType.HYDROGEN_HELIUM),
                new Planet("Uranus", false, true, AtmosphereType.HYDROGEN_HELIUM_METHANE),
                new Planet("Neptune", false, true, AtmosphereType.HYDROGEN_HELIUM_METHANE));

        List<Planet> getPlanets() {
            return planets;
        }
    }
}

