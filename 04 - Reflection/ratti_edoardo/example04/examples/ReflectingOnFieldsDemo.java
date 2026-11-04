package example04.examples;

import java.lang.reflect.Field;

class Point {
    public int x, y;
    private String label;
}

public class ReflectingOnFieldsDemo {
    public static void main(String[] args) {
        Point point = new Point();
        point.x = 3;
        point.y = 4;
        try {
            Field x = Point.class.getDeclaredField("x");
            System.out.println(x.get(point)); // 3

            // private field requires setAccessible:
            Field l = Point.class.getDeclaredField("label");
            l.setAccessible(true);
            l.set(point, "origin");
            System.out.println(l.get(point)); // origin
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("Error accessing field");
        }
    }
}
