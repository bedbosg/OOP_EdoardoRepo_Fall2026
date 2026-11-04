package example05.examples;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
@interface NonNull {

}

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
@interface Interned {
}

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
@interface Readonly {
}

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
@interface Critical {
}

// 3. implements clause
abstract class UnmodifiableList<T> implements @Readonly List<@Readonly T> {

    // 4. throws clause
    void monitor() throws @Critical Exception {
        // ...
    }
}

public class TypeAnnotationDemo {

    public void createObject() {
        // 1. Instance creation
        Object obj = new @Interned Object();
    }

    public String castValue(Object str) {
        // 2. Cast
        return (@NonNull String) str;
    }

    public static void main(String[] args) {
        // Declaration annotation on variable
        @NonNull String string;

        // Type annotation directly before the type
        List<@NonNull String> names = new ArrayList<>();

        // TYPE_USE also covers array component types
        String @NonNull [] array;
    }
}
