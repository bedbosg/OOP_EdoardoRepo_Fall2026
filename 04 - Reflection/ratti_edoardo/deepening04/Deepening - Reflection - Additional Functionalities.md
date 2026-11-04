# Reflection - Additional Functionalities
#### Object Oriented Programming - Prof. Tiziano Leidi - Bachelor in Computer Science - SUPSI

---

## Support for inheritance

To obtain the extended class, `Class<T>` offers the `getSuperclass()` method. To obtain the implemented interfaces, the `getInterfaces()` method can be used.

Due to compile-time erasure, `getSuperclass()` and `getInterfaces()` do not provide generic parameters.
To address this limitation, `getGenericSuperclass()` and `getGenericInterfaces()` are provided, which offer additional support for generic types.


```java
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;

class IntegerClass extends ArrayList<Integer> {
}

// returns the superclass
Type type = IntegerClass.class.getGenericSuperclass();
System.out.println(type);

ParameterizedType p = (ParameterizedType) type;
System.out.println(p.getActualTypeArguments()[0]);
```

## Obtaining `Class<T>` of the Declaring Class

### Obtaining `Class<T>` from Field, Method and Constructor

Each of the classes that represent contained members (Field, Method, Constructor) enables the retrieval of the declaring class's Class.

- `java.lang.reflect.Field.getDeclaringClass()`
- `java.lang.reflect.Method.getDeclaringClass()`
- `java.lang.reflect.Constructor.getDeclaringClass()`

#### Example


```java
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class TargetClass {
    private Integer theAnswer = 42;

    public TargetClass(int theAnswer) {
        this.theAnswer = theAnswer;
    }

    public Integer getTheAnswer() {
        return theAnswer;
    }
    
    private Integer getTheAnswerInternal() {
        return theAnswer;
    }
}

// calling code
Constructor theConstructor = TargetClass.class.getDeclaredConstructor(Integer.TYPE);
System.out.println("theConstructor.getDeclaringClass(): " + theConstructor.getDeclaringClass());

Field theField = TargetClass.class.getDeclaredField("theAnswer");
System.out.println("theField.getDeclaringClass()      : " + theField.getDeclaringClass());

Method theMethod = TargetClass.class.getMethod("getTheAnswer");
System.out.println("theMethod.getDeclaringClass()     : "+ theMethod.getDeclaringClass());
```

#### Example of `getDeclaringClass` for a local class


```java
public class MainClass {
    public void test() {
        class LocalClass {
            public LocalClass() {
                System.out.println("enclosing: " + LocalClass.class.getEnclosingClass()); // MainClass
                System.out.println("declaring: " + LocalClass.class.getDeclaringClass()); // null
            }
        }
        LocalClass localClass = new LocalClass();
        //...
    }
}

// calling code
MainClass test = new MainClass();
test.test();
```

#### Example of `getDeclaringClass` for an anoymous inner class


```java
public class AnonymousDeclaring {
    public AnonymousDeclaring() {
        new Object() {
            void runMe() {
                System.out.println("enclosing: " + this.getClass().getEnclosingClass()); // class AnonymousDeclaring
                System.out.println("declaring: " + this.getClass().getDeclaringClass()); // null
            }
        }.runMe();
    }
}

// calling code
AnonymousDeclaring anonymous = new AnonymousDeclaring();
```

## Additional support in java.lang.reflect

The `java.lang.reflect` package also provides:
- `java.lang.reflect.Modifier`: static methods and constants to retrieve class and member access modifiers.
- `java.lang.reflect.Parameter`: information about method parameters. The parameter name isn’t usually stored in the compiled class.
- `java.lang.reflect.Array`: static methods to dynamically create and access arrays.

### Example of extracting class methods and method parameters


```java
import java.lang.reflect.*;

class Calculate {
    int add(int a, int b) {
        return (a + b);
    }
    
    int mul(int a, int b) {
        return (b * a);
    }

    long subtract(long a, long b) {
        return (a - b);
    }
}

// calling code
Class<Calculate> cls = Calculate.class;
Method[] methods = cls.getDeclaredMethods();
for (Method method : methods) {
    System.out.print("Method: " + method.getName() + ". Parameters: ");

    Parameter[] parameters = method.getParameters();
    for (Parameter parameter : parameters) {
        System.out.print(parameter.getParameterizedType() + " ");
        System.out.print(parameter.getName() + " ");
    }
    System.out.println();
}
```
