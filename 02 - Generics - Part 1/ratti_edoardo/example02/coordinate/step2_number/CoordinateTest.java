package example02.coordinate.step2_number;

class Coordinate {
    private Number x;
    private Number y;
    private Number z;

    public Coordinate(Number x, Number y, Number z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Number getX() {
        return x;
    }

    public Number getY() {
        return y;
    }

    public Number getZ() {
        return z;
    }
}


public class CoordinateTest {
    public static void main(String[] args) {
        Coordinate c = new Coordinate(12, 4, 33);
        Number x = c.getX();
        Number y = c.getY();
        Number z = c.getZ();
    }
}
