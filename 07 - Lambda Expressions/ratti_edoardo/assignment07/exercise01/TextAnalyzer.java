package assignment07.exercise01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;



//the word that contains the highest number of vowels


public class TextAnalyzer {
    private static String find(List<String> words, BiPredicate<String, String> operation){
        String best = words.getFirst();
        for (String word : words) {
            if(operation.test(best, word)){
                best = word;
            }
        }
        return best;
    }
    private static List<String> findAll(List<String> words, Predicate<String> operation){
        List<String> filtered = new ArrayList<>();
        for (String word : words) {
            if(operation.test(word)){
                filtered.add(word);
            }
        }
        return filtered;
    }

    public static void main(String[] args) {
        String vowels = "AaEeIiOoUu";
        try {
            List<String> words = Arrays.asList(new String(Files.readAllBytes(Paths.get("07 - Lambda Expressions/ratti_edoardo/assignment07/exercise01/file.txt"))).split("\\W+"));
            String longest = find(words, (best,current) -> current.length() > best.length());
            String shortest = find(words, (best,current) -> current.length() < best.length() && current.length() >= 5);
            String vowelest = find(words, (best,current) -> (current.length() - current.replaceAll(vowels, "").length()) > (best.length() - best.replaceAll(vowels, "").length()));
            List<String> startWithVowel = findAll(words, word -> vowels.contains(String.valueOf(word.charAt(0))));
            List<String> startWithCapitalT = findAll(words, word -> String.valueOf(word.charAt(0)).equals("T"));
            System.out.println("Longest word: " + longest);
            System.out.println("Shortest word with at least 5 characters: " + shortest);
            System.out.println("Word with most vowels: " + vowelest);
            System.out.println("Words starting with a vowel: " + startWithVowel);
            System.out.println("Words starting with capital T: " + startWithCapitalT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}