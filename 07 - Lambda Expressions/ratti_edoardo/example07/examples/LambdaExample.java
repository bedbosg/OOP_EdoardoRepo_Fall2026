package example07.examples;

@FunctionalInterface
interface MyFunction {
    void action(int value);
}

public class LambdaExample {
    private static void performOperation(MyFunction myFunction) {
        for (int i = 0; i < 5; i++) {
            myFunction.action(i);
        }
    }

    public static void main(String[] args) {
        MyFunction myFunc = currentValue -> System.out.println("value: " + currentValue);
        performOperation(myFunc);

        performOperation(currentValue -> {
            currentValue++;   // legal: a parameter is a local copy
            System.out.println("value after ++: " + currentValue);
        });
    }
}
