package example03.others;

import java.util.ArrayList;
import java.util.List;

public class LowerBoundedWildcardsExample {
    static void addIntegers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public static void main(String[] args) {
        List<Integer> li = new ArrayList<>();
        List<Number> ln = new ArrayList<>();
        List<Object> lo = new ArrayList<>();
        List<Double> ld = new ArrayList<>();

        addIntegers(li);  // Integer is Integer (or wider)
        addIntegers(ln);  // Number  is a supertype of Integer
        addIntegers(lo);  // Object  is a supertype of Integer

        // addIntegers(ld);  // Double is NOT a supertype of Integer
    }
}
