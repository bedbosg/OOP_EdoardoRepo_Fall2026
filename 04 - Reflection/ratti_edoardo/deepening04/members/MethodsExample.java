package deepening04.members;

import java.lang.reflect.Method;

class MethodTarget {
    private Integer theAnswer = 42;

    MethodTarget(int theAnswer) {
        this.theAnswer = theAnswer;
    }

    public Integer getTheAnswer() {
        return theAnswer;
    }

    private Integer getTheAnswerInternal() {
        return theAnswer;
    }
}

public class MethodsExample {

    public static void main(String[] args) {
        testPublicMethod();
        testPrivateMethod();
    }

    private static void testPublicMethod() {
        System.out.println("\n--- invoking a public method ---");
        MethodTarget targetObject = new MethodTarget(1);

        try {
            Method method = MethodTarget.class.getMethod("getTheAnswer");
            Object result = method.invoke(targetObject);
            System.out.println("Result of the call: " + result);
        } catch (ReflectiveOperationException e) {
            System.err.println("Reflection failed: " + e.getClass().getSimpleName());
        }
    }

    private static void testPrivateMethod() {
        System.out.println("\n--- invoking a private method ---");
        MethodTarget targetObject = new MethodTarget(1);

        try {
            Method method = MethodTarget.class.getDeclaredMethod("getTheAnswerInternal");
            method.setAccessible(true);
            Object result = method.invoke(targetObject);
            System.out.println("Result of the call: " + result);
        } catch (ReflectiveOperationException e) {
            System.err.println("Reflection failed: " + e.getClass().getSimpleName());
        }
    }
}
