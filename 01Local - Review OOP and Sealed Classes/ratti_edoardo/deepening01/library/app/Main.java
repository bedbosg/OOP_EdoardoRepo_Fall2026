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
