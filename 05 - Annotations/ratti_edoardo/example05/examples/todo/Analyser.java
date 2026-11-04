package example05.examples.todo;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Analyser {
    public static void main(String[] args) {
        try {
            // Class name in the string must match the declared class — using Example here.
            Class<Example> myClass = (Class<Example>) Class.forName("example05.examples.todo.Example");

            for (Method method : myClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Todo.class)) {
                    Todo todo = method.getAnnotation(Todo.class);
                    // task() returns String[] → Arrays.toString()
                    System.out.println(Arrays.toString(todo.task()) + " - " + todo.dueDate());
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
    }
}

// Output:
// [refactor this method, add unit tests, update documentation] - 01/01/2026
