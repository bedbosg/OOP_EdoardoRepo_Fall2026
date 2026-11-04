package example03.wildcards.step4_unbounded;

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

    // Lower Bounded
    private static void addRectangle(List<? super Shape> shapes) {
        shapes.add(new Rectangle());
    }

    // Unbounded
    private static void inspect(List<?> list) {
        for (Object elem : list)
            // Valid: only Object-level access — type is completely unknown
            System.out.print(elem + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        List<Rectangle> rectangles = new ArrayList<>();
        List<Circle> circles = new ArrayList<>();
        List<String> strings = List.of("a", "b", "c", "d", "e", "f", "g", "h");

        drawAll(shapes);
        drawAll(rectangles);
        drawAll(circles);
        // drawAll(strings); // Not Allowed! String is not compatible with Shape!

        addRectangle(shapes);
        // addRectangle(rectangles); // Not allowed!


        inspect(shapes);
        inspect(rectangles);
        inspect(circles);
        inspect(strings);
    }
}
