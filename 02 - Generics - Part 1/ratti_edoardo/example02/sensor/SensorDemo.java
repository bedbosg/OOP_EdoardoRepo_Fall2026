package example02.sensor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

class Device<T> {
    private T id;

    public Device(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}

interface Measurable<V> {
    V getValue();
}

interface Loggable<T, V> {
    void log(T id, V value);
}

interface Storable {
    void store();
}

class Sensor<T, V> extends Device<T> implements Measurable<V>, Loggable<T, V>, Storable {
    private T id;
    private V value;
    private boolean active;

    public Sensor(T id, V value, boolean active) {
        super(id);
        this.value = value;
        this.active = active;
    }

    public T getId() {
        return id;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void log(T id, V value) {
        System.out.printf("%s: %s%n", id, value);
    }

    @Override
    public void store() {
        String data = "id: %s, value: %s%n".formatted(id, value);
        try {
            Files.writeString(Path.of("file.txt"), data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error while writing to file.");
        }
    }
}

public class SensorDemo {
    public static void main(String[] args) {
        Sensor<String, Double> s1 = new Sensor<String, Double>("s1", 4.1, true);
        String s1Id = s1.getId();
        s1.setValue(1.2345);
        Sensor<Long, Collection<Integer>> s2 = new Sensor<Long, Collection<Integer>>(1L, new ArrayList<Integer>(), true);
        Long s2Id = s2.getId();
        s2.setValue(new HashSet<Integer>());
    }
}