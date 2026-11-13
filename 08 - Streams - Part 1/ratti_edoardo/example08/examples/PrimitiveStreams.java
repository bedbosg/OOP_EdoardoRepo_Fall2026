package example08.examples;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStreams {
    public static void main(String[] args) {
        // Stream<Integer> boxes every element
        List<Integer> values = List.of(1, 2, 3, 4);

        // no sum() on Stream<T>:
        // values.stream().sum();   does not compile

        // IntStream works on int directly
        int total = values.stream()
                .mapToInt(Integer::intValue)
                .sum();

        OptionalDouble avg = IntStream.of(1, 2, 3)
                .average();

        // back to objects when needed
        Stream<Integer> boxed = IntStream.range(0, 5)
                .boxed();
    }
}
