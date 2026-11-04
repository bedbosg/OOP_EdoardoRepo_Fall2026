package example03.wildcards.step2_upper_bounded;

import java.util.ArrayList;
import java.util.List;

abstract class Shape {
    private int x, y;

    public abstract void draw();
}

class Circle extends Shape {
    private int radius;

    @Override
    public void draw() { /* … */ }
}

class Rectangle extends Shape {
    private int width, height;

    @Override
    public void draw() { /* … */ }
}

public class ShapesExample {
    // Upper Bounded
    private static void drawAll(List<? extends Shape> shapes) {
        for (Shape s : shapes)
            s.draw();
    }

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        List<Rectangle> rectangles = new ArrayList<>();
        List<Circle> circles = new ArrayList<>();

        drawAll(shapes);
        drawAll(rectangles);
        drawAll(circles);
    }
}
