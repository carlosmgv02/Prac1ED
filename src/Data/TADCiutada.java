package Data;

public interface TADCiutada<T> {
	void crear();

	
	
	void Inserir(T data);
	
	
	void Inserir(int posicio,T data);
	
	T Obtenir(int posicio);
	
	
	int Longitud();
	
	
	void Esborrar(int posicio);
	
	
	int Buscar(T data);
	
}
