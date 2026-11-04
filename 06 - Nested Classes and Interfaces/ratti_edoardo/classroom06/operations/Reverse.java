package classroom06.operations;

public class Reverse implements TextOperation {
    public String process(String input) {
        return new StringBuffer(input).reverse().toString();
    }
}
