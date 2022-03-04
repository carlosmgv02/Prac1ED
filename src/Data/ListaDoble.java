package Data;
import java.lang.Iterable;
import Exceptions.*;
public class ListaDoble<T> implements TADCiutada <T>{
	private Nodo inicio,fin;
	private int nElems;
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
	public void recorrer() {
		if(!empty()) {
			String datos="<=>";
			Nodo auxiliar =inicio;
			while(auxiliar !=null) {
				datos=datos+ "["+auxiliar.data+"]<=>";
				auxiliar=auxiliar.siguiente;
			}
			System.out.println(datos);
		}
	}
	public int Longitud() {
		return nElems;
	}
	public T Obtenir(int posi)throws NoSePuede {
		if(posi<nElems&&posi>0) {
			int i=0;
			Nodo aux=inicio;
			while(i<posi) {
				inicio=inicio.siguiente;
				i++;
			}
			return (T)inicio;
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
			if(aux.data==dato) {
				return n;
			}
			aux=aux.siguiente;
			n++;i++;
		}
		throw new ElementoNoEncontrado(i);
		
	}

}
