package async05.markdown;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MarkdownDoc {
    boolean parentClass() default true;
    boolean interfaces() default true;
    boolean fields() default true;
    boolean constructors() default true;
    boolean methods() default true;
}

@Retention(RetentionPolicy.RUNTIME)
@interface MarkdownDocIgnore {}

public class MarkdownGenerator {

    private static void toMarkdown(final Class<?> theClass) {
        if (!theClass.isAnnotationPresent(MarkdownDoc.class)) {
            return;
        }
        MarkdownDoc doc = theClass.getAnnotation(MarkdownDoc.class);

        // Titolo classe
        System.out.printf("# Class `%s`%n", theClass.getName());

        // Parent class
        if (doc.parentClass() && theClass.getSuperclass() != null) {
            System.out.printf("Parent class: `%s`%n", theClass.getSuperclass().getName());
        }

        // Interface(s)
        if (doc.interfaces() && theClass.getInterfaces().length > 0) {
            System.out.printf("## Interface(s)%n");
            for (Class<?> iface : theClass.getInterfaces()) {
                System.out.printf("- `%s`%n", iface.getName());
            }
        }

        // Fields(s)
        if (doc.fields() && theClass.getDeclaredFields().length > 0) {
            boolean hasFields = false;
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("## Fields(s)%n"));
            for (Field field : theClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(MarkdownDocIgnore.class)) {
                    continue;
                }
                hasFields = true;
                sb.append(String.format("- `%s %s`%n", field.getType().getName(), field.getName()));
            }
            if (hasFields) {
                System.out.print(sb);
            }
        }

        // Constructor(s)
        if (doc.constructors() && theClass.getDeclaredConstructors().length > 0) {
            boolean hasConstructors = false;
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("## Constructor(s)%n"));
            for (Constructor<?> constructor : theClass.getDeclaredConstructors()) {
                if (constructor.isAnnotationPresent(MarkdownDocIgnore.class)) {
                    continue;
                }
                hasConstructors = true;
                sb.append(String.format("- `%s(", constructor.getDeclaringClass().getName()));
                Class<?>[] paramTypes = constructor.getParameterTypes();
                for (int i = 0; i < paramTypes.length; i++) {
                    sb.append(paramTypes[i].getName());
                    if (i < paramTypes.length - 1) {
                        sb.append(", ");
                    }
                }
                sb.append(String.format(")`%n"));
            }
            if (hasConstructors) {
                System.out.print(sb);
            }
        }

        // Methods(s)
        if (doc.methods() && theClass.getDeclaredMethods().length > 0) {
            boolean hasMethods = false;
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("## Methods(s)%n"));
            for (Method method : theClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(MarkdownDocIgnore.class)) {
                    continue;
                }
                hasMethods = true;
                sb.append(String.format("- `%s %s(", method.getReturnType().getName(), method.getName()));
                Class<?>[] paramTypes = method.getParameterTypes();
                for (int i = 0; i < paramTypes.length; i++) {
                    sb.append(paramTypes[i].getName());
                    if (i < paramTypes.length - 1) {
                        sb.append(", ");
                    }
                }
                sb.append(String.format(")`%n"));
            }
            if (hasMethods) {
                System.out.print(sb);
            }
        }
    }

    private static void toMarkdown(final String className) {
        try {
            Class<?> clazz = Class.forName(className);
            toMarkdown(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        toMarkdown("async05.markdown.Coordinate");
    }
}