import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//Clase que levanta el servidor RMI de la biblioteca.
public class Servidor {
  public static void main(String[] args) {
    try {
      // Argumentos: [0]=puerto (opcional, por defecto 1099) [1]=hostname (opcional) [2]=nombre servicio (opcional)
      int port = (args.length > 0) ? Integer.parseInt(args[0]) : 1099;
      String host = (args.length > 1) ? args[1] : System.getProperty("java.rmi.server.hostname", "localhost");
      String nombre = (args.length > 2) ? args[2] : "BibliotecaService";

      // Configurar hostname para el stub si fue provisto
      if (host != null && !host.isEmpty()) {
        System.setProperty("java.rmi.server.hostname", host);
      }

      // Levanta registry si no existe
      try { LocateRegistry.createRegistry(port); } catch (Exception ignored) {}
      //Instanciar el servicio remoto
      BibliotecaRemote servicio = new BibliotecaRemoteImpl();
      //Obtener el registro RMI
      Registry registry = LocateRegistry.getRegistry(port);
      //Registrar el servicio bajo un nombre 
      registry.rebind(nombre, servicio);

      //Mensaje de confirmación
      System.out.println("Servidor RMI listo en host " + System.getProperty("java.rmi.server.hostname") +
                         ":" + port + " con nombre: " + nombre);
      System.out.println("Ctrl+C para finalizar.");
    } catch (Exception e) {
      // Manejo de cualquier error referente a conexión entre otros.
      e.printStackTrace();
    }
  }
}