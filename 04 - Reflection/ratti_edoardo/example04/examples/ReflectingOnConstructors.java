package example04.examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class ReflectingOnConstructors {
    public static void main(String[] args) {
        try {
            Constructor<Person> constructor = Person.class
                    .getDeclaredConstructor(String.class);
            Person person = constructor.newInstance("Bob");
            System.out.println(person.getName()); // Bob

        } catch (NoSuchMethodException e) {
            System.err.println("Method not found");
        } catch (IllegalAccessException e) {
            System.err.println("Cannot access method");
        } catch (InvocationTargetException e) {
            System.err.println("Error invoking method");
        } catch (InstantiationException e) {
            System.err.println("Error creating instance");
        }
    }
}

