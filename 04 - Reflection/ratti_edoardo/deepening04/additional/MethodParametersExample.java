package deepening04.additional;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int mul(int a, int b) {
        return a * b;
    }

    long subtract(long a, long b) {
        return a - b;
    }
}

public class MethodParametersExample {

    public static void main(String[] args) {
        testMethodParameters();
    }

    private static void testMethodParameters() {
        Class<Calculator> cls = Calculator.class;
        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods) {
            System.out.print("Method: " + method.getName() + ". Parameters: ");
            Parameter[] parameters = method.getParameters();

            for (Parameter parameter : parameters) {
                System.out.print(parameter.getParameterizedType() + " " + parameter.getName() + " ");
            }
            System.out.println();
        }
    }
}
