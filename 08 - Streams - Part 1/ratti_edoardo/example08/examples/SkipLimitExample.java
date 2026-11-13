package example08.examples;

import java.util.List;
import java.util.stream.Stream;

public class SkipLimitExample {
    public static void main(String[] args) {
        Stream<Integer> naturals = Stream
                .iterate(1, i -> i + 1);

        // works: limit bounds the stream
        List<Integer> five = naturals
                .skip(3)
                .limit(5)
                .toList();
        //  [4, 5, 6, 7, 8]

        // naturals.skip(3).toList();
        // never returns: skip does not bound anything
    }
}
