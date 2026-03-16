package lmswithlmap.copy;

public class Book1 {
    private String title;
    private String author;
    private double price;
    private boolean isReserved;
    private boolean isBorrowed;

    public Book1(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isReserved = false;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }
    public void reserve() {
        if (!isReserved) {
            isReserved = true;
            System.out.println(title + " has been reserved.");
        } else {
            System.out.println(title + " is already reserved.");
        }
    }
    public void cancelReservation() {
        if (isReserved) {
            isReserved = false;
            System.out.println(title + " reservation cancelled.");
        }
    }
     public void borrowBook() {
        if (isBorrowed) {
        System.out.println(title + " is already borrowed.");
        } else if (isReserved) {
        System.out.println(title + " is reserved and cannot be borrowed.");
        } else {
        isBorrowed = true;
        System.out.println(title + " has been borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println(title + " has been returned.");
        } else {
            System.out.println(title + " was not borrowed.");
        }
    }
    @Override
    public String toString() {
        return "Book: " + title + ", Author: " + author + ", Price: $" + price
                + ", Reserved: " + isReserved + ", Borrowed: " + isBorrowed;
    }
}