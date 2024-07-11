/*
 * This class represents a library system where books are categorized and stored. Each book is represented by an inner class called Book.
 * The Library class uses a map to store books categorized by their genres or types. It provides methods to add books to the library,
 * retrieve available books by category, retrieve all available categories, and retrieve all available books irrespective of category.
 */

import java.util.*;

public class Library {
    // Inner class representing a book
    public static class Book {
        private String title;
        private String category;
        private String address;

        public Book(String title, String category, String address) {
            this.title = title;
            this.category = category;
            this.address = address;
        }

        public String getTitle() {
            return title;
        }

        public String getCategory() {
            return category;
        }

        public String getAddress() {
            return address;
        }
    }

    // Map to store books by category
    private Map<String, List<Book>> booksByCategory;

    // Library constructor initializes the map of books by category
    public Library() {
        booksByCategory = new HashMap<>();
    }

    // Method to add a book to the library
    public void addBook(String title, String category, String address) {
        Book book = new Book(title, category, address);
        if (!booksByCategory.containsKey(category)) {
            booksByCategory.put(category, new ArrayList<>());
        }
        booksByCategory.get(category).add(book);
    }

    // Method to get available books by category
    public List<Book> getBooksByCategory(String category) {
        return booksByCategory.getOrDefault(category, new ArrayList<>());
    }

    // Method to get all available categories
    public Set<String> getCategories() {
        return booksByCategory.keySet();
    }
    
    // Method to get all available books irrespective of category
    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>();
        for (List<Book> categoryBooks : booksByCategory.values()) {
            allBooks.addAll(categoryBooks);
        }
        return allBooks;
    }
}
