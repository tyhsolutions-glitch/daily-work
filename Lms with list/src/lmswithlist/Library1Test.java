package lmswithlist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Library1Test {

    @BeforeEach
    void setup() {
        Library1.clearBooks(); 
    }

    @Test
    void testTitleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Book1(1, null, 500, "John");
        });
    }

    @Test
    void testTitleIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Book1(2, "", 600, "Alice");
        });
    }

    @Test
    void testTitleIsWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Book1(3, "   ", 700, "Bob");
        });
    }

    @Test
    void testSuccessfulReservation() {
        Book1 book = new Book1(1, "Learn Java", 100.1f, "Patiwesh");

        Library1.addBook(book);
        Library1.reserve("Learn Java");

        assertEquals(STATUS.BOOKED, book.getStatus());
    }

    @Test
    void testBookNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            Library1.reserve("Unknown");
        });
    }

    @Test
    void testReserveWithExtraSpacesInTitle() {
        Book1 book = new Book1(1, "Java", 100, "Author");

        Library1.addBook(book);

        assertThrows(IllegalArgumentException.class, () -> {
            Library1.reserve("  Java  "); 
        });
    }
}
