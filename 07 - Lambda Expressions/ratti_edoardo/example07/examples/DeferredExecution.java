package example07.examples;

public class DeferredExecution {
    public static void main(String[] args) {
        System.out.println("1 - before");

        Runnable task = () -> {
            System.out.println("3 - inside the lambda");
        };

        System.out.println("2 - after defining");

        task.run();

        System.out.println("4 - after running");
    }
}
