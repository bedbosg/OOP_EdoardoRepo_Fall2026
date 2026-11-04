package example05.examples.returntypes;

// Enum and nested annotation types
enum Priority {
    LOW, MEDIUM, HIGH
}

@interface Author {
    String name();
}

@interface Task {
    Priority priority() default Priority.LOW;

    Author author();
}

public class CleanupJob {
    @Task(author = @Author(name = "Carol"))
    public void removeTemporaryFiles() {
        // Priority defaults to LOW
    }
}