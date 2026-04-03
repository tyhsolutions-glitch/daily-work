package lmswithlmap.copy;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
    Library1 library = new Library1();
    Scanner sc = new Scanner(System.in);
    int choice;

    do {
        System.out.println("\nLibrary Menu:");
        System.out.println("1. Add a book  ");
        System.out.println("2. Remove a book");
        System.out.println("3. Reserve book");
        System.out.println("4. List of books");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
        choice = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (choice) {
        case 1 -> {
        System.out.print("Enter title:");
        String title = sc.nextLine();
        System.out.print("Enter author:");
        String author = sc.nextLine();
        System.out.print("Enter price:");
        double price = sc.nextDouble();
        sc.nextLine(); // consume newline
        library.addBook(new Book1(title, author, price));
                }
        case 2 -> {
        System.out.print("Enter title to remove:");
        String title = sc.nextLine();
        library.removeBook(title);
                }
        case 3 -> {
        System.out.print("Enter title to reserve:");
        String title = sc.nextLine();
        library.removeBook(title);
                }
        case 4 -> library.listBooks();
        case 0 -> System.out.println("Exiting Library");
        default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}