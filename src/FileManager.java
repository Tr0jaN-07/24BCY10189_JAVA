import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String DATA_DIR = "data";
    private static final String BOOK_FILE = DATA_DIR + File.separator + "books.txt";
    private static final String MEMBER_FILE = DATA_DIR + File.separator + "members.txt";

    private FileManager() {
    }

    public static void saveBooks(List<Book> books) {
        createDataDirectory();

        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOK_FILE))) {
            for (Book book : books) {
                writer.println(book.getId() + "|"
                        + sanitize(book.getTitle()) + "|"
                        + sanitize(book.getAuthor()) + "|"
                        + book.isIssued());
            }
        } catch (IOException e) {
            System.out.println("Could not save books: " + e.getMessage());
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File(BOOK_FILE);

        if (!file.exists()) {
            return books;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);

                if (parts.length != 4) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0]);
                    Book book = new Book(id, parts[1], parts[2]);

                    if (Boolean.parseBoolean(parts[3])) {
                        book.issueBook();
                    }

                    books.add(book);
                } catch (NumberFormatException ignored) {
                    // Ignore malformed records.
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load books: " + e.getMessage());
        }

        return books;
    }

    public static void saveMembers(List<Member> members) {
        createDataDirectory();

        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBER_FILE))) {
            for (Member member : members) {
                String issued = member.getIssuedBooks().stream()
                        .map(String::valueOf)
                        .reduce((a, b) -> a + "," + b)
                        .orElse("");

                writer.println(member.getId() + "|"
                        + sanitize(member.getName()) + "|"
                        + issued);
            }
        } catch (IOException e) {
            System.out.println("Could not save members: " + e.getMessage());
        }
    }

    public static List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        File file = new File(MEMBER_FILE);

        if (!file.exists()) {
            return members;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);

                if (parts.length != 3) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0]);
                    Member member = new Member(id, parts[1]);

                    if (!parts[2].isBlank()) {
                        for (String bookId : parts[2].split(",")) {
                            member.issueBook(Integer.parseInt(bookId));
                        }
                    }

                    members.add(member);
                } catch (NumberFormatException ignored) {
                    // Ignore malformed records.
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load members: " + e.getMessage());
        }

        return members;
    }

    private static String sanitize(String value) {
        return value.replace("|", "/").replace("\n", " ").replace("\r", " ");
    }

    private static void createDataDirectory() {
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
