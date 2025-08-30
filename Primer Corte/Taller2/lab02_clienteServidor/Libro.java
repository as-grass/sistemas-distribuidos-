public class Libro {
    private String isbn;
    private String titulo;
    private int cantidad;

    public Libro() {
    }
    
    public Libro(String isbn, String titulo, int cantidad) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.cantidad = cantidad;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
}