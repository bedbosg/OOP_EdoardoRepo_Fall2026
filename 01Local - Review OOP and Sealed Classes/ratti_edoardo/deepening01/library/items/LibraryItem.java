package deepening01.library.items;

public abstract sealed class LibraryItem permits Book, Video, CustomLoanItem {
    private final String title;

    protected LibraryItem(String title) {
        this.title = title;
    }

    public final String title() {
        return title;
    }

    public abstract int loanDays();
}
