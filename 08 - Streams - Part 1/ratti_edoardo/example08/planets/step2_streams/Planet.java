package example08.planets.step2_streams;

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

        long count = planets.stream()
                .filter(Planet::isGasGiant)
                .count();

        // Reading it aloud: take the planets, keep the ones that are gas giants, tell me how many.
        // Planet::isGasGiant is a method reference. The equivalent lambda would be: p -> p.isGasGiant()
        System.out.println("Gas giants: " + count);
    }
}