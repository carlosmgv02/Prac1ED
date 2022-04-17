package Data;

public class Nodo<T extends Comparable<T>> {
	T data;
	public Nodo siguiente;
	public Nodo anterior;
	
	/**
	 * Constructor
	 * @param sig
	 * @param ant
	 * @param data
	 */
	public Nodo(Nodo sig,Nodo ant,T data) {
		siguiente=sig;
		anterior=ant;
		this.data=data;
	}
	public Nodo(T data) {
		this(null,null,data);
	}
	/**
	 * Getter
	 * @return this.data
	 */
	public T getData() {
		return data;
	}
	/**
	 * Setter
	 * @param data to override
	 */
	public void setData(T data) {
		this.data=data;
	}
	/**
	 * Getter
	 * @return next Node
	 */
	public Nodo getSiguiente() {
		return siguiente;
	}
	/**
	 * Setter
	 * @param sig next node to override
	 */
	public void setSiguiente(Nodo sig) {
		siguiente=sig;
	}
	/**
	 * Getter
	 * @return previous Node
	 */
	public Nodo getAnterior() {
		return anterior;
	}
	/**
	 * Setter
	 * @param ant Node to override
	 */
	public void setAnterior(Nodo ant) {
		anterior=ant;
	}
	/**
	 * Mètodo que retorna una copia del Nodo actual
	 * @return
	 */
	public Nodo copia() {
		return new Nodo(siguiente,anterior,data);
	}
	
}
