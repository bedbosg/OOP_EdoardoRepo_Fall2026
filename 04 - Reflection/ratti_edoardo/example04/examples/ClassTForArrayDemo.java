package example04.examples;

public class ClassTForArrayDemo {
    public static void main(String[] args) {
        byte[] bytes = new byte[4];
        Class<?> c = bytes.getClass();

        System.out.println(c.getName());
        // "[B"  (type encoding for byte[])

        System.out.println(c.isArray());
        // true

        System.out.println(c.getComponentType().getName());
        // "byte"
    }
}
