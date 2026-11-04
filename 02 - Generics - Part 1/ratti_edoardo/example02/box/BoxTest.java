package example02.box;

class Box<T> {
    private T value;

    public T get() {
        return value;
    }

    public void set(T v) {
        this.value = v;
    }
}

public class BoxTest {
    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.set("Hello");
        String content = box.get();
    }
}