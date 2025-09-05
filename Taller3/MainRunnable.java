/*
Taller 3
Clase: Sistemas distribuidos
Autores: Ana Sofía Grass, Sebastián Vargas, Sergio Ortiz, Isabela Palacio
*/
package Taller3;

public class MainRunnable implements Runnable{
	
	/** Cliente a procesar. */
	private Cliente cliente;
	/** Cajera que atiende al cliente. */
	private Cajera cajera;
	/** Instante inicial de referencia en milisegundos. */
	private long initialTime;
	
	/**
	 * Crea el proceso de compra uniendo cliente y cajera.
	 * @param cliente cliente a atender
	 * @param cajera cajera asignada
	 * @param initialTime instante de referencia en milisegundos
	 */
	public MainRunnable (Cliente cliente, Cajera cajera, long initialTime){
		this.cajera = cajera;
		this.cliente = cliente;
		this.initialTime = initialTime;
	}

	/**
	 * Punto de entrada del ejemplo basado en Runnable.
	 * Crea dos clientes, dos cajeras y lanza dos hilos para procesarlos.
	 * @param args argumentos de la línea de comandos
	 */
	public static void main(String[] args) {
		
		Cliente cliente1 = new Cliente("Cliente 1", new int[] { 2, 2, 1, 5, 2, 3 });
		Cliente cliente2 = new Cliente("Cliente 2", new int[] { 1, 3, 5, 1, 1 });
		
		Cajera cajera1 = new Cajera("Cajera 1");
		Cajera cajera2 = new Cajera("Cajera 2");
		
		// Tiempo inicial de referencia
		long initialTime = System.currentTimeMillis();
		
		Runnable proceso1 = new MainRunnable(cliente1, cajera1, initialTime);
		Runnable proceso2 = new MainRunnable(cliente2, cajera2, initialTime);
		
		new Thread(proceso1).start();
		new Thread(proceso2).start();

	}

	@Override
	public void run() {
		// Delegación del procesamiento a la cajera asignada
		this.cajera.procesarCompra(this.cliente, this.initialTime);
	}

}
