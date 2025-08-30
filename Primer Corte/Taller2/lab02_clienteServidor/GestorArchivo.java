import java.io.*;
import java.util.*;
 

public class GestorArchivo {

    private static final String ARCHIVO_LIBROS = "libros.txt"; // Ruta del archivo de libros

    // Lee el archivo y retorna una lista de libros (isbn,título,cantidad)
    public static List<Libro> leerLibros() {
        List<Libro> libros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_LIBROS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                    String isbn = datos[0].trim();
                    String titulo = datos[1].trim();
                    int cantidad = Integer.parseInt(datos[2].trim());
                    libros.add(new Libro(isbn, titulo, cantidad));
                } // líneas inválidas se ignoran
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }

    // Guarda los libros de vuelta en el archivo
    public static void guardarLibros(List<Libro> libros) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_LIBROS))) {
            for (Libro libro : libros) {
                bw.write(libro.getIsbn() + "," + libro.getTitulo() + "," + libro.getCantidad());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
