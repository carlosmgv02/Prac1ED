package Data;

public class Nodo<K,T extends Comparable<T>> implements Comparable<T> {
	T data;
	K key;
	public Nodo nextCol;
	public Nodo prev;
	int hash=0;
	public Nodo(){

	}
	public Nodo(Nodo col,T data) {
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

	public Nodo(Nodo sig, Nodo prev, T data) {
		this.data = data;
		this.nextCol = nextCol;
		this.prev = prev;
	}

	public Nodo(T data2) {
		this(null,data2);
	}
	public void add(K key,T data,int hash){
		Nodo temp=this;
		if(temp.data==null){
			temp.data=data;
			temp.hash=hash;
		}
		else {
			while (temp.nextCol != null) {
				temp = temp.nextCol;
			}
			temp.setNextCol(new Nodo(key,data,hash));
		}
	}
	public int lookFor(K key){
		Nodo<K,T>temp=this;
		int count=1;
		while(temp.nextCol!=null){
			if(temp.data.equals(key))return count;
			else temp=temp.nextCol;
			count++;
		}
		return count;
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

