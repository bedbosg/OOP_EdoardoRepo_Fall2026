package classroom02;

import java.util.ArrayList;
import java.util.List;

interface Matchable <T1, T2>{
    boolean match(T1 first, T2 second);
}

interface Printable {
    Object print();
}

class NumberMatcher implements Matchable<Integer, Double> {
    @Override
    public boolean match(Integer first, Double second) {
        return first == second.intValue();
    }
}
class PersonAddressMatcher implements Matchable<Person, Address> {
    @Override
    public boolean match(Person first, Address second) {
        return first.getName().equalsIgnoreCase(second.getName()) && first.getSurname().equalsIgnoreCase(second.getSurname());
    }
}

class MultiMatcher <T1, T2> implements Matchable<List<T1>, List<T2>>, Printable {
    private final List<T1> firstList;
    private final List<T2> secondList;
    private final Matchable<T1, T2> matcher;

    public MultiMatcher(List<T1> firstList, List<T2> secondList, Matchable<T1,T2> matcher) {
        this.firstList = firstList;
        this.secondList = secondList;
        this.matcher = matcher;
    }

    public boolean match() {
        return match(firstList, secondList);
    }

    @Override
    public boolean match(List<T1> first, List<T2> second) {
        if (first.size() != second.size()) {
            return false;
        }

        for (int index = 0; index < first.size(); index++) {
            T1 firstObject = first.get(index);
            T2 secondObject = second.get(index);
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
            T1 first = firstList.get(index);
            T2 second = secondList.get(index);
            boolean matches = matcher.match(first, second);
            sb.append("- %s vs %s = %s%n".formatted(first, second, matches));
        }
        return sb;
    }
}

public class MatcherDemo {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(2);
        integers.add(3);
        List<Double> doubles = new ArrayList<>();
        doubles.add(1.2);
        doubles.add(2.8);
        doubles.add(2.1);
        doubles.add(2.9);
        NumberMatcher numberMatcher = new NumberMatcher();
        MultiMatcher<Integer, Double> numberMultiMatcher = new MultiMatcher<>(integers, doubles, numberMatcher);
        System.out.println("Number MultiMatcher");
        System.out.println("Matches: " + numberMultiMatcher.match());
        System.out.println(numberMultiMatcher.print());

        // Part B

        List<Person> people = new ArrayList<>();
        people.add(new Person("Mario", "Rossi"));
        people.add(new Person("Lukas", "Müller"));
        people.add(new Person("Chantal", "Favre"));
        people.add(new Person("Andrea", "Meier"));
        people.add(new Person("Ladina", "Gubser"));

        List<Address> addresses = new ArrayList<>();
        // 3 addresses matching a Person's name/surname
        addresses.add(new Address("Mario", "Rossi", "Via Nassa 12", "Lugano", "6900"));
        addresses.add(new Address("Lukas", "Müller", "Bahnhofstrasse 45", "Zürich", "8001"));
        addresses.add(new Address("Chantal", "Favre", "Avenue de la Gare 27", "Lausanne", "1003"));
        // 2 addresses with different name/surname (not matching any Person)
        addresses.add(new Address("Reto", "Meier", "Rue de Bourg 14", "Lausanne", "1003"));
        addresses.add(new Address("Ladina", "Huber", "Poststrasse 6", "Luzern", "6002"));

        System.out.println("Person MultiMatcher");

        PersonAddressMatcher personAddressMatcher = new PersonAddressMatcher();
        MultiMatcher<Person, Address> personAddressMultiMatcher = new MultiMatcher<>(people, addresses, personAddressMatcher);
        System.out.println("Person-Address MultiMatcher");
        System.out.println("Matches: " + personAddressMultiMatcher.match());
        System.out.println(personAddressMultiMatcher.print());

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
