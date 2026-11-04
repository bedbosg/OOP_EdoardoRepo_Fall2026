package example06.calculator.step2_staticnested;

class Calculator {
    interface Operation {
        double execute(double a, double b);
    }

    private static class Sum implements Operation {
        public double execute(double a, double b) {
            return a + b;
        }
    }

    private static class Subtract implements Operation {
        public double execute(double a, double b) {
            return a - b;
        }
    }

    private static class Multiply implements Operation {
        public double execute(double a, double b) {
            return a * b;
        }
    }

    private final Operation[] ops = new Operation[3];
    private int cnt = 0;

    public Calculator() {
        addOperation(new Sum());
        addOperation(new Subtract());
        addOperation(new Multiply());
    }

    private void addOperation(Operation op) {
        ops[cnt++] = op;
    }

    public void showResults(double a, double b) {
        for (Operation op : ops)
            System.out.println(op.execute(a, b));
    }
}

public class CalculatorTest {
    public static void main(String[] args) {
        new Calculator().showResults(10, 5);
    }
}
