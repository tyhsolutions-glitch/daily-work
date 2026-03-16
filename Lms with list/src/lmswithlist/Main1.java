package lmswithlist;

import java.util.*;

public class Main1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Library1 li = new Library1();

        System.out.println("Enter Book Id:");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Book Title:");
        String title = sc.nextLine();

        System.out.println("Enter Book Price:");
        float price = sc.nextFloat();
        sc.nextLine();

        System.out.println("Enter Book Author:");
        String author = sc.nextLine();

        li.addBook(id, title, price, author);

        System.out.println("Enter Status (Available/Reserved/Regret):");
        String status = sc.nextLine();

        li.updateStatus(id, status);
        
        System.out.println("\nBooks in Library:");
        li.displayAvailableBooks();
    }
}
