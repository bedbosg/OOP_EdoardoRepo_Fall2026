package example08.examples;

import java.util.List;

public class TakeWhileDropWhileExample {
    public static void main(String[] args) {
        List<Integer> nums = List.of(2, 4, 6, 7, 8, 10);

        // stops at the first failure
        nums.stream().takeWhile(n -> n % 2 == 0).toList();
        //  [2, 4, 6]      not [2, 4, 6, 8, 10]

        // discards until the first failure, keeps the rest
        nums.stream().dropWhile(n -> n % 2 == 0).toList();
        //  [7, 8, 10]


        // compare with filter, which tests every element
        nums.stream().filter(n -> n % 2 == 0).toList();
        //  [2, 4, 6, 8, 10]
    }
}
