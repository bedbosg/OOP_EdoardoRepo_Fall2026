package deepening04.additional;

public class AnonymousClassExample {

    public static void main(String[] args) {
        testAnonymousClass();
    }

    private static void testAnonymousClass() {
        new Object() {
            void runMe() {
                System.out.println("Enclosing class: " + getClass().getEnclosingClass());
                System.out.println("Declaring class: " + getClass().getDeclaringClass());
            }
        }.runMe();
    }
}