package librarywithstreams;
 
import java.util.*;
import java.util.stream.Collectors;
 
public class Library {
 
    private List<Book> books = new ArrayList<>();
 
    public void addBook(Book book) {
        books.add(book);
    }
 
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available");
            return;
        }
 
        books.forEach(System.out::println);
    }
 
    // Search using Streams + Lambda
    public void searchBook(String title) {
 
        List<Book> result = books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
 
        if (result.isEmpty())
            System.out.println("Book not found");
        else
            result.forEach(System.out::println);
    }
 
    public void borrowBook(int id) {
 
        Optional<Book> book = books.stream()
                .filter(b -> b.getId() == id && !b.isBorrowed())
                .findFirst();
 
        if (book.isPresent()) {
            book.get().borrowBook();
            System.out.println("Book Borrowed");
        } else {
            System.out.println("Book not available");
        }
    }
 
    public void showAvailableBooks() {
 
        books.stream()
                .filter(b -> !b.isBorrowed())
                .forEach(System.out::println);
    }
 
    // Sort books using Streams
    public void sortBooks() {
 
        books.stream()
                .sorted((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()))
                .forEach(System.out::println);
    }
 
    public List<Book> getBooks() {
        return books;
    }
 
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}  
