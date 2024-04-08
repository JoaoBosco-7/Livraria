package interfaces;

import java.util.ArrayList;

import models.Book;
import models.Cliente;
import models.Manager;

public interface LisinerCallback {
    void ManagerRegisterlisinerCallback(Manager m);

    void ClientRegisterlisinerCallback(Cliente c);

    void BookRegisterlisinerCallback(Book b);

    void ManagerDeletelisinerCallback(String acountId);

    boolean ClientDeletelisinerCallback(String acountId);

    boolean rentBookLisinerCallBack(String acountId, String title);

    boolean returnBookLisinerCallBack(String acountId, String title);

    Cliente getCliente(String acountId);

    ArrayList<Cliente> getAllClientes();

}
