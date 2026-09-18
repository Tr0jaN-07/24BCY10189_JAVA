import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
    private final int id;
    private final String name;
    private final List<Integer> issuedBooks;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getIssuedBooks() {
        return Collections.unmodifiableList(issuedBooks);
    }

    public boolean hasBook(int bookId) {
        return issuedBooks.contains(bookId);
    }

    public boolean canIssueMoreBooks() {
        return issuedBooks.size() < 3;
    }

    public void issueBook(int bookId) {
        if (!issuedBooks.contains(bookId)) {
            issuedBooks.add(bookId);
        }
    }

    public void returnBook(int bookId) {
        issuedBooks.remove(Integer.valueOf(bookId));
    }

    @Override
    public String toString() {
        return "Member ID: " + id
                + " | Name: " + name
                + " | Books Issued: " + issuedBooks.size()
                + " | Book IDs: " + issuedBooks;
    }
}
