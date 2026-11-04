package classroom06.operations;

public class RemoveSpaces implements TextOperation {
    public String process(String input) {
        return input.replaceAll("\\s+", "");
    }
}
