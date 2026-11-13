package example08.examples;

import java.util.List;
import java.util.Optional;

public class ReduceExample {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4);

        // 1 - accumulator only -> Optional<T>
        Optional<Integer> sum1 = nums.stream()
                .reduce(Integer::sum);
        // empty if the stream is empty

        // 2 - identity + accumulator -> T
        int sum2 = nums.stream()
                .reduce(0, Integer::sum);
        // 0 if the stream is empty
    }
}
