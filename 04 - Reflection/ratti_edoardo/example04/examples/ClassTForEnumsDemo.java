package example04.examples;

enum Season {SPRING, SUMMER, AUTUMN, WINTER}

public class ClassTForEnumsDemo {
    public static void main(String[] args) {

        Class<Season> c = Season.class;

        System.out.println(c.isEnum());
        // true

        System.out.println(c.getName());
        // "Season"

        // Retrieve all declared constants
        Season[] values = c.getEnumConstants();
        for (Season v : values)
            System.out.println(v);
        // SPRING  SUMMER  AUTUMN  WINTER
    }
}
