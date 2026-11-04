package example02.coordinate.step1_hardcoded;

class Coordinate {
    private float x;
    private float y;
    private float z;

    public Coordinate(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}


public class CoordinateTest {
    public static void main(String[] args) {
        Coordinate c = new Coordinate(12, 4, 33);
        float x = c.getX();
        float y = c.getY();
        float z = c.getZ();
    }
}
