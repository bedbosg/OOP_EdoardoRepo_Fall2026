package example07.calculator.step3_method_references;

public class Calculator {
    interface Operation {
        double execute(double a, double b);
    }

    private static class MathUtilities {
        private static double multiply(double a, double b) {
            return a * b;
        }

        private double divide(double a, double b) {
            return a / b;
        }
    }


    private Operation[] ops = new Operation[4];
    private int cnt = 0;

    public Calculator() {
        addOperation((a, b) -> a + b);
        addOperation((a, b) -> a - b);
        addOperation(MathUtilities::multiply);      // static
        addOperation(new MathUtilities()::divide);  // bound instance
    }

    private void addOperation(Operation op) {
        ops[cnt++] = op;
    }

    public void showResults(double a, double b) {
        for (Operation op : ops)
            System.out.println(op.execute(a, b));
    }

    public static void main(String[] args) {
        new Calculator().showResults(42.42, 666.666);
    }
}
