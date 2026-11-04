package example06.calculator.step4_anonymous;

class Calculator {
    interface Operation {
        double execute(double a, double b);
    }

    private Operation[] ops = new Operation[3];
    private int cnt = 0;

    public Calculator() {
        // The types are unreachable outside the constructor.
        // All 3 Operation objects have distinct anonymous types.
        addOperation(new Operation() {
            public double execute(double a, double b) {
                return a + b;
            }
        });
        addOperation(new Operation() {
            public double execute(double a, double b) {
                return a - b;
            }
        });
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
}

public class CalculatorTest {
    public static void main(String[] args) {
        new Calculator().showResults(10, 5);
    }
}
