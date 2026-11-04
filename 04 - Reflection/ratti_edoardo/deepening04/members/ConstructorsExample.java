package deepening04.members;

import java.lang.reflect.Constructor;

class ConstructorTarget {
    private Integer theAnswer = 42;

    public ConstructorTarget(int theAnswer) {
        this.theAnswer = theAnswer;
    }

    public Integer getTheAnswer() {
        return theAnswer;
    }
}

public class ConstructorsExample {

    public static void main(String[] args) {
        testNoArgumentConstructor();
        testConstructorWithArguments();
    }

    private static void testNoArgumentConstructor() {
        System.out.println("\n--- getDeclaredConstructor().newInstance() ---");

        try {
            Class<?> myClass = Class.forName("java.util.Random");
            Object instance = myClass.getDeclaredConstructor().newInstance();
            System.out.println("Instance: " + instance);
            Object result = myClass.getMethod("nextInt").invoke(instance);
            System.out.println("Result of nextInt(): " + result);
        } catch (ReflectiveOperationException e) {
            System.err.println("Reflection failed: " + e.getClass().getSimpleName());
        }
    }

    private static void testConstructorWithArguments() {
        System.out.println("\n--- Constructor.newInstance(arguments) ---");

        try {
            String className = ConstructorTarget.class.getName();
            System.out.println("Class name: " + className);
            Class<?> myClass = Class.forName(className);
            Constructor<?> constructor = myClass.getDeclaredConstructor(int.class);
            Object instance = constructor.newInstance(1);
            Object result = myClass.getMethod("getTheAnswer").invoke(instance);
            System.out.println("The answer is " + result);
        } catch (ReflectiveOperationException e) {
            System.err.println("Reflection failed: " + e.getClass().getSimpleName());
        }
    }
}
