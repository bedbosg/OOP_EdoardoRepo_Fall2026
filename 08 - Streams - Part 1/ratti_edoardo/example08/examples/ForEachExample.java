package example08.examples;

import java.util.List;
import java.util.stream.Stream;

public class ForEachExample {
    public static void main(String[] args) {
        List<String> names = List.of("John", "Jane", "Jack", "Jas");

        Stream<String> nameStream = names.stream();
        long count1 = nameStream.count();

        List<String> list1 = nameStream.toList();  // IllegalStateException
        // stream has already been closed

        // Correct: build a new stream from the source
        long count2 = names.stream().count();
        List<String> list2 = names.stream().toList();
    }
}
