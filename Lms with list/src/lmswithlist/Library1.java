package lmswithlist;

import java.util.ArrayList;
import java.util.List;

public class Library1 {

    static List<Book1> books = new ArrayList<>();

    public static void addBook(Book1 book) {
        books.add(book);
    }

    public static void reserve(String title) {

        for (Book1 book : books) {
            if (book.getTitle().equals(title)) {
                book.setStatus(STATUS.BOOKED);
                return;
            }
        }

        throw new IllegalArgumentException("Book not found");
    }
    public static void clearBooks() {
        books.clear();
    }
}
