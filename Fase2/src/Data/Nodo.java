package Data;

public class Nodo<T extends Comparable<T>>  {
	T data;
	public Nodo nextCol;
	public Nodo prev;
	
	public Nodo(Nodo col,T data) {
		this.data=data;
		nextCol=col;
		prev=null;
	}

	public Nodo(Nodo sig, Nodo prev, T data) {
		this.data = data;
		this.nextCol = nextCol;
		this.prev = prev;
	}

	public Nodo(T data2) {
		this(null,data2);
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

	@Override
	public String toString() {
		return "Nodo{" +
				"data=" + data +
				", nextCol=" + nextCol +
				'}';
	}
}

