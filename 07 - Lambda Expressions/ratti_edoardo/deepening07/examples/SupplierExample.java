package deepening07.examples;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<LocalDateTime> dateTimeSupplier = () -> LocalDateTime.now();
        LocalDateTime time1 = dateTimeSupplier.get();
        System.out.println(time1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Supplier<String> stringDateSupplier = () -> dtf.format(LocalDateTime.now());
        String time2 = stringDateSupplier.get();
        System.out.println(time2);
    }
}
