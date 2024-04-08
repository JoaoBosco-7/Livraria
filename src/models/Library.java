package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    private HashMap<String, ArrayList<Book>> booksByGender;

    public Library() {
        this.booksByGender = new HashMap<>();
    }

    public void addBook(Book book) {
        String gender = book.getGender();
        if (!booksByGender.containsKey(gender)) {
            booksByGender.put(gender, new ArrayList<>());
        }
        booksByGender.get(gender).add(book);
    }

    public boolean removeBook(Book book) {
        String gender = book.getGender();
        if (booksByGender.containsKey(gender)) {
            return booksByGender.get(gender).remove(book);
        }
        return false;
    }

    public Book getBookByTitle(String title) {
        for (ArrayList<Book> books : booksByGender.values()) {
            for (Book book : books) {
                if (book.getTitle().equals(title)) {
                    return book;
                }
            }
        }
        return null;
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> allBooks = new ArrayList<>();
        for (ArrayList<Book> books : booksByGender.values()) {
            allBooks.addAll(books);
        }
        return allBooks;
    }

    public HashMap<String, ArrayList<Book>> getBooksByGender() {
        HashMap<String, ArrayList<Book>> booksByGender = new HashMap<>();
        for (Book book : this.getAllBooks()) {
            String gender = book.getGender();
            if (!booksByGender.containsKey(gender)) {
                booksByGender.put(gender, new ArrayList<>());
            }
            booksByGender.get(gender).add(book);
        }
        return booksByGender;
    }

}
