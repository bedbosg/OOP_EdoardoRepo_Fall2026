package example08.examples;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CountMinMaxExample {
    record Planet(String name, double mass) {
    }  // 10^24 kg

    public static void main(String[] args) {
        List<Planet> planets = List.of(
                new Planet("Mercury", 0.330),
                new Planet("Earth", 5.97),
                new Planet("Jupiter", 1898.0));

        // count: a reduction to a long
        long n = planets.stream().count();
        System.out.println(n);   // 3

        // min and max return Optional<T>
        Optional<Planet> smallest = planets.stream()
                .min(Comparator.comparing(Planet::mass));

        // unwrapping is the caller's responsibility
        smallest.ifPresent(System.out::println);
        // Planet[name=Mercury, mass=0.33]

        // on an empty stream there is no minimum
        Optional<Planet> none = List.<Planet>of().stream()
                .min(Comparator.comparing(Planet::mass));
        System.out.println(none);   // Optional.empty
    }
}