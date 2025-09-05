/*
Taller 3
Clase: Sistemas distribuidos
Autores: Ana Sofía Grass, Sebastián Vargas, Sergio Ortiz, Isabela Palacio
*/
package Taller3;

public class Cliente {

	/** Nombre del cliente. */
	private String nombre;
	/**
	 * Tiempos de procesamiento de cada producto en el carro de compra.
	 * Cada entero representa segundos a esperar por producto.
	 */
	private int[] carroCompra;

	/**
	 * Crea un cliente sin datos.
	 */
	public Cliente() {
	}

	/**
	 * Crea un cliente con nombre y carro de compra.
	 * @param nombre nombre del cliente
	 * @param carroCompra arreglo con los tiempos por producto en segundos
	 */
	public Cliente(String nombre, int[] carroCompra) {
		this.nombre = nombre;
		this.carroCompra = carroCompra;
	}

	/**
	 * Obtiene el nombre del cliente.
	 * @return nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Actualiza el nombre del cliente.
	 * @param nombre nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el carro de compra del cliente.
	 * @return arreglo de tiempos por producto (segundos)
	 */
	public int[] getCarroCompra() {
		return carroCompra;
	}

	/**
	 * Actualiza el carro de compra del cliente.
	 * @param carroCompra arreglo con los tiempos por producto (segundos)
	 */
	public void setCarroCompra(int[] carroCompra) {
		this.carroCompra = carroCompra;
	}

}
