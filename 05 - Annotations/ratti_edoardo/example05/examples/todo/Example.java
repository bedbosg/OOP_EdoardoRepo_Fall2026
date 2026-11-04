package example05.examples.todo;

public class Example {
    @Todo(
            dueDate = "01/01/2026",
            // task is String[] → array literal
            task = {
                    "refactor this method",
                    "add unit tests",
                    "update documentation"
            }
    )
    public static void main(String[] args) {
        // ...
    }
}
