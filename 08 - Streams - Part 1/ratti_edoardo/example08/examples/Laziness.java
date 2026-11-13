package example08.examples;

import java.util.List;

public class Laziness {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> result = numbers.stream()
                .filter(n -> {
                    System.out.println("filter " + n);
                    return n % 2 == 0;
                })
                .map(n -> {
                    System.out.println("  map " + n);
                    return n * n;
                })
                .limit(2)
                .toList();

        // Output:
        // filter 1
        // filter 2
        //   map 2
        // filter 3
        // filter 4
        //   map 4
    }
}
