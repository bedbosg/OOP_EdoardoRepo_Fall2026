# Generic Methods
#### Object Oriented Programming - Prof. Tiziano Leidi - Bachelor in Computer Science - SUPSI

---

In Java, generics are commonly employed to define generic data types such as classes and interfaces. However, generics also allows for the parameterization of methods.

Generic methods are often utilized to establish relationships between parameter types and the method's return type. These generics 
can be applied to static methods, instance methods, and constructors.

To define a generic method, one must specify a list of formal type parameters enclosed within angle brackets `<` and `>`, positioned before the method's return type. These formal type parameters can then be employed as types for method parameters, the method's return type, as well as within the method's body.


```java
public static <T> T addAndReturn(T element, Collection<T> collection) {
    T curElement = element;
    collection.add(curElement);
    return curElement;
}

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
```

Generic methods are invoked in a similar way to non-generic methods. Typically, the actual type argument is not explicitly specified, and the compiler infers it from the context of use. However, there is an option to explicitly provide the type if necessary:

```java
String theString = TestClass.<String>addAndReturn(stringElement, stringList);
```

When the type is inferred, the compiler selects the most specific type common to all the provided actual arguments.


```java
public static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
    for (int i = 0; i < a.length; i++)
        c.add(a[i]);
}

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

fromArrayToCollection(an, cs); // compile-time error
```
