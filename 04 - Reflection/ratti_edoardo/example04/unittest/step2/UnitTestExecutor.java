package example04.unittest.step2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UnitTestExecutor {
    public void execute(String className) {
        try {
            Class<?> testClass = Class.forName(className);
            Object testObject = testClass.getDeclaredConstructor().newInstance();

            for (Method method : testClass.getDeclaredMethods()) {
                if (method.getName().startsWith("test")) {
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
}
