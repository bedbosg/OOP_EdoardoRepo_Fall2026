package deepening05.todo;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TodoContainer.class)
@interface Todo {
    String dueDate();

    String task() default "";
}

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface TodoContainer {
    Todo[] value();
}

public class ExampleClass {
    @Todo(dueDate = "01/01/2021", task = "refactor this method")
    @Todo(dueDate = "01/02/2021", task = "add jsdoc comments")
    private static void test() {
        // ...
    }

    public static void main(String[] args) {
        Class<ExampleClass> myClass = ExampleClass.class;
        Method[] methods = myClass.getDeclaredMethods();
        System.out.println("Class: " + myClass);
        for (Method method : methods) {
            if (method.isAnnotationPresent(TodoContainer.class)) {
                Todo[] todos = method.getAnnotationsByType(Todo.class);
                System.out.println("todos " + todos.length);
                for (Todo todo : todos) {
                    System.out.println("Annotation elements: " + todo.task() + " - " + todo.dueDate());
                }
            }
        }
    }
}