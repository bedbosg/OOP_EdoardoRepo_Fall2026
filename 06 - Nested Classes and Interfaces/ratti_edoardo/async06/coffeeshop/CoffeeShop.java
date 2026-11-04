package async06.coffeeshop;

import java.util.ArrayList;
import java.util.List;

interface DiscountPolicy {
    String label();
    double apply(double amount);
}

interface OrderListener {
    void onReady(Order order);
}

class Drink {
    final String name;
    final double price;

    Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Order {
    final CoffeeShop shop;
    final int number;
    final String customer;
    final Drink drink;
    final int quantity;

    Order(CoffeeShop shop, String customer, Drink drink, int quantity) {
        this.shop = shop;
        this.number = shop.nextOrderNumber();
        this.customer = customer;
        this.drink = drink;
        this.quantity = quantity;
    }

    double subtotal() { return drink.price * quantity; }
}

class StudentDiscount implements DiscountPolicy {
    public String label() { return "Student (-10%)"; }
    public double apply(double amount) { return amount * 0.90; }
}

class NoDiscount implements DiscountPolicy {
    public String label() { return "No discount"; }
    public double apply(double amount) { return amount; }
}

class ReceiptPrinter {
    private final CoffeeShop shop;

    ReceiptPrinter(CoffeeShop shop) { this.shop = shop; }

    void print(Order order, DiscountPolicy discount) {
        System.out.printf("%s | order #%d for %s%n", shop.getName(), order.number, order.customer);
        System.out.printf("%d x %s: %.2f %s%n", order.quantity, order.drink.name,
                discount.apply(order.subtotal()), shop.getCurrency());
        System.out.println("Discount: " + discount.label());
    }
}

class BaristaDisplay implements OrderListener {
    public void onReady(Order order) {
        System.out.printf("[%s barista] Order #%d ready%n", order.shop.getName(), order.number);
    }
}

class PhoneNotifier implements OrderListener {
    public void onReady(Order order) {
        System.out.println("[Phone] " + order.customer + ", your drink is ready!");
    }
}

public class CoffeeShop {
    private final String name, currency;
    private int orderCounter = 0;
    private final List<OrderListener> listeners = new ArrayList<>();
    private final ReceiptPrinter printer = new ReceiptPrinter(this);

    CoffeeShop(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    String getName() { return name; }
    String getCurrency() { return currency; }
    int nextOrderNumber() { return ++orderCounter; }

    void addListener(OrderListener listener) { listeners.add(listener); }

    void checkout(Order order, DiscountPolicy discount) {
        printer.print(order, discount);
        for (OrderListener listener : listeners) listener.onReady(order);
        System.out.println();
    }

    public static void main(String[] args) {
        CoffeeShop town = new CoffeeShop("Town Café", "CHF");
        CoffeeShop airport = new CoffeeShop("Airport Café", "EUR");

        town.addListener(new BaristaDisplay());
        town.addListener(new PhoneNotifier());
        airport.addListener(new BaristaDisplay());

        Drink latte = new Drink("Latte", 4.50);
        Drink espresso = new Drink("Espresso", 3.00);
        DiscountPolicy student = new StudentDiscount();

        town.checkout(new Order(town, "Anna", latte, 1), student);
        airport.checkout(new Order(airport, "Leo", espresso, 2), new NoDiscount());
        town.checkout(new Order(town, "Mia", latte, 1), student);
    }
}
