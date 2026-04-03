package librarywithstreams;

import java.io.*;
import java.util.*;

public class Saving {

    public static void saveBooks(List<Book> books) {

        try {

            BufferedWriter out =
                    new BufferedWriter(new FileWriter("books.txt"));

            for (Book b : books) {
                out.write(b.getId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.isBorrowed());
                out.newLine();
            }

            out.close();

            System.out.println("Books saved successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Book> loadBooks() {

        try {

            BufferedReader in =
                    new BufferedReader(new FileReader("books.txt"));

            List<Book> books = new ArrayList<>();
            String line;

            while ((line = in.readLine()) != null) {

                String[] d = line.split(",");
                Book b = new Book(Integer.parseInt(d[0]), d[1], d[2]);

                if (Boolean.parseBoolean(d[3]))
                    b.borrowBook();

                books.add(b);
            }

            in.close();

            System.out.println("Books loaded successfully");

            return books;

        } catch (Exception e) {

            System.out.println("No saved books found");
            return null;
        }
    }
}
