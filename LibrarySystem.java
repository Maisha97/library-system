/**
 * This class represents a Library System that allows users to interact with multiple libraries,
 * borrow and return books, display available books, and select specific books from the libraries.
 * It utilizes Swing for the graphical user interface (GUI) and provides various options for users
 * to perform actions such as borrowing, returning, and viewing available books. The system maintains
 * information about different libraries and their collections of books, categorizing them based on
 * genres or subjects. Users can also check the status of libraries to see if they are open or closed.
 */


import javax.swing.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.*;

public class LibrarySystem {
    
    // Libraries
    private static Map<String, Library> libraries;

    public LibrarySystem() {
        // Initialize libraries
        libraries = new HashMap<>();
        libraries.put("Science Library", createScienceLibrary());
        libraries.put("Novel Library", createNovelLibrary());

        // Display initial library status
        updateLibraryStatus();

        // Prompt user for action
        String[] options = {"Borrow a Book", "Return a Book", "Display available books", "Select a book"};
        int choice = JOptionPane.showOptionDialog(null, "What would you like to do?", "Library System", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);//displays the four options the user is to select from

        // Process user choice
        switch (choice) {
            case 0:
                // Borrow a Book
                borrowBook();
                break;
            case 1:
                // Return a Book
                returnBook();
                break;
            case 2:
            // viewing list of books in the database
                displayAllBooksOption();
                break;
            case 3://incase user wants to see all books and selected from the database
                selectBookOption();
                break;    
            default:
                // Invalid choice or window closed
                JOptionPane.showMessageDialog(null, "Invalid choice or window closed. Exiting program.", "Library System", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
        }
    }
    // creates a science library object
    private Library createScienceLibrary() {
        Library scienceLibrary = new Library();
        addScienceBooks(scienceLibrary);
        return scienceLibrary;
    }
    //creates a novel library object
    private Library createNovelLibrary() {
        Library novelLibrary = new Library();
        addNovelBooks(novelLibrary);
        return novelLibrary;
    }

    private void addScienceBooks(Library library) {
        // addition of books has information of title, category and address to where it belongs

        // Add science books to the science library
        library.addBook("A Brief History of Time", "Cosmology", "Science Library Address 1");
        library.addBook("Sapiens: A Brief History of Humankind", "Anthropology", "Science Library Address 2");
        library.addBook("The Selfish Gene", "Biology/Evolutionary Biology", "Science Library Address 3");
        library.addBook("The Elegant Universe", "Physics/String Theory", "Science Library Address 4");
        library.addBook("The Structure of Scientific Revolutions", "History and Philosophy of Science", "Science Library Address 5");
        library.addBook("Guns, Germs, and Steel", "Anthropology/Geography", "Science Library Address 6");
        library.addBook("Cosmos", "Astronomy/Cosmology", "Science Library Address 7");
        library.addBook("The Origin of Species", "Biology/Evolutionary Biology", "Science Library Address 8");
        library.addBook("The Gene: An Intimate History", "Biology/Genetics", "Science Library Address 9");
        library.addBook("Silent Spring", "Environmental Science", "Science Library Address 10");
        library.addBook("The Immortal Life of Henrietta Lacks", "Biology/Medical Ethics", "Science Library Address 11");
        library.addBook("Thinking, Fast and Slow", "Psychology/Behavioral Economics", "Science Library Address 12");
        library.addBook("The Demon-Haunted World: Science as a Candle in the Dark", "Science Communication/Skepticism", "Science Library Address 13");
        library.addBook("The Blind Watchmaker", "Biology/Evolutionary Biology", "Science Library Address 14");
        library.addBook("Seven Brief Lessons on Physics", "Physics", "Science Library Address 15");
        library.addBook("The Hidden Life of Trees", "Biology/Ecology", "Science Library Address 16");
        library.addBook("The Sixth Extinction: An Unnatural History", "Environmental Science", "Science Library Address 17");
        library.addBook("Why Evolution Is True", "Biology/Evolutionary Biology", "Science Library Address 18");
        library.addBook("Longitude: The True Story of a Lone Genius Who Solved the Greatest Scientific Problem of His Time", "History of Science/Navigation", "Science Library Address 19");
        library.addBook("The Double Helix: A Personal Account of the Discovery of the Structure of DNA", "Biology/Genetics", "Science Library Address 20");
    }
    

private void addNovelBooks(Library library) {
    // Add novel books to the novel library
    // addition of books has information of title, category and address to where it belongs
    library.addBook("To Kill a Mockingbird", "Classic/American Literature", "Novel Library Address 1");
    library.addBook("1984", "Dystopian/Political Fiction", "Novel Library Address 2");
    library.addBook("Pride and Prejudice", "Romance/Classic", "Novel Library Address 3");
    library.addBook("The Great Gatsby", "Classic/American Literature", "Novel Library Address 4");
    library.addBook("Harry Potter and the Sorcerer's Stone", "Fantasy/Young Adult", "Novel Library Address 5");
    library.addBook("The Catcher in the Rye", "Coming-of-Age/Classic", "Novel Library Address 6");
    library.addBook("The Lord of the Rings", "Fantasy/High Fantasy", "Novel Library Address 7");
    library.addBook("The Handmaid's Tale", "Dystopian/Feminist Literature", "Novel Library Address 8");
    library.addBook("Jane Eyre", "Gothic Romance/Classic", "Novel Library Address 9");
    library.addBook("Brave New World", "Dystopian/Science Fiction", "Novel Library Address 10");
    library.addBook("The Picture of Dorian Gray", "Gothic/Fantasy", "Novel Library Address 11");
    library.addBook("Crime and Punishment", "Psychological Fiction/Classic", "Novel Library Address 12");
    library.addBook("Gone with the Wind", "Historical Fiction/Romance", "Novel Library Address 13");
    library.addBook("The Hobbit", "Fantasy/Adventure", "Novel Library Address 14");
    library.addBook("The Kite Runner", "Historical Fiction/Drama", "Novel Library Address 15");
    library.addBook("Moby-Dick", "Adventure/Classic", "Novel Library Address 16");
    library.addBook("The Girl with the Dragon Tattoo", "Mystery/Thriller", "Novel Library Address 17");
    library.addBook("One Hundred Years of Solitude", "Magical Realism/Latin American Literature", "Novel Library Address 18");
    library.addBook("The Alchemist", "Philosophical Fiction/Adventure", "Novel Library Address 19");
    
    }

    private void borrowBook() {
        // Display list of libraries to choose from
        String selectedLibrary = (String) JOptionPane.showInputDialog(null, "Which library would you like view:", "Library System", JOptionPane.QUESTION_MESSAGE, null, libraries.keySet().toArray(), libraries.keySet().toArray()[0]).toString();
        if (selectedLibrary != null) {
            Library library = libraries.get(selectedLibrary);

            // Check if library is open
            if (isLibraryOpen()) {
                // Library is open
                // Proceed with borrowing book
                String selectedCategory = (String) JOptionPane.showInputDialog(null, "Choose a Category:", "Library System", JOptionPane.QUESTION_MESSAGE, null, library.getCategories().toArray(), library.getCategories().toArray()[0]).toString();
                if (selectedCategory != null) {
                    List<Library.Book> books = library.getBooksByCategory(selectedCategory);
                    if (!books.isEmpty()) {
                        StringBuilder bookList = new StringBuilder();
                        for (Library.Book book : books) {
                            bookList.append(book.getTitle()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Books available in " + selectedCategory + ":\n" + bookList.toString(), "Library System", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No books available in this category.", "Library System", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                // Library is closed
                JOptionPane.showMessageDialog(null, "The library is currently closed.", "Library System", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void returnBook() {
        // Prompt user to select the library
        String selectedLibrary = (String) JOptionPane.showInputDialog(null, "Choose a Library:", "Return Book", JOptionPane.QUESTION_MESSAGE, null, libraries.keySet().toArray(), libraries.keySet().toArray()[0]).toString();
        if (selectedLibrary != null) {
            Library library = libraries.get(selectedLibrary);
    
            // Check if library is open
            if (isLibraryOpen()) {
                // Library is open
                // Proceed with returning book
                String selectedCategory = (String) JOptionPane.showInputDialog(null, "Choose a Category:", "Return Book", JOptionPane.QUESTION_MESSAGE, null, library.getCategories().toArray(), library.getCategories().toArray()[0]).toString();
                if (selectedCategory != null) {
                    // Prompt user to enter the title of the book to return
                    String bookTitle = JOptionPane.showInputDialog(null, "Enter the Title of the Book to Return:", "Return Book", JOptionPane.QUESTION_MESSAGE);
                    if (bookTitle != null && !bookTitle.isEmpty()) {
                        // Add the book back to the library
                        library.addBook(bookTitle, selectedCategory, "Returned Book Address");
                        JOptionPane.showMessageDialog(null, "Book returned successfully!", "Return Book", JOptionPane.INFORMATION_MESSAGE);
                        
                        // Display list of available books after returning
                        List<Library.Book> books = library.getBooksByCategory(selectedCategory);
                        if (!books.isEmpty()) {
                            StringBuilder bookList = new StringBuilder();
                            for (Library.Book book : books) {
                                bookList.append(book.getTitle()).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, "Available books in " + selectedCategory + ":\n" + bookList.toString(), "Library System", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No books available in this category.", "Library System", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid book title. Returning to main menu.", "Return Book", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                // Library is closed
                JOptionPane.showMessageDialog(null, "The library is currently closed. Returning to main menu.", "Return Book", JOptionPane.WARNING_MESSAGE);
            }
        }
    }    

    private void displayAllBooksOption() {
        String selectedLibrary = (String) JOptionPane.showInputDialog(null, "Choose a Library to Display All Books:", "Display All Books", JOptionPane.QUESTION_MESSAGE, null, libraries.keySet().toArray(), libraries.keySet().toArray()[0]).toString();
        if (selectedLibrary != null) {
            displayAllBooksInLibrary(selectedLibrary);
        }
    }

    private void displayAllBooksInLibrary(String selectedLibrary) {
        Library library = libraries.get(selectedLibrary);
        if (library != null) {
            List<Library.Book> allBooks = new ArrayList<>();
            for (String category : library.getCategories()) {
                allBooks.addAll(library.getBooksByCategory(category));
            }
            if (!allBooks.isEmpty()) {
                StringBuilder bookList = new StringBuilder();
                for (Library.Book book : allBooks) {
                    bookList.append(book.getTitle()).append("\n");
                }
                JOptionPane.showMessageDialog(null, "All books in " + selectedLibrary + ":\n" + bookList.toString(), "Library System", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No books available in this library.", "Library System", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid library selected.", "Library System", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selectBookOption() {
        String selectedLibrary = (String) JOptionPane.showInputDialog(null, "Choose a Library to Select a Book:", "Select a Book", JOptionPane.QUESTION_MESSAGE, null, libraries.keySet().toArray(), libraries.keySet().toArray()[0]).toString();
        if (selectedLibrary != null) {
            selectBookFromLibrary(selectedLibrary);
        }
    }
    
    private void selectBookFromLibrary(String selectedLibrary) {
        Library library = libraries.get(selectedLibrary);
        List<Library.Book> allBooks = library.getAllBooks();
        if (!allBooks.isEmpty()) {
            String[] bookTitles = allBooks.stream().map(Library.Book::getTitle).toArray(String[]::new);
            String selectedBookTitle = (String) JOptionPane.showInputDialog(null, "Choose a Book:", "Select a Book", JOptionPane.QUESTION_MESSAGE, null, bookTitles, bookTitles[0]);
            if (selectedBookTitle != null) {
                // Find the book object based on the selected title
                Library.Book selectedBook = null;
                for (Library.Book book : allBooks) {
                    if (book.getTitle().equals(selectedBookTitle)) {
                        selectedBook = book;
                        break;
                    }
                }
                if (selectedBook != null) {
                    JOptionPane.showMessageDialog(null, "You selected: " + selectedBook.getTitle() + "\nBook address: " + selectedBook.getAddress(), "Selected Book", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid book selection.", "Library System", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No books available in this library.", "Library System", JOptionPane.WARNING_MESSAGE);
        }
    }
        

    private boolean isLibraryOpen() {
        // Get current time
        LocalTime currentTime = LocalTime.now();

        // Check if current time is within library operating hours (9am to 5pm)
        return currentTime.isAfter(LocalTime.of(9, 0)) && currentTime.isBefore(LocalTime.of(17, 00));
    }

    // Method to update library status
    private void updateLibraryStatus() {
        StringBuilder statusMessage = new StringBuilder();
        statusMessage.append("Library Status:\n");

        // Loop through each library to check status
        for (Map.Entry<String, Library> entry : libraries.entrySet()) {
            String libraryName = entry.getKey();
            Library library = entry.getValue();

            if (isLibraryOpen()) {
                statusMessage.append(libraryName).append(": Open\n");
            } else {
                statusMessage.append(libraryName).append(": Closed, come back between 7am and 5pm.\n");
            }
        }

        JOptionPane.showMessageDialog(null, statusMessage.toString(), "Library System", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new LibrarySystem();

        
    }
}
