package Data;
import Data.*;
public class HashElem<T extends Comparable<T>> {
	int nElems=0;
	int estado=0;	//0-Empty 1-Deleted 2-Full
	Nodo firstElem;
	public HashElem(T data) {
		firstElem=new Nodo(data);
		estado=2;
		nElems=1;
	}
	public void addElems() {
		
	}
	public int getStatus() {
		return estado;
	}
	public void append(Nodo next) {
		firstElem.setNextCol(next);
		nElems++;
	}
	public HashElem() {
		firstElem=new Nodo();
		estado=0;
	}
	public void print() {
		Nodo aux=firstElem;
		while(aux!=null) {
			System.out.println(aux.data);
			aux=aux.getNextCol();
		}
		
	}
	}
