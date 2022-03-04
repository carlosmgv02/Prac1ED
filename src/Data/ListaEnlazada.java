package Data;
public class ListaEnlazada<T> implements TADCiutada <T>{
	Nodo lista;
	
	public ListaEnlazada() {
		lista=new Nodo(null, null, null);
	}
	
	public void add1(T data) {
		Nodo first=new Nodo();
		if(lista.getData()==null) {
			lista=new Nodo(null,null,data);
			
		}
		else {
			Nodo temp=getNode(size()-1);
			temp.setSiguiente(new Nodo(null,temp,data));}
	}
	public void add(T data) {
		Nodo ultimo=getNode(size()-1);
		Nodo temp=new Nodo(null,ultimo,data);
		ultimo.setSiguiente(temp);
		 
	}
	private Nodo getNode(int index) {
		if(index>size())
			throw new IndexOutOfBoundsException();
		Nodo node =lista;
		for(int i=0;i<=index;i++) {
			node.getSiguiente();
		}
		return node;
	}

	@Override
	public void Inserir(Object data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Inserir(int posicio, Object data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T Obtenir(int posicio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Longitud() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void Esborrar(int posicio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int Buscar(Object data) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int size() {
		int n=0;
		Nodo node=lista.getSiguiente();
		while(node!=null) {
			node=node.getSiguiente();
			n++;
		}
		return n;
	}
	
	
	
}
