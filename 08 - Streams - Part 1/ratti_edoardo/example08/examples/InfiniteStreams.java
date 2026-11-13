package example08.examples;

import java.util.stream.Stream;

public class InfiniteStreams {
    public static void main(String[] args) {
        // iterate(): each value from the previous one
        Stream<Integer> tens = Stream.iterate(0, n -> n + 10);

        tens.limit(5).forEach(System.out::println);
        //  0  10  20  30  40

        // generate(): each value independent of the last
        Stream<Double> randoms = Stream.generate(Math::random);


        // three-argument iterate() is bounded
        Stream.iterate(1, i -> i <= 100, i -> i * 2)
                .forEach(System.out::println);
        //  1 2 4 8 16 32 64
    }
}
