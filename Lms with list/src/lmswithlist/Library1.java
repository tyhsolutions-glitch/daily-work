package lmswithlist;

import java.util.*;
import java.io.*;

public class Library1 {

    List<Book1> list = new ArrayList<>();

    void addBook(int id, String title, float price, String author) {
        try {
            Book1 b = new Book1(id, title, price, author);
            list.add(b);
            FileWriter fw = new FileWriter("LibraryData.txt", true);
            fw.write("ID:" + id + "," + title + "," + author + "," + price + "," + b.status + "\n");
            fw.close();
            System.out.println("Book added and saved to file");
        } catch(IOException e) {
            System.out.println("Error writing to file");
        }
    }

    void updateStatus(int id, String newStatus) {
        try {
            for (Book1 b : list) {
                if (b.id == id) {
                    b.status = newStatus;
                    break;
                }
            }
            FileWriter fw = new FileWriter("LibraryData.txt", false);  
            for (Book1 b : list) {
                fw.write("ID:" + b.id + "," + b.title + "," + b.author + "," + b.price + "," + b.status + "\n");
            }
            fw.close();
            System.out.println("Status updated for ID:" + id + " -> " + newStatus);
        } catch(IOException e) {
            System.out.println("Error updating status");
        }
    }

    void displayAvailableBooks() {
        try (BufferedReader br = new BufferedReader(new FileReader("LibraryData.txt"))) {
            String line;
            System.out.println("\nAvailable Books:");
            while ((line = br.readLine()) != null) {
                if (line.contains("Available")) { 
                    System.out.println(line);
                }
            }
        } catch(IOException e) {
            System.out.println("Error reading file");
        }
    }
}
