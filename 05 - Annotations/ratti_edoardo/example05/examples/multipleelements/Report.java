package example05.examples.multipleelements;

@interface ClassPreamble {
    String author();

    String date();

    int revision() default 1;

    String[] reviewers();
}

// All non-default elements must be named.
@ClassPreamble(
        author = "Jane Doe",
        date = "2024-01-15",
        reviewers = {"Alice", "Bob"}
)
public class Report {
    // ...
}
