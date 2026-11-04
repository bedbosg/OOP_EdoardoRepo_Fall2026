package classroom06.operations;

public class ExtractInitials implements TextOperation {
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