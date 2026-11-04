package classroom05;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
enum Visibiliy{
    PRIVATE, PACKAGE_PRIVATE, PROTECTED, PUBLIC
}
//Encapsulate
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface Encapsulate{
    Visibiliy value() default Visibiliy.PRIVATE;
}
// GetterSetter
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface GetterSetter{
    boolean getter() default true;
    boolean setter() default true;
}
// DefaultConstructor
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface DefaultConstructor{}
// ExcludeFromConstructor
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface ExcludeFromConstructor{}


public class Encapsulator {
    private static final StringBuilder fieldsStringBuilder = new StringBuilder();
    private static final StringBuilder methodsStringBuilder = new StringBuilder();

    private static String encapsulate(String className) {
        final Class<?> sampleClass;
        try {
            sampleClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found");
            return "";
        }
        if(!sampleClass.isAnnotationPresent(Encapsulate.class)) return "";
        Encapsulate encapsulateAnnotation = sampleClass.getAnnotation(Encapsulate.class);
        try {
            Constructor<?> constructor = sampleClass.getDeclaredConstructor();
            Object instance = constructor.newInstance();

            Field[] fields = sampleClass.getDeclaredFields();
            for (Field field : fields) {
                // Get current field modifier
                Visibiliy visibiliy = encapsulateAnnotation.value();
                String modifier = switch (visibiliy){
                    case PRIVATE -> "private ";
                    case PROTECTED -> "protected ";
                    case PUBLIC ->  "public ";
                    default -> "";
                }; //visibility(field)

                Class<?> fieldType = field.getType();
                String fieldTypeName = fieldType.getSimpleName();

                String fieldName = field.getName();
                String capitalFieldName = capitalize(fieldName);

                // Determine the default value by checking the value of the field
                field.setAccessible(true);
                Object defaultValue = field.get(instance);
                addField(modifier, fieldTypeName, fieldName, defaultValue);
                if(field.isAnnotationPresent(GetterSetter.class)){
                    GetterSetter getterSetterAnnotation = field.getAnnotation(GetterSetter.class);
                    if(getterSetterAnnotation.getter()){
                        addGetterMethod(capitalFieldName, fieldTypeName, fieldName);
                    }
                    if(getterSetterAnnotation.setter()) {
                        addSetterMethod(capitalFieldName, fieldTypeName, fieldName);
                    }
                }
            }

            return """
                    public class %s {
                    %s%s}
                    """.formatted(sampleClass.getSimpleName(), fieldsStringBuilder.toString(), methodsStringBuilder.toString());

        } catch (NoSuchMethodException e) {
            System.err.println("Default constructor not found.");
            return "";
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.err.println("Failed to create new instance of " + className);
            System.err.println(e.getMessage());
            return "";
        }
    }

    private static void addField(String modifier, String fieldTypeName, String fieldName, Object defaultValue) {
        if (defaultValue == null) {
            fieldsStringBuilder.append("""
                        %s%s %s;
                    """.formatted(modifier, fieldTypeName, fieldName));
        } else {
            String defaultLiteral = literal(defaultValue);
            fieldsStringBuilder.append("""
                        %s%s %s = %s;
                    """.formatted(modifier, fieldTypeName, fieldName, defaultLiteral));
        }
    }

    private static void addGetterMethod(String capitalFieldName, String fieldTypeName, String fieldName) {
        methodsStringBuilder.append("""
                
                    public %s get%s() {
                        return this.%s;
                    }
                """.formatted(fieldTypeName, capitalFieldName, fieldName));
    }

    private static void addSetterMethod(String capitalFieldName, String fieldTypeName, String fieldName) {
        methodsStringBuilder.append("""
                
                    public void set%s(%s %s) {
                        this.%s = %s;
                    }
                """.formatted(capitalFieldName, fieldTypeName, fieldName, fieldName, fieldName));
    }

    private static String capitalize(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    // Returns the string representation of the current field, adding a space if not package-private. E.g. private -> "private "
    private static String visibility(Field field) {
        if (Modifier.isPublic(field.getModifiers())) {
            return "public ";
        }
        if (Modifier.isProtected(field.getModifiers())) {
            return "protected ";
        }
        if (Modifier.isPrivate(field.getModifiers())) {
            return "private ";
        }
        return "";
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

    public static void main(String[] args) {
        System.out.println(encapsulate("classroom05.ServerConfiguration"));
    }
}
