package classroom06;

import classroom06.operations.*;

import java.util.List;

public class TextProcessorPartB {
    public static String shoutInitials(String input) {
        TextOperation extractInitials = new ExtractInitials();
        TextOperation toUpperCase = new ToUpper();
        TextOperation surround = new Surround("[", "]");
        List<TextOperation> shoutInitials = List.of(extractInitials, toUpperCase, surround);

        String result = input;
        for (TextOperation operation : shoutInitials) {
            // Replace the result with the new operation result
            result = operation.process(result);
        }
        return result;
    }

    public static String lowercaseEndings(String input) {
        TextOperation extractInitials = new ExtractInitials();
        TextOperation reverse = new Reverse();
        TextOperation removePunctuation = new RemovePunctuation();
        List<TextOperation> lowercaseEndings = List.of(reverse, removePunctuation, extractInitials, reverse);

        String result = input;
        for (TextOperation operation : lowercaseEndings) {
            // Replace the result with the new operation result
            result = operation.process(result);
        }
        return result;
    }

    // Cipher: shift by 13 chars and reverse string
    public static String cipher(String input) {
        TextOperation shift13 = new Shifter(13);
        TextOperation reverse = new Reverse();
        List<TextOperation> cipher = List.of(shift13, reverse);

        String result = input;
        for (TextOperation operation : cipher) {
            // Replace the result with the new operation result
            result = operation.process(result);
        }
        return result;
    }

    public static void main(String[] args) {
        String text = "The quick brown fox, jumps over the lazy dog!";
        System.out.println("Text: " + text);

        System.out.println("shoutInitials       : " + TextProcessorPartB.shoutInitials(text));
        System.out.println("lowercaseEndings    : " + TextProcessorPartB.lowercaseEndings(text));
        String cipherResult = TextProcessorPartB.cipher(text);
        System.out.println("cipher(text)        : " + cipherResult);
        System.out.println("cipher(cipherResult): " + TextProcessorPartB.cipher(cipherResult));
    }
}
