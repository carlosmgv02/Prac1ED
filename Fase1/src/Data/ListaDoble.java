package Data;
import java.util.*;

import Exceptions.*;
public class ListaDoble<T extends Comparable<T>> implements TADCiutada <T>,Iterable<Ciutada>{
	public Nodo inicio,fin;
	private int nElems;
	private int posicioIterator;

	public ListaDoble() {
		inicio=fin=null;
	}
	/**
	 * Creació de la llista doblement encadenada donada una mida
	 * @param nElems
	 */
	public ListaDoble(int nElems) {
		int i=0;
		//inicio=new Nodo(null);
		this.nElems++;
		
		Nodo<T>nodoAux=inicio;
		while(i<nElems-1) {
			nodoAux.siguiente=new Nodo<>(i);
			nodoAux.siguiente.anterior=nodoAux;
			nodoAux=nodoAux.siguiente;
			this.nElems++;i++;

		}
	}
	
	/**
	 * Inicialització del primer element de la llista encadenada
	 */
	public void crear() {
		inicio=new Nodo(null,null,null);
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
			fin.anterior.siguiente=fin;

		}
		else {
			inicio=fin=new Nodo(data);
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
		Nodo<T>aux=new Nodo<T>(data);
		if(inicio==null) {
			inicio=aux;
			fin=aux;
		}
		else if(posi==0) {
			aux.siguiente=inicio;
			inicio.anterior=aux;
			inicio=aux;
		}
		else if(posi==nElems) {
			aux.anterior=fin;
			fin.siguiente=aux;
			fin=aux;
		}
		else {
			Nodo<T>nodoAux=inicio;
			for(int i=1;i<posi;i++) {
				nodoAux=nodoAux.siguiente;
			}
			aux.siguiente=nodoAux.siguiente;
			nodoAux.siguiente=aux;
			aux.anterior=nodoAux;
			aux.siguiente.anterior=aux;
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
			Nodo aux=inicio;
			while(i<posi) {
				aux=aux.siguiente;
				i++;
			}
			return (T)aux.data;
		}
		else {
			throw new NoSePuede(posi);
		}

	}
	
	/**
	 * Mètode per a esborrar un node, utilitzat al mètode 'Esborrar(int posi)'
	 * @param elem: node a eliminar de la llista
	 */
	public void eliminarNodo(Nodo elem) {
		if(inicio==null||elem==null)
			return;
		if(inicio==elem)
			inicio=elem.siguiente;

		//Ant<->Actual<->Siguiente
		//Ant<->Siguiente
		if(elem.siguiente!=null)
			elem.siguiente.anterior=elem.anterior;

		if(elem.anterior!=null)
			elem.anterior.siguiente=elem.siguiente;
	}
	/**
	 * Mètode per a esborrar un element donada una posició
	 * @param posi: índex de l'element que es vol esborrar
	 */
	public void Esborrar(int posi) {
		if(posi>=0 && posi<nElems) {
			Nodo aux=inicio;
			int i;
			for(i=1;aux!=null&&i<posi;i++) {
				aux=aux.siguiente;
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
		Nodo aux=inicio;
		String temp=new String();

		while(i<nElems) {
			if(aux.data.compareTo(dato)==0)
				return n;

			aux=aux.siguiente;
			n++;i++;
		}
		throw new ElementoNoEncontrado(i);

	}
	
	/**
	 * Mètode de l'iterator
	 */
	public Iterator<Ciutada>iterator(){
		CiutadaIterator a=new CiutadaIterator(this);
		return a;
	}
	/**
	 * Mètode per a duplicar llista actual
	 * @return copia
	 */
	public ListaDoble copia() {
		return this;
	}
	/**
	 * Mètode per a imprimir la llista, utilitzem el mètode iterator()
	 */
	public void recorrer() {
		Iterator<Ciutada>i=this.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}
	/**
	 * Mètode que retorna la longitud de la llista actual
	 */
	public int Longitud() {
		return nElems;
	}


}
