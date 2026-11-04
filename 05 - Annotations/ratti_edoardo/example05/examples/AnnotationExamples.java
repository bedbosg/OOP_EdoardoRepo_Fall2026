package example05.examples;

import java.util.List;

public class AnnotationExamples {

    // No elements — parentheses are optional
    @Override
    public String toString() {
        return "AnnotationExamples";
    }

    // Single value shorthand:
    // works only when the element is named 'value'
    // and it is the only element being set.
    @SuppressWarnings("unchecked")
    public <T> T[] uncheckedExample(int n) {
        return (T[]) new Object[n];
    }

    // Equivalent to:
    @SuppressWarnings(value = "unchecked")
    public <T> T[] uncheckedExampleExplicit(int n) {
        return (T[]) new Object[n];
    }

    // Multiple values (array literal)
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void multipleWarnings(List rawList) {
        List<String> typedList = rawList;
    }

    // Named syntax - required when more than
    // one element is present
    @Deprecated(since = "2.0", forRemoval = true)
    public void oldMethod() {
        System.out.println("Old Method");
    }

    // Multiple annotations on the same element
    @Deprecated
    @SuppressWarnings("unused")
    private void legacyMethod() {
        System.out.println("Legacy method");
    }
}
