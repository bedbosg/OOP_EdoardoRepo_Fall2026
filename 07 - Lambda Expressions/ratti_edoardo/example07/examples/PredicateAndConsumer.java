package example07.examples;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateAndConsumer {
    public static void main(String[] args) {
        Predicate<String> isEmpty = s -> s.isEmpty();
        Predicate<String> isLong = s -> s.length() > 10;

        System.out.println(isEmpty.test(""));       // true
        System.out.println(isLong.test("lambda"));  // false

        // combined with default methods
        Predicate<String> ok = isEmpty.negate()
                .and(isLong.negate());
        System.out.println(ok.test("lambda"));      // true

        Consumer<String> print = s -> System.out.println(s);
        Consumer<String> shout = s -> System.out.println(s.toUpperCase());

        print.andThen(shout).accept("done");  // done  DONE
    }
}
