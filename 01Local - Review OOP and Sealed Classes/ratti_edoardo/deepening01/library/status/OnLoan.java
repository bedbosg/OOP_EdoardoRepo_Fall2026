package deepening01.library.status;

public record OnLoan(int remainingDays) implements LoanStatus {

    @Override
    public boolean isAvailable() {
        return false;
    }
}
