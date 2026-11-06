package deepening07.examples;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class UnaryOperatorAndBinaryOperatorExample {
    public static void main(String[] args) {
        Function<Integer, Integer> f1 = x -> x * 2;
        System.out.println(f1.apply(2));

        UnaryOperator<Integer> f2 = x -> x * 2;
        System.out.println(f2.apply(2));

        BiFunction<Integer, Integer, Integer> f3 = (x1, x2) -> x1 + x2;
        System.out.println(f3.apply(2, 3));

        BinaryOperator<Integer> f4 = (x1, x2) -> x1 + x2;
        System.out.println(f4.apply(2, 3));

    }
}
