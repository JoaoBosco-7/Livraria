package models;

import java.util.ArrayList;

public class Cliente extends Acount {

    private ArrayList<Book> bookList;

    public Cliente(String acountId, String passWord) {
        super(acountId, passWord);
        this.bookList = new ArrayList<>();
    }

    public void getNewBook(Book newBook) {
        this.bookList.add(newBook);
    }

    public void returnBook(String bookTitle) {
        for (Book book : bookList) {
            if (book.getTitle().equals(bookTitle)) {
                bookList.remove(book);
                System.out.println("Livro \"" + bookTitle + "\" devolvido com sucesso.");
                return;
            }
        }
        System.out.println("Livro \"" + bookTitle + "\" não encontrado na lista de livros.");
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    // Método para listar todos os livros na biblioteca
    public ArrayList<Book> listAllBooks(Library library) {
        return library.getAllBooks();
    }

    public Book searchBook(String title) {

        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

}
