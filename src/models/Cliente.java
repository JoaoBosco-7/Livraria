package models;

import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.Desktop;
import java.nio.file.Path;

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

    // Função para ler um arquivo PDF
     public void readPdf(String bookUrl) {
        bookUrl = bookUrl.replace(" ", "%20");
        bookUrl = "src/books/" + bookUrl + ".pdf";

   

        try {
            // Obter o caminho absoluto para o arquivo
            Path filePath = Paths.get(bookUrl).toAbsolutePath();
            
            // Converter o caminho para o formato de URL
            String url = "file:///" + filePath.toString().replace("\\", "/");

            // Criar um objeto URI com a URL especificada
            URI uri = new URI(url);

            // Verificar se o suporte ao Desktop é suportado
            if (Desktop.isDesktopSupported()) {
                // Obter a instância do Desktop
                Desktop desktop = Desktop.getDesktop();

                // Abrir o navegador padrão com a URL especificada
                desktop.browse(uri);
            } else {
                System.out.println("Desktop não é suportado.");
                // Você pode tomar alguma ação alternativa aqui, como exibir a URL na saída padrão
                System.out.println("URL: " + url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
