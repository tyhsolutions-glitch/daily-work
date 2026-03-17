package dsafundamentals;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String author;
    private boolean borrowed;
    private String status;   

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowed = false;
        this.status = "Available";   
    }

    public void borrowBook() {
        borrowed = true;
        status = "Borrowed";   
    }

    public void returnBook() {
        borrowed = false;
        status = "Available";  
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | Status: " + status;
    }
}
