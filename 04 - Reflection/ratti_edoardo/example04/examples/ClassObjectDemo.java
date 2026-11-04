package example04.examples;

public class ClassObjectDemo {
    public static void main(String[] args) {
        String s = "hello";

        // 1. Class literal — compile-time known type
        Class<String> c1 = String.class;

        // 2. getClass() — actual run-time type of a live object
        Class<?> c2 = s.getClass();

        // 3. Class.forName() — name known only at run time
        try {
            Class<?> c3 = Class.forName("java.lang.String");

            // All three refer to the same Class object:
            System.out.println(c1 == c2); // true
            System.out.println(c2 == c3); // true
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
    }
}
