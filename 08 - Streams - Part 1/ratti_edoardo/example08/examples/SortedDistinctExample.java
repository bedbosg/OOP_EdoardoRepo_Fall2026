package example08.examples;

import java.util.Comparator;
import java.util.List;

public class SortedDistinctExample {
    public static void main(String[] args) {
        List<String> names = List.of("Ada", "Bob", "Ada", "Cy");

        names.stream().sorted().toList();
        //  [Ada, Ada, Bob, Cy]      natural order

        names.stream().distinct().toList();
        //  [Ada, Bob, Cy]

        names.stream()
                .sorted(Comparator.comparing(String::length))
                .toList();

        // never returns on an infinite stream:
        // Stream.iterate(1, i -> i + 1).sorted()
    }
}
