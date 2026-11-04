package deepening01.library.items;

public final class Video extends LibraryItem {
    private final int durationMinutes;

    public Video(String title, int durationMinutes) {
        super(title);
        this.durationMinutes = durationMinutes;
    }

    public int durationMinutes() {
        return durationMinutes;
    }

    @Override
    public int loanDays() {
        return 7;
    }
}
