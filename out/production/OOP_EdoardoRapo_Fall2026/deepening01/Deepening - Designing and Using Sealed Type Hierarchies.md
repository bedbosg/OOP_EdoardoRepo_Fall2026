# Designing and Using Sealed Type Hierarchies

#### Object Oriented Programming - Prof. Tiziano Leidi - Bachelor in Computer Science - SUPSI

---

A sealed class or interface restricts which types may directly extend or implement it. The permitted types are normally listed in a `permits` clause, and each permitted branch must then be closed, remain sealed, or be reopened.

The following declarations represent separate source files in the same package:

```java
public abstract sealed class LibraryItem permits Book, Video {
}

public final class Book extends LibraryItem {
}

public final class Video extends LibraryItem {
}
```

Only `Book`, `Video` may directly extend `LibraryItem`.

## Abstract and Sealed Describe Different Restrictions

The modifiers `abstract` and `sealed` answer different questions.

| Modifier   | Question it answers                                     |
|------------|---------------------------------------------------------|
| `abstract` | May an object of this exact class be created?           |
| `sealed`   | Which types may directly extend or implement this type? |

An abstract class cannot be instantiated, but it is open to subclasses unless another rule restricts them. For instance, if `LibraryItem` were declared only as:

```java
public abstract class LibraryItem {
    public abstract int loanDays();
}
```

Any accessible class may extend `LibraryItem` and implement `loanDays()`.

An abstract sealed class combines two independent restrictions:

```java
public abstract sealed class LibraryItem permits Book, Video, CustomLoanItem {
    // ...
}
```

This declaration means that:

* a `LibraryItem` object cannot be created directly because the class is abstract;
* only the three permitted classes may directly extend it because the class is sealed.

A sealed class does not have to be abstract. A concrete sealed class may be instantiated while still restricting its direct subclasses. However, an abstract root is common when the root represents only a general category.

## When Should a Hierarchy Be Sealed?

A hierarchy should be sealed when the program treats its possible alternatives as a controlled set.

Useful questions are:

1. Does the application define a limited set of meaningful alternatives?
2. Should the programmer who adds a subtype also review all code that processes the hierarchy?
3. Is extension by unrelated application code undesirable?

If the answer to these questions is generally yes, a sealed hierarchy is often appropriate.

A hierarchy should normally remain open when new implementations are expected. Sealing a class that may have subclasses that the original author cannot predict, would prevent legitimate extensions.

The number of subclasses that currently exist is not sufficient reason to seal a hierarchy. A hierarchy containing only two subclasses today may still represent an open concept. The relevant question is whether the set of alternatives has to be controlled.

Sometimes only part of a hierarchy should be open. The following declarations represent separate source files in the same package:

```java
public abstract sealed class LibraryItem permits Book, Video, CustomLoanItem {
    public abstract int loanDays();
}

public final class Book extends LibraryItem {
    public  int loanDays() { return 28; }
}

public final class Video extends LibraryItem {
    public  int loanDays() { return 7; }
}

public non-sealed class CustomLoanItem extends LibraryItem {
    public  int loanDays() { return 14; }
}
```

No new class may directly extend `LibraryItem`, but any accessible class may extend `CustomLoanItem`. The root therefore controls where new variants enter the hierarchy while leaving one intentional extension point.

For example, the `CustomLoanItem` can can be extended by a class in another package:

```java
package deepening01.library.special;

import deepening01.library.items.CustomLoanItem;

public class PromotionalItem extends CustomLoanItem {

    public PromotionalItem(String title) {
        super(title);
    }

    @Override
    public int loanDays() {
        return 3;
    }
}
```

## Choosing a Sealed Class or a Sealed Interface

Both classes and interfaces may be sealed, but they provide different foundations for their permitted types.

| Sealed class                                | Sealed interface                                           |
|---------------------------------------------|------------------------------------------------------------|
| May contain inherited instance fields       | Cannot provide inherited instance fields                   |
| May define constructors                     | Has no constructors                                        |
| May provide common method implementations   | May provide abstract, default, static, and private methods |
| A permitted class uses `extends`            | A permitted class uses `implements`                        |

### Example 1: A sealed class with shared state and behavior

A library item has a title and a standard operation that every concrete item must implement:

```java
package deepening01.library.items;

import deepening01.library.items.Book;
import deepening01.library.items.CustomLoanItem;
import deepening01.library.items.Video;

public abstract sealed class LibraryItem permits Book, Video, CustomLoanItem {
    private final String title;

    protected LibraryItem(String title) {
        this.title = title;
    }

    public final String title() {
        return title;
    }

    public abstract int loanDays();
}
```

The permitted subclasses reuse the state and constructor defined by the root:

```java
package deepening01.library.items;

import deepening01.library.items.LibraryItem;

public final class Book extends LibraryItem {
    private final String author;

    public Book(String title, String author) {
        super(title);
        this.author = author;
    }

    public String author() {
        return author;
    }

    @Override
    public int loanDays() {
        return 28;
    }
}
```

```java
package deepening01.library.items;

import deepening01.library.items.LibraryItem;

public final class Video extends LibraryItem {
    private final int durationMinutes;

    public Video(String title, int durationMinutes) {
        super(title);
        this.durationMinutes = durationMinutes;
    }

    public int durationMinutes() {
        return durationMinutes;
    }

    @Override
    public int loanDays() {
        return 7;
    }
}
```

The permitted subclass that reopens the hierarchy is declared `non-sealed`:

```java
package deepening01.library.items;

import deepening01.library.items.LibraryItem;

public non-sealed class CustomLoanItem extends LibraryItem {

    public CustomLoanItem(String title) {
        super(title);
    }

    @Override
    public int loanDays() {
        return 14;
    }
}
```

A sealed class is useful here because every subclass instance contains the state defined by `LibraryItem`, initializes that state through the superclass constructor, and inherits the `title()` implementation.

### Example 2: A sealed interface with record implementations

A library item may also have a loan status. The possible statuses contain different data:

```java
package deepening01.library.status;

import deepening01.library.status.Available;
import deepening01.library.status.OnLoan;
import deepening01.library.status.Reserved;

public sealed interface LoanStatus permits Available, OnLoan, Reserved {
    boolean isAvailable();
}
```

The implementations can be records:

```java
package deepening01.library.status;

import deepening01.library.status.LoanStatus;

public record Available() implements LoanStatus {

    @Override
    public boolean isAvailable() {
        return true;
    }
}
```

```java
package deepening01.library.status;

import deepening01.library.status.LoanStatus;

public record OnLoan(int remainingDays) implements LoanStatus {

    @Override
    public boolean isAvailable() {
        return false;
    }
}
```

```java
package deepening01.library.status;

import deepening01.library.status.LoanStatus;

public record Reserved(String reservedBy) implements LoanStatus {

    @Override
    public boolean isAvailable() {
        return false;
    }
}
```

A record is implicitly final, so it closes its branch without an explicit `final` modifier.

Records cannot extend application classes because every record already extends `java.lang.Record`. They can implement interfaces. Consequently, a sealed interface is the appropriate root when the permitted alternatives should be records.

The two sealed hierarchies therefore model two different parts of the same library domain:

* `LibraryItem` is a sealed class because all items share state, constructor logic, and behavior;
* `LoanStatus` is a sealed interface because the alternatives mainly represent different data variants and can be implemented naturally as records.

## Where May Permitted Types Be Declared?

The placement rule depends on whether the application uses a named Java module.

| Application organization | Required location of permitted direct subtypes                        |
|--------------------------|-----------------------------------------------------------------------|
| Named Java module        | In the same module as the sealed type; different packages are allowed |
| Unnamed module           | In the same package as the sealed type                                |

Most introductory applications do not contain a `module-info.java` file and run from the classpath. Their classes belong to the unnamed module. In such an application, a sealed root and all of its permitted direct subtypes must be in the same package.

The `LibraryItem` hierarchy follows this rule:

```text
src/main/java/
└── deepening01/
    └── single/
        └── library/
            ├── items/
            │   ├── LibraryItem.java
            │   ├── Book.java
            │   ├── Video.java
            │   └── CustomLoanItem.java
            ├── status/
            │   ├── LoanStatus.java
            │   ├── Available.java
            │   ├── OnLoan.java
            │   └── Reserved.java
            ├── special/
            │   └── PromotionalItem.java
            ├── display/
            │   └── LibraryPrinter.java
            └── app/
                └── Main.java
```

The item hierarchy source files begin with:

```java
package deepening01.library.items;
```

The status hierarchy source files begin with:

```java
package deepening01.library.status;
```

The types may be in separate files. The same-package rule does not require the hierarchy to be written in one file.

The following organization is not legal for a classpath application:

```text
deepening01.library.items.LibraryItem
deepening01.library.books.Book
deepening01.library.videos.Video
```

`Book` and `Video` would be in different packages from the sealed root, so they cannot directly extend it.

The same rule applies to the sealed interface. `Available`, `OnLoan`, and `Reserved` must be in the same package as `LoanStatus` when the application runs in the unnamed module.

### Visibility and inheritance control are separate

A public sealed type may still be used from another package:

```java
package deepening01.library.display;

import deepening01.library.items.LibraryItem;

public class LibraryPrinter {

    public void print(LibraryItem item) {
        System.out.println(item.title());
    }
}
```

This class only uses `LibraryItem`; it does not extend it. Code in another package may store references, call public methods, use `instanceof`, or apply a pattern-matching switch. Sealing restricts the introduction of direct subtypes, not ordinary use of the hierarchy.

The package restriction also applies only to direct permitted types. If a permitted class is `non-sealed`, a more distant subclass may be placed in another package, subject to normal accessibility rules.

For example, `CustomLoanItem` is `non-sealed`, so `PromotionalItem` may live in the different package `deepening01.library.special`:

```java
package deepening01.library.special;

import deepening01.library.items.CustomLoanItem;

public class PromotionalItem extends CustomLoanItem {

    public PromotionalItem(String title) {
        super(title);
    }

    @Override
    public int loanDays() {
        return 3;
    }
}
```

## Combining Dynamic Dispatch and Pattern Matching

Sealed hierarchies support ordinary method overriding. Sealing changes which types may enter the hierarchy; it does not change how overridden methods are selected.

For example, client code can ask any library item for its loan period:

```java
public static void printLoanPeriod(LibraryItem item) {
    System.out.println(item.title() + " -> " + item.loanDays() + " days");
}
```

The call to `loanDays()` uses dynamic dispatch. The implementation in `Book`, `Video`, `CustomLoanItem`, or a subclass such as `PromotionalItem` executes according to the object's runtime type.

This method is appropriately part of the hierarchy because the loan period is an intrinsic property of each library item. Every part of the application should obtain the same answer.

The same principle applies to the sealed interface. Client code can ask any loan status whether the item is available:

```java
public static void checkAvailability(LoanStatus status) {
    if (status.isAvailable()) {
        System.out.println("The item is available.");
    }
}
```

The call to `isAvailable()` uses dynamic dispatch. The implementation in `Available`, `OnLoan`, or `Reserved` executes according to the object's runtime type.

This method is appropriately part of the hierarchy because availability is an intrinsic property of each loan status. Every part of the application should obtain the same answer.

Pattern matching is useful when an operation belongs to an external context. A user interface may need a message containing the subtype-specific data:

```java
public static String createUserMessage(LoanStatus status) {
    return switch (status) {
        case Available() -> "Available";
        case OnLoan(int remainingDays) -> "On loan for " + remainingDays + " more days";
        case Reserved(String reservedBy) -> "Reserved by " + reservedBy;
    };
}
```

The switch does not need a `default` branch because the compiler can determine that the three cases cover all permitted implementations of `LoanStatus`.

The user-interface message should not necessarily become a method of `LoanStatus`. Another context may interpret the same status differently:

* a graphical interface may create a short label;
* an audit log may create a detailed entry;
* a monitoring component may record a metric.

Adding all these operations as methods would make domain types depend on unrelated presentation and infrastructure concerns. External switches allow each context to define its own processing while still receiving compiler assistance from the sealed hierarchy.

The two mechanisms are therefore complementary:

| Use dynamic dispatch when                               | Use pattern matching when                                        |
|---------------------------------------------------------|------------------------------------------------------------------|
| The behavior is an intrinsic responsibility of the type | The operation belongs to an external context                     |
| Every caller should receive the same behavior           | Different contexts interpret the variants differently            |
| The root can define a meaningful common method          | Adding the operation to every subtype would mix responsibilities |

They may be combined in the same operation. A switch can select subtype-specific data and still invoke polymorphic behavior:

```java
public static String createAuditEntry(LoanStatus status) {
    String details = switch (status) {
        case Available() -> "state=available";
        case OnLoan(int remainingDays) -> "remainingDays=" + remainingDays;
        case Reserved(String reservedBy) -> "reservedBy=" + reservedBy;
    };

    return details + ", available=" + status.isAvailable();
}
```

Here the switch performs external, data-specific formatting, while `isAvailable()` remains behavior supplied by the objects themselves.

If a new permitted loan status is introduced, two checks occur:

* a new concrete implementation must provide or inherit an implementation of `isAvailable()`;
* exhaustive switches may have to add a case for the new alternative.

The compiler consequently checks both the behavior required inside each concrete object and the external operations that depend on the complete set of alternatives.

The following `Main` class demonstrates the two parts of the same library example together:

```java
package deepening01.library.app;

import deepening01.library.display.LibraryPrinter;
import deepening01.library.items.Book;
import deepening01.library.items.CustomLoanItem;
import deepening01.library.items.LibraryItem;
import deepening01.library.items.Video;
import deepening01.library.special.PromotionalItem;
import deepening01.library.status.Available;
import deepening01.library.status.LoanStatus;
import deepening01.library.status.OnLoan;
import deepening01.library.status.Reserved;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<LibraryItem, LoanStatus> loans = Map.of(
                new Book("Dune", "Frank Herbert"), new Available(),
                new Video("Concurrency in Java", 95), new OnLoan(5),
                new CustomLoanItem("Board Game Kit"), new Reserved("Alice"),
                new PromotionalItem("Java Week Giveaway"), new Available()
        );

        LibraryPrinter printer = new LibraryPrinter();

        for (Map.Entry<LibraryItem, LoanStatus> entry : loans.entrySet()) {
            LibraryItem item = entry.getKey();
            LoanStatus status = entry.getValue();

            printer.print(item);
            printLoanPeriod(item);

            System.out.println(createUserMessage(status));
            System.out.println(createAuditEntry(status));

            checkAvailability(status);

            System.out.println();
        }
    }

    private static void printLoanPeriod(LibraryItem item) {
        System.out.println(item.title() + " -> " + item.loanDays() + " days");
    }

    private static void checkAvailability(LoanStatus status) {
        if (status.isAvailable()) {
            System.out.println("The item is available.");
        }
    }

    private static String createUserMessage(LoanStatus status) {
        return switch (status) {
            case Available() -> "Available";
            case OnLoan(int remainingDays) -> "On loan for " + remainingDays + " more days";
            case Reserved(String reservedBy) -> "Reserved by " + reservedBy;
        };
    }

    private static String createAuditEntry(LoanStatus status) {
        String details = switch (status) {
            case Available() -> "state=available";
            case OnLoan(int remainingDays) -> "remainingDays=" + remainingDays;
            case Reserved(String reservedBy) -> "reservedBy=" + reservedBy;
        };

        return details + ", available=" + status.isAvailable();
    }
}
```
