import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BibliotecaRemote extends Remote {
    // Método para préstamo por ISBN: si es positivo, retorna fecha de devolución
    String prestarPorISBN(String isbn) throws RemoteException;
    
    // Método para préstamo por Título: si es positivo, retorna fecha de devolución
    String prestarPorTitulo(String titulo) throws RemoteException;
    
    // Método para consulta por ISBN
    String consultarPorISBN(String isbn) throws RemoteException;
    
    // Método para devolución por ISBN (mensaje de confirmación)
    String devolverPorISBN(String isbn) throws RemoteException;
}
