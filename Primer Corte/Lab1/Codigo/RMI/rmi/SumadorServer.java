package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SumadorServer {
    public static void main(String[] args) {
        try {
            // Iniciar rmiregistry embebido si no está corriendo
            int port = 1099;
            try {
                Registry registry = LocateRegistry.getRegistry(port);
                registry.list();
                System.out.println("rmiregistry detectado en puerto " + port);
            } catch (Exception ex) {
                System.out.println("rmiregistry no encontrado, creando en puerto " + port);
                LocateRegistry.createRegistry(port);
            }

            new SumadorImpl("MiSumador");
            System.out.println("Servidor listo.");
        } catch (Exception e) {
            System.err.println("Excepcion: " + e);
            e.printStackTrace();
        }
    }
}


