package classroom06.operations;

public class Surround implements TextOperation {
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