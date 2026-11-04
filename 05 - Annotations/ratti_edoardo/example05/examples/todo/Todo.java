package example05.examples.todo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Todo {
    String dueDate();

    // task is an array: usage must use array literal syntax { ... }
    String[] task() default {""};
}
