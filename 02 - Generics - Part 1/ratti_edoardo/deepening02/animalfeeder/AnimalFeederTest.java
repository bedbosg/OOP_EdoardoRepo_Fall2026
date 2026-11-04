package deepening02.animalfeeder;

import java.util.ArrayList;
import java.util.List;

class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(this + " is eating");
    }

    public String toString() {
        return name;
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}

class AnimalFeederGeneric<T> {
    private Class<T> type;

    public AnimalFeederGeneric(Class<T> type) {
        this.type = type;
    }

    public List<T> feed(List<Animal> animals) {
        System.out.println("AnimalFeeder feeding " + type.getSimpleName());
        List<T> list = new ArrayList<T>();
        for (Animal animal : animals) {
            if (type.isInstance(animal)) {
                T objAsType = type.cast(animal);
                list.add(objAsType);

                animal.eat();
            }
        }
        return list;
    }
}

public class AnimalFeederTest {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Cat("Felix"));
        animals.add(new Dog("Rex"));
        System.out.println("Animals: " + animals);

        AnimalFeederGeneric<Cat> catFeeder = new AnimalFeederGeneric<Cat>(Cat.class);
        List<Cat> fedAnimals = catFeeder.feed(animals);
        System.out.println("Fed animals: " + fedAnimals);
    }
}