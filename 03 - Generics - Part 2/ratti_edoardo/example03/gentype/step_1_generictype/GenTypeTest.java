package example03.gentype.step_1_generictype;

class GenType<T> {
    private T t;

    public T get() {
        return this.t;
    }

    public void set(T t) {
        this.t = t;
    }

    public void performOperation() {
        // t. ???
    }
}

public class GenTypeTest {
    public static void main(String[] args) {
        GenType<Integer> gi = new GenType<>();
        GenType<Double> gd = new GenType<>();
        GenType<String> gs = new GenType<>();

        gi.set(42);
        gi.performOperation();

        gd.set(3.14);
        gd.performOperation();
    }
}
