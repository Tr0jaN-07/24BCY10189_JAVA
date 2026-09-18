# Library Management System

## Problem Statement

Managing books and library members manually can make it difficult to keep track of available books, issued books, registered members, and book returns. A simple system is required to organize these operations and maintain the information efficiently.

The Library Management System is a command-line Java application designed to manage books and library members. It provides operations for adding, removing, searching, issuing, and returning books while maintaining application data using file-based storage.

## Scope of the Project

The project focuses on basic library management operations through a terminal-based interface.

The system covers:

* Adding and removing books
* Searching and displaying books
* Registering and displaying library members
* Issuing books to members
* Returning issued books
* Limiting each member to a maximum of three issued books
* Validating duplicate IDs and user input
* Saving and loading application data using text files

The project is intended as a standalone command-line application and does not currently include a graphical user interface or database.

## Target Users

The primary target users of the system are:

* **Library staff:** To manage books and member records and handle book issue and return operations.
* **Students or library members:** To have their membership and issued-book information maintained by the system.

## High-Level Features

1. **Book Management**

   * Add new books
   * Remove books
   * Display all books
   * Search books by title or author

2. **Member Management**

   * Register library members
   * Display registered members
   * Maintain the books issued to each member

3. **Book Issue and Return**

   * Issue available books to registered members
   * Return issued books
   * Prevent issuing an already issued book
   * Limit members to a maximum of three issued books

4. **Validation and Error Handling**

   * Detect duplicate book and member IDs
   * Validate user input
   * Handle invalid operations

5. **Data Persistence**

   * Store book and member information in text files
   * Load stored data when the application starts
   * Save changes during application execution
