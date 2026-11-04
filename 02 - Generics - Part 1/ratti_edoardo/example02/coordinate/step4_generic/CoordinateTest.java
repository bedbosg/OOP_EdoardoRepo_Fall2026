package example02.coordinate.step4_generic;

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
        Coordinate<Integer> c = new Coordinate<Integer>(12, 4, 33);
        int x = c.getX();
        int y = c.getY();
        int z = c.getZ();
    }
}
