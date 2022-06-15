package Data;

import Exceptions.ElementoNoEncontrado;

public interface TADTaulaHash<K, T extends Comparable<T>> {
    public void Crear();
    public void Inserir(K key, T data) throws ElementoNoEncontrado;
    T Obtenir(K key);
    public int Buscar(K key)throws ElementoNoEncontrado;
    public int Mida();
    public void Esborrar(K key) throws ElementoNoEncontrado;
    public ListaDoble<K,T>ObtenirValors();
    public ListaDoble<K,T>ObtenirClaus();
    public float factorCarga();

}
