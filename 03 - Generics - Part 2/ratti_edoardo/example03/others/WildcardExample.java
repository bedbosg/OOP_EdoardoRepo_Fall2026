package example03.others;

import java.util.ArrayList;
import java.util.List;

class A {
}

class B extends A {
}

class C extends B {
}

class D extends B {
}

public class WildcardExample {
    public static void main(String[] args) {

        // Upper-bounded: accepts List<A>, List<B>, List<C>, List<D>
        List<? extends A> la;
        la = new ArrayList<B>(); // OK
        la = new ArrayList<C>();  // OK
        la = new ArrayList<D>();  // OK

        // Lower-bounded: accepts List<B>, List<A>, List<Object>
        List<? super B> lb;
        lb = new ArrayList<A>(); // OK: A is a supertype of B
        //lb = new ArrayList<C>(); // Compile error: C is a SUBTYPE of B
    }

    static void someMethod(List<? extends B> list) {

        B b = list.get(0);
        // OK: every element IS-A B

        // list.add(new B());
        // Compile error: the actual list could be List<C> cannot write
    }

    static void otherMethod(List<? super B> list) {

        list.add(new B());
        // OK: B is accepted by any List<B> or supertype

        Object o = list.get(0);
        // OK: only Object

        //B b = list.get(0);
        // Compile error: actual type unknown (could be A)
    }
}
