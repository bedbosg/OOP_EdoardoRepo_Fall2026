package classroom06;

import java.util.List;

public class TextProcessorPartA {
    private interface TextOperation {
        String process(String input);



    }
    public static String scramble(String input) {
        TextOperation removeSpaces = new TextOperation(){
            public String process(String input) {
                return input.replaceAll("\\s+", "");
            }
        };
        final int shift = ((input.length() % 26) + 26) % 26;
        TextOperation shifter = new TextOperation() {
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
        };
        List<TextOperation> scramble = List.of(removeSpaces, shifter);

        String result = input;
        for (TextOperation operation : scramble) {
            // Replace the result with the new operation result
            result = operation.process(result);
        }
        return result;
    }

    public static void main(String[] args) {
        String input = "Hello World";
        System.out.println("Scramble: " + TextProcessorPartA.scramble(input));
    }
}
