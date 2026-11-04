package classroom06;

import java.util.List;

public class TextProcessorPartBNestedStatic {
    public interface TextOperation {
        String process(String input);
    }
    private static class ExtractInitials implements TextOperation {
        @Override
        public String process(String input) {
            String[] parts = input.trim().split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String p : parts) {
                if (!p.isEmpty()) {
                    sb.append(p.charAt(0));
                }
            }
            return sb.toString();
        }
    }
    private static class ToUpper implements TextOperation {
        public String process(String input) {
            return input.toUpperCase();
        }
    }
    private static class Surround implements TextOperation {
        private final String prefix, suffix;

        public Surround(String prefix, String suffix) {
            this.prefix = prefix;
            this.suffix = suffix;
        }

        @Override
        public String process(String input) {
            return prefix + input + suffix;
        }
    }
    private static class Reverse implements TextOperation {
        public String process(String input) {
            return new StringBuffer(input).reverse().toString();
        }
    }
    private static class RemovePunctuation implements TextOperation {
        public String process(String input) {
            return input.replaceAll("[\\p{Punct}]", "");
        }
    }
    private static class Shifter implements TextOperation {
        private final int shift;

        public Shifter(int shift) {
            this.shift = shift;
        }

        @Override
        public String process(String input) {
            StringBuilder sb = new StringBuilder(input.length());
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    sb.append((char) ('a' + (c - 'a' + shift) % 26));
                } else if (c >= 'A' && c <= 'Z') {
                    sb.append((char) ('A' + (c - 'A' + shift) % 26));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }
    private static String shoutInitials(String input) {
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

        System.out.println("shoutInitials       : " + TextProcessorPartBNestedStatic.shoutInitials(text));
        System.out.println("lowercaseEndings    : " + TextProcessorPartBNestedStatic.lowercaseEndings(text));
        String cipherResult = TextProcessorPartBNestedStatic.cipher(text);
        System.out.println("cipher(text)        : " + cipherResult);
        System.out.println("cipher(cipherResult): " + TextProcessorPartBNestedStatic.cipher(cipherResult));
    }
}
