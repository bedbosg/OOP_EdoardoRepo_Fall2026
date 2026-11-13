package deepening08.shop;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShopStreams {
    static class Customer {
        private final String name;
        private final String city;

        Customer(String name, String city) {
            this.name = name;
            this.city = city;
        }

        String getName() {
            return name;
        }

        String getCity() {
            return city;
        }
    }

    static class Order {
        private final int id;
        private final Customer customer;
        private final double total;

        Order(int id, Customer customer, double total) {
            this.id = id;
            this.customer = customer;
            this.total = total;
        }

        int getId() {
            return id;
        }

        Customer getCustomer() {
            return customer;
        }

        double getTotal() {
            return total;
        }

        @Override
        public String toString() {
            return "#" + id;
        }
    }

    private final List<Order> orders;

    ShopStreams(List<Order> orders) {
        this.orders = orders;
    }

    public void printOrdersAbove(double amount) {
        orders.stream()
                .filter(order -> order.getTotal() > amount)
                .forEach(order -> System.out.println("  " + order));
    }

    public List<Integer> orderIdsFrom(String city) {
        return orders.stream()
                .filter(order -> order.getCustomer().getCity().equals(city))
                .map(Order::getId)
                .toList();
    }

    public Set<String> deliveryCities() {
        return orders.stream()
                .map(Order::getCustomer)
                .map(Customer::getCity)
                .collect(Collectors.toSet());
    }

    public List<String> customerNamesFromCityAbove(String city, double amount) {
        return orders.stream()
                .filter(order -> order.getTotal() > amount)
                .map(Order::getCustomer)
                .filter(customer -> customer.getCity().equals(city))
                .map(Customer::getName)
                .toList();
    }

    public static void main(String[] args) {
        Customer anna = new Customer("Anna Rossi", "Lugano");
        Customer marco = new Customer("Marco Bernasconi", "Bellinzona");
        Customer giulia = new Customer("Giulia Ferrari", "Lugano");
        Customer lea = new Customer("Lea Keller", "Zurich");

        List<Order> orders = List.of(
                new Order(1001, anna, 928.80),
                new Order(1002, marco, 49.00),
                new Order(1003, giulia, 154.40),
                new Order(1004, anna, 129.90),
                new Order(1005, lea, 899.00));

        ShopStreams shop = new ShopStreams(orders);
        System.out.println("1. Orders above 100 CHF:");
        shop.printOrdersAbove(100);
        System.out.println("2. Order IDs from Lugano: " + shop.orderIdsFrom("Lugano"));
        System.out.println("3. Delivery cities: " + shop.deliveryCities());
        System.out.println("4. Lugano customers with orders above 100 CHF: "
                + shop.customerNamesFromCityAbove("Lugano", 100));
    }
}
