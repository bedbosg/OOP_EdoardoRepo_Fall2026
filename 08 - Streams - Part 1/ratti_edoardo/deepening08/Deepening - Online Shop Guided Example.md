# Online Shop Guided Example
#### Object Oriented Programming - Prof. Tiziano Leidi - Bachelor in Computer Science - SUPSI

This guide explores stream concepts through one complete program. The simulation of a small online shop written with 
classic loops (`ShopClassic.java`) is converted, one method at a time, into a stream-based version (`ShopStreams.java`).

The deepening focuses on `filter()` and `map()`, exploring how to print selected elements or collect the results into a 
list using `toList()` or a set using `Collectors.toSet()`. The complete Java programs are provided separately. 
Only the relevant code snippets appear below.

---

## 1. The Program

The shop has two domain classes. All fields are private and read through getters.

| Class      | Holds               | Useful getters                           |
|------------|---------------------|------------------------------------------|
| `Customer` | name, city          | `getName()`, `getCity()`                 |
| `Order`    | id, customer, total | `getId()`, `getCustomer()`, `getTotal()` |

The getters return related objects, allowing navigation from one object to another: `order.getCustomer().getCity()` gives 
the city of the customer who placed an order. This kind of navigation can be expressed with `map()`.

The shop class keeps a list of orders (`orders`) and answers questions about them: which orders exceed an amount, which 
orders came from Lugano, which cities the shop delivers to, and which customers placed qualifying orders.

---

## 2. Step by Step

### Step 1 — Filtering and printing: `filter()` + `forEach()`

Which orders are worth more than 100 CHF? The matching orders are printed to console.

```java
// classic
for (Order order : orders) {
    if (order.getTotal() > amount) {
        System.out.println("  " + order);
    }
}
```

```java
// stream
orders.stream()
        .filter(order -> order.getTotal() > amount)
        .forEach(order -> System.out.println("  " + order));
```

The `if` becomes the predicate of `filter()`, and the printing statement becomes the terminal operation `forEach()`. 
The loop itself disappears. This is **internal iteration**: the program specifies *what* should happen, while the library 
controls how the list is traversed.

The pipeline begins with the source (`orders.stream()`), applies an intermediate operation (`filter()`), and ends with 
a terminal operation (`forEach()`). The terminal operation performs an effect: printing each selected order without 
returning a result. For this ordered list and sequential stream, the orders are printed in their original order.

### Step 2 — Building a list: `filter()` + `map()` + `toList()`

What are the IDs of the orders placed by customers from Lugano (or another given city)?

```java
// classic
List<Integer> ids = new ArrayList<>();
for (Order order : orders) {
    if (order.getCustomer().getCity().equals(city)) {
        ids.add(order.getId());
    }
}
return ids;
```

```java
// stream
return orders.stream()
        .filter(order -> order.getCustomer().getCity().equals(city))
        .map(Order::getId)          // Stream<Order> -> Stream<Integer>
        .toList();
```

The `if` again becomes a `filter()`. The value added to the list, `order.getId()`, becomes a `map()`. **`map()` changes 
the element type**: before it the stream carries `Order` objects; after it the stream carries `Integer` values. 
The manually filled `ArrayList` becomes the terminal operation `toList()`.

The order of the operations matters. Filtering comes first, while the full `Order` object is still available for checking the 
customer's city. After `map()`, only the ID remains. The method reference `Order::getId` is equivalent to `order -> order.getId()`.

> **Important:** `Stream.toList()` returns an **unmodifiable** list. The classic method returns an `ArrayList`, which can be modified. 
> The values and order are the same, but the lists have different mutability. 
> This distinction matters when callers need to modify the returned collection.

### Step 3 — Following a getter chain into a set: `Collectors.toSet()`

Which cities does the shop deliver to? Each city should appear only once.

```java
// classic
Set<String> cities = new HashSet<>();
for (Order order : orders) {
    Customer customer = order.getCustomer();
    String city = customer.getCity();
    cities.add(city);
}
return cities;
```

```java
// stream
return orders.stream()
        .map(Order::getCustomer)    // Stream<Order>    -> Stream<Customer>
        .map(Customer::getCity)     // Stream<Customer> -> Stream<String>
        .collect(Collectors.toSet());
```

Each local variable of the loop becomes one `map()` stage. The getter chain `order.getCustomer().getCity()` turns into 
two method references, one per step of the navigation. Read from top to bottom, the pipeline means: *take the orders, extract 
their customers, extract their cities, and collect the cities into a set.*

A `Set` keeps each city only once, even if several orders originate from that city. `collect(Collectors.toSet())` replaces 
the explicit creation and filling of the set. Unlike a list, the set does not guarantee the order in which its elements are printed.

### Step 4 — Filtering after mapping: `filter()` + `map()` + `filter()` + `map()`

Which customer names correspond to orders from a given city above a given amount? The result is a **list containing one 
name per matching order**. For example, the city can be Lugano and the amount 100 CHF.

```java
// classic
List<String> names = new ArrayList<>();
for (Order order : orders) {
    if (order.getTotal() > amount) {
        Customer customer = order.getCustomer();
        if (customer.getCity().equals(city)) {
            names.add(customer.getName());
        }
    }
}
return names;
```

```java
// stream
return orders.stream()
        .filter(order -> order.getTotal() > amount)
        .map(Order::getCustomer)
        .filter(customer -> customer.getCity().equals(city))
        .map(Customer::getName)
        .toList();
```

The classic loop checks the order total first. For each qualifying order, a local variable extracts its `Customer`; a 
second `if` checks the customer's city, and the name is added to the list only when both conditions hold. This sequence 
corresponds directly to the stream pipeline: the first `filter()` selects orders above the amount, `map(Order::getCustomer)` 
extracts each qualifying customer, the second `filter()` checks the city, and `map(Customer::getName)` extracts the name. 
Finally, `toList()` builds the result list.

The key difference from Step 2 is that **the two filters operate on different types**:

```java
// Stream<Order>    -- filter by total --> Stream<Order>
// Stream<Order>    -- map customer ----> Stream<Customer>
// Stream<Customer> -- filter by city --> Stream<Customer>
// Stream<Customer> -- map name -------> Stream<String>
```

The local variable `Customer customer = order.getCustomer()` in the classic loop becomes the first `map()` stage. This 
changes the stream from `Order` objects to `Customer` objects, so the city can be checked directly in the second filter. 
The order-total condition must be checked before this transformation: once the stream contains customers, the order total 
is no longer available. The position of `map()` determines which properties later operations can access.

With the supplied data, `customerNamesFromCityAbove("Lugano", 100)` returns `[Anna Rossi, Giulia Ferrari, Anna Rossi]`. 
Anna Rossi appears twice because two different orders satisfy the conditions. This is intentional: **a list retains duplicates**, 
whereas the set used in Step 3 removes them.

---

## 3. Pitfalls

**A stream is consumed once.** A stream should not be stored in a field for reuse. Calling two terminal operations on 
the same stream causes the second operation to throw `IllegalStateException: stream has already been operated upon or closed`. 
Each method creates a new stream from `orders`.

**Intermediate operations are lazy.** `filter()` and `map()` describe transformations but do not process the source until 
a terminal operation is invoked. Here, `forEach()`, `toList()`, and `collect()` trigger processing.
