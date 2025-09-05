/*
Taller 3
Clase: Sistemas distribuidos
Autores: Ana Sofía Grass, Sebastián Vargas, Sergio Ortiz, Isabela Palacio
*/
package Taller3;


public class CajeraThread extends Thread {

	/** Nombre de la cajera. */
	private String nombre;

	/** Cliente atendido por esta cajera. */
	private Cliente cliente;

	/** Instante inicial de referencia en milisegundos. */
	private long initialTime;


	/**
	 * Constructor por defecto.
	 */
	public CajeraThread() {
	}

	/**
	 * Crea una cajera-hilo con su nombre, cliente e instante inicial.
	 * @param nombre nombre de la cajera
	 * @param cliente cliente a atender
	 * @param initialTime instante de referencia en milisegundos
	 */
	public CajeraThread(String nombre, Cliente cliente, long initialTime) {
		this.nombre = nombre;
		this.cliente = cliente;
		this.initialTime = initialTime;
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
	 * Devuelve el instante inicial de referencia.
	 * @return tiempo inicial en milisegundos
	 */
	public long getInitialTime() {
		return initialTime;
	}

	/**
	 * Actualiza el instante inicial de referencia.
	 * @param initialTime nuevo tiempo inicial en milisegundos
	 */
	public void setInitialTime(long initialTime) {
		this.initialTime = initialTime;
	}

	/**
	 * Devuelve el cliente que se atenderá.
	 * @return cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Actualiza el cliente a atender.
	 * @param cliente nuevo cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {

		System.out.println("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " 
					+ this.cliente.getNombre() + " EN EL TIEMPO: " 
					+ (System.currentTimeMillis() - this.initialTime) / 1000 
					+ "seg");

		for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
			// Se procesa el pedido en X segundos
			this.esperarXsegundos(cliente.getCarroCompra()[i]);
			System.out.println("Procesado el producto " + (i + 1) 
						+ " del cliente " + this.cliente.getNombre() + "->Tiempo: " 
						+ (System.currentTimeMillis() - this.initialTime) / 1000 
						+ "seg");
		}

		System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " 
						+ this.cliente.getNombre() + " EN EL TIEMPO: " 
						+ (System.currentTimeMillis() - this.initialTime) / 1000 
						+ "seg");
	}

	/**
	 * Pausa el hilo actual por la cantidad de segundos indicada.
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