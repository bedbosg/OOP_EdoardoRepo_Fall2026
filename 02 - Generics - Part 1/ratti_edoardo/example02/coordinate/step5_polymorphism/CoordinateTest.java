package example02.coordinate.step5_polymorphism;

class Coordinate<T> {
    private T x;
    private T y;
    private T z;

    public Coordinate(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public T getZ() {
        return z;
    }
}


public class CoordinateTest {
    public static void main(String[] args) {
        Coordinate<Number> c = new Coordinate<>(6, 25, 2l);
        Number x = c.getX();
        Number y = c.getY();
        Number z = c.getZ();
    }
}
