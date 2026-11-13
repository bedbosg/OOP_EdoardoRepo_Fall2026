package example08.examples;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

class EmployeeExample {
    record Employee(String name, int salary) {
    }

    static Map<Integer, Employee> db = Map.of(
            1, new Employee("Ana", 80000),
            2, new Employee("Ben", 125000),
            3, new Employee("Cia", 140000));

    static Employee findById(int id) {
        return db.get(id);
    }

    public static void main(String[] args) {
        Integer[] ids = {1, 2, 3};
        Optional<Employee> found = Stream.of(ids)
                .map(EmployeeExample::findById)
                .filter(Objects::nonNull)
                .filter(e -> e.salary() > 100000)
                .findFirst();

        found.ifPresentOrElse(
                e -> System.out.println(e.name()),
                () -> System.out.println("none found"));
    }
}
