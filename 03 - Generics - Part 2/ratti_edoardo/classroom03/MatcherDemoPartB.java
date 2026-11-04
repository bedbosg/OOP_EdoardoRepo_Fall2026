package classroom03;

import java.util.ArrayList;
import java.util.List;

interface MatchableB<F, S> {
    boolean match(F first, S second);
}

interface PrintableB<T> {
    T print();
}

class MultiMatcherB<F, S> implements MatchableB<List<F>, List<S>>, PrintableB<StringBuffer> {
    private final List<F> firstList;
    private final List<S> secondList;
    private final MatchableB<F, S> matcher;

    public MultiMatcherB(List<F> firstList, List<S> secondList, MatchableB<F, S> matcher) {
        this.firstList = firstList;
        this.secondList = secondList;
        this.matcher = matcher;
    }

    public boolean match() {
        return match(firstList, secondList);
    }

    @Override
    public boolean match(List<F> firstList, List<S> secondList) {
        if (firstList.size() != secondList.size()) {
            return false;
        }

        for (int index = 0; index < firstList.size(); index++) {
            F firstObject = firstList.get(index);
            S secondObject = secondList.get(index);
            if (!matcher.match(firstObject, secondObject)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public StringBuffer print() {
        StringBuffer sb = new StringBuffer();
        sb.append("MultiMatcher comparison:");
        sb.append(System.lineSeparator());

        if (firstList.size() != secondList.size()) {
            sb.append("- List size mismatch: %d vs %d%n".formatted(firstList.size(), secondList.size()));
            return sb;
        }

        for (int index = 0; index < firstList.size(); index++) {
            F first = firstList.get(index);
            S second = secondList.get(index);
            boolean matches = matcher.match(first, second);
            sb.append("- %s vs %s = %s%n".formatted(first, second, matches));
        }
        return sb;
    }
}

class PersonAddressMatcher<P extends Person, A extends Address> implements MatchableB<P, A> {
    @Override
    public boolean match(Person first, Address second) {
        return (first.getName().equalsIgnoreCase(second.getName()) &&
                first.getSurname().equalsIgnoreCase(second.getSurname()));
    }
}
class PersonMatcher<P1 extends Person,P2 extends Person> implements MatchableB<P1, P2> {
    @Override
    public boolean match(P1 first, P2 second) {
        return (first.getName().equalsIgnoreCase(second.getName()) &&
                first.getSurname().equalsIgnoreCase(second.getSurname()));
    }
}

public class MatcherDemoPartB {
    private static void runPersonAddressMatcher(List<? extends Person> persons, List<? extends Address> addresses) {
        MultiMatcherB<? extends Person, ? extends Address> personMultiMatcher = new MultiMatcherB<>(persons, addresses, new PersonAddressMatcher());
        System.out.println("Person MultiMatcher(Person, Address)");
        System.out.println("Matches: " + personMultiMatcher.match());
        System.out.println(personMultiMatcher.print());
    }
    private static void runPersonMatcher(List<? extends Person> people1, List<? extends Person> people2) {
        MultiMatcherB<? extends Person, ? extends Person> personMultiMatcher = new MultiMatcherB<>(people1, people2, new PersonMatcher<>());
        System.out.println("Person MultiMatcher(? extends Person, ? extends Person)");
        System.out.println("Matches: " + personMultiMatcher.match());
        System.out.println(personMultiMatcher.print());
    }


    public static void main(String[] args) {
        List<BachelorStudent> bachelorStudents = List.of(
                new BachelorStudent("Mario", "Rossi", 2),
                new BachelorStudent("Lukas", "Müller", 4),
                new BachelorStudent("Andrea", "Meier", 6));

        List<MasterStudent> masterStudents = List.of(
                new MasterStudent("Chantal", "Favre", 1),
                new MasterStudent("Ladina", "Gubser", 3),
                new MasterStudent("Nina", "Keller", 2));

        List<Student> students = new ArrayList<>();
        students.addAll(bachelorStudents);
        students.addAll(masterStudents);

        List<Teacher> teachers = List.of(
                new Teacher("Anna", "Keller"),
                new Teacher("Marco", "Bianchi"),
                new Teacher("Sophie", "Dubois"));

        List<Person> persons = new ArrayList<>();
        persons.addAll(students);
        persons.addAll(teachers);

        List<Address> addresses = List.of(
                new Address("Mario", "Rossi", "Via Nassa 12", "Lugano", "6900"),
                new Address("Lukas", "Müller", "Bahnhofstrasse 45", "Zürich", "8001"),
                new Address("Chantal", "Favre", "Avenue de la Gare 27", "Lausanne", "1003"),
                new Address("Anna", "Keller", "Seestrasse 8", "Zürich", "8002"),
                new Address("Marco", "Bianchi", "Via Cantonale 21", "Bellinzona", "6500"),
                new Address("Reto", "Huber", "Poststrasse 6", "Luzern", "6002"));

        runPersonAddressMatcher(persons, addresses);

        runPersonMatcher(bachelorStudents, masterStudents);
        runPersonMatcher(teachers, masterStudents);
        runPersonMatcher(bachelorStudents, teachers);
    }
}

// PART B
class Person {
    private final String name;
    private final String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "[%s %s]".formatted(name, surname);
    }
}

class Student extends Person {
    private final int semester;

    public Student(String name, String surname, int semester) {
        super(name, surname);
        this.semester = semester;
    }

    public int getSemester() {
        return semester;
    }

    public boolean sameSemester(Student other) {
        return semester == other.semester;
    }
}

class BachelorStudent extends Student {
    public BachelorStudent(String name, String surname, int semester) {
        super(name, surname, semester);
    }
}

class MasterStudent extends Student {
    public MasterStudent(String name, String surname, int semester) {
        super(name, surname, semester);
    }
}

class Teacher extends Person {
    public Teacher(String name, String surname) {
        super(name, surname);
    }
}

class Address {
    private final String name;
    private final String surname;
    private final String street;
    private final String city;
    private final String zipCode;

    public Address(String name, String surname, String street, String city, String zipCode) {
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "[%s %s @ %s - %s, %s]".formatted(name, surname, zipCode, street, city);
    }
}
