import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Library {
    private final List<Book> books;
    private final List<Member> members;

    public Library() {
        books = FileManager.loadBooks();
        members = FileManager.loadMembers();
        repairIssuedState();
    }

    public void addBook(int id, String title, String author) {
        if (findBook(id) != null) {
            System.out.println("A book with this ID already exists.");
            return;
        }

        if (title.isBlank() || author.isBlank()) {
            System.out.println("Title and author cannot be empty.");
            return;
        }

        books.add(new Book(id, title.trim(), author.trim()));
        saveData();
        System.out.println("Book added successfully.");
    }

    public void removeBook(int bookId) {
        Book book = findBook(bookId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Cannot remove an issued book.");
            return;
        }

        books.remove(book);
        saveData();
        System.out.println("Book removed successfully.");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n========== BOOK LIST ==========");
        books.stream()
                .sorted(Comparator.comparingInt(Book::getId))
                .forEach(System.out::println);
    }

    public void searchBook(String keyword) {
        if (keyword.isBlank()) {
            System.out.println("Search term cannot be empty.");
            return;
        }

        String query = keyword.toLowerCase();
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query)
                    || book.getAuthor().toLowerCase().contains(query)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    public void addMember(int id, String name) {
        if (findMember(id) != null) {
            System.out.println("A member with this ID already exists.");
            return;
        }

        if (name.isBlank()) {
            System.out.println("Member name cannot be empty.");
            return;
        }

        members.add(new Member(id, name.trim()));
        saveData();
        System.out.println("Member registered successfully.");
    }

    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        System.out.println("\n========== MEMBERS ==========");
        members.stream()
                .sorted(Comparator.comparingInt(Member::getId))
                .forEach(System.out::println);
    }

    public void issueBook(int bookId, int memberId) {
        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Book is already issued.");
            return;
        }

        if (!member.canIssueMoreBooks()) {
            System.out.println("A member can have a maximum of 3 books.");
            return;
        }

        book.issueBook();
        member.issueBook(bookId);
        saveData();

        System.out.println("Book issued successfully.");
    }

    public void returnBook(int bookId, int memberId) {
        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (!book.isIssued()) {
            System.out.println("This book is not currently issued.");
            return;
        }

        if (!member.hasBook(bookId)) {
            System.out.println("This book is not recorded under this member.");
            return;
        }

        book.returnBook();
        member.returnBook(bookId);
        saveData();

        System.out.println("Book returned successfully.");
    }

    public void saveData() {
        FileManager.saveBooks(books);
        FileManager.saveMembers(members);
    }

    private Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    private Member findMember(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    private void repairIssuedState() {
        for (Book book : books) {
            book.returnBook();
        }

        for (Member member : members) {
            for (Integer bookId : member.getIssuedBooks()) {
                Book book = findBook(bookId);
                if (book != null) {
                    book.issueBook();
                }
            }
        }
    }
}
