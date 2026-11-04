package classroom06.operations;

/**
 * Shifts each letter by a fixed number of positions in the alphabet,
 * wrapping around from Z to A (e.g., with shift = 3, "ABC" becomes "DEF").
 * Non-letter characters remain unchanged.
 */
public class Shifter implements TextOperation {
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
