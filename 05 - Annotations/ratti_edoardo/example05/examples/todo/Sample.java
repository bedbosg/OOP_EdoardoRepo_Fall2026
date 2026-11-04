package example05.examples.todo;

public class Sample {
    // Compile error: @Todo not allowed on a field
    // @Todo(dueDate = "2026/12/31", task = {"rename"})
    private int counter;

    // Valid: annotating a method
    @Todo(dueDate = "2026/12/31", task = {"update logic"})
    public void process() {
        // ...
    }

    // ...
}
