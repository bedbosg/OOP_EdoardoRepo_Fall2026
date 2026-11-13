package assignment08.exercise01;

import example08.examples.StreamReuse;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class NumberSequences {
    public static List<Integer> getFirstNMultiplesOfSeven(int n){
        return Stream.iterate(0, m -> m + 7).limit(n).toList();
    }
    public static List<Integer> getCountdown(int start, int step){
        return Stream.iterate(start, n -> n - step).limit(start / step + 1).toList();
    }
    public static int getDiceRoll(){
        return (int)(Math.random() * 6 + 1);
    }
    public static List<Integer> simulateDiceRolls(int n){
        return Stream.generate(NumberSequences::getDiceRoll).limit(n).toList();
    }
    public static List<Long> getOddNumberSquares(long max){
        return LongStream.rangeClosed(1, max).filter(n -> n%2 == 1)
                .map(n -> (long)Math.pow(n, 2))
                .boxed().toList();
    }
    public static Set<Integer> getUniqueLastDigits(Integer[] numbers){
        return Arrays.stream(numbers).map(n -> n % 10).collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        // 1. Test getFirstNMultiplesOfSeven
        System.out.println("=== 1. Test getFirstNMultiplesOfSeven ===");
        List<Integer> multiples = getFirstNMultiplesOfSeven(5);
        System.out.println("Primi 5 multipli di 7: " + multiples);
        // Atteso: [7, 14, 21, 28, 35]

        // 2. Test getCountdown
        System.out.println("\n=== 2. Test getCountdown ===");
        List<Integer> countdown = getCountdown(20, 3);
        System.out.println("Conto alla rovescia (start 20, step 3): " + countdown);
        // Atteso: [20, 17, 14, 11, 8, 5, 2]

        // 3. Test simulateDiceRolls
        System.out.println("\n=== 3. Test simulateDiceRolls ===");
        List<Integer> diceRolls = simulateDiceRolls(10);
        System.out.println("10 lanci di dado: " + diceRolls);
        // Atteso: Una lista di 10 numeri compresi tra 1 e 6

        // 4. Test getOddNumberSquares
        System.out.println("\n=== 4. Test getOddNumberSquares ===");
        List<Long> squares = getOddNumberSquares(10);
        System.out.println("Quadrati dei numeri dispari fino a 10: " + squares);
        // Atteso: [1, 9, 25, 49, 81] (quadrati di 1, 3, 5, 7, 9)

        // 5. Test getUniqueLastDigits
        System.out.println("\n=== 5. Test getUniqueLastDigits ===");
        Integer[] numbers = {12, 45, 67, 102, 95, 30, 7};
        Set<Integer> uniqueLastDigits = getUniqueLastDigits(numbers);
        System.out.println("Array originale: " + Arrays.toString(numbers));
        System.out.println("Ultime cifre univoche: " + uniqueLastDigits);

        // Atteso: [2, 5, 7, 0] (l'ordine potrebbe variare a seconda del Set)
    }
}
