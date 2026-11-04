package deepening04.members;

import java.lang.reflect.Field;

class PublicFieldTarget {
    public Integer theAnswer = 42;

    PublicFieldTarget(int theAnswer) {
        this.theAnswer = theAnswer;
    }

    Integer getTheAnswer() {
        return theAnswer;
    }
}

class PrivateFieldTarget {
    private Integer theAnswer = 42;

    PrivateFieldTarget(int theAnswer) {
        this.theAnswer = theAnswer;
    }

    Integer getTheAnswer() {
        return theAnswer;
    }
}

public class FieldsExample {

    public static void main(String[] args) {
        testPublicFieldWithGetField();
        testPrivateFieldWithGetField();
        testPrivateFieldWithGetDeclaredField();
        testPrivateFieldWithAccessibleDeclaredField();
    }

    private static void testPublicFieldWithGetField() {
        System.out.println("\n--- public field via getField() ---");
        PublicFieldTarget targetObject = new PublicFieldTarget(1);
        System.out.println("The answer is " + targetObject.getTheAnswer());

        try {
            Field field = PublicFieldTarget.class.getField("theAnswer");
            System.out.println("Field: " + field);
            System.out.println("Field value: " + field.get(targetObject));
            field.set(targetObject, -273);
            System.out.println("The answer is now " + targetObject.getTheAnswer());
        } catch (ReflectiveOperationException e) {
            System.err.println("Reflection failed: " + e.getClass().getSimpleName());
        }
    }

    private static void testPrivateFieldWithGetField() {
        System.out.println("\n--- private field via getField(): expected lookup failure ---");

        try {
            Field field = PrivateFieldTarget.class.getField("theAnswer");
            System.out.println(field);
        } catch (ReflectiveOperationException e) {
            System.out.println("Expected reflection failure: " + e.getClass().getSimpleName());
        }
    }

    private static void testPrivateFieldWithGetDeclaredField() {
        System.out.println("\n--- private field via getDeclaredField(): expected access failure ---");
        PrivateFieldTarget targetObject = new PrivateFieldTarget(1);

        try {
            Field field = PrivateFieldTarget.class.getDeclaredField("theAnswer");
            System.out.println("Field: " + field);
            System.out.println("Field value: " + field.get(targetObject));
        } catch (ReflectiveOperationException e) {
            System.out.println("Expected reflection failure: " + e.getClass().getSimpleName());
        }
    }

    private static void testPrivateFieldWithAccessibleDeclaredField() {
        System.out.println("\n--- private field via getDeclaredField() + setAccessible(true) ---");
        PrivateFieldTarget targetObject = new PrivateFieldTarget(1);

        try {
            Field field = PrivateFieldTarget.class.getDeclaredField("theAnswer");
            field.setAccessible(true);
            System.out.println("Field value: " + field.get(targetObject));
            field.set(targetObject, -273);
            System.out.println("The answer is now " + targetObject.getTheAnswer());
        } catch (ReflectiveOperationException e) {
            System.err.println("Reflection failed: " + e.getClass().getSimpleName());
        }
    }
}
