package deepening02.methods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericMethodsDemo {
    public static <T> T addAndReturn(T element, Collection<T> collection) {
        T curElement = element;
        collection.add(curElement);
        return curElement;
    }

    public static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (int i = 0; i < a.length; i++)
            c.add(a[i]);
    }

    public static void main(String[] args) {
        // Example using Strings
        String stringElement = "stringElement";
        List<String> stringList = new ArrayList<>();

        String theString = addAndReturn(stringElement, stringList);
        System.out.println("theString: " + theString + " stringList: " + stringList);
        theString = addAndReturn("<HELLO>", stringList);
        System.out.println("theString: " + theString + " stringList: " + stringList);

        // Example using Integer
        Integer integerElement = Integer.valueOf(123);
        List<Integer> integerList = new ArrayList<>();

        Integer theInteger = addAndReturn(integerElement, integerList);
        System.out.println("theInteger: " + theInteger + " integerList: " + integerList);
        theInteger = addAndReturn(123456, integerList);
        System.out.println("theInteger: " + theInteger + " integerList: " + integerList);

        Object[] ao = new Object[100];
        Collection<Object> co = new ArrayList<Object>();
        fromArrayToCollection(ao, co); // T inferred to be Object

        String[] as = new String[100];
        Collection<String> cs = new ArrayList<String>();
        fromArrayToCollection(as, cs); // T inferred to be String
        fromArrayToCollection(as, co); // T inferred to be Object

        Integer[] ai = new Integer[100];
        Float[] af = new Float[100];
        Number[] an = new Number[100];
        Collection<Number> cn = new ArrayList<Number>();
        fromArrayToCollection(ai, cn); // T inferred to be Number
        fromArrayToCollection(af, cn); // T inferred to be Number
        fromArrayToCollection(an, cn); // T inferred to be Number
        fromArrayToCollection(an, co); // T inferred to be Object

        //fromArrayToCollection(an, cs); // compile-time error
    }
}
