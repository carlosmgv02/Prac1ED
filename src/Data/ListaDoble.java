package Data;
import java.util.*;

import javax.swing.JOptionPane;

import Exceptions.*;
public class ListaDoble<T extends Comparable<T>> implements TADCiutada <T>,Iterable<Ciutada>{
	private Nodo inicio,fin;
	private int nElems;
	private int posicioIterator;
	
	public ListaDoble() {
		inicio=fin=null;
	}
	public void crear() {
		inicio=new Nodo(null,null,null);
	}

	public boolean empty() {
		
		return inicio==null;
	}

	public void Inserir(T data) {
		if(!empty()) {
			fin=new Nodo(null,fin,data);
			fin.anterior.siguiente=fin;

		}
		else {
			inicio=fin=new Nodo(data);
		}
		nElems++;
	}
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
	public void Esborrar(int posi) {
		if(posi>0 && posi<nElems) {
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
	public int Buscar(T dato)throws ElementoNoEncontrado {
		int n=1,i=0;
		Nodo aux=inicio;
		while(i<nElems) {
			
			if(aux.data.compareTo(dato)==0) {
				
				
				return n;
			}
			aux=aux.siguiente;
			n++;i++;
		}
		throw new ElementoNoEncontrado(i);
		
	}
	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public Iterator<Ciutada>iterator(){
		CiutadaIterator a=new CiutadaIterator(this);
		return a;
	}
	public ListaDoble copia() {
		return this;
	}
	public void recorrer() {
		Iterator<Ciutada>i=this.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}
	public int Longitud() {
		return nElems;
	}


}
