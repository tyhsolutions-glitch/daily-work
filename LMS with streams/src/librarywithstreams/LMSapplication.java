package librarywithstreams;
 
import java.util.*;
 
public class LMSapplication {
 
    public static void main(String[] args) {
 
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
 
        while (true) {
 
            System.out.println("\n====== Library Management System ======");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Show Available Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Save Books");
            System.out.println("8. Load Books");
            System.out.println("9. Exit");
 
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
 
            switch (choice) {
 
                case 1:
 
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
 
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
 
                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();
 
                    Book book = new Book(id, title, author);
 
                    library.addBook(book);
 
                    System.out.println("Book Added Successfully");
                    break;
 
                case 2:
 
                    System.out.println("All Books:");
                    library.displayBooks();
                    break;
 
                case 3:
 
                    System.out.print("Enter book title to search: ");
                    String searchTitle = sc.nextLine();
 
                    library.searchBook(searchTitle);
                    break;
 
                case 4:
 
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowId = sc.nextInt();
 
                    library.borrowBook(borrowId);
                    break;
 
                case 5:
 
                    System.out.println("Available Books:");
                    library.showAvailableBooks();
                    break;
 
                case 6:
 
                    System.out.println("Sorted Books:");
                    library.sortBooks();
                    break;
 
                case 7:
 
                    Saving.saveBooks(library.getBooks());
                    break;
 
                case 8:
 
                    List<Book> books = Saving.loadBooks();
 
                    if (books != null) {
                        library.setBooks(books);
                    }
 
                    break;
 
                case 9:
 
                    System.out.println("Exiting Program...");
                    System.exit(0);
                    break;
 
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}