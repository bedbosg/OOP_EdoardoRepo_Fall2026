package classroom06.operations;

public class RemovePunctuation implements TextOperation {
    public String process(String input) {
        return input.replaceAll("[\\p{Punct}]", "");
    }
}