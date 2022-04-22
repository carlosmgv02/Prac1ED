package Data;
import Data.*;
import Exceptions.ElementoNoEncontrado;
public class HashElem<T extends Comparable<T>> {
	int nElems=0;
	int estado=0;	//0-Empty 1-Deleted 2-Full
	Nodo firstElem;
	Nodo lastNode;
	/**
	 * Constructor dado un dato a añadir
	 * @param data
	 */
	public HashElem(T data) {
		firstElem=new Nodo(data);
		lastNode=firstElem;
		estado=2;
		nElems=1;
	}
	/**
	 * Constructor vacío
	 */
	public HashElem() {
		firstElem=new Nodo();
		estado=0;
	}
	/**
	 * Checks status
	 * @return 0-Empty 2-Full
	 */
	public int getStatus() {
		return estado;
	}
	/**
	 * Método para añadir una colisión
	 * @param next elemento siguiente al actual
	 */
	public void append(Nodo next) {
		Nodo last=next;
		if(nElems==1) {
			firstElem.setNextCol(last);
		}
		lastNode.setNextCol(next);
		lastNode=lastNode.nextCol;
		nElems++;
	}
	/**
	 * Imprime nodo actual y posibles colisiones
	 */
	public void print() {
		Nodo aux=firstElem;
		while(aux!=null) {
			System.out.println(aux.data);
			aux=aux.getNextCol();
		}

	}
	/**
	 * Método para buscar un dato pasado como parámetro
	 * @param data dato que queremos buscar
	 * @return 1 if found
	 */
	public int lookFor(T data) {
		boolean found=false;
		
		Nodo aux=firstElem;
		int i=1;
		while(aux!=null) {
			if(aux.data instanceof Ciutada) {
				Ciutada ciu=(Ciutada)aux.data;
				if(ciu.getDni().equalsIgnoreCase((String)data)&&aux.data!=null) 
					return i;

			}
			else {
				try {
					if(aux.data.compareTo(data)==0&&aux.data!=null)
						return i;
					}catch(ClassCastException e) {

						}}
			aux=aux.nextCol;
			i++;
		}
		return -1;
	}
}
