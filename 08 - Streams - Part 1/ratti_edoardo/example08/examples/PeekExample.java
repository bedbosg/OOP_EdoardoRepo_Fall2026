package example08.examples;

import java.util.List;

public class PeekExample {
    public static void main(String[] args) {
        List<String> names = List.of("Ada", "Bob", "Catherine", "Dirk");

        // intended use: seeing inside a pipeline
        List<String> out = names.stream()
                .filter(s -> s.length() > 4)
                .peek(System.out::println)
                .map(String::toUpperCase)
                .toList();

        // misuse: doing the work inside peek
        // employees.stream()
        //     .peek(e -> e.salaryIncrement(10.0))
        //     .count();

        // count() may not need the elements at all,
        // so the increment may never happen
    }
}
