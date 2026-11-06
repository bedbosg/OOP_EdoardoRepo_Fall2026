package example07.calculator.step2_lambda;

public class Calculator {
    interface Operation {
        double execute(double a, double b);
    }

    private Operation[] ops = new Operation[3];
    private int cnt = 0;

    public Calculator() {
        addOperation((a, b) -> a + b);
        addOperation((double a, double b) -> a - b);
        addOperation(new Operation() {
            public double execute(double a, double b) {
                return a * b;
            }
        });
    }

    private void addOperation(Operation op) {
        ops[cnt++] = op;
    }

    public void showResults(double a, double b) {
        for (Operation op : ops)
            System.out.println(op.execute(a, b));
    }

    public static void main(String[] args) {
        new Calculator().showResults(10, 5);
    }
}
