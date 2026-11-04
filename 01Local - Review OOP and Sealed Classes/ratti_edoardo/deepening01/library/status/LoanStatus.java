package deepening01.library.status;

public sealed interface LoanStatus permits Available, OnLoan, Reserved {

    boolean isAvailable();
}
