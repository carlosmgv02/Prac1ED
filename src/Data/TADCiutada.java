package Data;
import Exceptions.*;
public interface TADCiutada<T> {
	void crear();

	
	
	void Inserir(T data);
	
	
	void Inserir(int posicio,T data)throws NoSePuede;
	
	T Obtenir(int posicio)throws NoSePuede;
	
	
	int Longitud();
	
	
	void Esborrar(int posicio);
	
	
	int Buscar(T data)throws ElementoNoEncontrado;

	int compareTo(T o);
	
}
