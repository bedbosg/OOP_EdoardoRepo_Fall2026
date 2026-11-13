package example08.examples;
/*
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class GatherersExample {
    public static void main(String[] args) {
        // fixed windows
        List<List<Integer>> fixedWindows = Stream.of(1, 2, 3, 4, 5, 6, 7)
                .gather(Gatherers.windowFixed(3))
                .toList();
        System.out.println(fixedWindows);
        //  [[1, 2, 3], [4, 5, 6], [7]]

        // sliding windows
        List<List<Integer>> slidingWindows = Stream.of(1, 2, 3, 4)
                .gather(Gatherers.windowSliding(2))
                .toList();
        System.out.println(slidingWindows);
        //  [[1, 2], [2, 3], [3, 4]]

        // running totals
        List<Integer> runningTotals = Stream.of(1, 2, 3, 4)
                .gather(Gatherers.scan(() -> 0, Integer::sum))
                .toList();
        System.out.println(runningTotals);
        //  [1, 3, 6, 10]
    }
}
*/