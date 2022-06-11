package Data;

public interface TADTaulaHash<K, T> {
    public void Crear();
    public void Inserir(K key, T data);
    T Obtenir(K key);
    int Buscar(K key);
    int Mida();
    void Esborrar(K key);


}
