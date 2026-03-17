package dsafundamentals;

public class ArraysExample {
    public static void main(String[] args) {

        Book[] books = new Book[5];

        books[0] = new Book(1, "Java Basics", "John");
        books[1] = new Book(2, "DSA Guide", "Alice");
        books[2] = new Book(3, "OOP Concepts", "Bob");
        books[3] = new Book(4, "Spring Boot", "David");
        books[4] = new Book(5, "Microservices", "Emma");

        // change status of one book
        books[2].borrowBook();

        // print all books
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i]);
        }
    }
}
