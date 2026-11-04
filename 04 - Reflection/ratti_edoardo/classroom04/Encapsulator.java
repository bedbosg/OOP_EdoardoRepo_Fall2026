package classroom04;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Encapsulator {
    public static void main(String[] args) {
        System.out.println(encapsulate("classroom04.ServerConfiguration"));
    }

    private static String encapsulate(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            System.out.printf("public class %s {%n", className);
            Field[] fields = clazz.getDeclaredFields();
            Constructor<?> constructor = clazz.getConstructor();
            Object instance = constructor.newInstance();
            for (Field field : fields) {
                //fields
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();
                Object fieldValue = field.get(instance);
                System.out.printf("\tprivate %s %s = %s; %n%n", fieldType.getSimpleName(),field.getName(), literal(fieldValue));
                //Getters
                System.out.printf("\tpublic %s get%s() {%n", fieldType.getSimpleName(), capitalize(fieldName));
                System.out.printf("\t\treturn this.%s;%n", fieldName);
                System.out.printf("\t}%n%n");
                //Setters
                System.out.printf("\tpublic void %s(%s %s) {%n", capitalize(fieldName), fieldType.getSimpleName(), fieldName);
                System.out.printf("\t\tthis.%s = %s;%n ", fieldName, fieldName);
                System.out.printf("\t}%n%n");
            }
            System.out.printf("}%n");

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (NoSuchMethodException e) {
            System.out.println("No such method");
        } catch (InvocationTargetException  | InstantiationException | IllegalAccessException e) {
            System.out.println("Failed to create instance");
        }
        return "";
    }

    private static String capitalize(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    // Transform value to a literal e.g String test -> "test", float 1.2 -> 1.2f, etc.
    private static String literal(Object value) {
        if (value == null) {
            return "null";
        }
        return switch (value) {
            case String string -> '"' + string + '"';
            case Character character -> "'" + character + "'";
            case Long longValue -> longValue + "L";
            case Float floatValue -> floatValue + "f";
            default -> String.valueOf(value);
        };
    }
}
