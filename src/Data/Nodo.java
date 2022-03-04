package Data;

public class Nodo<T> {
	T data;
	public Nodo siguiente;
	public Nodo anterior;
	
	public Nodo(Nodo sig,Nodo ant,T data) {
		siguiente=sig;
		anterior=ant;
		this.data=data;
	}
	public Nodo(T data) {
		this(null,null,data);
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data=data;
	}
	public Nodo getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Nodo sig) {
		siguiente=sig;
	}
	public Nodo getAnterior() {
		return anterior;
	}
	public void setAnterior(Nodo ant) {
		anterior=ant;
	}
	public Nodo copia() {
		return new Nodo(siguiente,anterior,data);
	}
	
}
