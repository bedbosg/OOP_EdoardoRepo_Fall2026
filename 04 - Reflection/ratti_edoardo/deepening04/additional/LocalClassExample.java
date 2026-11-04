package deepening04.additional;

public class LocalClassExample {

    public static void main(String[] args) {
        testLocalClass();
    }

    private static void testLocalClass() {
        class LocalClass {
            LocalClass() {
                System.out.println("Enclosing class: " + LocalClass.class.getEnclosingClass());
                System.out.println("Declaring class: " + LocalClass.class.getDeclaringClass());
            }
        }

        new LocalClass();
    }
}