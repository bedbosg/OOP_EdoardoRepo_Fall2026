package asynchronous04.exercise01;

import java.awt.image.CropImageFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

abstract class AbstractShape {
    private String shapeName;

    AbstractShape(String shapeName) {
        this.shapeName = shapeName;
    }

    private String getShapeName() {
        return shapeName;
    }
}

class Circle extends AbstractShape {
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

public class ReflectionExercise {
    public static void main(String[] args) throws Exception {
        Class<Circle> circleClass = Circle.class;
        Constructor<Circle> creator = circleClass.getConstructor(String.class, double.class);
        Circle circle = creator.newInstance("test", 5.1);
        Method calculateArea = circleClass.getMethod("calculateArea");
        double area = (double) calculateArea.invoke(circle);
        System.out.println("Area of Circle: " + area/1000);
        Class<?> parent = circleClass.getSuperclass();
        Method getShapeName = parent.getDeclaredMethod("getShapeName");
        getShapeName.setAccessible(true);
        String shapeName = (String)getShapeName.invoke(circle);
        System.out.println("shapeName: '" + shapeName + "' length: " + shapeName.length());

    }
}
