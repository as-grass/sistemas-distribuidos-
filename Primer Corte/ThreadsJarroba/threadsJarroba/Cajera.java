package threadsJarroba;
/*
Taller 3
Clase: Sistemas distribuidos
Autores: Ana Sofía Grass, Sebastián Vargas, Sergio Ortiz, Isabela Palacio
*/


public class Cajera { // Clase que modela a una cajera que procesa compras

	private String nombre; // Nombre de la cajera

	public Cajera() { // Constructor por defecto
	}

	public Cajera(String nombre) { // Constructor que recibe el nombre
		this.nombre = nombre; // Asigna el nombre al atributo
	}

	public String getNombre() { // Getter del nombre
		return nombre; // Retorna el nombre actual
	}

	public void setNombre(String nombre) { // Setter del nombre
		this.nombre = nombre; // Actualiza el atributo nombre
	}

	public void procesarCompra(Cliente cliente, long timeStamp) { // Procesa la compra de un cliente dado un tiempo inicial

		System.out.println("La cajera " + this.nombre + 
				" COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre() + 
				" EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000	+
				"seg"); // Imprime inicio del procesamiento y tiempo transcurrido (seg)

		for (int i = 0; i < cliente.getCarroCompra().length; i++) { // Recorre cada producto del carro
			this.esperarXsegundos(cliente.getCarroCompra()[i]); // Simula tiempo de procesamiento del producto i
			System.out.println("Procesado el producto " + (i + 1) + 
					" ->Tiempo: " + (System.currentTimeMillis() - timeStamp) / 1000 + 
					"seg"); // Muestra tiempo transcurrido tras procesar el producto
		}

		System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " + 
						cliente.getNombre() + " EN EL TIEMPO: " + 
						(System.currentTimeMillis() - timeStamp) / 1000 + "seg"); // Imprime tiempo total al finalizar

	}

	private void esperarXsegundos(int segundos) { // Duerme el hilo actual por 'segundos'
		try { // Puede lanzar InterruptedException
			Thread.sleep(segundos * 1000); // Convierte segundos a milisegundos y duerme
		} catch (InterruptedException ex) { // Si el hilo fue interrumpido
			Thread.currentThread().interrupt(); // Restablece el estado de interrupción
		}
	}

}
