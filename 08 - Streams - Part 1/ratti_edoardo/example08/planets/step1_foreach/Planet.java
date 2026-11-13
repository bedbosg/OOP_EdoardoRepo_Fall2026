package example08.planets.step1_foreach;

import java.util.List;

public class Planet {
    private final String name;
    private final boolean gasGiant;

    Planet(String name, boolean gasGiant) {
        this.name = name;
        this.gasGiant = gasGiant;
    }

    String getName() {
        return name;
    }

    boolean isGasGiant() {
        return gasGiant;
    }

    public static void main(String[] args) {
        List<Planet> planets = List.of(
                new Planet("Mercury", false), new Planet("Venus", false),
                new Planet("Earth", false), new Planet("Mars", false),
                new Planet("Jupiter", true), new Planet("Saturn", true),
                new Planet("Uranus", false), new Planet("Neptune", false));

        // with a for-each loop
        long count = 0;
        for (Planet planet : planets) {
            if (planet.isGasGiant()) {
                count++;
            }
        }

        // with an explicit iterator
        /*
        long count = 0;
        Iterator<Planet> it = planets.iterator();
        while (it.hasNext()) {
            Planet planet = it.next();
            if (planet.isGasGiant()) {
                count++;
            }
        }
         */

        // the loop is ours to write, and ours to get right
        System.out.println("Gas giants: " + count);
    }
}