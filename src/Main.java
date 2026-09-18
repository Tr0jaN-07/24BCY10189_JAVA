import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Library library = new Library();

        System.out.println("======================================");
        System.out.println("       LIBRARY MANAGEMENT SYSTEM");
        System.out.println("======================================");

        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addBook(library);
                case 2 -> removeBook(library);
                case 3 -> library.displayBooks();
                case 4 -> searchBook(library);
                case 5 -> registerMember(library);
                case 6 -> library.displayMembers();
                case 7 -> issueBook(library);
                case 8 -> returnBook(library);
                case 9 -> {
                    library.saveData();
                    System.out.println("Data saved.");
                    System.out.println("Thank you for using the Library Management System.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter 1-9.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n----------- MAIN MENU -----------");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Display Books");
        System.out.println("4. Search Book");
        System.out.println("5. Register Member");
        System.out.println("6. Display Members");
        System.out.println("7. Issue Book");
        System.out.println("8. Return Book");
        System.out.println("9. Exit");
    }

    private static void addBook(Library library) {
        int id = readInt("Enter book ID: ");
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        library.addBook(id, title, author);
    }

    private static void removeBook(Library library) {
        int id = readInt("Enter book ID to remove: ");
        library.removeBook(id);
    }

    private static void searchBook(Library library) {
        System.out.print("Enter title or author to search: ");
        String keyword = scanner.nextLine();
        library.searchBook(keyword);
    }

    private static void registerMember(Library library) {
        int id = readInt("Enter member ID: ");
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();

        library.addMember(id, name);
    }

    private static void issueBook(Library library) {
        int bookId = readInt("Enter book ID: ");
        int memberId = readInt("Enter member ID: ");

        library.issueBook(bookId, memberId);
    }

    private static void returnBook(Library library) {
        int bookId = readInt("Enter book ID: ");
        int memberId = readInt("Enter member ID: ");

        library.returnBook(bookId, memberId);
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
}
