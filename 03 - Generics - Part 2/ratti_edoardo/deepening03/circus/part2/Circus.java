package deepening03.circus.part2;

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

// Keepers

class CatKeeper {
    public void addCats(List<? super Cat> animalList) {
        animalList.add(new Cat("Tim"));
        animalList.add(new Cat("Lucy"));
        animalList.add(new MaineCoon("Sally"));
    }
}

class DogKeeper {
    public void addDogs(List<? super Dog> animalList) {
        animalList.add(new Dog("Bob"));
        animalList.add(new Dog("Amy"));
    }
}

public class Circus {
    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<>();

        DogKeeper dogKeeper = new DogKeeper();
        dogKeeper.addDogs(animalList);

        CatKeeper catKeeper = new CatKeeper();
        catKeeper.addCats(animalList);

        // Animal trainer should be able to have each animal jump
        AnimalTrainer animalTrainer = new AnimalTrainer();
        animalTrainer.act(animalList);

        List<Cat> catList = new ArrayList<>();
        catKeeper.addCats(catList);
        CatTrainer catTrainer = new CatTrainer();
        catTrainer.act(catList);
    }
}