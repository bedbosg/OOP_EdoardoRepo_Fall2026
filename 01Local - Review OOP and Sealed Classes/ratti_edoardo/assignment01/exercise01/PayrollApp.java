package assignment01.exercise01;

import java.util.List;

sealed abstract class Employee permits RegularFullTimeEmployee, PartTimeEmployee, Contractor{
    public String name;

    public Employee(String name) {
        this.name = name;
    }
    public abstract double calculatePay();
    @Override
    public String toString() {
        return "Employee{name='" + name + "', type=" + this.getClass() + "}";
    }
}

sealed class RegularFullTimeEmployee extends Employee permits Manager{
    public double baseSalary;
    public RegularFullTimeEmployee(String name, double baseSalary) {
        super(name);
        this.baseSalary = baseSalary;
    }

    @Override
    public double calculatePay() {
        return baseSalary;
    }
}
final class PartTimeEmployee extends Employee{
    public double hourlyRate;
    public int hoursWorked;

    public PartTimeEmployee(String name, double hourlyRate, int hoursWorked) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculatePay() {
        double overtime = hoursWorked > 160
                ? (hoursWorked - 160) * hourlyRate * 0.5 : 0;
        return hourlyRate * hoursWorked + overtime;
    }
}
final class Manager extends RegularFullTimeEmployee{
    public int teamSize;
    public Manager(String name, double baseSalary, int teamSize) {
        super(name, baseSalary);
        this.teamSize = teamSize;
    }

    @Override
    public double calculatePay() {
        return baseSalary + 300 + (teamSize * 50);
    }
}
final class Contractor extends Employee{
    public double contractAmount;
    public Contractor(String name, double contractAmount) {
        super(name);
        this.contractAmount = contractAmount;
    }

    @Override
    public double calculatePay() {
        return contractAmount * 0.9; // 10% agency fee
    }
}
public class PayrollApp {

    public static double calculatePay(Employee e) {
        return e.calculatePay();
    }

    public static String describe(Employee e) {
        return switch (e){
            case Manager m -> m.name + " manages a team of " + m.teamSize;
            case RegularFullTimeEmployee m -> m.name + " is a full-time employee";
            case PartTimeEmployee m -> m.name + " works " + m.hoursWorked + "h this month";
            case Contractor m -> m.name + " is an external contractor";
        };
    }

    public static void main(String[] args) {
        Employee alice = new Manager("Alice", 4000, 6);
        Employee bob = new RegularFullTimeEmployee("Bob", 3200);
        Employee carol = new PartTimeEmployee("Carol", 25, 170);
        Employee dave = new Contractor("Dave", 5000);

        List<Employee> employees = List.of(alice, bob, carol, dave);

        for (Employee e : employees) {
            System.out.println(describe(e) + " - pay: $" + calculatePay(e));
        }
    }
}