package deepening01.library.status;

public record Available() implements LoanStatus {

    @Override
    public boolean isAvailable() {
        return true;
    }
}
