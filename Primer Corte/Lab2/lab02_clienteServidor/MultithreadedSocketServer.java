
/*
 Universidad Pontificia Javeriana
 Autora: Sofia Grass
 Fecha: 29/08/2025
 Archivo: MultithreadedSocketServer.java
 Descripción: Servidor de sockets multihilo que atiende clientes TCP
              creando un hilo por conexión (ServerClientThread).
*/
import java.net.*;

public class MultithreadedSocketServer {
  public static void main(String[] args) throws Exception {
    try{
      //Se crea un servidor de sockets en el puerto 8888
      ServerSocket server=new ServerSocket(8888);
      //Contador para llevar la cuenta de clientes que se conectan
      int counter=0;
      System.out.println("Server Started ....");
      //Bucle infinito para aceptar conexiones del cliente
      while(true){
        //Aumenta en uno cuando entra un cliente 
        counter++;
        //Espera a que un cliente se conecte
        Socket serverClient=server.accept();  //el servidor acepta la solicitud de conexión del cliente
        System.out.println(" >> " + "Client No:" + counter + " started!");
        //Crea un nuevo hilo para manejar la comunicación con el cliente conectado
        ServerClientThread sct = new ServerClientThread(serverClient,counter);
        //inicia el hilo para comunicarse con el cliente
        sct.start();
      }
    }catch(Exception e){
      System.out.println(e);
    }
  }
}