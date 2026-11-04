package example05.examples.multipleelements;

import java.util.ArrayList;
import java.util.List;

@interface Test {
    String description();
}

class AnnotationShorthandDemo {
    void processData() {

        // Shorthand: only when
        // (a) the element name is 'value', AND
        // (b) it is the only element being set

        // These two are equivalent:
        @SuppressWarnings("unchecked")
        List<String> names1 = new ArrayList();

        @SuppressWarnings(value = "unchecked")
        List<String> names2 = new ArrayList();
    }

    // Shorthand does NOT work for elements named
    // anything else — cannot omit 'description ='
    @Test(description = "my test")
    public void myTest() {
        // ...
    }
}
