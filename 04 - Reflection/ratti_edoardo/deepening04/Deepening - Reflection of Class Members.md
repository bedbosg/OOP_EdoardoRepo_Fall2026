# Reflection of Class Members
#### Object Oriented Programming - Prof. Tiziano Leidi - Bachelor in Computer Science - SUPSI

---

## Fields, methods and constructors

The java.lang.reflect package offers classes that provide functionality to retrieve information about the class members such as fields, methods and constructors:

- `java.lang.reflect.Field`
- `java.lang.reflect.Method`
- `java.lang.reflect.Constructor`

## Accessing fields

`java.lang.reflect.Field` represents a class field and facilitates the reading and modification of field values. It's feasible to access even private fields, by utilizing the `setAccessible` method before invoking the get/set operation: `field.setAccessible(true);`.

- To retrieve the value of the field for the designated object:

  `Object get(Object obj);`
  
- To alter the value of the field for the designated object:

  `void set(Object obj, Object value);`
  
- To obtain the Class object of the declared type:

  `Class<?> getType();`

### Obtaining a Field

`Class<T>` provides methods that:
- returns an array of public fields, including inherited fields: `myClass.getFields();`
- returns an array of public, protected, default and private fields declared by the class, excluding inherited fields: `myClass.getDeclaredFields();`
- returns a public field with the specified name, including a field that may reside in inherited classes: `myClass.getField(String name);`
- returns the specified field from the current class: `myClass.getDeclaredField(String name);`


### getField() vs getDeclaredField()

- `getField()` offers access to all the publicly available fields across the complete class hierarchy.
- `getDeclaredField()` offers access to all fields, regardless of their accessibility, limited to the current class only.

#### Example of using `getField()` with a public field


```java
import java.lang.reflect.Field;

public class TargetClass {
    public Integer theAnswer = 42;

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

TargetClass targetObject = new TargetClass(1);
System.out.println("the Answer is " + targetObject.getTheAnswer());
try {
    Field theField = TargetClass.class.getField("theAnswer");
    System.out.println("theField: " + theField);
    System.out.println("theField.get(targetObject): " + theField.get(targetObject));
    theField.set(targetObject, -273);
    System.out.println("Now the Answer is " + targetObject.getTheAnswer());
} catch (NoSuchFieldException | IllegalAccessException e) {
    e.printStackTrace();
}
```

#### Example of using `getField()` with a private field (throws an exception)

The following example doesn't execute due to the private declaration of the field `theAnswer`.


```java
import java.lang.reflect.Field;

public class TargetClass {
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

TargetClass targetObject = new TargetClass(1);
System.out.println("the Answer is " + targetObject.getTheAnswer());
try {
    Field theField = TargetClass.class.getField("theAnswer");
    System.out.println("theField: " + theField);
    System.out.println("theField.get(targetObject): " + theField.get(targetObject));
    theField.set(targetObject, -273);
    System.out.println("Now the Answer is " + targetObject.getTheAnswer());
} catch (NoSuchFieldException | IllegalAccessException e) {
    e.printStackTrace();
}
```

#### Example of using `getDeclaredField` with a private fields

Utilizing the `getDeclaredField()` method allows retrieval of the private field `theAnswer`. However, attempting to access the field will result in failure due to its private declaration.


```java
import java.lang.reflect.Field;

public class TargetClass {
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

TargetClass targetObject = new TargetClass(1);
System.out.println("the Answer is " + targetObject.getTheAnswer());
try {
    Field theField = TargetClass.class.getDeclaredField("theAnswer");
    System.out.println("theField: " + theField);
    System.out.println("theField.get(targetObject): " + theField.get(targetObject));
    theField.set(targetObject, -273);
    System.out.println("Now the Answer is " + targetObject.getTheAnswer());
} catch (NoSuchFieldException | IllegalAccessException e) {
    e.printStackTrace();
}
```

#### Example of accessing a private field

Through the employment of the `getDeclaredField()` method to obtain the private field and subsequently enabling access to the field by setting `setAccessible(true)`, it becomes feasible to read/modify its value.


```java
import java.lang.reflect.Field;

public class TargetClass {
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

TargetClass targetObject = new TargetClass(1);
System.out.println("the Answer is " + targetObject.getTheAnswer());
try {
    Field theField = TargetClass.class.getDeclaredField("theAnswer");
    System.out.println("theField: " + theField);

    theField.setAccessible(true);
    System.out.println("theField.get(targetObject): " + theField.get(targetObject));
    theField.set(targetObject, -273);
    System.out.println("Now the Answer is " + targetObject.getTheAnswer());
} catch (NoSuchFieldException | IllegalAccessException e) {
    e.printStackTrace();
}
```

## Invoking methods

`java.lang.reflect.Method` represents a class method. It offers insights into the method and permits scrutiny of its arguments and return types. Moreover, it enables the invocation of the method:

`Object invoke(Object obj, Object... args);`

- For private methods, invocation is only feasible once the `setAccessible(true)` method has been invoked on the `Method` object.
- In the scenario of a static method, the initial argument of the `invoke()` method needs to be set as `null`.
    
### Obtaining a Method

`Class<T>` provides methods that:
- returns an array of public methods, including inherited ones:
`myClass.getMethods();`
- returns an array of methods declared within the current class, excluding inherited methods:
`myClass.getDeclaredMethods();`
- returns a public method, even if inherited from other classes:
`myClass.getMethod(String name, Class<?>... paramTypes);`
- returns the specified method from the current class:
`myClass.getDeclaredMethod(String name, Class<?>... paramTypes);`

#### Example of invoking a method


```java
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

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

TargetClass targetObject = new TargetClass(1);
try {
    Method publicMethod = TargetClass.class.getMethod("getTheAnswer");
    System.out.println("Result of the call " + publicMethod.invoke(targetObject));
} catch (NoSuchMethodException e) {
    e.printStackTrace();
} catch (IllegalAccessException e) {
    e.printStackTrace();
} catch (InvocationTargetException e) {
    e.printStackTrace();
}
```

#### Example of invoking a private method

Enabling the method for invocation can be achieved by employing `setAccessible(true);`.


```java
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

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

TargetClass targetObject = new TargetClass(1);
try {
    Method privateMethod = TargetClass.class.getDeclaredMethod("getTheAnswerInternal");
    privateMethod.setAccessible(true);
    System.out.println("Result of the call " + privateMethod.invoke(targetObject));
} catch (NoSuchMethodException e) {
    e.printStackTrace();
} catch (IllegalAccessException e) {
    e.printStackTrace();
} catch (InvocationTargetException e) {
    e.printStackTrace();
}
```

## Invoking constructors

Starting from Java 9, the `Class.newInstance()` method has been deprecated.

In its place, the following Class method should be employed to invoke a constructor: `Class.getDeclaredConstructor().newInstance()`.

- The `Class.newInstance()` method is limited to invoking zero-argument constructors. In contrast, `Constructor.newInstance()` permits the invocation of constructors with any number of parameters.
- When using `Class.newInstance()`, any exception thrown by the constructor, whether checked or unchecked, is directly propagated. On the other hand, `Constructor.newInstance()` consistently wraps the thrown exception with an `InvocationTargetException`.
- While `Class.newInstance()` mandates the constructor's visibility, `Constructor.newInstance()` also facilitates the invocation of private constructors under specific conditions.

|   | `Class.newInstance()` - **_Deprecated_** | `Constructor.newInstance()` |
|---|---|---|
| **Parameters**  | Is restricted to invoking only zero-argument constructors.  |  Enables the invocation of constructors with any number of parameters. |
| **Exceptions**  | Any exception thrown by the constructor, regardless of its type, is propagated as is.  |  Consistently wraps the thrown exception with an `InvocationTargetException`. |
| **Visibility** | Necessitates that the constructor be visible. | Also grants the capability to invoke private constructors in specific scenarios. |

### Example of `Class.newInstance()`


```java
Class<?> myClass = Class.forName("java.util.Random");
// Deprecated!!
// final Object instance = myClass.newInstance();
final Object instance = myClass.getDeclaredConstructor().newInstance();

System.out.println("instance=" + instance);
System.out.println("myClass.getMethod(\"nextInt\").invoke(instance)=" + myClass.getMethod("nextInt").invoke(instance));
```

### Example of `Constructor.newInstance()`


```java
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

// REMARK: not working in JavaConsole used by notebook! Therefore using 'TargetClass.class.getName()' to obtain class name.
// Class<?> myClass = Class.forName("TargetClass");

String targetClassName = TargetClass.class.getName();
System.out.println("TargetClass name: " + targetClassName);
Class<?> myClass = Class.forName(targetClassName);

Constructor constructor = myClass.getDeclaredConstructor(Integer.TYPE);
Object instance = constructor.newInstance(1);
System.out.println("Reflection: the answer is " + myClass.getMethod("getTheAnswer").invoke(instance));
```
