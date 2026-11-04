package example03.multiplebounds;

// interface bound
interface ShapeWithColor {
    int getColor();
}

// class bound
abstract class GeometricFigure {
    private int x = 0, y = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

// generic class — T must extend GeometricFigure and implement ShapeWithColor
class Box<T extends GeometricFigure & ShapeWithColor> {
    private T t;

    public void setT(T t) {
        this.t = t;
    }

    public String toString() {
        return "Figure at (" + t.getX() + "," + t.getY() + ") color: " + t.getColor();
    }
}

// A class that satisfies both bounds
class ColoredCircle extends GeometricFigure implements ShapeWithColor {
    private int color;

    ColoredCircle(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}

public class BoxExample {
    public static void main(String[] args) {
        Box<ColoredCircle> box = new Box<>();
        box.setT(new ColoredCircle(0xFF0000));
        System.out.println(box);
        // Figure at (0,0) color: 16711680

        // Box<String> invalid = new Box<>();
        // Compile error: String does not extend GeometricFigure and does not implement ShapeWithColor.
    }
}
