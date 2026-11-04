package deepening04.additional;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class DeclaringClassTarget {
    private Integer theAnswer = 42;

    public DeclaringClassTarget(int theAnswer) {
        this.theAnswer = theAnswer;
    }

    public Integer getTheAnswer() {
        return theAnswer;
    }
}

public class DeclaringClassExample {

    public static void main(String[] args) {
        testDeclaringClassFromMembers();
    }

    private static void testDeclaringClassFromMembers() {
        try {
            Constructor<?> constructor = DeclaringClassTarget.class.getDeclaredConstructor(int.class);
            System.out.println("Constructor declaring class: " + constructor.getDeclaringClass());

            Field field = DeclaringClassTarget.class.getDeclaredField("theAnswer");
            System.out.println("Field declaring class: " + field.getDeclaringClass());

            Method method = DeclaringClassTarget.class.getMethod("getTheAnswer");
            System.out.println("Method declaring class: " + method.getDeclaringClass());
        } catch (ReflectiveOperationException e) {
            System.err.println("Reflection failed: " + e.getClass().getSimpleName());
        }
    }
}
