package example05.unittest.step2;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface Test {
    String testDescription();
}

public class UnitTest {
    public static void assertEquals(Object expected, Object actual) {
        if ((expected == null) && (actual == null)) {
            return;
        }
        if ((expected != null) && expected.equals(actual)) {
            return;
        }
        throw new AssertionError("The object is not equal [expected: <" + expected + ">, actual: <" + actual + ">]");
    }

    public void execute(String className) {
        try {
            Class<?> testClass = Class.forName(className);
            Object testObject = testClass.getDeclaredConstructor().newInstance();

            for (Method method : testClass.getDeclaredMethods()) {
                Test annotation = method.getAnnotation(Test.class);
                if (annotation != null) {
                    System.out.printf("Testing %s.%s() - %s%n", className, method.getName(), annotation.testDescription());
                    method.setAccessible(true);
                    try {
                        method.invoke(testObject);
                        System.out.println("PASS: " + method.getName());
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        Throwable cause = e.getCause();
                        if (cause instanceof AssertionError) {
                            System.out.println("FAIL: " + method.getName() + " — " + cause.getMessage());
                        } else {
                            System.out.println("ERROR: " + method.getName() + " — " + cause.getMessage());
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
        } catch (NoSuchMethodException e) {
            System.out.println("Could not find constructor for: " + className);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error while creating a new instance of: " + className);
        }
    }

    public static void main(String[] args) {
        UnitTest executor = new UnitTest();
        executor.execute("example05.unittest.step2.MathTest");
    }
}