package example08.examples;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CollectorsExample {
    public static void main(String[] args) {
        List<String> names = List.of("Ada", "Bob", "Cy", "Bob");

        // containers
        names.stream().collect(Collectors.toList());                      // List
        names.stream().collect(Collectors.toSet());                       // Set, duplicates gone
        names.stream().collect(Collectors.toCollection(TreeSet::new));    // a chosen implementation


        // joining: three forms
        names.stream().collect(Collectors.joining());
        //  AdaBobCyBob
        names.stream().collect(Collectors.joining(", "));
        //  Ada, Bob, Cy, Bob
        names.stream().collect(Collectors.joining(", ", "[", "]"));
        //  [Ada, Bob, Cy, Bob]

        // for a plain List, prefer:
        names.stream().toList();
    }
}