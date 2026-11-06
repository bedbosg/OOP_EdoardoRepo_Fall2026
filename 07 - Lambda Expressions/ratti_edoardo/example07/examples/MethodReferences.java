package example07.examples;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferences {
    public static void main(String[] args) {
        // 1 - static method
        Function<String, Integer> parse1 = s -> Integer.parseInt(s);
        Function<String, Integer> parse2 = Integer::parseInt;
        System.out.println(parse1.apply("42"));   // 42
        System.out.println(parse2.apply("42"));   // 42

        // 2 - instance method of a particular object
        StringBuilder builder = new StringBuilder("lambda");
        Supplier<String> text1 = () -> builder.toString();
        Supplier<String> text2 = builder::toString;
        System.out.println(text1.get());        // lambda
        System.out.println(text2.get());        // lambda

        // 3 - instance method of an arbitrary object of a type
        BiFunction<String, Integer, String> sub1 = (s, i) -> s.substring(i);
        BiFunction<String, Integer, String> sub2 = String::substring;
        System.out.println(sub1.apply("expression", 6));  // sion
        System.out.println(sub2.apply("expression", 6));  // sion

        // 4 - constructor
        Supplier<ArrayList<String>> maker1 = () -> new ArrayList();
        Supplier<ArrayList<String>> maker2 = ArrayList::new;
        System.out.println(maker1.get());       // []
        System.out.println(maker2.get());       // []

    }
}
