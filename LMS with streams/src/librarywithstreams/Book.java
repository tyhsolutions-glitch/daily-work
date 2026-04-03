package librarywithstreams;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String author;
    private boolean borrowed;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void borrowBook() {
        borrowed = true;
    }

    public void returnBook() {
        borrowed = false;
    }

    public String toString() {
        return id + " | " + title + " | " + author + " | Borrowed: " + borrowed;
    }
}
