package example08.examples;

import java.util.List;
import java.util.stream.Stream;

public class StreamReuse {
    public static void main(String[] args) {
        List<String> names = List.of("Ada", "Bob", "Cathy");

        Stream<String> s = names.stream();
        long count1 = s.count();
        List<String> l = s.toList(); // IllegalStateException
        // stream has already been closed


        // Correct: build a new stream from the source
        long count2 = names.stream().count();
        List<String> list2 = names.stream().toList();
    }
}
