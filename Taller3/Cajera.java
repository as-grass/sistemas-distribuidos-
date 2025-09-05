/*
Taller 3
Clase: Sistemas distribuidos
Autores: Ana Sofía Grass, Sebastián Vargas, Sergio Ortiz, Isabela Palacio
*/
package Taller3;
public class Cajera {

	/**
	 * Nombre de la cajera.
	 */
	private String nombre;

	/**
	 * Crea una cajera sin nombre.
	 */
	public Cajera() {
	}

	/**
	 * Crea una cajera asignando su nombre.
	 * @param nombre nombre de la cajera
	 */
	public Cajera(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el nombre de la cajera.
	 * @return nombre de la cajera
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Actualiza el nombre de la cajera.
	 * @param nombre nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Procesa la compra de un cliente de forma secuencial.
	 * Recorre los productos y simula el tiempo de procesamiento por cada uno.
	 * Muestra por consola el progreso y los tiempos relativos al inicio.
	 * @param cliente cliente a atender
	 * @param timeStamp instante inicial de referencia en milisegundos
	 */
	public void procesarCompra(Cliente cliente, long timeStamp) {

		System.out.println("La cajera " + this.nombre + 
				" COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre() + 
				" EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000	+
				"seg");

		for (int i = 0; i < cliente.getCarroCompra().length; i++) {
			this.esperarXsegundos(cliente.getCarroCompra()[i]);
			System.out.println("Procesado el producto " + (i + 1) + 
					" ->Tiempo: " + (System.currentTimeMillis() - timeStamp) / 1000 + 
					"seg");
		}

		System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " + 
							cliente.getNombre() + " EN EL TIEMPO: " + 
							(System.currentTimeMillis() - timeStamp) / 1000 + "seg");

	}

	/**
	 * Pausa el hilo actual durante la cantidad de segundos indicada.
	 * @param segundos segundos a esperar
	 */
	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

}