package example08.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FiniteStreams {
    public static void main(String[] args) {
        // 1 - from any Collection
        List<String> names = List.of("Mercury", "Venus", "Earth");
        Stream<String> s1 = names.stream();

        // 2 - from an array
        String[] values = {"one", "two", "three"};
        Stream<String> s2 = Arrays.stream(values);
        Stream<String> s3 = Stream.of(values);

        // 3 - from individual values
        Stream<String> s4 = Stream.of("one", "two", "three");

        // 4 - built up element by element
        Stream.Builder<String> b = Stream.builder();
        b.accept("one");
        b.accept("two");
        Stream<String> s5 = b.build();

        // 5 - the empty stream, useful as a neutral value
        Stream<String> s6 = Stream.empty();

    }
}
