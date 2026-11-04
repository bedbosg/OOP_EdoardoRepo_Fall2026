package example04.examples;


public class ClassTForPrimitiveTypesDemo {
    public static void main(String[] args) {
        // Primitive type literals — valid:
        Class<Integer> ci = int.class;
        Class<Double> cd = double.class;
        Class<Boolean> cb = boolean.class;

        System.out.println(ci.getName());
        // "int"
        System.out.println(ci.isPrimitive());
        // true

        // Compile error — primitives have no getClass():
        // int x = 5;
        // x.getClass();   ← not valid; int is not an object

        // Autoboxing gives a wrapper descriptor, not primitive:
        Integer boxed = 42;
        System.out.println(boxed.getClass());
        // class java.lang.Integer   (not int)
    }
}
