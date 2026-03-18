package lmswithlist;

public class Book1 {

    private int id;
    private String title;
    private float price;
    private String author;
    private STATUS status;

    public Book1(int id, String title, float price, String author) {

        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }

        if (title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
        this.status = STATUS.AVAILABLE;
    }

    public String getTitle() {
        return title;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }
}
