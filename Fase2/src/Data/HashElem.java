package Data;
import Data.*;
public class HashElem<T extends Comparable<T>> {
	int nElems=0;
	int estado=0;	//0-Empty 1-Deleted 2-Full
	Nodo firstElem;
	Nodo lastNode;
	public HashElem(T data) {
		firstElem=new Nodo(data);
		lastNode=firstElem;
		estado=2;
		nElems=1;
	}
	public HashElem(int nElems,int estado,Nodo firstElem,Nodo lastNode) {
		this.nElems=nElems;
		this.estado=estado;
		this.firstElem=firstElem;
		this.lastNode=lastNode;
		
	}
	public void addElems() {
		
	}
	public int getStatus() {
		return estado;
	}
	public void append(Nodo next) {
		Nodo last=next;
		if(nElems==1) {
			firstElem.setNextCol(last);
		}
		lastNode.setNextCol(next);
		lastNode=lastNode.nextCol;
		nElems++;
	}
	public HashElem() {
		firstElem=new Nodo();
		estado=0;
	}
	public HashElem copia() {
		return new HashElem(nElems,estado,firstElem,lastNode);
	}
	public void print() {
		Nodo aux=firstElem;
		while(aux!=null) {
			System.out.println(aux.data);
			aux=aux.getNextCol();
		}
		
	}
	
	public int lookFor(T data) {
		boolean found=false;
		Nodo aux=firstElem;
		
		while(aux!=null) {
			if(aux.data instanceof Ciutada) {
				Ciutada ciu=(Ciutada)aux.data;
				if(ciu.getDni().equalsIgnoreCase((String)data)&&aux.data!=null) 
					return 1;
				
			}
			else
				try {
				if(aux.data.compareTo(data)==0&&aux.data!=null)
				return 1;}catch(ClassCastException e) {
					
				}
			aux=aux.nextCol;
		}
		return -1;
	}
	}
