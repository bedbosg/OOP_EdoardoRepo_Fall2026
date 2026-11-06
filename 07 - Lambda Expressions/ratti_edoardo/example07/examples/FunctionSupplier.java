package example07.examples;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionSupplier {
    public static void main(String[] args) {
        Function<String, Integer> length = String::length;
        Function<Integer, String> stars =
                n -> "*".repeat(n);

        System.out.println(length.apply("lambda")); // 6

        // andThen: length first, then stars
        Function<String, String> bar = length.andThen(stars);
        System.out.println(bar.apply("lambda"));    // ******

        // compose: the other order
        Function<Integer, Integer> f = length.compose(stars);
        System.out.println(f.apply(4));             // 4

        Supplier<LocalDate> today = LocalDate::now;
        System.out.println(today.get());              // 2026-08-19
    }
}
