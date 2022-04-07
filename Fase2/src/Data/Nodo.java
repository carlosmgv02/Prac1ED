package Data;

public class Nodo <T extends Comparable<T>> {
	T data;
	Nodo nextCol;
	public Nodo(Nodo col,T data) {
		this.data=data;
		nextCol=col;
	}
	public Nodo(T data) {
		this(null,data);
	}
	public Nodo() {
		this(null,null);
	}
	public T getData() {
		return this.data;
	}
	public void setData(T data) {
	this.data=data;
	}
	public Nodo getNextCol() {
		return nextCol;
	}
	public void setNextCol(Nodo col) {
		this.nextCol=col;
	}
	public Nodo copia() {
		return new Nodo(nextCol,data);
	}
	
}
