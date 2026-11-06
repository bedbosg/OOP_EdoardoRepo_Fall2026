package deepening07.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        display();
        multiply();
    }

    private static void display() {
        // Consumer to display a number
        Consumer<Integer> display = a -> System.out.println("display: " + a);

        // Implement display using accept()
        display.accept(10);
    }

    private static void multiply() {
        // Consumer to multiply 2 to every integer of a list
        Consumer<List<Integer>> modify = list -> {
            for (int i = 0; i < list.size(); i++)
                list.set(i, 2 * list.get(i));
        };

        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(1);
        list.add(3);

        System.out.println("List before modify:");
        list.forEach(System.out::println);

        // Implement modify using accept()
        modify.accept(list);

        System.out.println("List after modify:");
        list.forEach(System.out::println);
    }
}
