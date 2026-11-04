# More on Meta Annotations
#### Object Oriented Programming - Prof. Tiziano Leidi - Bachelor in Computer Science - SUPSI

---

Meta annotations are annotations applied to other annotations such as `@Target` and `@Retention`. Other useful meta annotations are:

- `@Documented`: This meta annotation ensures that the affected annotation will be included in the JavaDoc documentation.
- `@Inherited`: It is used to indicate that an annotation should be inherited by subclasses of the annotated class.
- `@Repeatable`: This meta annotation allows an annotation to be applied multiple times to the same declaration. Repeatable annotations are stored in an annotation container for compatibility reasons.

## Example: @Repeatable

Applying the same annotation without first declaring it to be repeatable will result in a compile-time error. To resolve this issue, the annotation needs to be modified as follows:

```java
public class Main {
   @Todo(dueDate = "01/01/2021", task = "refactor this method")
   @Todo(dueDate = "01/02/2021", task = "add jsdoc comments")
   public static void test() {
       // ...
   }
}

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TodoContainer.class)
public @interface Todo {
   String dueDate();
   String task() default "";
}
```

A containing annotation (e.g., `TodoContainer` in our example) needs to be provided. It is not necessary to explicitly use the container annotation since the Java compiler automatically handles it. The containing annotation must have a value element with the correct array type. The `@Target` of the containing annotation must be a subset of the contained annotation's target (in this example, `{ElementType.METHOD}` is a subset of `{ElementType.METHOD}`). The `@Retention` of the containing annotation must not be shorter than the contained annotation's retention policy. If these conditions are not met, the code will not compile.

```java
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TodoContainer {
   Todo[] value();
}
```

In order to access the information in the container, one must use the method `isAnnotationPresent()` with `TodoContainer.class` as a parameter. The contained annotations can be extracted using `getAnnotationsByType()`, which returns an array of annotations.


```java
import java.lang.annotation.Retention;
import java.lang.annotation.Repeatable;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
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

class ExampleClass {
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
```
