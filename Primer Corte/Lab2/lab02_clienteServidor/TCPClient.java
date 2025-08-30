/*
 Universidad Pontificia Javeriana
 Autora: Sofia Grass
 Fecha: 29/08/2025
 Archivo: TCPClient.java
 Descripción: Cliente TCP interactivo que envía números al servidor y
              muestra la respuesta recibida.
*/
import java.net.*;
import java.io.*;

/**
 * Cliente TCP interactivo.
 *
 * Establece una conexión con el servidor en 127.0.0.1:8888, solicita al
 * usuario números por consola y envía cada entrada al servidor. Muestra
 * la respuesta recibida. Finaliza cuando el usuario escribe "bye".
 */
public class TCPClient {
  /**
   * Punto de entrada del cliente.
   * @param args no utilizado
   * @throws Exception si ocurre un error de E/S o de red
   */
  public static void main(String[] args) throws Exception {
  try{
    // Abre un socket TCP hacia el servidor (host: 127.0.0.1, puerto: 8888)
    Socket socket=new Socket("127.0.0.1",8888);
    // Flujos de entrada/salida para intercambiar mensajes con el servidor
    DataInputStream inStream=new DataInputStream(socket.getInputStream());
    DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
    // Lector de consola para obtener la entrada del usuario
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    String clientMessage="",serverMessage="";
    // Bucle principal: lee desde consola, envía al servidor y muestra la respuesta
    while(!clientMessage.equals("bye")){
      System.out.println("Enter number :");
      // Lee una línea desde la consola
      clientMessage=br.readLine();
      // Envía el texto al servidor y asegura el envío inmediato
      outStream.writeUTF(clientMessage);
      outStream.flush();
      // Espera la respuesta del servidor y la imprime
      serverMessage=inStream.readUTF();
      System.out.println(serverMessage);
    }
    // Cierra recursos abiertos (salida, entrada y socket)
    outStream.close();
    outStream.close();
    socket.close();
  }catch(Exception e){
    // Muestra el error en caso de excepción
    System.out.println(e);
  }
  }
}