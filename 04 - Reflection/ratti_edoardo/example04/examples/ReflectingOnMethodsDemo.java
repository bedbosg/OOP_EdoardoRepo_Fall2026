package example04.examples;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Greeter {
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}

public class ReflectingOnMethodsDemo {
    public static void main(String[] args) {
        Greeter g = new Greeter();
        try {
            Method m = Greeter.class
                    .getDeclaredMethod("greet", String.class);
            String result = (String) m.invoke(g, "Alice");
            System.out.println(result); // Hello, Alice!

        } catch (NoSuchMethodException e) {
            System.err.println("Method not found");
        } catch (IllegalAccessException e) {
            System.err.println("Cannot access method");
        } catch (InvocationTargetException e) {
            System.err.println("Error invoking method");
        }
    }
}

