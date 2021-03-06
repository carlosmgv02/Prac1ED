package Data;

import Exceptions.ElementoNoEncontrado;
import Exceptions.NoSePuede;

public class ListaDoble<K,T extends Comparable<T>>{
	private Nodo<K,T> inicio,fin;
	private int nElems;
	private int posicioIterator;

	public ListaDoble() {
		inicio=fin=null;
	}

	
	/**
	 * Inicialització del primer element de la llista encadenada
	 */
	public void crear() {
		inicio=new Nodo<>(null,null,null);
	}
	/**
	 * Mètode que comprova si la llista està plena o no
	 * @return true if empty | false if not
	 */
	public boolean empty() {

		return inicio==null;
	}
	/**
	 * Mètode per a inserir un element al final de la llista
	 * @param data: dada a afegir
	 */
	public void Inserir(T data) {
		if(!empty()) {
			fin=new Nodo<>(null,fin,data);
			fin.prev.nextCol=fin;

		}
		else {
			inicio=fin=new Nodo<>(data);
		}
		nElems++;
	}
	/**
	 * Mètode per a inserir un element donada una posició
	 * @param posi: index on afegir
	 * @param data: dada a afegir
	 */
	public void Inserir(int posi,T data) throws NoSePuede {
		if(posi<0||posi>nElems) 
			throw new NoSePuede(posi);
		Nodo<K,T>aux=new Nodo<>(data);
		if(inicio==null) {
			inicio=aux;
			fin=aux;
		}
		else if(posi==0) {
			aux.nextCol=inicio;
			inicio.prev=aux;
			inicio=aux;
		}
		else if(posi==nElems) {
			aux.prev=fin;
			fin.nextCol=aux;
			fin=aux;
		}
		else {
			Nodo<K,T>nodoAux=inicio;
			for(int i=1;i<posi;i++) {
				nodoAux=nodoAux.nextCol;
			}
			aux.nextCol=nodoAux.nextCol;
			nodoAux.nextCol=aux;
			aux.prev=nodoAux;
			aux.nextCol.prev=aux;
		}
		nElems++;
	}
	/**
	 * Mètode per a retornar l'element d'una posició donada en cas de ser possible
	 * @param posi: index de la posició
	 */
	public T Obtenir(int posi)throws NoSePuede {
		if(posi<nElems&&posi>=0) {
			int i=0;
			Nodo<K,T> aux=inicio;
			while(i<posi) {
				aux=aux.nextCol;
				i++;
			}
			return aux.data;
		}
		else {
			throw new NoSePuede(posi);
		}

	}
	
	/**
	 * Mètode per a esborrar un node, utilitzat al mètode 'Esborrar(int posi)'
	 * @param elem: node a eliminar de la llista
	 */
	public void eliminarNodo(Nodo<K,T> elem) {
		if(inicio==null||elem==null)
			return;
		if(inicio==elem)
			inicio=elem.nextCol;

		//Ant<->Actual<->Siguiente
		//Ant<->Siguiente
		if(elem.nextCol!=null)
			elem.nextCol.prev=elem.prev;

		if(elem.prev!=null)
			elem.prev.nextCol=elem.nextCol;
	}
	/**
	 * Mètode per a esborrar un element donada una posició
	 * @param posi: índex de l'element que es vol esborrar
	 */
	public void Esborrar(int posi) {
		if(posi>=0 && posi<nElems) {
			Nodo<K,T> aux=inicio;
			int i;
			for(i=1;aux!=null&&i<posi;i++) {
				aux=aux.nextCol;
			}
			if(aux==null)
				return;

			eliminarNodo(aux);
			nElems--;

		}

	}
	/**
	 * Mètode per a comprovar si existeix a la llista un element passat per paràmetre
	 * @param dato: element que volem buscar a la llista
	 */
	public int Buscar(T dato)throws ElementoNoEncontrado {
		int n=1,i=0;
		Nodo<K,T> aux=inicio;
		while(i<nElems) {
				if(aux.data.compareTo(dato)==0)
					return n;
			aux=aux.nextCol;
			n++;i++;
		}
		throw new ElementoNoEncontrado(i);

	}


	public int Longitud() {
		return nElems;
	}


}
