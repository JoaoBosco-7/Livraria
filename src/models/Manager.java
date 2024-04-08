package models;

import java.util.ArrayList;

import controller.ExternalApiSimulator;
import interfaces.LisinerCallback;

public class Manager extends Acount {
    private ArrayList<LisinerCallback> callbacks;

    public Manager(String acountId, String passWord) {
        super(acountId, passWord);
        this.callbacks = new ArrayList<>();
    }

    public boolean clienteExists(String acountId, String passWord) {
        Cliente existingCliente = getCliente(acountId);
        return existingCliente != null && existingCliente.getPassWord().equals(passWord);
    }

    public void addCliente(Cliente cliente) {
        if (!clienteExists(cliente.getAcountId(), cliente.getPassWord())) {
            callbacks.forEach(callback -> callback.ClientRegisterlisinerCallback(cliente));
        } else {
            System.out.println("Cliente já existe.");
        }
    }

    public boolean removeCliente(String acountId) {
        int length = callbacks.size();
        int counter = 0;
        callbacks.forEach(callbacks -> {
            if (callbacks.ClientDeletelisinerCallback(acountId)) {
                System.out.println("Cliente encontrado e excluído em "
                        + counter + "/" + length + " listas de clientes");
            }
        });
        return false;
    }

    public void rentBook(String acountId, String title) {
        for (LisinerCallback lisinerCallback : callbacks) {
            if (lisinerCallback.rentBookLisinerCallBack(acountId, title)) {
                break;
            }
        }
    }

    public void returnBook(String acountId, String title) {
        for (LisinerCallback lisinerCallback : callbacks) {
            if (lisinerCallback.returnBookLisinerCallBack(acountId, title)) {
                break;
            }
        }
    }

    public void registerCallBack(ExternalApiSimulator r) {
    }

    public void getClientes() {
        int maxLength = 0;
        for (LisinerCallback callback : callbacks) {
            for (Cliente c : callback.getAllClientes()) {
                if (c.getAcountId().length() > maxLength) {
                    maxLength = c.getAcountId().length();
                }
            }
        }
        String format = "| %-" + maxLength + "s |\n";
        System.out.printf(format, "Clientes");
        for (LisinerCallback callback : callbacks) {
            for (Cliente c : callback.getAllClientes()) {
                System.out.printf(format, c.getAcountId());
            }
        }
    }

    public void showClientesWithBook() {
        callbacks.forEach(callbacks -> {
            ArrayList<Cliente> clientes = callbacks.getAllClientes();

            for (Cliente cliente : clientes) {
                String clienteName = cliente.getAcountId();
                System.out.println("O cliente " + clienteName + " alugou os seguintes livros:");

                ArrayList<Book> bookList = cliente.getBookList();
                if (!bookList.isEmpty()) {
                    int maxTitleLength = "Título".length(), maxAuthorLength = "Autor".length(),
                            maxGenderLength = "Gênero".length(), maxVolLength = "Volume".length();

                    for (Book book : bookList) {
                        if (book.getTitle().length() > maxTitleLength) {
                            maxTitleLength = book.getTitle().length();
                        }
                        if (book.getAuthor().length() > maxAuthorLength) {
                            maxAuthorLength = book.getAuthor().length();
                        }
                        if (book.getGender().length() > maxGenderLength) {
                            maxGenderLength = book.getGender().length();
                        }
                    }

                    String format = "| %-" + maxTitleLength + "s | %-" + maxAuthorLength + "s | %-" + maxGenderLength
                            + "s | %-" + maxVolLength + "s |\n";

                    System.out.printf(format, "Título", "Autor", "Gênero", "Volume");

                    for (Book book : bookList) {
                        System.out.printf(format, book.getTitle(), book.getAuthor(), book.getGender(), book.getVol());
                    }
                } else {
                    System.out.println("Nenhum livro alugado.");
                }

                System.out.println();
            }
        });
    }

    public Cliente getCliente(String acountId) {
        for (LisinerCallback lisinerCallback : this.callbacks) {
            Cliente cliente = lisinerCallback.getCliente(acountId);
            if (cliente != null) {
                return cliente;
            }
        }
        return null;
    }

    public void addLisinner(LisinerCallback lineListener) {
        callbacks.add(lineListener);
    }

    public void printClientTable(ArrayList<Cliente> clientes) {
        int maxLength = 0;
        for (Cliente c : clientes) {
            if (c.getAcountId().length() > maxLength) {
                maxLength = c.getAcountId().length();
            }
        }
        String format = "| %-" + maxLength + "s |\n";
        System.out.printf(format, "Clientes");
        for (Cliente c : clientes) {
            System.out.printf(format, c.getAcountId());
        }
    }

    public void printBookTable(ArrayList<Book> books) {
        int maxTitleLength = 0, maxAuthorLength = 0, maxGenderLength = 0;

        for (Book book : books) {
            if (book.getTitle().length() > maxTitleLength) {
                maxTitleLength = book.getTitle().length();
            }
            if (book.getAuthor().length() > maxAuthorLength) {
                maxAuthorLength = book.getAuthor().length();
            }
            if (book.getGender().length() > maxGenderLength) {
                maxGenderLength = book.getGender().length();
            }
        }

        int volumeLength = 6;
        String format = "| %-" + maxTitleLength + "s | %-" + maxAuthorLength + "s | %-"
                + maxGenderLength + "s | %" + volumeLength + "s |\n";

        System.out.printf(format, "Título", "Autor", "Gênero", "Volume");

        for (Book book : books) {
            System.out.printf(format, book.getTitle(), book.getAuthor(), book.getGender(), book.getVol());
        }
    }

    public void printRentedBooksTable(ArrayList<Book> rentedBooks) {
        int maxTitleLength = 0, maxAuthorLength = 0, maxGenderLength = 0;

        for (Book book : rentedBooks) {
            if (book.getTitle().length() > maxTitleLength) {
                maxTitleLength = book.getTitle().length();
            }
            if (book.getAuthor().length() > maxAuthorLength) {
                maxAuthorLength = book.getAuthor().length();
            }
            if (book.getGender().length() > maxGenderLength) {
                maxGenderLength = book.getGender().length();
            }
        }

        int volumeLength = 6;
        String format = "| %-" + maxTitleLength + "s | %-" + maxAuthorLength + "s | %-"
                + maxGenderLength + "s | %" + volumeLength + "s |\n";

        System.out.printf(format, "Título", "Autor", "Gênero", "Volume");

        for (Book book : rentedBooks) {
            System.out.printf(format, book.getTitle(), book.getAuthor(), book.getGender(), book.getVol());
        }
    }
}
