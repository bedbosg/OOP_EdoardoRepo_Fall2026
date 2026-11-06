package async07.library;

import java.util.ArrayList;
import java.util.List;

class Book {
    private final String title;
    private final String author;
    private final int year;
    private final String genre;

    public Book(String title, String author, int year, String genre) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + year + ") - Genre: " + genre;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks(List<Book> bookList) {
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
}

public class LibraryExample {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", 1937, "Fantasy"));
        library.addBook(new Book("1984", "George Orwell", 1949, "Dystopian"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "Fiction"));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "Classic"));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "Fiction"));
        library.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, "Fantasy"));
        library.addBook(new Book("The Chronicles of Narnia", "C.S. Lewis", 1950, "Fantasy"));
        library.addBook(new Book("Brave New World", "Aldous Huxley", 1932, "Dystopian"));
        library.addBook(new Book("Fahrenheit 451", "Ray Bradbury", 1953, "Dystopian"));
        library.addBook(new Book("The Bell Jar", "Sylvia Plath", 1963, "Fiction"));

        // TODO: Uncomment and complete this section of code
        /*

        // Find all books that are in the "Fantasy" genre (lambda expression)
        System.out.println("Fantasy books:");
        List<Book> allFantasyBooks =
        library.displayBooks(allFantasyBooks);

        // Find all books by J.R.R. Tolkien (method reference)
        System.out.println("\nBooks by J.R.R. Tolkien:");
        List<Book> authorBooks =
        library.displayBooks(authorBooks);

        // Classify books by genre (lambda expression)
        System.out.println("\nBooks by genre:");
        Map<String, List<Book>> booksByGenre =
        for (String key : booksByGenre.keySet())
            System.out.printf(" - %s : %s%n", key, booksByGenre.get(key));

        // Classify books by author (method reference)
        System.out.println("\nBooks by author:");
        Map<String, List<Book>> booksByAuthor =
        for (String key : booksByAuthor.keySet())
            System.out.printf(" - %s : %s%n", key, booksByAuthor.get(key));

        */
    }
}