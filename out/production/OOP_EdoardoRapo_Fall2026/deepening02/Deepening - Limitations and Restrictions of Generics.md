# Limitations and Restrictions of Generics
#### Object Oriented Programming - Prof. Tiziano Leidi - Bachelor in Computer Science - SUPSI

---

Generics come with certain limitations and restrictions:
- Cannot Instantiate Generic Types with Primitive Types
- Cannot Create Instances of Type Parameters
- Cannot Declare Static Fields Whose Types are Type Parameters
- Cannot Use Casts or instanceof With Parameterized Types
- Cannot Create Arrays of Parameterized Types
- Cannot Create, Catch, or Throw Objects of Parameterized Types
- Cannot Overload a Method Where the Formal Parameter Types of Each Overload Erase to the Same Raw Type

To learn more about these limitations and gain further insights, please refer to the following resources:
https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html

Below, a couple of workarounds are presented to address these limitations.

## Generic types and arrays - workaround

In Java, creating arrays of parameterized data types is not permitted.

For instance, the following syntax is invalid:

```java
new HashMap<String, Object>[];
```

However, this limitation can be circumvented by instantiating an array of the RAW type (`HashMap[]`) and then casting it to the required parameterized type:

```java
HashMap<String, Object>[] myArray = (Map<String, Object>[]) new HashMap[10];
```

While this solution works, it does generate a warning during compilation. To address this warning, the code can be annotated accordingly with `@SupressWarnings("unchecked")` to eventually remove it:


```java
String str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam interdum malesuada urna, "
        + "vel ornare massa pellentesque id. Donec ultricies, mauris id rutrum porta, tellus sem accumsan "
        + "risus, eu porttitor urna arcu quis nulla. Etiam lacinia, lorem sit amet sagittis venenatis, "
        + "quam libero semper elit, vel pharetra lectus dolor sit amet risus. In libero ante, "
        + "feugiat eu finibus nec, fermentum ac.";
String[] words = str.replaceAll("[.,]", "").split(" ");

@SuppressWarnings("unchecked")
HashMap<String, Integer>[] maps = (HashMap<String, Integer>[]) new HashMap[5];

for (int i = 0; i < maps.length; i++)
    maps[i] = new HashMap<>();

for (int j = 0; j < words.length; j++) {
    String word = words[j];
    if (word.contains("a") && maps[0].putIfAbsent(word, 1) != null)
        maps[0].put(word, maps[0].get(word) + 1);
    else if (word.contains("e") && maps[1].putIfAbsent(word, 1) != null)
        maps[1].put(word, maps[1].get(word) + 1);
    else if (word.contains("i") && maps[2].putIfAbsent(word, 1) != null)
        maps[2].put(word, maps[2].get(word) + 1);
    else if (word.contains("o") && maps[3].putIfAbsent(word, 1) != null)
        maps[3].put(word, maps[3].get(word) + 1);
    else if (word.contains("u") && maps[4].putIfAbsent(word, 1) != null)
        maps[4].put(word, maps[4].get(word) + 1);
}

for (int i = 0; i < maps.length; i++) {
    System.out.printf("%s=", "aeiou".charAt(i));
    HashMap<String, Integer> map = maps[i];
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
        String k = entry.getKey();
        Integer v = entry.getValue();
        System.out.printf("%s: %s, ", k, v);
    }
    System.out.println();
}
```

## Instanceof and cast - workaround

In a typical scenario, type checking and class casting are accomplished using instanceof and the explicit cast operator (round brackets):

```java
public void feed(List<Animal> animals) {
   for (Animal animal : animals) {
       animal.eat();
       if (animal instanceof Cat) {
           ((Cat) animal).meow();
       }
   }
}
```

However, there exists an alternative method to perform type checks and casting, using the `isInstance()` and `cast()` methods provided by the `Class` class:


```java
public void alternativeCast() {
   Animal animal = new Cat();
  
   // ...
  
   if (Cat.class.isInstance(animal)) {
       Cat cat = Cat.class.cast(animal);
       cat.meow();
   }
}
```

This alternative approach is commonly used in generic types because the specific type is unknown. It's important to note that the `Class` instance should also be passed to the generic class, since it cannot be obtained directly from the type parameter `T`.


```java
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

public class AnimalFeederGeneric<T> {
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

        AnimalFeederGeneric<Cat> catFeeder  = new AnimalFeederGeneric<Cat>(Cat.class);
        List<Cat> fedAnimals = catFeeder.feed(animals);
        System.out.println("Fed animals: " + fedAnimals);
    }
}
```
