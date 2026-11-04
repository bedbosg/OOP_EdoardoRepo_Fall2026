package deepening01.library.items;

public final class Book extends LibraryItem {
    private final String author;

    public Book(String title, String author) {
        super(title);
        this.author = author;
    }

    public String author() {
        return author;
    }

    @Override
    public int loanDays() {
        return 28;
    }
}
