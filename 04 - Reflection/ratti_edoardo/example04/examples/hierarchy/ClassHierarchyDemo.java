package example04.examples.hierarchy;

import java.io.Serializable;
import java.util.Arrays;

class Animal {
}

class Dog extends Animal implements Serializable {
}

public class ClassHierarchyDemo {
    public static void main(String[] args) {
        Class<?> c = Dog.class;

        System.out.println(c.getSuperclass().getName());
        // reflection.examples.hierarchy.Animal

        System.out.println(c.getSuperclass().getSuperclass().getName());
        // java.lang.Object

        System.out.println(Arrays.toString(c.getInterfaces()));
        // [interface java.io.Serializable]

        System.out.println(Object.class.getSuperclass());
        // null
    }
}