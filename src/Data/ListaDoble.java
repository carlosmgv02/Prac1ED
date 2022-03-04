package Data;
import java.lang.Iterable;
public class ListaDoble<T> implements TADCiutada <T>,Iterable<T>,Comparable<Nodo>{
	private Nodo inicio,fin;
	private int nElems;
	public ListaDoble() {
		inicio=fin=null;
	}
	public void Crear() {
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
	public T Obtenir(int posi) {
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
			Nodo retorno=new Nodo(null,null,null);
			return (T)retorno;
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

}
