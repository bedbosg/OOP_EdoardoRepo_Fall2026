package deepening08.shop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShopClassic {
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

    ShopClassic(List<Order> orders) {
        this.orders = orders;
    }

    public void printOrdersAbove(double amount) {
        for (Order order : orders) {
            if (order.getTotal() > amount) {
                System.out.println("  " + order);
            }
        }
    }

    public List<Integer> orderIdsFrom(String city) {
        List<Integer> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer().getCity().equals(city)) {
                result.add(order.getId());
            }
        }
        return result;
    }

    public Set<String> deliveryCities() {
        Set<String> result = new HashSet<>();
        for (Order order : orders) {
            Customer customer = order.getCustomer();
            String city = customer.getCity();
            result.add(city);
        }
        return result;
    }

    public List<String> customerNamesFromCityAbove(String city, double amount) {
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

        ShopClassic shop = new ShopClassic(orders);
        System.out.println("1. Orders above 100 CHF:");
        shop.printOrdersAbove(100);
        System.out.println("2. Order IDs from Lugano: " + shop.orderIdsFrom("Lugano"));
        System.out.println("3. Delivery cities: " + shop.deliveryCities());
        System.out.println("4. Lugano customers with orders above 100 CHF: "
                + shop.customerNamesFromCityAbove("Lugano", 100));
    }
}
