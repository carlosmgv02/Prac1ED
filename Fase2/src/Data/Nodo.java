package Data;

public class Nodo<K,T extends Comparable<T>> implements Comparable<T> {
	T data;
	K key;
	public Nodo<K,T> nextCol;
	public Nodo<K,T> prev;
	int hash=0;

	public Nodo(Nodo<K,T> col,T data) {
		this.data=data;
		nextCol=col;
		prev=null;
	}
	public Nodo(K key,T data,int hash){
		this.data=data;
		this.key=key;
		this.hash=hash;
		nextCol=null;
		prev=null;
	}

	public Nodo(Nodo<K,T> sig, Nodo<K,T> prev, T data) {
		this.data = data;
		this.nextCol = sig;
		this.prev = prev;
	}

	public Nodo(T data2) {
		this(null,data2);
	}
	public void add(K key,T data,int hash){
		Nodo<K,T> temp=this;
		if(temp.data==null){
			temp.data=data;
			temp.hash=hash;
		}
		else {
			while (temp.nextCol != null) {
				temp = temp.nextCol;
			}
			temp.setNextCol(new Nodo<>(key,data,hash));
		}
	}

	public K getKey() {
		return key;
	}

	public T getData() {
		return this.data;
	}
	public void setData(T data) {
	this.data=data;
	}

	public void setNextCol(Nodo<K,T> col) {
		this.nextCol=col;
	}

	@Override
	public String toString() {
		return "Nodo{" +
				"data=" + data +
				", nextCol=" + nextCol +
				'}';
	}
	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public int compareTo(T o) {
		if(o.hashCode()==hashCode())return 0;
		return -1;
	}
}

