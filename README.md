# Library Management System

## Project Overview

The Library Management System is a command-line Java application for managing books and library members.

The application supports adding and removing books, searching for books, registering members, issuing and returning books, and storing application data in text files.

## Features

- Add new books
- Remove books
- Display all books
- Search books by title or author
- Register library members
- Display registered members
- Issue books to members
- Return books
- Maximum of 3 issued books per member
- Duplicate ID validation
- Input validation and exception handling
- File-based data persistence
- Terminal-based interface

## Technologies Used

- Java 17 or later
- Object-Oriented Programming
- Java Collections Framework
- File Handling
- Exception Handling
- Command Line Interface

## Project Structure

```text
LibraryManagementSystem/
├── src/
│   ├── Main.java
│   ├── Book.java
│   ├── Member.java
│   ├── Library.java
│   └── FileManager.java
├── data/
│   ├── books.txt
│   └── members.txt
├── README.md
└── .gitignore
```

## Requirements

Install Java Development Kit (JDK) 17 or later.

Check the installation:

```bash
java -version
javac -version
```

## Setup and Execution

### 1. Clone the repository

```bash
git clone https://github.com/YOUR-USERNAME/library-management-system.git
cd library-management-system
```

### 2. Compile the project

```bash
javac -d out src/*.java
```

### 3. Run the application

```bash
java -cp out Main
```

## Menu

```text
----------- MAIN MENU -----------
1. Add Book
2. Remove Book
3. Display Books
4. Search Book
5. Register Member
6. Display Members
7. Issue Book
8. Return Book
9. Exit
```

## Example

```text
Enter your choice: 1
Enter book ID: 101
Enter book title: Java Programming
Enter author name: James Gosling
Book added successfully.

Enter your choice: 5
Enter member ID: 501
Enter member name: Rahul
Member registered successfully.

Enter your choice: 7
Enter book ID: 101
Enter member ID: 501
Book issued successfully.
```

## Data Persistence

The application stores book and member information in the `data` directory. Data is loaded when the program starts and saved after changes and before exit.

## OOP Concepts Demonstrated

- Classes and objects
- Encapsulation
- Constructors
- Methods
- Access modifiers
- Composition
- Collections
- Exception handling
- File handling

## Future Enhancements

- Due dates and fine calculation
- Book categories
- Administrator login
- Sorting and filtering
- Database integration
- Graphical user interface

## Author

Your Name

Programming in Java Project
