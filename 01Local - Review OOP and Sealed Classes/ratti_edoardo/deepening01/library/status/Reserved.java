package deepening01.library.status;

public record Reserved(String reservedBy) implements LoanStatus {

    @Override
    public boolean isAvailable() {
        return false;
    }
}
