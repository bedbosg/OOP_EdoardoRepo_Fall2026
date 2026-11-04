package example04.examples;

class Animal {
}

class Dog extends Animal {
}

public class PolymorphismGetClassDemo {

    public static void main(String[] args) {
        Animal a = new Dog();

        Class<? extends Animal> aClass = a.getClass();
        System.out.println(aClass);
        // class reflection.examples.Dog

        System.out.println(aClass.getName());
        // reflection.examples.Dog

        System.out.println(aClass.getSimpleName());
        // Dog
    }
}