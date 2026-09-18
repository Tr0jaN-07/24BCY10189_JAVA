public class Book {
    private final int id;
    private final String title;
    private final String author;
    private boolean issued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
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

    public boolean isIssued() {
        return issued;
    }

    public void issueBook() {
        issued = true;
    }

    public void returnBook() {
        issued = false;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Title: " + title
                + " | Author: " + author
                + " | Status: " + (issued ? "Issued" : "Available");
    }
}
