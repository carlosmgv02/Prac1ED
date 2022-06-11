package Data;

import Exceptions.ElementoNoEncontrado;

public interface TADTaulaHash<K, T> {
    public void Crear();
    public void Inserir(K key, T data);
    T Obtenir(K key);
    int Buscar(K key)throws ElementoNoEncontrado;
    int Mida();
    void Esborrar(K key);


}
