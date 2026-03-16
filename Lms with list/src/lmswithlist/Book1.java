package lmswithlist;

public class Book1 {

    int id;
    String title;
    float price;
    String author;
    boolean reserved;
    String status;  

    public Book1(int id, String title, float price, String author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
        this.reserved = false;
        this.status = "Available";  
    }
}
