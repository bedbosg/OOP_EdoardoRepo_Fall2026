package example08.examples;

import java.util.List;
import java.util.stream.Stream;

public class FlatMapDemo {
    record Satellite(String name) {
    }

    record Planet(String name, List<Satellite> satellites) {
    }

    static List<Planet> PLANETS = List.of(
            new Planet("Mercury", List.of()),
            new Planet("Venus", List.of()),
            new Planet("Earth", List.of(new Satellite("Moon"))),
            new Planet("Mars", List.of(new Satellite("Phobos"), new Satellite("Deimos"))));

    public static void main(String[] args) {
        // With map: a stream of streams, one per planet
        Stream<Stream<Satellite>> streams = PLANETS.stream()
                .map(planet -> planet.satellites().stream());
        System.out.println(streams.count());   // 4

        // With flatMap: one flat stream of all satellites
        List<String> names = PLANETS.stream()
                .flatMap(planet -> planet.satellites().stream())
                .map(Satellite::name)
                .toList();
        System.out.println(names);   // [Moon, Phobos, Deimos]
    }
}