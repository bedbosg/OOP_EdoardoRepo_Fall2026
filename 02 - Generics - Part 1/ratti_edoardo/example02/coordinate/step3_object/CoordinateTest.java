package example02.coordinate.step3_object;

class Coordinate {
    private Object x;
    private Object y;
    private Object z;

    public Coordinate(Object x, Object y, Object z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Object getX() {
        return x;
    }

    public Object getY() {
        return y;
    }

    public Object getZ() {
        return z;
    }
}

public class CoordinateTest {
    public static void main(String[] args) {
        Coordinate c = new Coordinate(12, 18.5, "7"); // MIXED TYPES!!
        int x = (Integer) c.getX();
        int y = (Integer) c.getY();
        int z = (Integer) c.getZ();
    }
}
