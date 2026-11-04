package deepening04.additional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

class IntegerList extends ArrayList<Integer> {
}

public class GenericInheritanceExample {

    public static void main(String[] args) {
        testGenericSuperclass();
    }

    private static void testGenericSuperclass() {
        Type type = IntegerList.class.getGenericSuperclass();
        System.out.println("Generic superclass: " + type);

        ParameterizedType p = (ParameterizedType) type;
        Type actualType = p.getActualTypeArguments()[0];
        System.out.println("Actual type argument: " + actualType);
    }
}
