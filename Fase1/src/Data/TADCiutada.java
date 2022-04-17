package Data;
import Exceptions.*;
public interface TADCiutada<T> {
	/**
	 * Constructor per a inicialitzar la llista
	 */
	void crear();
	
	/**
	 * Funció per tal d'inserir un element al final de la llista
	 * @param data
	 */
	void Inserir(T data);
	
	/**
	 * Funció per tal d'inserir un element a la llista en la posició indicada
	 * @param posicio
	 * @param data
	 * @throws NoSePuede
	 */
	void Inserir(int posicio,T data)throws NoSePuede;
	
	/**
	 * Funció que retorna l'element que hi ha en una posició determianda
	 * @param posicio
	 * @return
	 * @throws NoSePuede
	 */
	T Obtenir(int posicio)throws NoSePuede;
	
	/**
	 * Retorna el nombre d'elements que conté la llista en aquest  moment
	 * @return list.length()
	 */
	int Longitud();
	
	/**
	 * Funció per tal d'esborrar un element de la llista en una posició determinada
	 * @param posicio
	 */
	void Esborrar(int posicio);
	
	/**
	 * Funció que comprova si un element està a la llista
	 * @param data
	 * @return posició de l'element
	 * @throws ElementoNoEncontrado
	 */
	int Buscar(T data)throws ElementoNoEncontrado;

	
}
