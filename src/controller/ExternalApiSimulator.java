package controller;

import java.util.ArrayList;

import interfaces.LisinerCallback;
import models.Book;
import models.Cliente;
import models.Library;
import models.Manager;

// Classe BibliotecaRepos

public class ExternalApiSimulator implements LisinerCallback {

    private ArrayList<Manager> managers;
    private ArrayList<Cliente> clientes;
    private ArrayList<Book> books;
    private ArrayList<Library> libraries;

    public ExternalApiSimulator() {
        this.managers = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.books = new ArrayList<>();
        this.libraries = new ArrayList<>();

        this.loadDefoultLibrary();
        this.loadDefoultManager();
        this.loadDefoultClient();

    }

    public Cliente authenticateCliente(String acountId, String passWord) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getAcountId().equals(acountId) && cliente.getPassWord().equals(passWord)) {
                return cliente;
            }
        }
        return null;
    }

    public Manager authenticateManager(String acountId, String passWord) {
        for (Manager manager : this.managers) {
            if (manager.getAcountId().equals(acountId) && manager.getPassWord().equals(passWord)) {
                return manager;
            }
        }
        return null;
    }

    public ArrayList<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(ArrayList<Library> libraries) {
        this.libraries = libraries;
    }

    public ArrayList<Manager> getManagers() {
        return this.managers;
    }

    public void setManagers(ArrayList<Manager> managers) {
        this.managers = managers;
    }

    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    private void loadDefoultLibrary() {
        Library library = new Library();

        // Criar alguns novos livros e Adiciona os livros à biblioteca
        library.addBook(new Book("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", "1"));
        library.addBook(new Book("Harry Potter e a Pedra Filosofal", "J.K. Rowling", "Fantasia", "1"));
        library.addBook(new Book("Moby Dick", "Herman Melville", "Ficção", "1"));
        library.addBook(new Book("Orgulho e Preconceito", "Jane Austen", "Romance", "1"));
        library.addBook(new Book("1984", "George Orwell", "Ficção Científica", "1"));
        library.addBook(new Book("O Sol é para todos", "Harper Lee", "Ficção", "1"));
        library.addBook(new Book("O Grande Gatsby", "F. Scott Fitzgerald", "Ficção", "1"));
        library.addBook(new Book("Dom Quixote", "Miguel de Cervantes", "Aventura", "1"));
        library.addBook(new Book("Guerra e Paz", "Leo Tolstoy", "Histórico", "1"));
        library.addBook(new Book("Ulisses", "James Joyce", "Modernista", "1"));
        library.addBook(new Book("Em busca do tempo perdido", "Marcel Proust", "Modernista", "1"));
        library.addBook(new Book("Cem Anos de Solidão", "Gabriel García Márquez", "Realismo Mágico", "1"));
        library.addBook(new Book("A Divina Comédia", "Dante Alighieri", "Poesia", "1"));
        library.addBook(new Book("Crime e Castigo", "Fyodor Dostoevsky", "Ficção", "1"));
        library.addBook(new Book("Anna Karenina", "Leo Tolstoy", "Romance", "1"));
        library.addBook(new Book("Odisseia", "Homero", "Épico", "1"));
        library.addBook(new Book("Ilíada", "Homero", "Épico", "1"));

        // Gênero: Poesia
        library.addBook(new Book("A Rosa do Povo", "Carlos Drummond de Andrade", "Poesia", "1"));
        library.addBook(new Book("Sonetos", "William Shakespeare", "Poesia", "1"));
        library.addBook(new Book("O Guardador de Águas", "Manoel de Barros", "Poesia", "1"));
        library.addBook(new Book("Livro de Sóror Saudade", "Hilda Hilst", "Poesia", "1"));
        library.addBook(new Book("Antologia Poética", "Vinicius de Moraes", "Poesia", "1"));

        // Gênero: Suspense
        library.addBook(new Book("O Código Da Vinci", "Dan Brown", "Suspense", "1"));
        library.addBook(new Book("A Garota no Trem", "Paula Hawkins", "Suspense", "1"));
        library.addBook(new Book("O Iluminado", "Stephen King", "Suspense", "1"));
        library.addBook(new Book("Garota Exemplar", "Gillian Flynn", "Suspense", "1"));
        library.addBook(new Book("A Mulher na Janela", "A.J. Finn", "Suspense", "1"));

        // Gênero: Biografia
        library.addBook(new Book("Steve Jobs", "Walter Isaacson", "Biografia", "1"));
        library.addBook(new Book("Malcolm X: Uma Vida de Reinvenções", "Manning Marable", "Biografia", "1"));
        library.addBook(new Book("Cem Dias Entre Céu e Mar", "Amyr Klink", "Biografia", "1"));
        library.addBook(new Book("Diários de Che Guevara", "Che Guevara", "Biografia", "1"));
        library.addBook(new Book("Ayrton: O Herói Revelado", "Ernesto Rodrigues", "Biografia", "1"));

        // Gênero: Ficção Científica
        library.addBook(new Book("Fahrenheit 451", "Ray Bradbury", "Ficção Científica", "1"));
        library.addBook(new Book("Neuromancer", "William Gibson", "Ficção Científica", "1"));
        library.addBook(new Book("O Homem do Castelo Alto", "Philip K. Dick", "Ficção Científica", "1"));
        library.addBook(new Book("Duna", "Frank Herbert", "Ficção Científica", "1"));
        library.addBook(new Book("Fundação", "Isaac Asimov", "Ficção Científica", "1"));

        this.libraries.add(library);
    }

    private void loadDefoultClient() {
        this.clientes.add(new Cliente("cliente1", "senha1"));
    }

    private void loadDefoultManager() {
        this.managers.add(new Manager("gerente1", "senha1"));
    }

    @Override
    public void ManagerRegisterlisinerCallback(Manager m) {
        this.managers.add(m);
    }

    @Override
    public void ClientRegisterlisinerCallback(Cliente c) {
        this.clientes.add(c);
    }

    @Override
    public void BookRegisterlisinerCallback(Book b) {
        this.books.add(b);
        this.libraries.forEach(library -> {
            library.addBook(b);
        });
    }

    @Override
    public void ManagerDeletelisinerCallback(String acountId) {
    }

    @Override
    public boolean ClientDeletelisinerCallback(String acountId) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getAcountId().equals(acountId)) {
                return this.clientes.remove(cliente);
            }
        }
        return false;
    }

    @Override
    public boolean rentBookLisinerCallBack(String acountId, String title) {
        int actuaClienteIndex = clientes.indexOf(this.getCliente(acountId));
        Cliente actuaCliente = clientes.get(actuaClienteIndex);
        for (Library library : libraries) {

            Book book = library.getBookByTitle(title);
            if (book != null) {
                actuaCliente.getNewBook(book);
                clientes.set(actuaClienteIndex, actuaCliente);
                library.removeBook(book);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean returnBookLisinerCallBack(String acountId, String title) {
        int actuaClienteIndex = clientes.indexOf(this.getCliente(acountId));
        Cliente actuaCliente = clientes.get(actuaClienteIndex);

        for (Library library : libraries) {
            Book book = actuaCliente.searchBook(title);
            if (book != null) {
                actuaCliente.returnBook(title);
                clientes.set(actuaClienteIndex, actuaCliente);
                int index = this.libraries.indexOf(library);
                library.addBook(book);
                this.libraries.set(index, library);
                return true;
            }
        }
        return false;
    }

    @Override
    public Cliente getCliente(String acountId) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getAcountId().equals(acountId)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Cliente> getAllClientes() {
        return clientes;
    }

}
