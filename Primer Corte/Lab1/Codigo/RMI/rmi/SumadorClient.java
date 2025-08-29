package rmi;

public class SumadorClient {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Uso: java SumadorClient <host>");
            System.exit(1);
        }
        String host = args[0];
        String url = "rmi://" + host + "/MiSumador";
        Sumador misuma = (Sumador) java.rmi.Naming.lookup(url);
        int res = misuma.sumar(5, 2);
        System.out.println("5 + 2 = " + res);
    }
}


