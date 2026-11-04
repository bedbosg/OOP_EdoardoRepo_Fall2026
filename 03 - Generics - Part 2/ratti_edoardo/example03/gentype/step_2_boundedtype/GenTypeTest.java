package example03.gentype.step_2_boundedtype;

class GenType<T extends Number> {
    private T t;

    public T get() {
        return this.t;
    }

    public void set(T t) {
        this.t = t;
    }

    public void performOperation() {
        // doubleValue() is now accessible — it is declared in Number
        double doubleValue = t.doubleValue();
        // further numeric operations possible here
    }
}

public class GenTypeTest {
    public static void main(String[] args) {
        GenType<Integer> gi = new GenType<>();
        GenType<Double> gd = new GenType<>();
        // GenType<String> gs = new GenType<>();
        // Compile error: String does not extend Number.

        gi.set(42);
        gi.performOperation();
        // doubleValue() returns 42.0

        gd.set(3.14);
        gd.performOperation();
        // doubleValue() returns 3.14

        // The generic bound is checked at compile time, not at runtime.
    }
}
