package Library;

enum STATUS {
    AVAILABLE, BORROWED
}

class Book {
    String title;
    STATUS status;

    Book(String title, STATUS status) {
        this.title = title;
        this.status = status;
    }

    String statusString() {
        return status == STATUS.AVAILABLE ? "Available" : "Borrowed";
    }
}