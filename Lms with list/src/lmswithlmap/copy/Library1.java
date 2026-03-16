package lmswithlmap.copy;

import java.util.Scanner;

public class Library1 {
    public static void main(String[] args) {
        Library1 library = new Library1();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add a book");
            System.out.println("2. Remove a book");
            System.out.println("3. Reserve a book");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. List of books");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    sc.nextLine(); // consume newline
                    library.addBook(new Book1(title, author, price));
                }
          case 2 -> {
          System.out.print("Enter title to remove: ");
          String title = sc.nextLine();
          library.removeBook(title);
                }
          case 3 -> {
          System.out.print("Enter title to reserve: ");
          String title = sc.nextLine();
          library.removeBook(title);
                }
          case 4 -> {
          System.out.print("Enter title to borrow: ");
          String title = sc.nextLine();
          library.borrowBook(title);
                }
          case 5 -> {
          System.out.print("Enter title to return: ");
          String title = sc.nextLine();
          library.returnBook(title);
                }
          case 6 -> library.listBooks();
          case 0 -> System.out.println("Exiting Library");
          default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }

	private void borrowBook(String title) {
		// TODO Auto-generated method stub
		
	}

	void removeBook(String title) {
		// TODO Auto-generated method stub
		
	}

	void addBook(Book1 book1) {
		// TODO Auto-generated method stub
		
	}

	private void returnBook(String title) {
		// TODO Auto-generated method stub
		
	}

	public Object listBooks() {
		// TODO Auto-generated method stub
		return null;
	}
}