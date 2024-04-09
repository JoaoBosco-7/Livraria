import models.Book;
import models.Cliente;
import models.Library;
import models.Manager;
import view.CmdInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import controller.ExternalApiSimulator;

public class App {
    public static void main(String[] args) {
        // Criar uma nova biblioteca
        ExternalApiSimulator libraryRepo = new ExternalApiSimulator();

        Library library = libraryRepo.getLibraries().get(0);
        // Criar um novo gerente
        Manager manager = libraryRepo.getManagers().get(0);
        manager.addLisinner(libraryRepo);
        // Criar um novo cliente
        Cliente cliente = libraryRepo.getClientes().get(0);

        // Adicionar o cliente ao gerente
        manager.addCliente(cliente);

        // Alugar um livro
        manager.rentBook(cliente.getAcountId(), "O Senhor dos Anéis");
        manager.rentBook(cliente.getAcountId(), "Moby Dick");

        // Devolver um livro
        // manager.returnBook(cliente.getAcountId(), "O Senhor dos Anéis");
        CmdInterface cmdInterface = new CmdInterface();
        cmdInterface.hearder();
        System.out.println();
        System.out.println("         ++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("         ++++++++!!!! Bem vindo a  minha livraria  !!!!!+++++++");
        System.out.println("         ++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.flush();
            System.out.println(
                    "Digite 'cliente' para fazer login como cliente, 'gerente' para fazer login como gerente, ou 'sair' para sair:");
            String role = scanner.nextLine();

            String acountId;
            String passWord;
            boolean EntradaValida = false;

            switch (role.toLowerCase()) {
                case "sair":
                    return;

                case "cliente":
                    System.out.println("Digite seu AcountId:");
                    acountId = scanner.nextLine();
                    System.out.println("Digite sua senha:");
                    passWord = scanner.nextLine();
                    Cliente authenticatedCliente = libraryRepo.authenticateCliente(acountId, passWord);
                    EntradaValida = false;
                    if (authenticatedCliente != null) {
                        System.out.println("Você está logado como cliente.");
                        while (true) {
                            System.out.println("Digite o número correspondente à ação desejada:\n" +
                                    "'1'. Buscar um livro\n" +
                                    "'2'. Ver todos os livros disponíveis\n" +
                                    "'3'. Alugar um livro\n" +
                                    "'4'. Verificar livros alugados\n" +
                                    "'5'. Devolver um livro\n" +
                                    "'6'. Ler um livro\n" +
                                    "Digite 'sair' para sair:");
                            String action = scanner.nextLine();

                            String title;
                            switch (action.toLowerCase()) {

                                case "1":
                                    System.out.println(
                                            "Digite 'titulo' para buscar por título ou 'genero' para buscar por gênero:");
                                    String searchType = scanner.nextLine();
                                    switch (searchType.toLowerCase()) {
                                        case "titulo":
                                            System.out.println("Digite o título do livro que você deseja buscar:");
                                            String titlee = scanner.nextLine();
                                            Book book = library.getBookByTitle(titlee);
                                            if (book != null) {
                                                System.out.println("Livro encontrado: " + book.getTitle());
                                            } else {
                                                System.out.println("Livro não encontrado.");
                                            }
                                            break;

                                        case "genero":
                                            HashMap<String, ArrayList<Book>> booksByGender = library.getBooksByGender();

                                            // Verifique se há gêneros disponíveis
                                            if (booksByGender.isEmpty()) {
                                                System.out.println("Nenhum livro encontrado.");
                                            } else {
                                                // Exiba os gêneros disponíveis em formato de tabela com numeração
                                                System.out.println("Gêneros disponíveis:");
                                                int index = 1;
                                                System.out.println("+----+------------------+");
                                                System.out.println("| #  |     Gênero       |");
                                                System.out.println("+----+------------------+");
                                                for (String gender : booksByGender.keySet()) {
                                                    System.out.printf("| %-2d | %-16s |\n", index, gender);
                                                    index++;
                                                }
                                                System.out.println("+----+------------------+");

                                                boolean validChoice = false;
                                                int chosenIndex = -1;

                                                // Solicite ao usuário que escolha um gênero até que uma entrada válida
                                                // seja fornecida
                                                while (!validChoice) {
                                                    System.out.println(
                                                            "Digite o número do gênero que você deseja buscar:");
                                                    String userInput = scanner.nextLine();

                                                    // Verifique se a entrada do usuário é um número válido
                                                    try {
                                                        chosenIndex = Integer.parseInt(userInput);
                                                        if (chosenIndex >= 1 && chosenIndex <= booksByGender.size()) {
                                                            validChoice = true;
                                                        } else {
                                                            System.out.println(
                                                                    "Opção inválida. Por favor, escolha um número de gênero válido.");
                                                        }
                                                    } catch (NumberFormatException e) {
                                                        System.out.println(
                                                                "Entrada inválida. Por favor, insira um número de gênero válido.");
                                                    }
                                                }

                                                // Recupere o gênero escolhido pelo usuário
                                                String[] genres = booksByGender.keySet().toArray(new String[0]);
                                                String chosenGender = genres[chosenIndex - 1];

                                                // Recupere os livros no gênero escolhido pelo usuário
                                                ArrayList<Book> booksInGender = booksByGender.get(chosenGender);
                                                if (booksInGender != null && !booksInGender.isEmpty()) {
                                                    System.out.println(
                                                            "Livros encontrados no gênero " + chosenGender + ":");
                                                    for (Book bookInGender : booksInGender) {
                                                        System.out.println("- " + bookInGender.getTitle());
                                                    }
                                                } else {
                                                    System.out.println(
                                                            "Nenhum livro encontrado no gênero " + chosenGender + ".");
                                                }
                                            }
                                            break;

                                        default:
                                            System.out.println("Opção inválida. Tente novamente.");
                                            break;
                                    }
                                    break;

                                case "2":
                                    System.out.println("Lista de livros disponíveis:");
                                    ArrayList<Book> allBooks = library.getAllBooks();
                                    Collections.sort(allBooks, Comparator.comparing(Book::getTitle));
                                    manager.printBookTable(allBooks);
                                    break;

                                case "3":
                                    System.out.println("Digite o título do livro que você deseja alugar:");
                                    title = scanner.nextLine();
                                    manager.rentBook(authenticatedCliente.getAcountId(), title);
                                    System.out.println("Livro '" + title + "' alugado com sucesso.");
                                    break;

                                case "4":
                                    ArrayList<Book> rentedBooks = manager.getCliente(authenticatedCliente.getAcountId())
                                            .getBookList();
                                    if (rentedBooks.isEmpty()) {
                                        System.out.println("Você não alugou nenhum livro.");
                                    } else {
                                        System.out.println("Você alugou os seguintes livros:");
                                        int MaxTitleLength = 0, MaxAuthorLength = 0, MaxGenderLength = 0;
                                        String VolumeHeader = "Volume";
                                        int MaxVolLength = VolumeHeader.length();
                                        for (Book rentedBook : rentedBooks) {
                                            if (rentedBook.getTitle().length() > MaxTitleLength) {
                                                MaxTitleLength = rentedBook.getTitle().length();
                                            }
                                            if (rentedBook.getAuthor().length() > MaxAuthorLength) {
                                                MaxAuthorLength = rentedBook.getAuthor().length();
                                            }
                                            if (rentedBook.getGender().length() > MaxGenderLength) {
                                                MaxGenderLength = rentedBook.getGender().length();
                                            }
                                        }
                                        String Format = "| %-" + MaxTitleLength + "s | %-" + MaxAuthorLength + "s | %-"
                                                + MaxGenderLength + "s | %-" + MaxVolLength + "s |\n";
                                        System.out.printf(Format, "Título", "Autor", "Gênero", VolumeHeader);
                                        for (Book rentedBook : rentedBooks) {
                                            System.out.printf(Format, rentedBook.getTitle(), rentedBook.getAuthor(),
                                                    rentedBook.getGender(), rentedBook.getVol());
                                        }
                                    }
                                    break;

                                case "5":
                                    ArrayList<Book> rentedBookss = authenticatedCliente.getBookList();

                                    if (rentedBookss.isEmpty()) {
                                        System.out.println("Você não alugou nenhum livro ainda.");
                                    } else {
                                        System.out.println("Livros alugados:");
                                        for (int i = 0; i < rentedBookss.size(); i++) {
                                            System.out.println((i + 1) + ". " + rentedBookss.get(i).getTitle());
                                        }

                                        // Solicitar ao usuário que escolha o livro a ser devolvido
                                        System.out.println("Digite o número do livro que você deseja devolver:");
                                        int choice = scanner.nextInt();
                                        scanner.nextLine(); // Limpar o buffer do scanner

                                        // Verificar se a escolha é válida
                                        if (choice >= 1 && choice <= rentedBookss.size()) {
                                            String titlee = rentedBookss.get(choice - 1).getTitle();
                                            manager.returnBook(authenticatedCliente.getAcountId(), titlee);
                                        } else {
                                            System.out.println(
                                                    "Escolha inválida. Por favor, escolha um número de livro válido.");
                                        }
                                    }
                                    break;
                                
                                case "6":
                                    ArrayList<Book> rentedBooksS = authenticatedCliente.getBookList();
                                    if (rentedBooksS.isEmpty()) {
                                        System.out.println("Você não alugou nenhum livro ainda.");
                                    } else {
                                        System.out.println("Livros alugados:");
                                        for (int i = 0; i < rentedBooksS.size(); i++) {
                                            System.out.println((i + 1) + ". " + rentedBooksS.get(i).getTitle());
                                        }
                                
                                        // Solicitar ao usuário que escolha o livro a ser lido
                                        System.out.println("Digite o número do livro que você deseja ler:");
                                        int choice = scanner.nextInt();
                                        scanner.nextLine(); // Limpar o buffer do scanner
                                
                                        // Verificar se a escolha é válida
                                        if (choice >= 1 && choice <= rentedBooksS.size()) {
                                            String bookTitle = rentedBooksS.get(choice - 1).getTitle();
                                            authenticatedCliente.readPdf(bookTitle);
                                        } else {
                                            System.out.println("Escolha inválida. Por favor, escolha um número de livro válido.");
                                        }
                                    }
                                    break;    

                                case "sair":
                                    System.out.println("Saindo...");
                                    EntradaValida = true;
                                    break;

                                default:
                                    System.out.println("Entrada inválida. Por favor, tente novamente.");
                                    break;
                            }
                            if (EntradaValida)
                                break;
                        }
                        break;

                    } else {
                        System.out.println("AcountId ou senha incorretos. Por favor, tente novamente.");
                    }

                case "gerente":
                    System.out.println("Digite seu AcountId:");
                    acountId = scanner.nextLine();
                    System.out.println("Digite sua senha:");
                    passWord = scanner.nextLine();
                    Manager authenticatedManager = libraryRepo.authenticateManager(acountId, passWord);
                    EntradaValida = false;
                    if (authenticatedManager != null) {
                        System.out.println("Você está logado como gerente.");
                        while (true) {
                            System.out.println(
                                    "Digite '1' para cadastrar um novo cliente,\nDigite '2' para buscar um cliente cadastrado,\nDigite '3' para remover um cliente,\nDigite '4' para ver a lista de clientes,\nDigite '5' para adicionar um novo livro,\nDigite '6' para remover um livro,\nDigite '7' para ver a lista de livros,\nDigite '8' para ver os livros alugados,\nDigite 'sair' para sair:");
                            String action = scanner.nextLine();

                            switch (action) {
                                case "1":
                                    System.out.println("Digite o AcountId do novo cliente:");
                                    String newAcountId = scanner.nextLine();
                                    System.out.println("Digite a senha do novo cliente:");
                                    String newPassWord = scanner.nextLine();

                                    // Verifica se o cliente já existe
                                    Cliente existingCliente = manager.getCliente(newAcountId);
                                    if (existingCliente != null) {
                                        System.out.println("Cliente já existe.");
                                    } else {
                                        // Cria um novo cliente e adiciona ao gerente
                                        Cliente newCliente = new Cliente(newAcountId, newPassWord);
                                        manager.addCliente(newCliente);
                                        System.out.println("Novo cliente cadastrado com sucesso.");
                                    }
                                    break;

                                case "2":
                                    System.out.println("Digite o AcountId do cliente que você deseja buscar:");
                                    acountId = scanner.nextLine();
                                    Cliente clientee = manager.getCliente(acountId);
                                    if (clientee != null) {
                                        System.out.println("Cliente encontrado: " + clientee.getAcountId());
                                    } else {
                                        System.out.println("Cliente não encontrado.");
                                    }
                                    break;

                                case "3":
                                    System.out.println("Digite o AcountId do cliente que você deseja remover:");
                                    acountId = scanner.nextLine();
                                    Cliente clienteToRemove = manager.getCliente(acountId);
                                    if (clienteToRemove != null) {
                                        ArrayList<Book> rentedBooks = clienteToRemove.getBookList();
                                        if (rentedBooks.isEmpty()) {
                                            boolean removed = manager.removeCliente(acountId);
                                            if (removed) {
                                                System.out.println("Cliente removido com sucesso.");
                                            } else {
                                                System.out.println("Não foi possível remover o cliente.");
                                            }
                                        } else {
                                            System.out.println(
                                                    "O cliente ainda possui livros alugados. Devolva os livros antes de remover o cliente.");
                                        }
                                    } else {
                                        System.out.println("Cliente não encontrado.");
                                    }
                                    break;

                                case "4":
                                    System.out.println("Lista de clientes: ");
                                    manager.getClientes();
                                    break;

                                case "5":
                                    // Solicitar os detalhes do novo livro (título, autor, gênero, etc.)
                                    System.out.println("Digite o título do novo livro:");
                                    String newTitle = scanner.nextLine();
                                    System.out.println("Digite o autor do novo livro:");
                                    String newAuthor = scanner.nextLine();
                                    System.out.println("Digite o gênero do novo livro:");
                                    String newGender = scanner.nextLine();
                                    System.out.println("Digite o volume do novo livro (se aplicável):");
                                    String newVol = scanner.nextLine();

                                    // Criar um novo objeto Book com os dados fornecidos
                                    Book newBook = new Book(newTitle, newAuthor, newGender, newVol);

                                    // Adicionar o livro à biblioteca chamando o método addBook() da biblioteca
                                    library.addBook(newBook);

                                    System.out.println("Novo livro adicionado com sucesso.");
                                    break;

                                case "6":
                                    System.out.println("Digite o título do livro que você deseja remover:");
                                    String titleToRemove = scanner.nextLine();
                                    Book bookToRemove = library.getBookByTitle(titleToRemove);
                                    if (bookToRemove != null) {
                                        boolean remove = library.removeBook(bookToRemove);
                                        if (remove) {
                                            System.out.println("Livro removido com sucesso.");
                                        } else {
                                            System.out.println("Não foi possível remover o livro.");
                                        }
                                    } else {
                                        System.out.println("Livro não encontrado.");
                                    }
                                    break;

                                case "7":
                                    System.out.println("Lista de livros disponíveis:");
                                    ArrayList<Book> allBooks = library.getAllBooks();
                                    Collections.sort(allBooks, Comparator.comparing(Book::getTitle)); // Ordena os
                                                                                                      // livros pelo
                                                                                                      // título
                                    manager.printBookTable(allBooks);
                                    break;

                                case "8":
                                    System.out.println("Livros alugados:");
                                    manager.showClientesWithBook();
                                    break;

                                case "sair":
                                    System.out.println("Saindo...");
                                    EntradaValida = true;
                                    break;

                                default:
                                    System.out.println("Entrada inválida. Por favor, tente novamente.");
                                    break;
                            }
                            if (EntradaValida)
                                break;
                        }
                        break;

                    } else {
                        System.out.println("AcountId ou senha incorretos. Por favor, tente novamente.");
                    }
                    scanner.close();
            }
        }
    }
}
