package example04.examples;

public class ComparingClassObjectsDemo {
    public static void main(String[] args) {
        Animal a = new Dog();
        Animal b = new Animal();

        // == checks exact type equality
        System.out.println(a.getClass() == Dog.class);
        // true

        System.out.println(a.getClass() == Animal.class);
        // false — a is a Dog

        // instanceof checks assignability
        System.out.println(a instanceof Animal);
        // true
    }
}