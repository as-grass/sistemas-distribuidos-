import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {
  public static void main(String[] args) throws Exception {
    String host = args.length > 0 ? args[0] : "localhost";
    BibliotecaRemote api = (BibliotecaRemote) Naming.lookup("rmi://" + host + "/Biblioteca");

    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.println("1) Prestar ISBN  2) Prestar Titulo  3) Consultar ISBN  4) Devolver ISBN  5) Salir");
      int op = Integer.parseInt(sc.nextLine().trim());
      if (op == 5) break;
      switch (op) {
        case 1: System.out.print("ISBN: "); System.out.println(api.prestarPorISBN(sc.nextLine().trim())); break;
        case 2: System.out.print("Titulo: "); System.out.println(api.prestarPorTitulo(sc.nextLine())); break;
        case 3: System.out.print("ISBN: "); System.out.println(api.consultarPorISBN(sc.nextLine().trim())); break;
        case 4: System.out.print("ISBN: "); System.out.println(api.devolverPorISBN(sc.nextLine().trim())); break;
        default: System.out.println("Opcion invalida");
      }
    }
    sc.close();
  }
}
