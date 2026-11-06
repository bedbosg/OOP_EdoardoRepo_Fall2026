package deepening07.examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionAndBiFunctionExample {
    public static void main(String[] args) {
        Function<String, Integer> func = x -> x.length();
        System.out.println(func.apply("Example"));

        // takes two Integers and returns an Integer
        BiFunction<Integer, Integer, Integer> func1 = (x1, x2) -> x1 + x2;
        System.out.println(func1.apply(2, 3));

        // takes two Integers and returns an Double
        BiFunction<Integer, Integer, Double> func2 = (x1, x2) -> Math.pow(x1, x2);
        System.out.println(func2.apply(2, 4));

        // takes two Integers and returns a List<Integer>
        BiFunction<Integer, Integer, List<Integer>> func3 = (x1, x2) -> Arrays.asList(x1 + x2);
        System.out.println(func3.apply(2, 3));
    }
}
