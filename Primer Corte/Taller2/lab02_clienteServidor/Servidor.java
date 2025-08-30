import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

public class Servidor {
  public static void main(String[] args) {
    try {
      try { LocateRegistry.createRegistry(1099); } catch (Exception ignored) {}
      Remote impl = new BibliotecaRemoteImpl();
      Naming.rebind("Biblioteca", impl);
      System.out.println("Servidor RMI listo en rmi://localhost/Biblioteca");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}