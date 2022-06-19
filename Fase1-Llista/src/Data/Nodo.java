package Data;

public class Nodo<T extends Comparable<T>> {
	T data;
	public Nodo<T> siguiente;
	public Nodo<T> anterior;
	
	/**
	 * Constructor
	 * @param sig
	 * @param ant
	 * @param data
	 */
	public Nodo(Nodo<T> sig,Nodo<T> ant,T data) {
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
	public Nodo<T> getSiguiente() {
		return siguiente;
	}
	/**
	 * Setter
	 * @param sig next node to override
	 */
	public void setSiguiente(Nodo<T> sig) {
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
	public void setAnterior(Nodo<T> ant) {
		anterior=ant;
	}
	/**
	 * Mètodo que retorna una copia del Nodo actual
	 * @return
	 */
	public Nodo<T> copia() {
		return new Nodo<T>(siguiente,anterior,data);
	}
	
}
