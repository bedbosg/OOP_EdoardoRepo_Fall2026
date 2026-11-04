package deepening03.circus.part1;

import java.util.ArrayList;
import java.util.List;

class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void jump() {
        System.out.println(getName() + " is jumping.");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    public void meow() {
        System.out.println(getName() + " is meowing.");
    }
}

class MaineCoon extends Cat {
    public MaineCoon(String name) {
        super(name);
    }

    public void meow() {
        System.out.println("Maine Coon" + getName() + " is meowing.");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    public void bark() {
        System.out.println(getName() + " is barking.");
    }
}

// Trainers

class AnimalTrainer {
    public void act(List<? extends Animal> animalList) {
        for (Animal animal : animalList)
            animal.jump();
    }
}

class CatTrainer {
    public void act(List<? extends Cat> catList) {
        for (Cat cat : catList)
            cat.meow();
    }
}

public class Circus {
    private static void circus1() {
        AnimalTrainer animalTrainer = new AnimalTrainer();
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Dog("Bob"));
        animalList.add(new Dog("Amy"));
        animalTrainer.act(animalList);
    }

    private static void circus2() {
        AnimalTrainer animalTrainer = new AnimalTrainer();
        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat("Tim"));
        catList.add(new Cat("Lucy"));
        animalTrainer.act(catList);
    }

    public static void main(String[] args) {
        circus1();
        circus2();
    }
}